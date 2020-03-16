package com.springboot.multithread.chapter1_threadknowlegde;


/*
* 二叉搜索树的实现
*  特点：
*  1.如果它的左子树不为空，则左子树上结点的值都小于根结点；
*  2.如果它的右子树不为空，则右子树上结点的值都大于根结点；
*  3.子树同样也要遵循以上两点；
*  只要一棵树是二叉搜索树，那么它的中序遍历一定是有序的
*      左  根（要输出） 右
*
*
*  如果下面的数组中以0为根构建二叉搜索树，那就变成了一个链表；时间复杂度变成了 O(n);
*  解决：
*   AVL树：平衡二叉树。  左旋 右旋 红黑树是平衡二叉树里的一种；
*
*  
* */
public class Test14 {
    public static void main(String[] args) {

        int data[] = {5,6,8,3,4,0};
        BinarySearchTree root = new BinarySearchTree(data[0]);
        for (int i = 1; i < data.length; i++) {
            root.insert(root,data[i]);
        }
        root.in(root);
    }
}

class BinarySearchTree{
    int data;
    BinarySearchTree left;
    BinarySearchTree right;


    public BinarySearchTree(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public void insert(BinarySearchTree root,int data){
        if(root.data < data){  //将data放到右边节点
            if(root.right == null){  //右边没有值 直接插入
                root.right = new BinarySearchTree(data);
            }else {
                insert(root.right,data);
            }
        }else{
            if(root.left == null) {
                root.left = new BinarySearchTree(data);
            }else{
                insert(root.left,data);
            }
        }
    }

    public void in(BinarySearchTree root){
        if(root!=null){
            in(root.left);
            System.out.println(root.data + " ");
            in(root.right);
        }
    }

}
