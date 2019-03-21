package com.learn.chaptertest.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
作者：Arviiin
原文：https://blog.csdn.net/weixin_38450840/article/details/79803087
 *  题目描述：给出一个正整数n，请给出所有的包含n个左小括号和n个右小括号，使得左小括号和右小括号可以完全匹配。
 * 例如：
 * (())(),()()()都是合法的；
 * ()()(是不合法的。
 * 请按照_字典序_给出所有合法的字符串   //对 首字符排序，如果首字符相同，再按第二个字符排序，以此类推
 * 输入描述：输入为1个正整数
 * 输出描述：输出为所有合法的字符串，用英文逗号隔开
 * 示例：输入 2 输出(()),()()
 * */
public class MathClass {

/*
* 算法思想：给出一个整数n，那么生成的合法括号串的长度是2 * n，那么运用递归去枚举每个位置上的可能出现的字符(，)，枚举的时候要注意，当前位置可以放(，但是能不能放)就要看到目前为止放了多少个)，如果(的个数比)多，那么当前位置就可以放)，这其实和用栈验证括号串的合法性的思想是一样的。
* */

        // currentTotal是当前括号总数，left是当前左括号数，right是当前右括号数
        public static void dfs(int n, List<String> arrayList, int currentTotal, int left, int right, String str) {
            if (currentTotal == 2 * n) {
                arrayList.add(str);
                return;
            }
            if (left < n)
                dfs(n, arrayList, currentTotal + 1, left + 1, right, str + "(");
            if (right < n && left > right)
                dfs(n, arrayList, currentTotal + 1, left, right + 1, str + ")");
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            List<String> arrayList = new ArrayList<String>();

            while (sc.hasNext()) {
                int n = sc.nextInt();
                arrayList.clear();
                dfs(n, arrayList, 0, 0, 0, "");
                for (int i = 0; i < arrayList.size(); i++)
                    System.out.print(arrayList.get(i) + (i != arrayList.size() - 1 ? "," : "\n"));
            }

        }

}
