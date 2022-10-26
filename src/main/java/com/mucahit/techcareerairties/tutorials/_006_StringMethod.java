package com.mucahit.techcareerairties.tutorials;

public class _006_StringMethod {
    public static void main(String[] args) {
        String v1 = "jsp", v2 = "jsf";
        System.out.println(v1 + v2);
        System.out.println(v1.concat(v2));

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(v1).append(v2);
        String formatToString=stringBuilder.toString();
        System.out.println(formatToString);

        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(v1).append(v2);
        String formatToString2=stringBuffer.toString();
        System.out.println(formatToString2);

    }
}
