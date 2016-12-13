package s2.threading;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by russl on 12/7/2016.
 */
class Consumer2 implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer2(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        // As long as there are empty positions in our array,
        // we want to check what's going on.
        while (queue.remainingCapacity() > 0) {
            System.out.println(Thread.currentThread().getName() + ":  Queue size: " + queue.size() +
                    ", remaining capacity: " + queue.remainingCapacity());

//            if ( !queue.isEmpty()) {
//            if (queue.remainingCapacity() > 0) {
            try {
                Integer take = queue.take();
                System.out.println(Thread.currentThread().getName() + ":  take value:  " + take);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Integer poll = queue.poll();
//            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
