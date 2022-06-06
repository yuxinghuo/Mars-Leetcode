package org.jeecg;

import java.util.Arrays;
import java.util.Collections;

public class huawei01 {
    /**
     * 连续字母长度
     *
     * @param str
     * @return
     */
    public static int huaweilianxuzimulength(String str, int k) {
        //先统计每个字母的长度
        Integer a[] = new Integer[26];
        for (int i = 0; i < 26; i++) {
            a[i] = 0;
        }
        int p = 0, q = 1;
        while (p <= q && p < str.length() && q < str.length()) {
            if (str.charAt(p) == str.charAt(q)) {
                q++;
            } else {
                if (q - p > a[str.charAt(p) - 'A']) {
                    a[str.charAt(p) - 'A'] = q - p;
                }
                p = q;
                q++;
            }
        }
        a[str.charAt(p) - 'A'] = q - p;
        Arrays.sort(a, Collections.reverseOrder());
        return a[k-1];
    }
    public static void main(String[] args) {
        System.out.println(huaweilianxuzimulength("ABC", 1));
    }
}
