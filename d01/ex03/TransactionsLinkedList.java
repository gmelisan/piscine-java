package ex03;

import java.util.UUID;

class TransactionNotFoundException extends RuntimeException {}

public class TransactionsLinkedList implements TransactionsList {

    private Node head = null;
    private Node tail = null;
    private Integer size = 0;

    private class Node {
        Transaction transaction;
        Node next;

        public Node(Transaction transaction) {
            this.transaction = transaction;
            next = null;
        }
    }

    @Override
    public void add(Transaction transaction) {
        ++size;
        Node node = new Node(transaction);
        if (head == null) {
            head = node;
            tail = node;
            return ;
        }
        tail.next = node;
        tail = node;
    }

    @Override
    public void remove(UUID id) {
        Node prevNode = null;
        for (Node node = head; node != null; node = node.next) {
            if (node.transaction.getId() == id) {
                if (prevNode == null) {
                    head = node.next;
                    --size;
                    return ;
                }
                prevNode.next = node.next;
                --size;
                return ;
            }
            prevNode = node;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Node node = head;
        for (int i = 0; i < size; ++i) {
            array[i] = node.transaction;
            node = node.next;
        }
        return array;
    }

    @Override
    public String toString() {
        String s = "(" + size + ")";
        if (size == 0)
            return s += " {}";
        s += " {\n";
        for (Node node = head; node != null; node = node.next) {
            s += "    " + node.transaction + "\n";
        }
        s += "}";
        return s;
    }
}
