package systemdesign.ratelimiter;

import java.time.Duration;
import java.util.HashMap;

public class UserBucketCreator {
    private HashMap<String, RateLimiter> bucket;

    public UserBucketCreator(String userName) {
        bucket = new HashMap<>();
        bucket.put(userName, new LeakyBucket(10, Duration.ofSeconds(2)));
    }

    void accessHelloWordApi(String userName) {
        try {
            if (bucket.get(userName).allowRequest()) {
                System.out.println(Thread.currentThread().getName() + " -> Access granted for user: " + userName);
            } else {
                System.out.println(Thread.currentThread().getName() + " -> Too many requests, Please try again later.Access denied for user: " + userName);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " -> Access granted for user: " + userName);
            System.out.println(e.getMessage());
        }
    }
}
