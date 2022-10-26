package com.mucahit.techcareerairties.tutorials;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class _011_ExceptionHandling {
    public static void main(String[] args) throws ArithmeticException, IOException {
        try{
            int number=4/0;
            System.out.println(number);
        }catch (ArithmeticException ai){
           // ai.printStackTrace();
        }finally {
            System.out.println("db.close");
        }
       System.out.println("son satır");
    }
}
