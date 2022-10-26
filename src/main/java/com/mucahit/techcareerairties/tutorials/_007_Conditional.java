package com.mucahit.techcareerairties.tutorials;

public class _007_Conditional {
    public static void main(String[] args) {
       int number=5;
       if(number==1){
           System.out.println("sayı 1");
       }else{
           System.out.println("sayı 1 farklıdır");
       }

       String ternary= (number==1) ?"sayı 1" :"sayı 1 farklıdır" ;
        System.out.println(ternary);
    }
}
