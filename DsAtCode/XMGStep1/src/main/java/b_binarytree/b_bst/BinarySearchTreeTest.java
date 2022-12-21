package b_binarytree.b_bst;

import b_binarytree.a_com.print.BinaryTrees;
import org.junit.Test;

/**
 * ========================
 * module_name:[]
 * module_desc:
 *
 * @author: dp
 * @date: 2022/12/10
 * @since: 1.0
 * ========================
 */
public class BinarySearchTreeTest {

    @Test
    public void testCompare() {
        /**
         * 可以传入一个Comparator，也可以不传
         */
        BinarySearchTree<PersonPoJo> binarySearchTree1 = new BinarySearchTree<>((o1, o2) -> o1.age - o2.age);
        binarySearchTree1.add(new PersonPoJo(18, "zhangsan"));
        binarySearchTree1.add(new PersonPoJo(20, "lisi"));
        binarySearchTree1.add(new PersonPoJo(12, "wangwu"));
        BinaryTrees.println(binarySearchTree1);

        /**
         * 若不传，则元素必须实现{@link Comparable#compareTo(Object)}方法
         */
        BinarySearchTree<PersonPoJo> binarySearchTree2 = new BinarySearchTree<>();
        binarySearchTree2.add(new PersonPoJo(18, "zhangsan"));
        binarySearchTree2.add(new PersonPoJo(20, "lisi"));
        binarySearchTree2.add(new PersonPoJo(12, "wangwu"));

        BinaryTrees.println(binarySearchTree2);
    }

    @Test
    public void testPrint() {
        Integer[] data = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3
        };
        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            integerBinarySearchTree.add(data[i]);
        }

        BinaryTrees.println(integerBinarySearchTree);
        System.out.println("111");

    }

    /**
     * 测试二叉树的四种遍历
     */
    @Test
    public void testTraversalByRecs() {
        Integer[] data = new Integer[]{
                7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
        };
        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();
        for (Integer item : data) {
            integerBinarySearchTree.add(item);
        }
        BinaryTrees.println(integerBinarySearchTree);
        // 前序遍历（递归）
        System.out.println("------------前序遍历---------------");
//        integerBinarySearchTree.preorderTraversalByRecs();
        // 中序遍历（递归）
        System.out.println("------------中遍历---------------");
//        integerBinarySearchTree.inorderTraversalByRecs();
        // 后序遍历（递归）
        System.out.println("------------后序遍历---------------");
//        integerBinarySearchTree.postorderTraversalByRecs();
        // 层序遍历（递归）
        System.out.println("------------层遍历---------------");
//        integerBinarySearchTree.levelOrderTraversal();
    }

    /**
     * 测试外部访问器
     */
    @Test
    public void testTraversalVisitor() {
        Integer[] data = new Integer[]{
                7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
        };
        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            integerBinarySearchTree.add(data[i]);
        }
        // 用户传入visitor
        integerBinarySearchTree.levelOrderTraversal(element -> {
            // 用户自己去实现遍历的逻辑
            System.out.println("元素：" + element);
            int newElement = element + 1;
            System.out.println("元素加上字符串1的结果：" + newElement);
        });
    }

    @Test
    public void height() {
        Integer[] data = new Integer[]{
                7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
        };
        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            integerBinarySearchTree.add(data[i]);
        }
        //System.out.println("树的高度（递归计算）：" + integerBinarySearchTree.heightByRecs());
        //System.out.println("树的高度（迭代计算-层序遍历）：" + integerBinarySearchTree.heightByIt());

        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
        for (int i = 0; i < 20; i++) {
            tree2.add((int) (Math.random() * 100));
        }
        BinaryTrees.println(tree2);
        System.out.println(tree2.heightByRecs());
        System.out.println(tree2.heightByIt());
    }

    @Test
    public void testFullBinaryTree(){
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<>();
        /**
         *       8
         *    6      12
         *  3   7  11
         */
        Integer[] data = new Integer[]{
                8,6,12,3,7,11
        };
//        Integer[] data = new Integer[]{
//                7,4,9,2,5
//        };
        for (int i = 0; i < data.length; i++) {
            tree1.add(data[i]);
        }
        BinaryTrees.println(tree1);
        System.out.println(tree1.isCompleteBT());
    }


}
