package com.echart.helper;

import java.util.ArrayList;
import java.util.Stack;

public class Test {
    public String replaceSpace(StringBuffer str) {
        char[] arr = str.toString().toCharArray();
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                result += "%20";
            } else {
                result += arr[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        Stack<Integer> stack=new Stack<>();
        while (listNode!=null){
            stack.push(listNode.val);
            listNode=listNode.next;
        }
        ArrayList<Integer> list=new ArrayList<>();
        while (stack!=null){
            list.add(stack.pop());
        }

        return list;
    }
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}


