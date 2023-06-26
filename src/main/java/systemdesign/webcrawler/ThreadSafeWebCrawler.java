package systemdesign.webcrawler;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadSafeWebCrawler {
    private final Set<String> visitedUrls;
    private final BlockingQueue<String> urlQueue;

    public ThreadSafeWebCrawler() {
        this.visitedUrls = new HashSet<>();
        this.urlQueue = new LinkedBlockingQueue<>();
    }

    public void crawl(String startUrl) {
        urlQueue.offer(startUrl);
        while (!urlQueue.isEmpty()) {
            String url = urlQueue.poll();
            if (url != null && !visitedUrls.contains(url)) {
                // Perform crawling logic for the URL
                System.out.println("Crawling: " + url);

                // Mark the URL as visited
                visitedUrls.add(url);

                // Get URLs from the current page and add them to the queue
                String[] urls = getUrlsFromPage(url);
                for (String u : urls) {
                    if (!visitedUrls.contains(u)) {
                        urlQueue.offer(u);
                    }
                }
            }
        }

    }
    private String[] getUrlsFromPage(String url) {
        // Simulated method to retrieve URLs from a webpage
        // Implement your own logic to extract URLs from the webpage
        return new String[]{ /* URLs from the webpage */};
    }

}
