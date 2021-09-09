package ex04;

import java.util.UUID;

public class Program {

	public static void printTransactions(Transaction[] transactions) {
		System.out.println("{");
		for (Transaction t : transactions) {
			System.out.println("    " + t);
		}
		System.out.println("}");
	}

	public static void main(String[] args) {
		TransactionsService service = new TransactionsService();
		User u1 = new User("John", 5000);
		User u2 = new User("Mike", 10000);
		service.addUser(u1);
		service.addUser(u2);
		System.out.println("Balance: " + u1 + " " + service.getUserBalance(u1.getId()) +
				", " + u2 + " " + service.getUserBalance(u2.getId()));
		service.performTransaction(u1.getId(), u2.getId(), 1000);
		printTransactions(service.getTransactions(u1.getId()));
		printTransactions(service.getTransactions(u2.getId()));
		System.out.println("Balance: " + u1 + " " + service.getUserBalance(u1.getId()) +
				", " + u2 + " " + service.getUserBalance(u2.getId()));

		service.performTransaction(u1.getId(), u2.getId(), -3000);
		printTransactions(service.getTransactions(u1.getId()));
		printTransactions(service.getTransactions(u2.getId()));
		System.out.println("Balance: " + u1 + " " + service.getUserBalance(u1.getId()) +
				", " + u2 + " " + service.getUserBalance(u2.getId()));

		System.out.println(u1 + " tries to give " + u2 + " 5000");
		try {
			service.performTransaction(u1.getId(), u2.getId(), -5000);
		} catch (IllegalTransactionException e) {
			System.out.println("caught " + e);
		}
		printTransactions(service.getTransactions(u1.getId()));
		printTransactions(service.getTransactions(u2.getId()));
		System.out.println("Balance: " + u1 + " " + service.getUserBalance(u1.getId()) +
				", " + u2 + " " + service.getUserBalance(u2.getId()));

		System.out.println("Check for unpaired");
		Transaction[] unpaired = service.check();
		System.out.println("length = " + unpaired.length);

		System.out.println("Remove 1 transaction for test");
		service.testRemoveTransaction();
		printTransactions(service.getTransactions(u1.getId()));
		printTransactions(service.getTransactions(u2.getId()));
		unpaired = service.check();
		System.out.println("length = " + unpaired.length);
		System.out.println("unpaired[0] = " + unpaired[0]);
	}
}
