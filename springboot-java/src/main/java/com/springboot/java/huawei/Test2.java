package com.springboot.java.huawei;

import java.util.Scanner;

public class Test2 {

    public static int getCount(String str,char c){
        int count = 0;
        if(str != null && str.length() > 0){
            for(int i = 0;i < str.length();i++){
                if(c == str.charAt(i)){
                    count++;
                }
            }
        }else{
            count = 0;
        }
        return count;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String str = s.next();
        char c = s.next().charAt(0);
        int i = getCount(str,c);
        System.out.println(i);
    }
}
