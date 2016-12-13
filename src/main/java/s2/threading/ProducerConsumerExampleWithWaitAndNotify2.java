package s2.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by russl on 12/7/2016.
 */
public class ProducerConsumerExampleWithWaitAndNotify2
{
    public static void main(String[] args)
    {
        int MAX_CAPACITY = 10;
        BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(MAX_CAPACITY);

//        List<Integer> taskQueue = new ArrayList<Integer>();

        Thread tProducer = new Thread(new Producer2(taskQueue), "Producer");
        Thread tConsumer1 = new Thread(new Consumer2(taskQueue), "Consumer1");
        Thread tConsumer2 = new Thread(new Consumer2(taskQueue), "Consumer2");
        tProducer.start();
        tConsumer1.start();
        tConsumer2.start();
        System.out.println(" done");
    }
}
