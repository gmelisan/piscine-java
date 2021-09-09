package ex02;

public class Program {

	public static void main(String[] args) {
		String[] names = {
				"John", "Mike", "Stephan",
				"Jordan", "Peter", "Jonatan",
				"Bruce", "Kate", "Violet",
				"Rose", "Margo", "Richard",
				"Robert", "Leonardo", "Daniel",
				"Dan", "Nicole", "Martin"
		};
		UsersList list = new UsersArrayList();
		for (String name : names) {
			list.add(new User(name));
		}
		System.out.println("Users:");
		for (int i = 0; i < list.size(); ++i) {
			User user = list.getByIndex(i);
			System.out.print(user + "[" + user.getId() + "] ");
		}
		System.out.println();
		System.out.println("List size: " + list.size());
		System.out.println("getById(5): " + list.getById(5));
		System.out.println("getByIndex(10): " + list.getByIndex(10));
		try {
			System.out.print("getById(20): ");
			System.out.println(list.getById(20));
		} catch (UserNotFoundException e) {
			System.out.println("caught " + e);
		}
	}
}
