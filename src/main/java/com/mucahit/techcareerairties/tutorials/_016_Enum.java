package com.mucahit.techcareerairties.tutorials;


public enum _016_Enum {
  SMALL,MEDIUM,LARGE
}

enum permission {
  ADMIN,WRITER,USER
}

class Deneme{
  public static void main(String[] args) {
    System.out.println(_016_Enum.MEDIUM);
    System.out.println(_016_Enum.MEDIUM.toString());
    System.out.println(_016_Enum.MEDIUM.ordinal());
  }
}
