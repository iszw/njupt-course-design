package com.pers.exprparser;

/*
 * Created by Su on 28/06/2017.
 */

import java.util.LinkedList;


public class Polish {

    private int priority(char c){
        if(c == '*' || c== '/')
            return 2;
        else if(c == '+' || c== '-')
            return 1;
        else
            return 0;
    }

    private boolean leftPirorityIsNotLess(Character c1,char c2){
        if(c1 == null)return false;
        return priority(c1)>=priority(c2);
    }
    private boolean isLetter(char c){
        return 'a' <= c && c <= 'z';
    }
    private boolean isLeftBracket(char c){
        return c == '(';
    }
    private boolean isRightBracket(char c){
        return c == ')';
    }
    private boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public String parse(String str){
        class Stack<T>{
            private LinkedList<T> list = new LinkedList<T>();

            public void push(T t){
                list.addLast(t);
            }

            public T pop(){
                return list.removeLast();
            }

            public T top(){
                return list.peekLast();
            }

            public boolean isEmpty(){
                return list.isEmpty();
            }
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] cs = str.toCharArray();

        for(int i=0; i<cs.length; ++i){
            char c = cs[i];
            //遇到操作数，直接输出，添加到后缀表达式
            if( isLetter(c)){
                sb.append(c);
            }
            //遇到左括号，直接入栈
            else if( isLeftBracket(c)){
                stack.push(c);
            }
            //遇其他运算符：加减乘除，弹出所有优先级大于或等于该运算符的栈顶元素，然后将该元素入栈
            else if( isOperator(c)){
                while( leftPirorityIsNotLess(stack.top(), c)){
                    sb.append( stack.pop());
                }
                stack.push(c);
            }
            //遇右括号，执行出栈，直至弹出的左括号，括号不输出
            else if( isRightBracket(c)){
                while( !isLeftBracket( stack.top())){
                    sb.append(stack.pop());
                }
                stack.pop();
            }
        }
        //最终将栈中元素依次出栈
        while( !stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}