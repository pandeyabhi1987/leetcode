package systemdesign.ratelimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloWorldApi {
    public static void main(String[] args) {
        UserBucketCreator userBucketCreator = new UserBucketCreator("beinrhythm");
        ExecutorService executors = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 12; i++) {
            executors.execute(() ->  userBucketCreator.accessHelloWordApi("beinrhythm"));

        }
        executors.shutdown();
    }
}
