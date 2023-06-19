import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class PriorityBlockingQueueThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        ArrayList<Integer> polledElements = new ArrayList<>();

        queue.add(1);
        queue.add(5);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        queue.drainTo(polledElements);
        assertThat(polledElements).containsExactly(1, 2, 3, 4, 5);

        new Thread(() -> {
            System.out.println("Polling...");

            try {
                Integer poll = queue.take();
                System.out.println("Polled: " + poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        System.out.println("Adding to queue");
        queue.add(1);
    }
}
