package BinaryTree;

/*
 * Created by su on 2016/12/5.
 */
/*
 * 输入一组数据保存到数组中
 */
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;
        import javax.swing.JTextField;

import BinaryTree.Transfer.Node;

public class Input {
    protected static final String JOptionpan = null;
    private static  int[] array;
    private static int length1;

    private  static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("请输入数字:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(100,200,100,50);
        panel.add(userLabel);

        /*JLabel userLabel0 = new JLabel("arr[0]");
        userLabel0.setBounds(500,200,10,50);
        panel.add(userLabel);
        JLabel userLabel1 = new JLabel(arr[1]);
        userLabel1.setBounds(500,400,10,50);
        panel.add(userLabel);*/


        JTextField jtf = new JTextField(20);
        jtf.setBounds(200,200,500,50);
        panel.add(jtf);
        JButton Button1 = new JButton("建树");
        Button1.setBounds(300, 300, 100, 50);
        panel.add(Button1);
        Button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                //获取用空格分隔开的输入值
                String string= jtf.getText().trim();
                //将获取到的字符串分隔成字符串数组
                String arr[]=string.split(" ");
                for (int j=0;j<arr.length;j++){
                    System.out.println(arr[j]);
                }
                //将String类型的数组arr转化成整形数组arra
                int []arra = new int[arr.length];
                for (int i=0; i<arr.length; i++) {
                    arra[i]=Integer.parseInt(arr[i]);}
                length1=arra.length;
                array=arra;
                //JOptionPane.showMessageDialog((JButton)e.getSource(),array);
                for(int n=0;n<length1;n++){
                    System.out.println(array[n]);
                }
                Transfer binTree = new Transfer();
                binTree.createBinTree();
                Node root = Transfer.nodeList.get(0);

                System.out.println("先序遍历：");
                Transfer.preOrderTraverse(root);
                System.out.println();

                System.out.println("中序遍历：");
                Transfer.inOrderTraverse(root);
                System.out.println();

                System.out.println("后序遍历：");
                Transfer.postOrderTraverse(root);


            }});
        //System.out.println(length1);


        JButton Button2 = new JButton("搜索");
        Button2.setBounds(500, 300, 100, 50);
        panel.add(Button2);
        Button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String string0= jtf.getText().trim();
                int s=Integer.parseInt(string0);

                if (s==array[0]){
                    JOptionPane.showMessageDialog((JButton)e.getSource(),"找到了！");}
                else{
                    JOptionPane.showMessageDialog((JButton)e.getSource(),"找不到！");}
            }});
    }

    public static int[] getArray(){
        return array;
    }


    public static void main(String[] args){
        JFrame frame=new JFrame("搜索树操作程序");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        array=new int[5];
        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);


        // 设置界面可见
        frame.setVisible(true);

    }




}
