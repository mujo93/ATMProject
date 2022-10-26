package com.mucahit.techcareerairties.tutorials;

public class _009_Loop {
    public static void main(String[] args) {
        //break:    döngü kırmak
        //return:   method kırma
        //continue: sadece o alanda es geçip devam ediyor.

        // 3tane loop
        //for
        for(int i=0; i<=10; i+=1){
            System.out.print(i+" ");
        }
        //sonsuz for(;;){}
        System.out.println("\n*******************");

        //while
       int k=0;
        while(k<=10){
            System.out.print(k+" ");
            k++;
        }
        // sonsuz while(true){}
        System.out.println("\n*******************");
        //do-while
        int m=0;
        do{
            System.out.print(m+" ");
            m++;
        }while(m<=10);

    }
}
