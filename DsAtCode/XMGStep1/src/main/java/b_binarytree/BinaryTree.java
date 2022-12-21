package b_binarytree;


import b_binarytree.a_com.print.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ========================
 * module_name:[]
 * module_desc:
 *
 * @author: dp
 * @date: 2022/12/13
 * @since: 1.0
 * ========================
 */
public class BinaryTree<E> implements BinaryTreeInfo {

    protected Node<E> root;
    protected int size;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() != 0;
    }

    public void clear() {
        this.root = null;
    }

    /**
     * 前序遍历
     *
     * @param visitor 元素访问器（用户自自定义访问元素逻辑）
     * @author dp
     * @date 2022/12/13 21:40
     * @since 2.3
     **/
    public void preorderTraversalByRecs(Visitor<E> visitor) {
        this.preorderTraversalByRecs(visitor, this.root);
    }

    /**
     * 前序遍历：指定节点开始遍历
     *
     * @param visitor 元素访问器（用户自自定义访问元素逻辑）
     * @param node    指定开始遍历的节点
     * @author dp
     * @date 2022/12/13 21:40
     * @since 2.3
     **/
    protected void preorderTraversalByRecs(Visitor<E> visitor, Node<E> node) {
        if (visitor == null || node == null) {
            return;
        }
        visitor.visit(node.element);
        this.preorderTraversalByRecs(visitor, node.left);
        this.preorderTraversalByRecs(visitor, node.right);
    }

    /**
     * 中序遍历
     *
     * @param visitor 元素访问器（用户自自定义访问元素逻辑）
     * @author dp
     * @date 2022/12/13 21:40
     * @since 2.3
     **/
    public void inorderTraversalByRecs(Visitor<E> visitor) {
        this.inorderTraversalByRecs(visitor, this.root);
    }

    /**
     * 中序遍历：指定节点开始遍历
     *
     * @param visitor 元素访问器（用户自自定义访问元素逻辑）
     * @param node    指定开始遍历的节点
     * @author dp
     * @date 2022/12/13 21:40
     * @since 2.3
     **/
    protected void inorderTraversalByRecs(Visitor<E> visitor, Node<E> node) {
        if (null == visitor || node == null) {
            return;
        }
        this.inorderTraversalByRecs(visitor, node.left);
        visitor.visit(node.element);
        this.inorderTraversalByRecs(visitor, node.right);
    }

    /**
     * 后序遍历
     *
     * @param visitor 元素访问器（用户自自定义访问元素逻辑）
     * @author dp
     * @date 2022/12/13 21:40
     * @since 2.3
     **/
    public void postorderTraversalByRecs(Visitor<E> visitor) {
        this.postorderTraversalByRecs(visitor, this.root);
    }

    /**
     * 后序遍历
     *
     * @param visitor 元素访问器（用户自自定义访问元素逻辑）
     * @author dp
     * @date 2022/12/13 21:40
     * @since 2.3
     **/
    protected void postorderTraversalByRecs(Visitor<E> visitor, Node<E> node) {
        if (null == visitor || node == null) {
            return;
        }
        this.inorderTraversalByRecs(visitor, node.left);
        this.inorderTraversalByRecs(visitor, node.right);
        visitor.visit(node.element);
    }

    /**
     * 层序遍历
     *
     * @param visitor 元素访问器（用户自自定义访问元素逻辑）
     * @author dp
     * @date 2022/12/13 21:40
     * @since 2.3
     **/
    public void levelOrderTraversal(Visitor<E> visitor) {
        this.levelOrderTraversal(visitor, this.root);
    }

    /**
     * 层序遍历
     *
     * @param visitor 元素访问器（用户自自定义访问元素逻辑）
     * @author dp
     * @date 2022/12/13 21:40
     * @since 2.3
     **/
    protected void levelOrderTraversal(Visitor<E> visitor, Node<E> node) {
        if (null == visitor || null == node) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        // 1.先将头结点入队
        queue.offer(node);
        while (!queue.isEmpty()) {
            // 2.FIFO取出头元素，进行方法
            Node<E> poll = queue.poll();
            visitor.visit(poll.element);
            // 3.访问完元素会后：将其左/右子节点入队（下一层的元素进行入队）
            // 所以，每当当前层访问完了，那么下一层的所有元素都入队了
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }

    /**
     * 节点的元素值非null校验
     *
     * @param element {@link Node#element}值
     * @author dp
     * @date 2022/12/13 21:48
     * @since 2.3
     **/
    protected void elementNotNullCheck(E element) {
        if (null == element) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 计算树的高度（递归方式）
     *
     * @return 二叉树的高度 == 根节点的高度
     * @author dp
     * @date 2022/12/13 21:49
     * @since 2.3
     **/
    public int heightByRecs() {
        return this.heightByRecs(this.root);
    }

    /**
     * 计算树的高度（递归方式）：计算指定节点的高度
     *
     * @param node 指定计算高度的节点
     * @return 节点高度
     * @author dp
     * @date 2022/12/13 21:50
     * @since 2.3
     **/
    protected int heightByRecs(Node<E> node) {
        if (node == null) {
            return 0;
        }
        // 计算逻辑：1 + node左子树和右子树高度的最大值
        return 1 + Math.max(heightByRecs(node.left), heightByRecs(node.right));
    }

    /**
     * 计算树的高度（迭代） <br/>
     *
     * @return 二叉树的高度 == 根节点的高度
     * @author dp
     * @date 2022/12/13 21:49
     * @since 2.3
     **/
    public int heightByIt() {
        return this.heightByIt(this.root);
    }

    /**
     * 计算树的高度（递归方式）：计算指定节点的高度<br/>
     * 计算逻辑：层序遍历遍历的层数就是高度，即：每遍历完一层，高度+1
     *
     * @param node 指定计算高度的节点
     * @return 节点高度
     * @author dp
     * @date 2022/12/13 21:50
     * @since 2.3
     **/
    protected int heightByIt(Node<E> node) {
        if (null == node) {
            return 0;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        // 1.初始情况
        // levelSize = 1:初始情况下，只将传进来的node入队了，所以size为1
        int height = 0;
        int levelSiz = 1;
        queue.offer(node);
        // 2.循环访问每一个元素
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            // 2.1 每取出一个元素，则将当前层的元素数量减1
            levelSiz--;
            // 2.2 分别将当前元素的下一层节点（左右子节点）分别入队
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
            // 2.3 当levelSize == 0:表明当前层元素已经访问完了，此时：
            // (1)高度需要加+1
            // (2)下一层元素的个数就是此时队列中元素的个数
            if (levelSiz == 0) {
                height++;
                levelSiz = queue.size();
            }
        }
        return height;
    }

    /**
     * 完全二叉树的判断 <br/>
     * 判断的基本逻辑：从上到下，从左到右，逐个节点判断是否满足完全二叉树
     *
     * @return 是否为完全二叉树
     * @author dp
     * @date 2022/12/13 22:04
     * @since 2.3
     **/
    public boolean isCompleteBT() {
        if (this.root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        // 1.标记之后遍历的节点是否全部应该为叶子节点
        // 完全二叉树：当某个节点的left != null && right == null 或者 left == null && right == null时，
        // 那么后续的所有节点都应该是叶子节点才是完全二叉树
        boolean leaf = false;
        while (!queue.isEmpty()) {
            // 2.使用层序遍历这里需要注意：必须保证二叉树的所有节点都能遍历到，
            // 因此，必须原封不动的将层序遍历代码搬过来——每取出一个节点，分别判断左节点、右节点是否为nll，
            // 若不为null，则需要分别将其入队。
            Node<E> poll = queue.poll();
            if (leaf && !poll.isLeaf()) {
                return false;
            }
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            // left == null时：
            // (1)right != null：则肯定不是
            // (2)right == null，则表明之后的节点是叶子节点，正好可以和下面的right判断放在一起
            /*else { // 左节点为空
                if (poll.right != null){
                    // left == null && right != null
                    return false;
                } else {
                    // left == null && right == null
                    leaf = true;
                }
            }*/
            //
            else if (poll.right != null) {
                return false;
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            } else {
                // 因为：right == null && left != null 或者 right == null && left != null
                // 所以：right == null ==> 后边的节点一定是叶子节点才能满足是完全二叉树
                /*if (poll.left != null) {
                    leaf = true;
                } else {
                    leaf = true;
                }*/
                // 所以上述else直接简化：
                leaf = true;

            }
        }
        return true;

    }

    /**
     * 前驱结点：中序遍历时的前一个节点
     *
     * @param node 指定的节点
     * @return 前驱结点
     * @author dp
     * @date 2022/12/13 23:06
     * @since 2.3
     **/
    protected Node<E> predecessor(Node<E> node) {
        if (null == node) {
            return null;
        }

        // 1.左子树不为空，则迭代往右找
        if (node.left != null) {
            Node<E> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }


        // 2.左子树为空：那么前一个节点肯定在父节点里，因为右子树的所有节点肯定在当前节点的前边，
        // 所以，前驱结点必然可能出现在父节点中。
        // (1)特殊情况：假设该节点是该节点的父节点的右节点，那么该父节点肯定是他的前驱节点
        // (2)一般情况：根据(1)的推论，推论到一般情况：一直找到node.parent.right == node.parent
        // 规律总结：从当前节点开始，沿着其父节点向右上角斜着画一条线，那么他的前驱节点肯定出现，
        // 如果这条线上的某个节点出现向左拐时，那么这个节点肯定是这个节点的前驱结点。如果没有拐，则必然不存在前驱结点
        // 原因：向左拐了，那么个节点的右子树肯定是右边的这些组成的，而根据中序遍历的特点，这个节点一直到左拐的这个
        // 节点之间，必然是右边这个子树的前驱节点。
        /*
               [n]
                 \
                  []
                 /
               []
              /
            []
         */
        // 结束循环的条件：一直往上找父节点，
        // (1)直到父节点为空：node.parent == null，没有前驱节点
        // (2)或者找了前驱节点为止：node.parent.right == node.parent
        while (null != node.parent && node.parent.left == node.parent) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 后继结点：中序遍历时的后一个节点
     *
     * @param node 指定的节点
     * @return 后继节点
     * @author dp
     * @date 2022/12/14 10:14
     * @since 2.3
     **/
    protected Node<E> successor(Node<E> node) {
        if (null == node) {
            return null;
        }

        // 1.找右子树
        if (node.right != null) {
            Node<E> s = node.right;
            while (null != s.left) {
                s = s.left;
            }
            return s;
        }

        // 2.找左子树，往右拐
        while (node.parent != null && node.parent == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }


    protected static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        /**
         * 判断当前节点是否为父节点的左节点
         *
         * @return false-不是，true-是
         * @author dp
         * @date 2022/12/19 14:24
         * @since 2.3
         **/
        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        /**
         * 判断当前节点是否为父节点的右节点
         *
         * @return false-不是，true-是
         * @author dp
         * @date 2022/12/19 14:25
         * @since 2.3
         **/
        public boolean isRightChild() {
            return parent != null && parent.right == this;
        }

    }

    /**
     * 遍历链表时的元素访问器
     *
     * @param <E>
     */
    @FunctionalInterface
    public static interface Visitor<E> {

        /**
         * 遍历方法
         *
         * @param element 元素内容
         */
        void visit(E element);
    }


    /****************************** 打印信息 开始 *********************************/
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node) node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>) node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_" + parentString;
    }
    /****************************** 打印信息 结束 *********************************/
}
