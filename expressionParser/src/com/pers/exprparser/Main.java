package com.pers.exprparser;

/*
 * Created by Su on 27/06/2017.
 */

import com.sun.deploy.panel.JSmartTextArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

    private static String str1;

    public static void placeComponents(JFrame frame){

        frame = new JFrame("expressionParser");
        frame.setSize(900,620);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        frame.add(panel1);
        panel1.setVisible(true);
        panel1.setLayout(null);
        panel1.setBounds(0,0,900,100);
        panel1.setBorder(BorderFactory.createEtchedBorder());

        JPanel panel2 = new JPanel();
        frame.add(panel2);
        panel2.setVisible(false);
        panel2.setLayout(null);
        panel2.setBounds(0,100,900,100);
        panel2.setBorder(BorderFactory.createEtchedBorder());

        JPanel panel3 = new JPanel();
        frame.add(panel3);
        panel3.setVisible(false);
        panel3.setLayout(null);
        panel3.setBounds(0,200,450,400);
        panel3.setBorder(BorderFactory.createEtchedBorder());

        JPanel panel4 = new JPanel();
        frame.add(panel4);
        panel4.setVisible(false);
        panel4.setLayout(null);
        panel4.setBounds(0,200,400,400);
        panel4.setBorder(BorderFactory.createEtchedBorder());


        //输入一个表达式
        JLabel jl1 = new JLabel("输入表达式   S =");
        panel1.add(jl1);
        jl1.setBounds(90,20,100,60);

        JTextField jt1 = new JTextField(1);
        panel1.add(jt1);
        jt1.setBounds(200,20,400,60);

        JButton bt1 = new JButton("startParser");
        panel1.add(bt1);
        bt1.setBounds(700, 20, 100, 60);

        //逆波兰表示
        JLabel jl2 = new JLabel("逆波兰表示");
        panel2.add(jl2);
        jl2.setBounds(100,20,100,60);

        JLabel jt2 = new JLabel();
        panel2.add(jt2);
        jt2.setBounds(200,20,450,60);

        JButton bt2 = new JButton("drawTree");
        panel2.add(bt2);
        bt2.setBounds(700, 20, 100, 60);

        //三元式表示
        JLabel jl3 = new JLabel("三元式表示");
        panel3.add(jl3);
        jl3.setBounds(100,75,100,50);

        JTextArea jt3 = new JSmartTextArea();
        panel3.add(jt3);
        jt3.setBounds(200,150,200,100);

        //四元式表示
        JLabel jl4 = new JLabel("四元式表示");
        panel4.add(jl4);
        jl4.setBounds(500,275,100,50);

        JTextArea jt4 = new JSmartTextArea();
        panel4.add(jt4);
        jt4.setBounds(600,350,200,100);

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stri = jt1.getText().trim();
                str1 = stri;


                Polish input1 = new Polish();
                String str2 = input1.parse(str1);

                jt2.setText("S = "+str2);
                panel2.setVisible(true);


                Sanyuanshi input2 = new Sanyuanshi();
                String str3 = input2.tosanyuan(str2);

                jt3.setText(str3);
                panel3.setVisible(true);


                Siyuanshi input3 = new Siyuanshi();
                String str4 = input3.tosiyuan(str2);

                jt4.setText(str4);
                panel4.setVisible(true);

            }
        }

        );

        //点击button2来画树
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawTree.main();
            }
        });
    }

    public static void main(String[] args) {
	// write your code here
        JFrame frame = new JFrame();
        placeComponents(frame);
    }
}
