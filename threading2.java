
import java.util.ArrayList;
import java.util.*;
public class threading2 {
    ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        ProCon pc = new ProCon();

        // producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start
        t1.start();
        t2.start();

        // Join
        t1.join();
        t2.join();

    }
}
     class ProCon {
        List<Integer> list = new ArrayList<>();
        int size = 3;

        public void producer() throws InterruptedException {
            int counter = 0;
            while (true){
                synchronized (this) {

                    while (list.size() == this.size) {  // wait for other thread to execute
                        System.out.println("Waiting from consumer");
                        this.wait();
                    }
                    // add new element on the list
                    System.out.println("Producer: " + counter);
                    list.add(++counter);
                    // notify other threads
                    notify();


                    Thread.sleep(1000);
                }
            }
        }

        public void consumer() throws InterruptedException {
            while (true) {
                synchronized (this) {
                    while (list.size() == 0) { // wait for other thread to execute
                        System.out.println("Waiting on producer");
                        this.wait();
                    }
                    int num = list.remove(list.size()-1);
                    //list.removeAll(list);
                    System.out.println("Consumer: " + num);
                    this.notify();

                    Thread.sleep(1000);
                }
            }
        }
    }



