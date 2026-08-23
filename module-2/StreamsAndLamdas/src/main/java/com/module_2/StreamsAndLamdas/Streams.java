package com.module_2.StreamsAndLamdas;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        List<String> fruits = List.of("Banana","Apple","PineApple","Orange","Kiwi");
        Map<String,Integer> list=fruits.stream()
                .sorted()
                .collect(
                        Collectors.toMap(
                                fruit -> fruit,
                                fruit ->fruit.length()
                        )
                );
//        System.out.println(list);

//        Stream stream = fruits.stream();
//        stream.forEach(
//                fruit-> System.out.println(fruit)
//        );
        List<Integer> list1 =fruits.stream()
                .filter(fruit -> fruit.length()<5)
                .map(fruit -> fruit.length())
                .collect(Collectors.toList());
        System.out.println(list1);

    }
}
