package b_binarytree.c_avl;

import b_binarytree.b_bst.BinarySearchTree;

/**
 * ========================
 * module_name:[]
 * module_desc:
 *
 * @author: dp
 * @date: 2022/12/19
 * @since: 1.0
 * ========================
 */
public class AVLBinaryTree<E> extends BinarySearchTree<E> {

    @Override
    protected void afterAdd(Node<E> node) {
        // 只可能父节点、祖父节点失衡，其他节点不会失衡
        while ((node = node.parent) != null) {
            // 循环遍历parent，判断是否平衡
            if (isBalance(node)) {
                // 1.平衡，只需要去更新父节点高度即可
                updateHeight(node);
            } else {
                // 2.不平衡
                // 找到高度最低的那个失衡节点，然后对失衡节点进行调整。只要这个高度最低的失衡节点恢复平衡，那么所有的节点都恢复平衡了。
                rebalanced(node);
            }
        }
    }

    /**
     * 判断节点是否平衡
     *
     * @param node 指定节点
     * @return false-不平衡，true-平衡
     * @author dp
     * @date 2022/12/19 14:01
     * @since 2.3
     **/
    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    /**
     * 更新节点高度
     *
     * @param node 待更新节点
     * @author dp
     * @date 2022/12/19 14:02
     * @since 2.3
     **/
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    /**
     * 再平衡节点：
     * 1.判断是四种失衡中的哪一种
     * 2.进行旋转
     *
     * @param grand 不平衡的节点
     * @author dp
     * @date 2022/12/19 14:02
     * @since 2.3
     **/
    private void rebalanced(Node<E> grand) {
        // 1.分别找出parent、node
        // parent：肯定是grand的左右子树中最高的那一个
        // node：必然是parent的左右子树中最高的那一个
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();

        // 2.判断方向
        if (((AVLNode<E>) parent).isLeftChild()) {
            //L
            if (((AVLNode<E>) node).isLeftChild()) {
                // LL
                rotateRight(grand);
            } else {
                // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            //R
            if (((AVLNode<E>) node).isLeftChild()) {
                // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                // RR
                rotateLeft(grand);
            }
        }
    }

    private void rotateLeft(Node<E> grand) {
        // 1.进行旋转（维护节点本身的指向）
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        // 2.新的根节点的parent的关系维护
        // 原来根节点是其parent的左，则新的根节点指向parent的左；
        // 原来根节点是其parent的右，则新的根节点指向parent的右。
        /*if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        // 3.更新child的parent
        // 判空：child有可能是空的
        if (null != child) {
            child.parent = grand;
        }*/

        // 4.更新高度：只有grand子树、parent子树发生了变化
        // 4.1 grand的高度
        //updateHeight(grand);
        // 4.2 parent的高度
        //updateHeight(parent);

        afterRotate(grand,parent,child);
    }

    private void rotateRight(Node<E> grand) {
        // 1.进行旋转：维护节点本身的关系
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        // 2.维护变化节点的parent属性
        // 2.1 根节点
        /*if (grand.isLeftChild()){
            grand.parent.left = parent;
        } else if (grand.isRightChild()){
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        // 2.2 child节点
        if (child != null){
            child.parent = grand;
        }*/

        // 3.更新高度：只有grand子树、parent子树发生了变化
        // 3.1 grand的高度
        //updateHeight(grand);
        // 3.2 parent的高度
        //updateHeight(parent);
        afterRotate(grand,parent,child);
    }

    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
        // 2.维护变化节点的parent属性
        // 2.1 根节点
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        } else if (grand.isRightChild()){
            grand.parent.right = parent;
        } else {
            root = parent;
        }

        // 2.2 child节点
        if (child != null){
            child.parent = grand;
        }

        // 3.更新高度：只有grand子树、parent子树发生了变化
        // 3.1 grand的高度
        updateHeight(grand);
        // 3.2 parent的高度
        updateHeight(parent);
    }


    /**
     * AVL树的节点
     *
     * @author dp
     * @date 2022/12/19 14:03
     * @since 2.3
     **/
    protected static class AVLNode<E> extends Node<E> {
        // 默认值为1：因为新节点肯定是叶子节点，所以高度肯定是1
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 计算平衡因子：左子树高度 - 右子树高度
         *
         * @return 平衡因子
         * @author dp
         * @date 2022/12/19 13:59
         * @since 2.3
         **/
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        /**
         * 更新节点高度 = 1 + max(左、右子树高度)
         *
         * @author dp
         * @date 2022/12/19 14:00
         * @since 2.3
         **/
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = Math.max(leftHeight, rightHeight);
        }

        /**
         * 获取当前节点的较高的子节点
         * <p>
         * 说明：如果左右高度一致，则返回同方向的节点 —— 如果我是父节点的左节点，则返回我节点的左节点；
         * 如果我是父节点的右节点，则返回我节点的右节点。
         *
         * @return 较高的子节点
         * @author dp
         * @date 2022/12/19 14:21
         * @since 2.3
         **/
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            } else if (leftHeight < rightHeight) {
                return right;
            } else {
                return isLeftChild() ? left : right;
            }
        }


    }
}
