package com.mucahit.techcareerairties.tutorials;

import java.util.Base64;
import java.util.Scanner;

public class _017_DecoderEncoder {

    public static String getUser(){
        Scanner klavye=new Scanner(System.in);
        System.out.println("Lütfen birşeyler giriniz");
        return klavye.nextLine();
    }

    //şifreledik
    public static String getEncoder(String data){
        Base64.Encoder encoder=Base64.getEncoder();
        return encoder.encodeToString(data.getBytes());
    }

    //şifre çözdük
    public static String getDecoder(String data){
        Base64.Decoder decoder=Base64.getDecoder();
        return new String(decoder.decode(data));
    }

    public static void main(String[] args) {
        String data=getUser();
        System.out.println("Kullanıcı girdiği data: "+data);
        String encode= getEncoder(data);
        System.out.println( encode);

        System.out.println(getDecoder(encode));
    }

}
