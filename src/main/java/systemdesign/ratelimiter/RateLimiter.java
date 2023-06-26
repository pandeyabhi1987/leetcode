package systemdesign.ratelimiter;

public interface RateLimiter {
    boolean allowRequest() throws InterruptedException;
}
