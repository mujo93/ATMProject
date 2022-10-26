package com.mucahit.techcareerairties.tutorials;

public class _013_Methods {

    public static void voidliParametresiz(){
        System.out.println("voidli Parametresiz");
    }

    public static void voidliParametreli(String name){
        System.out.println("voidli Parametreli "+name);
    }

    public static String voidsizParametresiz(){
        return "voidsiz Parametresiz";
    }

    public static String voidsizParametrli(int number){
        return "voidsiz Parametrli "+number;
    }

    public static void main(String[] args) throws  HamitMizrakException {
        voidliParametresiz();
        voidliParametreli("Akçadağ");
        String data=  voidsizParametresiz();
        System.out.println(data);
        String data2=  voidsizParametrli(44);
        System.out.println(data2);
    }
}
