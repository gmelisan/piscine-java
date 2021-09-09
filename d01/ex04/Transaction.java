package ex04;

import java.util.UUID;

class IllegalTransactionException extends RuntimeException {}

public class Transaction {

	public enum Category {
		DEBIT,
		CREDIT
	}
	
	private UUID id;
	private User sender;
	private User recipient;
	private Category category;
	private Integer amount = 0;

	public Transaction(User sender,
					   User recipient,
					   Category category,
					   Integer amount,
					   UUID id) {
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.category = category;
		setAmount(amount);
		if (!sender.setBalance(sender.getBalance() + amount))
			throw new IllegalTransactionException();
	}

	public UUID getId() {
		return id;
	}

	private void setAmount(Integer amount) {
		if ((category == Category.CREDIT && amount < 0)
				|| (category == Category.DEBIT && amount > 0)) {
			this.amount = amount;
			return ;
		}
		throw new IllegalTransactionException();
	}

	@Override
	public String toString() {
		return sender + " -> " +
				recipient +
				", " + (amount >= 0 ? "+" : "") + amount +
				" " + category + ", id " + id;
	}
}
