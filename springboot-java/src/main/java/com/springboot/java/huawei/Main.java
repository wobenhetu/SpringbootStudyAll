package com.springboot.java.huawei;

import java.util.Scanner;

/*
计算字符串最后一个单词的长度，单词以空格隔开

输入描述:
一行字符串，非空，长度小于5000。

输出描述:
整数N，最后一个单词的长度
* */
import java.util.*;
public class Main{
    public static int lengthOfLast(String str) {
        String[] s =str.split(" ");
        return s[s.length-1].length();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String str = scan.nextLine();
            System.out.println(lengthOfLast(str));
        }
    }
}