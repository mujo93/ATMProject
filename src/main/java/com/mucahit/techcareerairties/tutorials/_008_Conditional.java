package com.mucahit.techcareerairties.tutorials;

public class _008_Conditional {
    public static void main(String[] args) {
        int number = 5;
        if (number == 1) {
            System.out.println("sayı 1");
        } else if (number == 2) {
            System.out.println("sayı 2");
        } else if (number == 3) {
            System.out.println("sayı3 ");
        } else if (number == 4) {
            System.out.println("sayı 4");
        } else if (number == 5) {
            System.out.println("sayı 5");
        } else {
            System.out.println("1<=sayi<=5 dışında bir sayıdır.");
        }

        switch (number) {
            case 1:
                System.out.println("sayı 1");
                break;
            case 2:
                System.out.println("sayı 2");
                break;
            case 3:
                System.out.println("sayı 3");
                break;
            case 4:
                System.out.println("sayı 4");
                break;
            case 5:
                System.out.println("sayı 5");
                break;
            default:
                System.out.println("1<=sayi<=5 dışında bir sayıdıt.");
                break;
        }

    }
}
