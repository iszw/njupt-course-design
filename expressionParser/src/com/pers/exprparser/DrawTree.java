package com.pers.exprparser;

/*
 * Created by Su on 01/07/2017.
 */


import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class DrawTree extends Frame {
    Image myImage;
    String temp = " ";
    static String tree = "-(*(a,b),/(+(c,d),-(e,f)))";
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    public static void main() {
        Frame f = new DrawTree();
        f.setBackground(Color.white);
        f.setSize(900, 540);
        f.setVisible(true);
        f.setResizable(false);
    }

    public boolean handleEvent(Event evt) {
        if (evt.id == Event.WINDOW_DESTROY)
            System.exit(0);
        return super.handleEvent(evt);
    }

    public void paint(Graphics g) {
        myImage = toolkit.getImage("Background.png");
        g.drawImage(myImage, 0, 0, this);
        int i = 0, j = 0;
        Mytree bt = new Mytree(tree);
        TreeNode Node, tNode[];
        int cx[], cy[];
        Node = bt.root;
        bt.printLayer();
        int top = -1;
        tNode = new TreeNode[bt.maxDepth];
        cx = new int[tree.length() + 1];
        cy = new int[tree.length() + 1];
        cx[0] = 1;
        cy[0] = 1;

        if (bt.root != null) {
            top++;
            tNode[top] = bt.root;
            while (top > -1) {
                Node = tNode[top];
                top--;
                g.setColor(Color.green);
                g.fillOval(100 + Node.x * 40, Node.layerNo * 50, 40, 20);
                g.setColor(Color.black);
                g.drawString(Node.data, 120 + Node.x * 40,
                        Node.layerNo * 50 + 13);

                cx[i] = 120 + Node.x * 40;
                cy[i] = Node.layerNo * 50;

                i++;
                if (Node.right != null) {
                    top++;
                    tNode[top] = Node.right;
                }
                if (Node.left != null) {
                    top++;
                    tNode[top] = Node.left;
                }
            }

        }
        g.setColor(Color.black);
        top = 0;
        int k = 1, s = 0, a = 0, b = i;

        s = cx[1] - cx[0];
        int tempx[], tx[];
        tx = new int[25];
        tx[0] = cx[0];
        for (i = 0; i < bt.maxDepth - 1; i++) {
            tempx = new int[25];
            for (j = i; j < b; j++) {
                if (cy[i + 1] == cy[j]) {
                    tempx[top] = cx[j];
                    if (tempx[top] != 0) {
                        top++;
                    }
                }
            }
            if (cy[i + 2] - cy[i + 1] != 50) {
                cy[i + 2] = (i + 3) * 50;
                cx[i + 2] = 0;
            }
            a = top;
            if (top != 0) {
                for (; top >= 0; top--) {
                    for (int c = 0; c <= k; c++) {
                        if (tempx[top] + s == tx[c])
                            g.drawLine(tx[c], cy[i] + 20, tempx[top], cy[i + 1]);
                        if (tempx[top] - s == tx[c])
                            g.drawLine(tx[c], cy[i] + 20, tempx[top], cy[i + 1]);
                    }
                    if ((a - top) % 2 == 1) {
                        k--;
                    }
                }

            }
            s = s / 2;
            tx = new int[25];
            k = a;
            tx = tempx;
            top = 0;
            tempx = new int[25];
        }

        String nstemp = "";
        bt.PreOrder(bt.root);
        for (j = 0; j < temp.length() - 1; j++) {
            nstemp = nstemp + temp.charAt(j) + " ";
        }
        nstemp = nstemp + temp.charAt(j);
        temp = temp.substring(0, 0);
        temp = "先序遍历（中左右）: " + nstemp;
        temp = temp.substring(0, temp.length());
        g.drawString(temp, cx[0] - temp.length() * 3, 400);
        temp = temp.substring(0, 0);
        nstemp = nstemp.substring(0, 0);

        bt.InOrder(bt.root);
        for (j = 0; j < temp.length() - 1; j++) {
            nstemp = nstemp + temp.charAt(j) + " ";
        }
        nstemp = nstemp + temp.charAt(j);
        temp = temp.substring(0, 0);
        temp = "中序遍历（左中右）: " + nstemp;
        temp = temp.substring(0, temp.length());
        g.drawString(temp, cx[0] - temp.length() * 3, 450);
        temp = temp.substring(0, 0);
        nstemp = nstemp.substring(0, 0);

        bt.PostOrder(bt.root);
        for (j = 0; j < temp.length() - 1; j++) {
            nstemp = nstemp + temp.charAt(j) + " ";
        }
        nstemp = nstemp + temp.charAt(j);
        temp = temp.substring(0, 0);
        temp = "后序遍历（左右中）: " + nstemp;
        temp = temp.substring(0, temp.length());
        g.drawString(temp, cx[0] - temp.length() * 3, 500);
        temp = temp.substring(0, 0);
        nstemp = nstemp.substring(0, 0);
    }


    class TreeNode {
        String data;
        TreeNode left;
        TreeNode right;
        int layerNo, x;

        public TreeNode(String v) {
            data = v;
            left = null;
            right = null;
        }

        public TreeNode(String v, TreeNode l, TreeNode r) {
            data = v;
            left = l;
            right = r;
        }

        public TreeNode() {
            // TODO Auto-generated constructor stub
        }

    }

    public class Mytree {
        public int cx, cy;
        public int i = 0, j = 0;
        TreeNode root;
        int maxDepth;

        Mytree(String v) {
            insert(v);
        }

        void insert(String v) {
            TreeNode p = new TreeNode(), head;
            TreeNode st[];
            st = new TreeNode[v.length() + 10];
            int top = -1, k = 0, j = 0;
            char ch;
            head = root;
            ch = v.charAt(j);
            if (head == null) {
                head = new TreeNode("" + ch);
                j++;
                ch = v.charAt(j);
            }
            int i = v.length();
            while (j < v.length() - 1)
            {
                switch (ch) {
                    case '(':
                        top++;
                        k = 1;
                        break;
                    case ')':
                        head = st[top - k];
                        top -= k;
                        k = 1;
                        break;
                    case ',':
                        head = st[top];
                        top++;
                        k = 2;
                        break;
                    default:
                        p = new TreeNode("" + ch);
                    {
                        switch (k) {
                            case 1:
                                head.left = p;
                                st[top] = head;
                                head = head.left;
                                break;
                            case 2:
                                head.right = p;
                                st[top] = head;
                                head = head.right;
                                break;
                        }
                    }
                }
                j++;
                ch = v.charAt(j);
            }
            root = st[0];
        }
        public void calcX(TreeNode tn, int x) {
            tn.x = x;

            if (tn.left != null)
                calcX(tn.left, x - 1);

            if (tn.right != null)
                calcX(tn.right, x + 1);
        }

        public void calcX2(TreeNode tn, int x) {
            tn.x = x;
            int dx = (int) Math.pow(2, maxDepth - tn.layerNo - 1);

            if (tn.left != null)
                calcX2(tn.left, x - dx);

            if (tn.right != null)
                calcX2(tn.right, x + dx);
        }

        public void calcDepth(TreeNode tn, int d) {
            tn.layerNo = d;
            if (maxDepth < d)
                maxDepth = d;
            d += 1;
            if (tn.left != null)
                calcDepth(tn.left, d);

            if (tn.right != null)
                calcDepth(tn.right, d);
        }


        public void printLayer() {
            calcDepth(root, 1);
            calcX2(root, (int) Math.pow(2, maxDepth - 1));

        }

        public void PreOrder(TreeNode tn) {
            temp = temp + tn.data;
            if (tn.left != null)
                PreOrder(tn.left);

            if (tn.right != null)
                PreOrder(tn.right);
        }

        public void InOrder(TreeNode tn) {
            if (tn.left != null)
                InOrder(tn.left);

            temp = temp + tn.data;

            if (tn.right != null)
                InOrder(tn.right);
        }


        public void PostOrder(TreeNode tn) {
            if (tn.left != null)
                PostOrder(tn.left);

            if (tn.right != null)
                PostOrder(tn.right);

            temp = temp + tn.data;

        }

    }

}
