package ex04;

public class User {

	private Integer id;
	private String name;
	private Integer balance = 0;
	public TransactionsList transactions;

	public User(String name) {
		this.id = UserIdsGenerator.getInstance().generateId();
		setName(name);
		transactions = new TransactionsLinkedList();
	}

	public User(String name, Integer balance) {
		this.id = UserIdsGenerator.getInstance().generateId();
		setName(name);
		setBalance(balance);
		transactions = new TransactionsLinkedList();
	}

	public Integer getId() { return id;	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBalance() {
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
