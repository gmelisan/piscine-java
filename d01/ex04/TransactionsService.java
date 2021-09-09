package ex04;

import java.util.UUID;

public class TransactionsService {

    private UsersList usersList;

    public TransactionsService() {
        usersList = new UsersArrayList();
    }
    public void addUser(User user) {
        usersList.add(user);
    }

    public Integer getUserBalance(Integer userId) {
        return usersList.getById(userId).getBalance();
    }

    public void performTransaction(Integer senderId, Integer recipientId, Integer amount) {
        User sender = usersList.getById(senderId);
        User recipient = usersList.getById(recipientId);

        UUID id = UUID.randomUUID();
        Transaction t1, t2;
        if (amount <= 0) {
            t1 = new Transaction(
                    sender, recipient, Transaction.Category.CREDIT, amount, id);
            t2 = new Transaction(
                    recipient, sender, Transaction.Category.DEBIT, -amount, id);
            sender.transactions.add(t1);
            recipient.transactions.add(t2);
        } else {
            t1 = new Transaction(
                    recipient, sender, Transaction.Category.CREDIT, -amount, id);
            t2 = new Transaction(
                    sender, recipient, Transaction.Category.DEBIT, amount, id);
            recipient.transactions.add(t1);
            sender.transactions.add(t2);
        }
    }

    public Transaction[] getTransactions(Integer userId) {
        return usersList.getById(userId).transactions.toArray();
    }

    public Transaction[] check() {
        TransactionsList transactionsList = new TransactionsLinkedList();
        for (int i = 0; i < usersList.size(); ++i) {
            Transaction[] transactions = usersList.getByIndex(i).transactions.toArray();
            for (int j = 0; j < transactions.length; ++j) {
                transactionsList.add(transactions[j]);
            }
        }
        Transaction[] array = transactionsList.toArray();
        TransactionsList unpaired = new TransactionsLinkedList();
        for (int i = 0; i < array.length; ++i) {
            int idCount = 0;
            for (int j = 0; j < array.length; ++j) {
                if (array[i].getId() == array[j].getId()) {
                    ++idCount;
                    if (idCount == 2)
                        break ;
                }
            }
            if (idCount != 2) {
                unpaired.add(array[i]);
            }
        }
        return unpaired.toArray();
    }

    public void testRemoveTransaction() {
        if (usersList.size() > 0) {
            Transaction[] t = usersList.getByIndex(0).transactions.toArray();
            if (t.length > 0)
                usersList.getByIndex(0).transactions.remove(t[0].getId());
        }
    }
}
