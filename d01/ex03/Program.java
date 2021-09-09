package ex03;

import java.util.UUID;

public class Program {

	public static void main(String[] args) {
		User u1 = new User("John", 5000);
		User u2 = new User("Mike", 5000);
		u1.transactions.add(new Transaction(
				u1, u2, Transaction.Category.CREDIT, -200));
		u1.transactions.add(new Transaction(
				u1, u2, Transaction.Category.DEBIT, +300));
		u1.transactions.add(new Transaction(
				u1, u2, Transaction.Category.DEBIT, +2000));
		u1.transactions.add(new Transaction(
				u1, u2, Transaction.Category.CREDIT, -1000));
		System.out.println("Transactions of " + u1 + ": " + u1.transactions);
		System.out.println("Transform to array:");
		Transaction[] array = u1.transactions.toArray();
		for (int i = 0; i < array.length; ++i) {
			System.out.println(i + ": " + array[i]);
		}
		System.out.println("Remove transaction [1] (" + array[1].getId() + ")");
		u1.transactions.remove(array[1].getId());
		System.out.println("Transactions of " + u1 + ": " + u1.transactions);
		System.out.println("Try remove not existent transaction:");
		try {
			u1.transactions.remove(UUID.randomUUID());
		} catch (Exception e) {
			System.out.println("caught " + e);
		}


	}
}
