package ex00;

import java.util.UUID;

public class User {

	private UUID id;
	private String name;
	private Integer balance = 0;

	public User() {
		id = UUID.randomUUID();
	}

	public User(String name, Integer balance) {
		id = UUID.randomUUID();
		setName(name);
		setBalance(balance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public boolean setBalance(Integer balance) {
		if (balance >= 0) {
			this.balance = balance;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}
}
