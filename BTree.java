public class BTree<E extends Comparable<E>> {
    // Atributos
    protected Node<E> root;
    protected int order;

    // Constructores de un BST vacio, solo se ingresa el orden
    public BTree(int n) {
        this.root = null;
        this.order = n;
    }

    // RECORRIDO
    public String recorrido() {
        if (this.root == null) return "*";
        return imprimirRecorridoNodo(this.root);
    }

    public String imprimirRecorridoNodo(Node<E> actual) {
        String res = "";
        res += imprimirRecorridoKey(actual.first);
        return res;
    }

    public String imprimirRecorridoKey(Key<E> actual) {
        if(actual==null) return "";
        String res = "";
        if (actual.left != null) res += imprimirRecorridoNodo(actual.left);
        return res + actual.value.toString() + " " + imprimirRecorridoKey(actual.next);
    }

    // BUSQUEDA
    public Node<E> search(E x) {
        return searchNode(x, this.root, false);
    }

    public Node<E> searchNode(E x, Node<E> n, boolean insercion) {
        if(n == null) return null;
        Node<E> res = n;
        Key<E> actual = n.first;
        Key<E> anterior = actual;
        while(actual != null) {
            if (actual.value.compareTo(x) == 0) return n;
            if (actual.value.compareTo(x) > 0) {
                res = searchNode(x, actual.left, insercion);
                break;}
            anterior = actual;
            actual = actual.next;
        }
        if (actual == null && anterior.value.compareTo(x) < 0)
            res = searchNode(x, anterior.right, insercion);
        if (res == null && insercion) return n;
        return res;
    }

    // INSERCIÓN
    public void insert(E x) {
        Node<E> res = searchNode(x, this.root, true);
        res.insert(x);
        if(res.size == order) overflow(res);
    }

    public void overflow(Node<E> n) {
        int derecha = (order-1)/2;
        Node<E> parent = getParent(n);
        Node<E> r = new Node<E>();
        Node<E> l = new Node<E>();
        for(int i=0; i < derecha; i++) {
            r.insert(n.pop().value);
        }
        E m = n.pop().value;
        parent.insert(m);
        while (n.size > 0) {
            l.insert(n.pop().value);
        }
        parent.getPrevByValue(m).right = l;
        parent.getByValue(m).left = l;
        parent.getByValue(m).right = r;
        parent.getByValue(m).next.left = r;

        if (parent.size == order) overflow(parent);
    }

    public Node<E> getParent(Node<E> n) {
        if (n == null || n == root)  return null;
        return getParentNode(root, n.first.value);
    }

    public Node<E> getParentNode(Node<E> a, E v) {
        if(a==null) return null;
        Key<E> key = a.first;
        while(key.next != null) {
            if (key.left.first.value.compareTo(v) == 0) return a;
            key = key.next;
        }
        if (key.value.compareTo(v) > 0) {
            if (key.left.first.value.compareTo(v) == 0) return a;
            return getParentNode(key.left, v);
        } else {
            if (key.right.first.value.compareTo(v) == 0) return a;
            return getParentNode(key.right, v);
        }
    }
}
