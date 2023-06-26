package systemdesign.ratelimiter;

import java.time.Duration;

public class RateLimiterTest {
    public static void main(String[] args) {
        // Create a rate limiter with capacity 5 and rate limit of 1 request per second
        LeakyBucket rateLimiter = new LeakyBucket(5, Duration.ofSeconds(1));

        // Simulate multiple users accessing the API
        for (int i = 1; i <= 10; i++) {
            UserThread userThread = new UserThread("User " + i, rateLimiter);
            userThread.start();
        }
    }

    private static class UserThread extends Thread {
        private final LeakyBucket rateLimiter;

        public UserThread(String name, LeakyBucket rateLimiter) {
            super(name);
            this.rateLimiter = rateLimiter;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    boolean allowed = rateLimiter.allowRequest();
                    if (allowed) {
                        System.out.println(getName() + ": Request " + i + " allowed");
                    } else {
                        System.out.println(getName() + ": Request " + i + " rejected");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
