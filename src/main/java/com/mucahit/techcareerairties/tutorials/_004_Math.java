package com.mucahit.techcareerairties.tutorials;
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class _004_Math {
    public static void main(String[] args) {
        System.out.println(Math.max(2,5));
        System.out.println(Math.floor(Math.random()*4+1));
        Random random=new Random();
        int number=random.nextInt(4)+1;
        System.out.println(number);
    }
}
