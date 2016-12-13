package s2.threading;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by russl on 12/7/2016.
 */
class Producer2 implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer2(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        // We are adding elements using offer() in order to check if
        // it actually managed to insert them.
        for (int i = 0; i < 80; i++) {
            System.out.println("putting value - " + i);
            try {
                while (queue.remainingCapacity() == 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.put(i);

            } catch (InterruptedException e) {
                System.out.println("interruptedException");

//                e.printStackTrace();
            }
//            System.out.println("did it");
//            System.out.println("Trying to add to queue: String " + i +
//                    " and the result was " + queue.offer("String " + i));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!queue.isEmpty()) {
            try {
                Thread.sleep(500);
                System.out.println(" waiting ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
