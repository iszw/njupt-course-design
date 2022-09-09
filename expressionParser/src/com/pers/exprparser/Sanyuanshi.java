package com.pers.exprparser;

/*
 * Created by Su on 29/06/2017.
 */

import java.util.LinkedList;

public class Sanyuanshi {

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

    private boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public String tosanyuan(String str){
        class Stack<T>{
            private LinkedList<T> list = new LinkedList<T>();

            public int size(Stack<Character> T){
                return list.size();
            }

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
        int x = 1;


        for(int i=0; i<cs.length; ++i){
            char c = cs[i];
            if( isLetter(c)){
                stack.push(c);
            }

            if(isOperator(c)){
                if(stack.size(stack)>= 2){
                    sb.append(x);
                    sb.append(" ");
                    x++;
                    sb.append(c);
                    sb.append(" ");
                    sb.append(stack.pop());
                    sb.append(" ");
                    sb.append(stack.pop());
                    sb.append("\n");
                }
                else if(stack.size(stack) == 1){
                    sb.append(x);
                    sb.append(" ");
                    x++;
                    sb.append(c);
                    sb.append(" ");
                    sb.append(stack.pop());
                    sb.append(" ");
                    sb.append(x-2);
                    sb.append("\n");
                }
                else if(stack.size(stack) == 0){
                    if (c == '+' || c == '-'){
                        sb.append(x);
                        sb.append(" ");
                        x++;
                        sb.append(c);
                        sb.append(" ");
                        sb.append(x-5);
                        sb.append(" ");
                        sb.append(x-2);
                        sb.append(" ");
                        sb.append("\n");
                    }
                    else if(c == '*' || c == '/'){
                        sb.append(x);
                        sb.append(" ");
                        x++;
                        sb.append(c);
                        sb.append(" ");
                        sb.append(x-3);
                        sb.append(" ");
                        sb.append(x-2);
                        sb.append(" ");
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }

}