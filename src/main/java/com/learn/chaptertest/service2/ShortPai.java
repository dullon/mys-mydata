package com.learn.chaptertest.service2;

/**
 * 写个排序
 */
public class ShortPai {


    public static int[] shortPai(int [] in){
        if(in.length == 0 || in ==null){
            new Throwable("入参错误，数组为0 或者 为空");
        }
        int length = in.length -1;
        //单层递归循环
        for (int i = 0 ; i < length ; i++ ){
            System.out.print(i);
            change(in,i,i+1);
            //递归判断
            if (i == length - 1){
                i = - 1;
                length --;
            }
        }
        return in;
    }
    public static void change(int [] in , int i ,int j){
        if (in[i] > in[j]){
            int temp = in[i];
            in[i] = in[j];
            in[j] = temp;
        }
    }
    public static void main(String [] args){
        int [] in = {22,4,55,3,8,10,67,21,19,2,11};
        shortPai(in);
        for (int i = 0 ; i < in.length ; i++ ){
            //System.out.print(in[i]+" ");
        }
    }
}
