package jianzhi;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class day1 {
    /**
     * 剑指 Offer 09. 用两个栈实现队列
     */
    class CQueue {

        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public CQueue() {
            stack1 = new LinkedList<Integer>();
            stack2 = new LinkedList<Integer>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (stack2.isEmpty()) {
                return -1;
            } else {
                int value = stack2.pop();
                return value;
            }
        }
    }

    /**
     * 剑指 Offer 30. 包含min函数的栈
     */
    class MinStack {
        Stack<Integer> A, B;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            A = new Stack<>();
            B = new Stack<>();
        }

        public void push(int x) {
            A.add(x);
            if(B.empty()||B.peek()>=x){
                B.add(x);
            }
        }

        public void pop() {
            if(A.pop().equals(B.peek())){
                B.pop();
            }
        }

        public int top() {
            return A.peek();
        }

        public int min() {
            return B.peek();
        }
    }
}
