public class Node<E extends Comparable<E>> {
    protected Key<E> first; // Primer nodo de la lista
    protected int size; // Tamano de la lista

    // Constructor (crea lista vacia)
    public Node() {
        first = null;
        size = 0;
    }

    public Node(E data) {
        this(new Key<E>(data));
    }

    // Constructor (valor incial)
    public Node(Key<E> k) {
        first = k;
        size = 1;
    }

    // Retorna el tamano de la lista
    public int size() {
        return size;
    }

    // Devuelve true si la lista esta vacia o false caso contrario
    public boolean isEmpty() {
        return (size == 0);
    }

    // Adiciona v al inicio de la lista
    public void addFirst(E v) {
        Key<E> newKey = new Key<E>(v, first);
        first = newKey;
        size++;
    }

    // Adiciona v al final de la lista
    public void addLast(E v) {
        Key<E> newNode = new Key<E>(v, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            Key<E> cur = first;
            while (cur.next != null)
                cur = cur.next;
            cur.next = newNode;
        }
        size++;
    }

    // Retorna la primera llave de la lista (o null si la lista esta vacia)
    public Key<E> getFirst() {
        if (isEmpty()) return null;
        return first;
    }

    // Retorna la ultima llave de la lista (o null si la lista esta vazia)
    public Key<E> getLast() {
        if (isEmpty()) return null;
        Key<E> cur = first;
        while (cur.next != null)
            cur = cur.next;
        return cur;
    }

    // Elimina el primer elemento de la lista (si esta vacia no hara nada)
    public void removeFirst() {
        if (isEmpty()) return;
        first = first.next;
        size--;
    }

    // Elimina el ultimo elemento de la lista (si esta vacia no hara nada)
    public void removeLast() {
        if (isEmpty()) return;
        if (size == 1) {
            first = null;
        } else {
            // Ciclo con for y uso de size para mostrar alternativa al while
            Key<E> cur = first;
            for (int i = 0; i < size - 2; i++)
                cur = cur.next;
            cur.next = cur.next.next;
        }
        size--;
    }

    // Convierte la lista para um String
    public String toString() {
        String str = "{";
        Key<E> cur = first;
        while (cur != null) {
            str += cur.value;
            cur = cur.next;
            if (cur != null)
                str += ",";
        }
        str += "}";
        return str;
    }

    // NUEVOS METODOS

    // Inserta un nuevo nodo en una posicion especifica de la lista
    public void insertNth(E data, int position) {
        if (position > size) { // Si la posicion es mayor al tamano entonces no cambiar nada
            System.out.println("Fuera de rango.");
            return;
        }
        if (position == 0) {
            addFirst(data);
            return;
        } // Si se quiere poner al comienzo, llamar a la funcion ya creada
        if (position == size) {
            addLast(data);
            return;
        } // Si se quiere poner al final, llamar a la funcion ya creada
        Key<E> cur = first; // Si la posicion es mayor a 0, empezar por el primer nodo
        for (int i = 1; i < position; i++)
            cur = cur.next; // E ir cambiandolo hasta llegar al anterior al cual se desea insertar
        Key<E> nuevo = new Key<E>(data, cur.next); // Crear el nodo dandole como next el nodo que ocupa su lugar
        cur.next = nuevo; // Y setearlo como next del anterior
        size++;
    }

    // Elimina el nodo de una posicion especifica de la lista
    public void deleteNth(int position) {
        if (position >= size) { // Si la posicion es mayor al tamano entonces no cambiar nada
            System.out.println("Fuera de rango.");
            return;
        }
        if (position == 0) {
            removeFirst();
            return;
        } // Si se quiere eliminar la primera, llamar a la funcion ya creada
        if (position == size - 1) {
            removeLast();
            return;
        } // Si se quiere eliminar al final, llamar a la funcion ya creada
        Key<E> cur = first; // Si la posicion es valida, empezar por el primer nodo
        for (int i = 1; i < position; i++)
            cur = cur.next; // E ir cambiandolo hasta llegar al anterior al cual se desea eliminar
        cur.next = cur.next.next; // Y setearlo como next del anterior
        size--;
    }

    public boolean find(E v) {
        if(isEmpty()) return false;
        return (first.value.compareTo(v) == 0) || findKey(v, first.next);
    }

    public boolean findKey(E v, Key<E> actual) {
        if (actual == null) return false;
        return (actual.value.compareTo(v) == 0) || findKey(v, actual.next);
    }

    public void insert(Key<E> v) {
        if (find(v.value)) return;
        if (isEmpty() || first.value.compareTo(v.value) > 0) addFirst(v.value);
        first.next = insertKey(v, first.next);
        size++;
    }

    public Key<E> insertKey(Key<E> v, Key<E> k) {
        Key<E> actual = k;
        if (actual.next.value.compareTo(v.value) > 0) {
            v.next = actual;
            return v;
        }
        actual.next = insertKey(v, actual.next);
        return actual;
    }

    public void insert(E v) {
        if(find(v)) return;
        if(isEmpty() || first.value.compareTo(v) > 0) addFirst(v);
        first.next = insertKey(v, first.next);
        size++;
    }

    public Key<E> insertKey(E v, Key<E> k) {
        Key<E> actual = k;
        if (actual.next.value.compareTo(v) > 0)
            return new Key<E>(v, actual);
        actual.next = insertKey(v, actual.next);
        return actual;
    }

    public Key<E> pop() {
        Key<E> temp = getLast();
        removeLast();
        size--;
        return temp;
    }

    public Key<E> getByValue(E v) {
        if (isEmpty() || !(find(v))) return null;
        if (size == 1) return getFirst();
        return getPrevByValue(v).next;
    }

    public Key<E> getPrevByValue(E v) {
        if (isEmpty() || size == 1 || !(find(v))) return null;
        Key<E> actual = getFirst();
        while (actual.next.value.compareTo(v) != 0) actual = actual.next;
        return actual;
    }
}
