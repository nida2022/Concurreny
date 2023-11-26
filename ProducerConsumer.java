package Threads;

import java.util.LinkedList;

public class ProducerConsumer {

    static LinkedList<Integer> buffer = new LinkedList<>();
    static int capacity = 5;
    static int value = 0;

    public static void main(String[] args) {

        Object lock = new Object();

        Runnable pr = () -> {
            int i = 0;
            synchronized (lock) {
                while(i < 10) {
                    while (buffer.size() >= capacity) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    buffer.add(value);
                    System.out.println(Thread.currentThread().getName() + " " + value);
                    value++;
                    lock.notifyAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    i++;
                }

            }
        };

        Runnable co = () -> {
            int i = 0;
            synchronized (lock) {
                while (i < 10) {
                    while (buffer.size() == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    int item = buffer.removeFirst();
                    System.out.println(Thread.currentThread().getName() + " " + item);
                    lock.notifyAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    i++;
                }
            }
        };

        Thread producer = new Thread(pr);
        Thread consumer = new Thread(co);
        producer.setName("Producer");
        consumer.setName("Consumer");

        producer.start();
        consumer.start();
    }
}
