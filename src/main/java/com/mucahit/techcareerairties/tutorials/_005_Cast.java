package com.mucahit.techcareerairties.tutorials;

public class _005_Cast {
    public static void main(String[] args) {
       float f1=14.53f;
       long l2=15515151515515L;

       int primitive=44;
       Integer wrapper=primitive;

       String data="44";
       int number1=Integer.valueOf(data);
       int number2=Integer.parseInt(data);
        System.out.println(number1+16);
        System.out.println(number2+16);

        int value=15;
        String data2=String.valueOf(value);
        String data3=Integer.toString(value);
    }
}
