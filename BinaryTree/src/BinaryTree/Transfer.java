package BinaryTree;

/*
 * Created by su on 2016/12/5.
 */
        import java.util.LinkedList;
        import java.util.List;

/*
 * 把数组的值存入二叉树中，然后进行3种方式的遍历
 */
public class Transfer {

    protected static final String preOrderTraverse = null;

    int[] array0=Input.getArray();
    //	int[] array0={2,4,5,8,6,1,3};
    public static List<Node> nodeList = null;

    /*
     * 内部类
     */
    public static class Node {
        Node leftChild;
        Node rightChild;
        int data;

        Node(int newData) {
            leftChild = null;
            rightChild = null;
            data = newData;
        }
    }

    public void createBinTree() {
        nodeList = new LinkedList<Node>();
        // 将一个数组的值依次转换为Node节点
        for (int nodeIndex = 0; nodeIndex < array0.length; nodeIndex++) {
            nodeList.add(new Node(array0[nodeIndex]));
        }
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array0.length / 2 - 1; parentIndex++) {
            // 左孩子
            nodeList.get(parentIndex).leftChild = nodeList
                    .get(parentIndex * 2 + 1);
            // 右孩子
            nodeList.get(parentIndex).rightChild = nodeList
                    .get(parentIndex * 2 + 2);
        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array0.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).leftChild = nodeList
                .get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (array0.length % 2 == 1) {
            nodeList.get(lastParentIndex).rightChild = nodeList
                    .get(lastParentIndex * 2 + 2);
        }
    }

    /*
     * 先序遍历
     */
    public static void preOrderTraverse(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    /*
     * 中序遍历
     */
    public static void inOrderTraverse(Node node) {
        if (node == null)
            return;
        inOrderTraverse(node.leftChild);
        System.out.print(node.data + " ");
        inOrderTraverse(node.rightChild);
    }

    /*
     * 后序遍历
     */
    public static void postOrderTraverse(Node node) {
        if (node == null)
            return;
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.data + " ");
    }

//	public static void main(String[] args) {
//		transfer binTree = new transfer();
//		binTree.createBinTree();
//		Node root = nodeList.get(0);
//
//		System.out.println("先序遍历：");
//		preOrderTraverse(root);
//		System.out.println();
//
//		System.out.println("中序遍历：");
//		inOrderTraverse(root);
//		System.out.println();
//
//		System.out.println("后序遍历：");
//		postOrderTraverse(root);
//	}

}

