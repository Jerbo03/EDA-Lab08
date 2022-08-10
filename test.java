public class test {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<Integer>(5);

        Integer array[] = { 1, 2, 3, 4, 5 };

        for (Integer value : array)
            tree.insert(value);

        System.out.println(tree);
    }
}
