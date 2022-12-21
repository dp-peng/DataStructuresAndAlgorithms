package b_binarytree.b_bst;


import b_binarytree.BinaryTree;

import java.util.Comparator;

/**
 * ========================
 * module_name:[二叉搜索树]
 * module_desc:
 *
 * @author: dp
 * @date: 2022/12/6
 * @since: 1.0
 * ========================
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    /**
     * 比较器：因为二叉搜索树的元素必须具有可比较性
     */
    private Comparator<E> comparator;


    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 添加元素 <br/>
     * 非根节点添加逻辑：<br/>
     * 1.找到父节点parent <br/>
     * 2.创建节点node <br/>
     * 3.parent.left = node 或 parent.right = node <br/>
     *
     * @param element 添加的元素
     * @author dp
     * @date 2022/12/10 21:07
     * @since 1.0
     **/
    public void add(E element) {
        // 1.限制添加的元素不能为空
        this.elementNotNullCheck(element);

        // 2.根节点判断
        if (this.root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 3.找父节点
        Node<E> parent = root;
        Node<E> compareNode = root;
        int comp = 0;
        // 结束条件：如果往左/右找比较的节点，当其为空时，表明这是新加节点的父节点
        while (compareNode != null) {
            // 3.1 父节点需要在可能赋值为空值之前保存下来
            parent = compareNode;

            // 3.2 比大小，找插入的位置（找父节点）
            comp = this.compare(element, compareNode.element);
            if (comp > 0) {
                // (1)若当前元素比父节点大，则往左继续找
                compareNode = compareNode.right;
            } else if (comp < 0) {
                // (2)若当前元素比父节点大，则往右继续找
                compareNode = compareNode.left;
            } else {
                // (3)若相等，则可自定义逻辑（这里可以覆盖或不做任何操作）

                return;
            }
        }

        // 4.确定新元素要插入到父节点左还是右？
        Node<E> newNode = new Node<>(element, parent);
        if (comp > 0) {
            // (1)比父节点大，则插入到右边
            parent.right = newNode;
        } else {
            // (2)比父节点小，则插入到左边
            parent.left = newNode;
        }
        // (3)相等的情况，上述while循环中已处理返回

        size++;
    }

    protected void afterAdd(Node<E> node){}

    /**
     * 删除分为以下重情况 <br/>
     * 1.删除的是：叶子结点 <br/>
     * （1）node == node.parent.left，则node.parent.left = null <br/>
     * （1）node == node.parent.right，则node.parent.right = null <br/>
     * （1）null == node.parent，则root = null(此种情况：只有一个节点，且是根节点) <br/>
     * <p>
     * 2.度为1的节点：用子节点替换原来节点的位置
     *
     * @param element
     * @return
     * @author dp
     * @date 2022/12/11 20:11
     * @since 2.3
     **/
    public void remove(E element) {
        // 对于用户来说，只知道元素的内容，所以：
        // 1.通过元素找到Node节点
        // 2.再通过删除Node节点去删除元素
        this.remove(this.node(element));
    }

    /**
     * 通过指定Node删除节点（真正的删除逻辑）
     *
     * @param node
     * @author dp
     * @date 2022/12/11 22:28
     * @since 2.3
     **/
    private void remove(Node<E> node) {
        if (null == node) {
            return;
        }

        size--;

        // 1.度为2的节点 == > 找前驱/后继 ==> 前驱/后继节点的度肯定是0或1
        // 1.1 找前驱或后继
        // 1.2 前驱/后继的element替换删除节点的element
        // 1.3 删除前驱和后继(此时的前驱和后继就是)
        if (node.hasTwoChildren()) {
            Node<E> successor = this.successor(node);
            node.element = successor.element;
            node = successor;
        }

        // 2.度为1和0的节点
        // 注意：度为2的节点的前驱/后继肯定是0或1，因此，度为2的节点的前驱/后继的逻辑就和删除度为1或0的一样
        // 2.1 找替代节点 ==> 判断度为1还是0
        Node<E> replaceNode = node.left != null ? node.left : node.right;
        if (replaceNode != null) {
            // 度为1：
            // 有可能有父节点，有可能没有父节点（没有父节点肯定是root节点）
            if (replaceNode.parent != null) {
                replaceNode.parent = node.parent;
                // 非根节点：判断左右
                if (node.parent.left != null) {
                    node.parent.left = replaceNode;
                } else {
                    node.parent.right = replaceNode;
                }
                node.parent = null;
            } else {
                // 根节点
                root = null;
            }
        } else {
            // 度为0
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    /**
     * 通过节点内容查找Node
     *
     * @param element 节点内容
     * @return 查找到的Node节点
     * @author dp
     * @date 2022/12/11 22:28
     * @since 2.3
     **/
    private Node<E> node(E element) {
        Node<E> node = this.root;
        while (node != null) {
            int cmp = this.compare(element, node.element);
            // 相等则表明找到了
            if (cmp == 0) {
                return node;
            }
            // 比大小：大则往右找，小则往左找
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public boolean contains(E element) {
        return false;
    }


    /**
     * 比较元素大小:
     * <ul>
     *     <li>结果大于0，e1>e2</li>
     *     <li>结果小于0，e1<e2</li>
     *     <li>结果等于0，e1=e2</li>
     * </ul>
     *
     * @param e1 比较元素
     * @param e2 比较元素
     * @return 比较结果
     * @author dp
     * @date 2022/12/10 21:50
     * @since 2.3
     **/
    private int compare(E e1, E e2) {
        return this.comparator != null ?
                comparator.compare(e1, e2) :
                ((Comparable<E>) e1).compareTo(e2);
    }


}
