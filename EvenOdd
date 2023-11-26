package Threads;

public class EvenOdd {

    static int i = 1;
    public static void main(String[] args) {

        EvenOdd eo = new EvenOdd();


        Runnable e = ()->{
            while(i < 20) {
                synchronized (eo) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName()+ " "+i  );
                        i++;
                        eo.notify();
                    } else {
                        try {
                            eo.wait();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }

        };

        Runnable o = ()->{
            while(i < 20){
                synchronized (eo) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName()+ " "+i  );
                        i++;
                        eo.notify();
                    } else {
                        try {
                            eo.wait();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        };

        Thread t1 = new Thread(e);
        t1.setName("Even");
        Thread t2 = new Thread(o);
        t2.setName("Odd");
        t1.start();
        t2.start();
    }
}
