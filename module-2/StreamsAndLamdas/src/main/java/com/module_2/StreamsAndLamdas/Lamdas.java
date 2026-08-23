package com.module_2.StreamsAndLamdas;

public class Lamdas {
    public static void main(String[] args) {
        Greetings greetings = (name)->
        {
            return "hello world "+name;
        };
        System.out.println(greetings.greet("Likitha"));

    }

}
//class Greet implements Greetings
//{
//    @Override
//    public String greet(String name) {
//       return "Hello world! "+name;
//    }
//}
interface Greetings{
    String greet(String name);
}
