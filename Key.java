public class Key<E extends Comparable<E>> {
    public E value;
    public Key<E> next;
    public Node<E> left;
    public Node<E> right;

    // Constructor
    public Key(E v, Key<E> n, Node<E> l, Node<E> r) {
       value = v;
       next = n;
       left = l;
       right = r;
    }

    public Key(E v, Key<E> n) {
        this(v, n, null, null);
    }

    public Key(E v) {
        this(v, null, null, null);
    }

    public Key(E v, Key<E> n, Node<E> l) {
        this(v, null, l, null);
    }

}
