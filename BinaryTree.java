import java.util.*;

/**
 * @author Liquid
 * @类名： BuildTree
 * @描述：
 * @date 2019/3/27
 */
public class BinaryTree<T> {

    /**
     * 根节点
     */
    private TreeNode<T> root;

    /**
     * 每个线程拥有一个存储遍历输出字符串的变量
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public BinaryTree() {

    }

    /**
     * 构造方法建立二叉树
     */
    public BinaryTree(T[] preArray, T[] midArray) {
        //创建好的树的根结点
        root = buildTree(preArray, 0, preArray.length - 1, midArray, 0, midArray.length - 1);
    }

    /**
     * 提供给外部调用的方法
     * pre表示先序遍历序列，mid表示中序遍历序列
     */
    /**
     * @param preArray,midArray 先序遍历序列数组preArray 中序遍历序列数组 midArray
     * @return
     * @throws IllegalArgumentException
     * @author Liquid
     * @描述：建立树
     * @date 2019/3/27
     */
    public void build(T[] preArray, T[] midArray) {
        //创建好的树的根结点
        root = buildTree(preArray, 0, preArray.length - 1, midArray, 0, midArray.length - 1);
    }

    /**
     * 前提条件，树中不存在重复元素
     * 由先序遍历序列和中序遍历序列，构造二叉树的方法
     * 我们建树的过程总是将序列不断地分割成左子树、右子树
     * lPre、rPre和lMid、rMid，分别就表示要对先序和中序的哪一部分进行建树
     */

    /**
     * @param preArray,preRootIndex,preRightIndex,midArray,midLeftIndex,midRightIndex
     * @return
     * @throws null
     * @author Liquid
     * @描述：
     * @date 2019/3/27
     */
    private TreeNode<T> buildTree(T[] preArray, int preRootIndex, int preRightIndex, T[] midArray, int midLeftIndex, int midRightIndex) {

        //在先序遍历序列中，找到当前这棵树的根结点
        T rootValue = preArray[preRootIndex];
        //在中序遍历序列中，根据先序中的根结点来查找在中序中的位置
        int rootIndex = getRootIndex(midArray, midLeftIndex, midRightIndex, rootValue);

        //如果没有找到，抛出参数异常
        if (rootIndex == -1) {
            throw new IllegalArgumentException("Illegal Argument!");
        }
        //计算当前这棵树，左右子树的个数(中序遍历的根节点左边是左子树，根节点右边是右子树，系列数组同理)
        int leftNum = rootIndex - midLeftIndex;
        int rightNum = midRightIndex - rootIndex;
        //先构建左子树
        TreeNode<T> left;
        //以当前结点为根的树，没有左子树
        if (leftNum == 0) {
            left = null;
        } else {
            //递归构造这棵树的左子树，传入左子树的先序根节点下标 preRootIndex + 1，最右子节点下标preRootIndex + leftNum
            // 中序最左子节点下标midLeftIndex和中序最右子节点下标rootIndex - 1
            left = buildTree(preArray, preRootIndex + 1, preRootIndex + leftNum, midArray, midLeftIndex, rootIndex - 1);
        }
        //构建右子树
        TreeNode<T> right;
        if (rightNum == 0) {
            right = null;
        } else {
            //递归的构造其右子树，传入右子树的先序根节点下标preRightIndex - rightNum + 1，最右子节点下标preRightIndex
            // 中序最左子节点下标rootIndex + 1和中序最右子节点下标midRightIndex
            right = buildTree(preArray, preRightIndex - rightNum + 1, preRightIndex, midArray, rootIndex + 1, midRightIndex);
        }

        //构造完整的二叉树
        return new TreeNode<T>(rootValue, left, right);
    }

    /**
     * @param mid,midLeftIndex,midRightIndex,root
     * @return 中序列数组根节点下标 （-1则没有）
     * @throws null
     * @author Liquid
     * @描述： 在中序遍历序列中，根据先序中的根结点来查找在中序中的位置
     * @date 2019/3/27
     */
    private int getRootIndex(T[] mid, int midLeftIndex, int midRightIndex, T rootValue) {
        for (int i = midLeftIndex; i <= midRightIndex; i++) {
            if (rootValue.equals(mid[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 提供五个让外界调用遍历的方法
     */
    public String preTraverse() {
        threadLocal.set("");
        preOrder(root);
        String preTraverse = threadLocal.get();
        threadLocal.remove();
        return preTraverse;
    }

    public String midTraverse() {
        threadLocal.set("");
        midOrder(root);
        String midTraverse = threadLocal.get();
        threadLocal.remove();
        return midTraverse;
    }

    public String postTraverse() {
        threadLocal.set("");
        postOrder(root);
        String postTraverse = threadLocal.get();
        threadLocal.remove();
        return postTraverse;
    }

    public String bfsTraverse() {
        threadLocal.set("");
        binaryTreeBfs(root);
        String bfsTraverse = threadLocal.get();
        threadLocal.remove();
        return bfsTraverse;
    }

    public String dfsTraverse() {
        threadLocal.set("");
        binaryTreeDfs(root);
        String dfsTraverse = threadLocal.get();
        threadLocal.remove();
        return dfsTraverse;
    }

    /**
     * @param root
     * @return
     * @throws null
     * @author Liquid
     * @描述：先序遍历（根-左-右）
     * @date 2019/3/27
     */
    private void preOrder(TreeNode root) {
        if (root != null) {
            //先从根节点遍历
            threadLocal.set(threadLocal.get() + root.value + " ");
            //递归访问左子树
            preOrder(root.left);
            //递归访问右子树
            preOrder(root.right);

        }
    }

    /**
     * @param
     * @return
     * @throws null
     * @author Liquid
     * @描述：中序遍历
     * @date 2019/3/27
     */
    private void midOrder(TreeNode root) {

        if (root != null) {
            //递归访问左子树
            midOrder(root.left);
            //访问根
            threadLocal.set(threadLocal.get() + root.value + " ");
            //递归访问右子树
            midOrder(root.right);
        }
    }

    /**
     * @param
     * @return
     * @throws null
     * @author Liquid
     * @描述：后序遍历
     * @date 2019/3/27
     */
    private void postOrder(TreeNode root) {
        if (root != null) {
            //递归访问左子树
            postOrder(root.left);
            //递归访问右子树
            postOrder(root.right);
            //访问根
            threadLocal.set(threadLocal.get() + root.value + " ");
        }
    }

    /**
     * @param
     * @return
     * @throws null
     * @author Liquid
     * @描述：深度优先遍历之先序遍历非递归方法
     * @date 2019/3/27
     */
    private void binaryTreeDfs(TreeNode root) {

        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                threadLocal.set(threadLocal.get() + node.value + " ");
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

    }

    /**
     * @param
     * @return
     * @throws null
     * @author Liquid
     * @描述：广度优先遍历
     * @date 2019/3/27
     */
    private void binaryTreeBfs(TreeNode root) {
        if (root != null) {
            //创建一个放TreeNode对象的队列
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            //将树的根节点入队列
            queue.offer(root);
            //循环执行广度优先遍历
            TreeNode node = null;
            TreeNode left = null;
            TreeNode right = null;
            while (!queue.isEmpty()) {
                //将当前的队头元素出队列
                node = queue.remove();
                //访问出队列的节点
                threadLocal.set(threadLocal.get() + node.value + " ");
                left = node.left;
                right = node.right;
                //出队列的节点是否有左孩子，有则将其左孩子入队列
                if (left != null) {
                    queue.offer(left);
                }
                //出队列的节点是否有右孩子，如果右，将其右孩子如队列
                if (right != null) {
                    queue.offer(right);
                }
            }
        }

    }

    public static void main(String[] args) {
        //构造先序遍历序列和中序遍历序列
        Character[] pre = {'A', 'B', 'E', 'K', 'L', 'F', 'D', 'H', 'J'};
        Character[] mid = {'K', 'E', 'L', 'B', 'F', 'A', 'H', 'D', 'J'};

        BinaryTree<Character> binaryTree = new BinaryTree<Character>();
        //根据遍历序列构建二叉树
        binaryTree.build(pre, mid);
        //先序遍历
        String preTraverse = binaryTree.preTraverse();
        System.out.println(preTraverse);
        //中序遍历
        String midTraverse = binaryTree.midTraverse();
        System.out.println(midTraverse);
        //后序遍历
        String postTraverse = binaryTree.postTraverse();
        System.out.println(postTraverse);
        //广度优先遍历
        String bfsTraverse = binaryTree.bfsTraverse();
        System.out.println(bfsTraverse);
        //深度优先遍历
        String dfsTraverse = binaryTree.dfsTraverse();
        System.out.println(dfsTraverse);

    }

    /**
     * @author Liquid
     * @描述：二叉树每一个结点的结构
     * @date 2019/3/27
     */
    private class TreeNode<V> {
        /**
         * 存储的数据
         */
        V value;
        /**
         * 左子节点
         */
        TreeNode left;
        /**
         * 右子节点
         */
        TreeNode right;

        /**
         * @author Liquid
         * @描述：创建节点的构造方法
         * @date 2019/3/27
         */
        public TreeNode(V value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}

