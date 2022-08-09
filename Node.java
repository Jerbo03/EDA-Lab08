class Node<E> {
    public E data;
    public Node<E> left;
    public Node<E> right;
    public int height;
    public boolean color; // 0 - negro; 1 - rojo
    public Node<E> parent;

    // Constructores
    public Node(E data, Node<E> left, Node<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 0;
        this.color = false;
        this.parent = null;
    }

    public Node(E data) {
        this(data, null, null);
    }
    // getters, setters
}
