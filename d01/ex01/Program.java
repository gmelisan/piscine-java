package ex01;

public class Program {

	public static void main(String[] args) {
		String[] names = {
				"John", "Mike", "Stephan",
				"Jordan", "Peter", "Jonatan",
				"Bruce", "Kate", "Violet", "Rose"};
		for (int i = 0; i < 10; ++i) {
			User user = new User(names[i]);
			System.out.println("Created " + user + ", id " + user.getId());
		}
	}
}
