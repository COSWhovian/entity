package s2.threading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by russl on 12/7/2016.
 */
public class ProducerConsumerExampleWithWaitAndNotify
{
    public static void main(String[] args)
    {
        List<Integer> taskQueue = new ArrayList<Integer>();
        int MAX_CAPACITY = 5;
        Thread tProducer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer = new Thread(new Consumer(taskQueue), "Consumer");
        tProducer.start();
        tConsumer.start();
    }
}
