package systemdesign.webcrawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSafeWebCrawlerTest {
    public static void main(String[] args) {
        int numThreads = 5;
        ThreadSafeWebCrawler webCrawler = new ThreadSafeWebCrawler();
        ExecutorService executor = Executors.newFixedThreadPool(12);
        for (int i = 0; i < numThreads; i++) {
            executor.execute(() -> webCrawler.crawl("webCrawler"));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
