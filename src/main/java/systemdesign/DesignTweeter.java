package systemdesign;

import java.util.*;

class Twitter {

    private HashMap<Integer, TreeSet<Tweet>> userNewsFeedMap;
    private HashMap<Integer, HashSet> userFollowMap;
    private HashMap<Integer, HashSet> userFolloweeMap;

    public Twitter() {
        userNewsFeedMap = new HashMap<>();
        userFollowMap = new HashMap<>();
        userFolloweeMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        TreeSet<Tweet> pq = userNewsFeedMap.getOrDefault(userId,  new TreeSet<>((o1, o2) -> (int)(o1.timestamp - o2.timestamp)));
        Tweet tweet = new Tweet(userId, tweetId, System.currentTimeMillis());
        pq.add(tweet);
        while (pq.size() > 10) {
            pq.pollFirst();
        }
        userNewsFeedMap.put(userId, pq);

        updateNewsFeedForFollowers(userId);
    }

    private void updateNewsFeedForFollowers(int userId) {
        if (!userNewsFeedMap.containsKey(userId)) return;
        TreeSet<Tweet> pq = userNewsFeedMap.get(userId);
        if (userFolloweeMap.containsKey(userId)) {
            userFolloweeMap.get(userId).forEach(u -> {
                TreeSet<Tweet> pqu = userNewsFeedMap.getOrDefault(u,new TreeSet<>((o1, o2) -> (int)(o1.timestamp - o2.timestamp)));
                pqu.addAll(pq);
                while (pqu.size() > 10) {
                    pqu.pollFirst();
                }
                userNewsFeedMap.put((Integer) u, pqu);
            });
        }
    }

    private void removeNewsFeedForFollowers(int userId) {
        if (!userNewsFeedMap.containsKey(userId)) return;
        TreeSet<Tweet> pq = userNewsFeedMap.get(userId);
        if (userFolloweeMap.containsKey(userId)) {
            userFolloweeMap.get(userId).forEach(u -> {
                TreeSet<Tweet> pqu = userNewsFeedMap.getOrDefault(u, new TreeSet<>((o1, o2) -> (int)(o1.timestamp - o2.timestamp)));
                pqu.removeAll(pq);
                userNewsFeedMap.put((Integer) u, pqu);
            });
        }
    }


    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList();
        if (userNewsFeedMap.containsKey(userId)) {
            System.out.println(userNewsFeedMap.get(userId).size());
            userNewsFeedMap.get(userId).forEach( t -> newsFeed.add(t.tweetId));
        }
        Collections.reverse(newsFeed);
        System.out.println(newsFeed);
        return newsFeed;

    }

    public void follow(int followerId, int followeeId) {
        var followeeSet = userFollowMap.getOrDefault(followerId, new HashSet());
        followeeSet.add(followeeId);
        userFollowMap.put(followerId, followeeSet);

        var followerSet = userFolloweeMap.getOrDefault(followeeId, new HashSet<>());
        followerSet.add(followerId);
        userFolloweeMap.put(followeeId, followerSet);
        updateNewsFeedForFollowers(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (userFollowMap.containsKey(followerId)) {
            var set = userFollowMap.get(followerId);
            removeNewsFeedForFollowers(followeeId);
            set.remove(followeeId);
            userFollowMap.put(followerId, set);
        } else {
            throw new RuntimeException("Can't unfollow someone which user doesn't follow" + followerId);
        }
        if (userFolloweeMap.containsKey(followeeId)) {
            var set = userFolloweeMap.get(followeeId);
//            updateNewsFeedForFollowers(followeeId, userFolloweeMap);
            set.remove(followerId);
            userFolloweeMap.put(followeeId, set);

        } else {
            throw new RuntimeException("Can't unfollow someone which user doesn't follow" + followerId);
        }
    }

    private class Tweet{
        int tweetId;
        int userId;
        long timestamp;

        public Tweet(int userId, int tweetId, long timestamp) {
            this.tweetId = tweetId;
            this.userId = userId;
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {
        Twitter t = new Twitter();
        /*System.out.println("Test 1");
        t.postTweet(1, 5);
        System.out.println(t.getNewsFeed(1));
        t.follow(1, 2);
        t.postTweet(2, 6);
        System.out.println(t.getNewsFeed(1));
        t.unfollow(1, 2);
        System.out.println(t.getNewsFeed(1));*/

       /* System.out.println("Test 2");
        t = new Twitter();
        t.postTweet(1, 1);
        System.out.println(t.getNewsFeed(1));
        t.follow(2, 1);
        System.out.println(t.getNewsFeed(2));
        t.unfollow(2, 1);
        System.out.println(t.getNewsFeed(2));*/

        System.out.println("Test 3");
        t = new Twitter();
        t.postTweet(1, 5);
        t.postTweet(1, 3);
        System.out.println(t.getNewsFeed(1));
    }
}