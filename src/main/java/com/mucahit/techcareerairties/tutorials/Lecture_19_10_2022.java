package com.mucahit.techcareerairties.tutorials;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lecture_19_10_2022 {


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("Adana");
        list.add("Canakkale");
        list.add("Mersin");
        list.add("Duzce");
        list.add("Balikesir");
        list.add("Trabzon");
        list.add("Gumushane");

        //list.forEach((element) -> System.out.println(element));
        //streamSortedLargeToSmallMethod();
        //streamLimitMethod();
        //streamDistinctMethod();
        //streamFilterMethod();
        //ListOddNumbers();
        streamMapMethod();
    }


    public static List<String> collectionListData() {
        List<String> strList = new ArrayList<>();
        strList.add("ankara");
        strList.add("malatya");
        strList.add("istanbul");
        strList.add("eskişehir");
        strList.add("sivas");
        strList.add("malatya");
        strList.add("elazığ");
        strList.add("malatya");
        return strList;
    }

    public static void streamSortedSmallToLargeMethod(){
        List<String> list = collectionListData().stream().sorted().collect(Collectors.toList());
     }

    public static void streamSortedLargeToSmallMethod(){

        collectionListData()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .forEach((temp)->{
                    System.out.println(temp+" ");
                });
    }

    public static void streamLimitMethod(){
        collectionListData().
                stream().
                sorted().
                limit(5).
                collect(Collectors.toList()).
                forEach((temp)-> System.out.print(temp.toUpperCase()+" "
                ));
    }

    public static void streamDistinctMethod(){
        collectionListData().
                stream().
                sorted().
                distinct().
                limit(3).
                collect(Collectors.
                        toList()).
                        forEach((temp) -> {
                            System.out.println(temp.toUpperCase());
        });
    }

    public static void streamCountMethod(){
    }

    public static void streamFilterMethod(){
        long counter= collectionListData().
                stream().
                filter((city) -> !city.equalsIgnoreCase(("Malatya"))).
                 count();

        System.out.println((int)counter);

    }

    //random 1-10 arasinda 8 tane sayi olsun ve bu sayilardan cift olanlari listeleyelim

    public static void ListOddNumbers(){

        List<Integer> mylist = new ArrayList<>();
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);
        mylist.add(4);
        mylist.add(5);
        mylist.add(6);

        mylist.stream().
                filter((number) -> number%2==0).
                forEach(number -> System.out.println(number));

    }

    //arrayin her bir elemanini bir artir.

    public static void streamMapMethod(){
       List<String> list= collectionListData().
                stream().
                map((temp)->temp+1).
                collect(Collectors.toList());

        System.out.println(list);

    }



}
