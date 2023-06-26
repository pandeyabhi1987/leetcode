package systemdesign.ratelimiter;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeakyBucket implements RateLimiter {
    private BlockingQueue<Instant> bucket;
    private final int capacity; // Maximum bucket capacity
    private final Duration rateLimit; // Minimum duration between requests

    public LeakyBucket(int capacity, Duration rateLimit) {
        this.bucket = new LinkedBlockingQueue<>(capacity);
        this.capacity = capacity;
        this.rateLimit = rateLimit;
    }

    @Override

    public boolean allowRequest() throws InterruptedException {
        Instant now = Instant.now();

        // Try to remove the oldest request from the bucket, if available
        Instant oldestRequest = bucket.poll();
        if (oldestRequest != null) {
            // Check if the time elapsed since the oldest request is within the rate limit
            Duration elapsed = Duration.between(oldestRequest, now);
            if (elapsed.compareTo(rateLimit) < 0) {
                // Re-insert the oldest request back into the bucket
                bucket.offer(oldestRequest);

                // Calculate the delay required before allowing the next request
                Duration delay = rateLimit.minus(elapsed);
                Thread.sleep(delay.toMillis());
            }
        }

        // Add the current request to the bucket
        boolean added = bucket.offer(now);

        // If the bucket is full, remove the oldest request and add the current request
        if (!added) {
            Instant removedRequest = bucket.take();
            bucket.offer(now);
        }

        return added;
    }
}
