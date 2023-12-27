public class Q1_GenericStack<T> {

    private static class Node<T> {

        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> top;
    private int size;

    Q1_GenericStack() {
        this.top = null;
        this.size = 0;
    }

    public void pushStack(T item) {
        Node<T> nn = new Node(item);
        if (this.size == 0) {
            this.top = nn;
            this.top.next = null;
            this.size++;
        } else {
            Node<T> temp = top;
            top = nn;
            top.next = temp;
            this.size++;
        }
    }

    public T popStack() throws EmptyStackException {
        if (this.size == 0) {
            throw new EmptyStackException("Stack Empty :(");
        }
        T data = this.top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean StackIsEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public int stackSize() {
        return this.size;
    }

    public void printStack() {
        System.out.println("Printing Stack:-");
        Node<T> temp = top;
        while (temp.next != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println(temp.data);
    }

    public static class EmptyStackException extends Exception {

        public EmptyStackException(String message) {
            super(message);
        }
    }

}
