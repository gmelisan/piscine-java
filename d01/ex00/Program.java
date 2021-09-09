package ex00;

public class Program {

	public static void main(String[] args) {
		User userJohn = new User("John", 1000);
		User userMike = new User("Mike", 500);

		System.out.println(userJohn + " balance: " + userJohn.getBalance());
		System.out.println(userMike + " balance: " + userMike.getBalance());
		Transaction t1 = new Transaction(
				userJohn, userMike,
				Transaction.Category.CREDIT,
				-200
				);
		System.out.println("1: " + t1);
		System.out.println(userJohn + " balance: " + userJohn.getBalance());
		System.out.println(userMike + " balance: " + userMike.getBalance());

		Transaction t2 = new Transaction(
				userJohn, userMike,
				Transaction.Category.CREDIT,
				-100
		);
		System.out.println("2: " + t2);
		System.out.println(userJohn + " balance: " + userJohn.getBalance());
		System.out.println(userMike + " balance: " + userMike.getBalance());

		Transaction t3 = new Transaction(
				userJohn, userMike,
				Transaction.Category.DEBIT,
				+700
		);
		System.out.println("3: " + t3);
		System.out.println(userJohn + " balance: " + userJohn.getBalance());
		System.out.println(userMike + " balance: " + userMike.getBalance());

		Transaction t4 = new Transaction(
				userMike, userJohn,
				Transaction.Category.CREDIT,
				-200
		);
		System.out.println("4: " + t4);
		System.out.println(userJohn + " balance: " + userJohn.getBalance());
		System.out.println(userMike + " balance: " + userMike.getBalance());

		Transaction t5 = new Transaction(
				userMike, userJohn,
				Transaction.Category.CREDIT,
				-100
		);
		System.out.println("5: " + t5);
		System.out.println(userJohn + " balance: " + userJohn.getBalance());
		System.out.println(userMike + " balance: " + userMike.getBalance());

		Transaction t6 = new Transaction(
				userMike, userJohn,
				Transaction.Category.DEBIT,
				+4000
		);
		System.out.println("6: " + t6);
		System.out.println(userJohn + " balance: " + userJohn.getBalance());
		System.out.println(userMike + " balance: " + userMike.getBalance());

		Transaction t7 = new Transaction(
				userMike, userJohn,
				Transaction.Category.DEBIT,
				+1500
		);
		System.out.println("7: " + t7);
		System.out.println(userJohn + " balance: " + userJohn.getBalance());
		System.out.println(userMike + " balance: " + userMike.getBalance());
	}
}
