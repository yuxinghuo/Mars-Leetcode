package org.jeecg;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.intern.InternUtil;
import cn.hutool.json.JSONUtil;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class java {
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

    class Node {
        int val;
        Node next, random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while (cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while (cur != null) {
            if (cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }


    /**
     * 剑指 Offer 05. 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {

        StringBuilder string = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                string.append("%20");
            } else {
                string.append(c);
            }

        }
        return string.toString();
    }


    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder res = new StringBuilder();
        for (int i = n; i < s.length(); i++)
            res.append(s.charAt(i));
        for (int i = 0; i < n; i++)
            res.append(s.charAt(i));
        return res.toString();
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                if (nums[low] == nums[high]) {
                    return high - low + 1;
                }
                if (nums[low] < target) {
                    low++;
                }
                if (nums[high] > target) {
                    high--;
                }
            }

        }
        return 0;
    }

    public int search1(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    int helper(int[] nums, int tar) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] <= tar) i = m + 1;
            else j = m - 1;
        }
        return i;
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     */
    public static int missingNumber(int[] nums) {
        int i = 0, j = nums.length;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == mid) {
                i = mid;
            }
            if (nums[mid] > mid) {
                j = mid;
            } else if (nums[mid] < mid) {
                i = mid;
            }
        }
        return i;
    }

    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        Arrays.sort(nums);
        int left = 1, right = nums.length - 1;
        int result = 0;
        int count = 0;
        for (int i = 0; i < nums.length - 2; ) {
            if (i == 0) {
                result = nums[i] + nums[left] + nums[right];
                if (result - target > 0) {
                    right--;
                } else {
                    left++;
                }
                count = Math.abs(result - target);
            }
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int current = sum - target;
                if (current > 0) {
                    right--;
                } else {
                    left++;
                }
                if (Math.abs(current) < count) {
                    result = sum;
                    count = Math.abs(current);
                }

            }
            i++;
            left = i + 1;
            right = nums.length - 1;
        }
        return result;
    }

    public static int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = -1;
        }
        //正向
        if (c == s.charAt(0)) {
            result[0] = 0;
        }
        for (int i = 1; i < s.length(); i++) {
            if (c == s.charAt(i)) {
                result[i] = 0;
            } else if (result[i - 1] != -1) {
                result[i] = result[i - 1] + 1;
            }
        }
        System.out.println(Arrays.toString(result));
        //反向
        for (int i = s.length() - 2; i >= 0; i--) {
            if ((result[i] != -1 && result[i + 1] + 1 < result[i]) || result[i] == -1) {
                result[i] = result[i + 1] + 1;
            }
        }
        return result;
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        getParenthesis(res, "", n, n);
        return res;
    }

    private static void getParenthesis(List<String> res, String str, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left == right) {
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(res, str + "(", left - 1, right);
        } else if (left < right) {
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if (left > 0) {
                getParenthesis(res, str + "(", left - 1, right);
            }
            getParenthesis(res, str + ")", left, right - 1);
        }
    }

    public int lengthLongestPath(String input) {
        String s[] = input.split("\n");
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ans = 0;
        for (int i = 0; i < s.length; i++) {
            int lev = level(s[i]);
            while (stack.size() > lev + 1) {
                stack.pop();
            }
            int l = s[i].length() - lev + stack.peek();
            if (s[i].contains(".")) {
                ans = Math.max(ans, l + lev);
            } else {
                stack.push(l);
            }
        }
        return ans;
    }

    public int level(String path) {
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) != '\t') {
                return i;
            }
        }
        return -1;
    }

    public static int maxRotateFunction(int[] nums) {
        int[] numss = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            numss[i] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            numss[nums.length + i] = nums[i];
        }
        int max = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            max = max + j++ * nums[i];
        }
        for (int i = 1; i < nums.length; i++) {
            int count = 0;
            int q = 0;
            for (int k = 0; k < nums.length; k++) {
                count = count + q++ * numss[i + k];
            }
            max = count > max ? count : max;
        }
        return max;
    }

    public static String toGoatLatin(String sentence) {
        String[] strings = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            result.append(del(strings[i], i)).append(" ");
        }
        return result.substring(0, result.length() - 1);
    }

    private static String del(String s, int i) {
        char c = s.charAt(0);
        if (idV(c)) {
            s = s + "ma";
        } else {
            s = s.substring(1, s.length()) + c + "ma";
        }
        for (int j = 0; j < i + 1; j++) {
            s = s + "a";
        }
        return s;
    }

    private static boolean idV(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            return true;
        }
        return false;
    }

    public static int binaryGap(int n) {
        String a = Integer.toBinaryString(n);
        int count = 0;
        if (n < 4) {
            return 0;
        }
        int last = -1;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '1') {
                last = i;
            }
        }
        if (last == -1) {
            return 0;
        }
        for (int i = last; i < a.length(); i++) {
            if (a.charAt(i) == '1') {
                count = i - last > count ? i - last : count;
                last = i;
            }
        }
        return count;
    }
}
