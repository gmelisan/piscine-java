package ex00;

import java.util.UUID;

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
					   Integer amount) {
		id = UUID.randomUUID();
		this.sender = sender;
		this.recipient = recipient;
		this.category = category;
		setAmount(amount);
		if (category == Category.CREDIT) {
			if (sender.setBalance(sender.getBalance() + amount))
				recipient.setBalance(recipient.getBalance() - amount);
		} else {
			if (recipient.setBalance(recipient.getBalance() - amount))
				sender.setBalance(sender.getBalance() + amount);
		}
	}

	private void setAmount(Integer amount) {
		if ((category == Category.CREDIT && amount < 0)
				|| (category == Category.DEBIT && amount > 0)) {
			this.amount = amount;
		}
	}

	@Override
	public String toString() {
		return sender + " -> " +
				recipient +
				", " + (amount >= 0 ? "+" : "") + amount +
				" " + category + ", id " + id;
	}
}
