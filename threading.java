import java.sql.SQLOutput;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threading {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String s1 = "A";
        String s2 = "B";
        String s3 = "C";


        ExecutorService exe = Executors.newFixedThreadPool(3);    // call 3 threads
        for(int i = 0; i < 10; ++i) {
            exe.submit(print(s1)).get();        // pass thread to a callable pool
            exe.submit(print(s2)).get();
            exe.submit(print(s3)).get();
        }
        exe.shutdown();

    }
    public static Callable<String> print(String msg) {
        return () -> {
            System.out.print(msg);
            return msg;     // return msg
        };
    }
}








//write a program with three threads, each of them print "A", "B", "C"
// only and the final result should be "ABCABCABCABCABCABCABCABCABCABC"


