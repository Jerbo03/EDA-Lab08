public class BTree<E extends Comparable<E>> {
    // Atributos
    protected Node<E> root;

    // Constructores de un BST vacio
    public BST() {
        this.root = null;
    }

    public void insert(E x) {
        this.root = insertNode(x, this.root);
    }

    protected Node<E> insertNode(E x, Node<E> actual) {
        Node<E> res = actual;
        if (actual == null) {
            res = new Node<E>(x);
        } else {
            // buscamos el lugar para insercion
            int resC = actual.data.compareTo(x);
            if (resC == 0)
                return actual;
            if (resC < 0)
                res.right = insertNode(x, actual.right);
            else
                res.left = insertNode(x, actual.left);
        }
        return res;
    }

    public void remove(E x) {
        this.root = removeNode(x, this.root);
    }

    protected Node<E> removeNode(E x, Node<E> actual) {
        Node<E> res = actual;
        if (actual == null) {
            System.out.println("No such data present in BST.");
            return null;
        }
        int resC = actual.data.compareTo(x);
        if (resC < 0)
            res.right = removeNode(x, actual.right);
        else if (resC > 0)
            res.left = removeNode(x, actual.left);
        else if (actual.left != null && actual.right != null) {// dos hijos
            res.data = minRecover(actual.right).data;
            res.right = minRemove(actual.right);
        } else { // 1 hijo o ninguno
            res = (actual.left != null) ? actual.left : actual.right;
        }
        return res;
    }

    // Elimina el menor de la izquierda de un nodo
    protected Node<E> minRemove(Node<E> actual) {
        if (actual.left != null) { // busca el minimo
            actual.left = minRemove(actual.left);
        } else { // elimina el minimo
            actual = actual.right;
        }
        return actual;
    }

    protected Node<E> minRecover(Node<E> actual) {
        if (actual.left == null) { // busca el minimo
            return actual;
        }
        return minRecover(actual.left);
    }

    public boolean search(E x) {
        Node<E> res = searchNode(x, root);
        if (res == null) {
            System.out.println(x + " not found.");
            return false;
        }
        System.out.println(x + " is present in given BST.");
        return true;
    }

    protected Node<E> searchNode(E x, Node<E> n) {
        if (n == null)
            return null;
        else {
            int resC = n.data.compareTo(x);
            if (resC < 0)
                return searchNode(x, n.right);
            else if (resC > 0)
                return searchNode(x, n.left);
            else
                return n;
        }
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public String postOrder() {
        if (this.root != null)
            return postOrder(this.root);
        else
            return "*";
    }

    public String preOrder() {
        if (this.root != null)
            return preOrder(this.root);
        else
            return "*";
    }

    protected String postOrder(Node<E> actual) {
        String res = "";
        if (actual.left != null)
            res += postOrder(actual.left);
        if (actual.right != null)
            res += postOrder(actual.right);
        return res + actual.data.toString() + " ";
    }

    protected String preOrder(Node<E> actual) {
        String res = actual.data.toString() + " ";
        if (actual.left != null)
            res += preOrder(actual.left);
        if (actual.right != null)
            res += preOrder(actual.right);
        return res;
    }

    public static void main(String [] args){
        BST<Integer> tree = new BST<Integer>();

        Integer array [] = {1, 2,3, 4, 5};

        for(Integer value : array)
            tree.insert(value);

        System.out.println(tree.postOrder());
    }
}
