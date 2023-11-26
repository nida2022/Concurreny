package Threads;

import java.util.stream.IntStream;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = ()->{
            IntStream.range(1,500).forEach(System.out::println);

        };

        Runnable r1 = ()->{
            IntStream.range(500,1000).forEach(System.out::println);
        };

        Thread t1 = new Thread(r);
        t1.start();
        t1.join();
        Thread t2 = new Thread(r1);


        t2.start();


        t2.join();

    }



}
