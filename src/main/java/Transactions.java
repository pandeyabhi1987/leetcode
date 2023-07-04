/*
 * Click `Run` to execute the snippet below!
 */

import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        DataStore<String, String> datastore = new DataStore<>();
        datastore.set("user1", "shirt");
        datastore.set("user2", "hat");
        System.out.println(datastore.get("user1")); // shirt

        datastore.begin();
        datastore.set("user1", "pants");
        System.out.println(datastore.get("user1")); // pants
        datastore.delete("user2");
        System.out.println(datastore.get("user2")); // null
        datastore.rollback();
        System.out.println(datastore.get("user1")); // shirt
        System.out.println(datastore.get("user2")); // hat

        System.out.println();
        datastore.set("user1", "shirt");
        datastore.set("user2", "hat");
        System.out.println(datastore.get("user1")); // shirt
        System.out.println(datastore.get("user2")); // hat
        datastore.begin();
        datastore.set("user1", "pants");
        System.out.println(datastore.get("user1")); // pants
        datastore.delete("user2");
        System.out.println(datastore.get("user2")); // None or Null
        datastore.commit();
        System.out.println(datastore.get("user1"));// pants
        System.out.println(datastore.get("user2"));// None or Null
    }
}

class DataStore<K, V> {

    private HashMap<K, V> committedMap = new HashMap<>();
    private HashMap<K, Transaction> transactionMap;
    private boolean inTransaction = false;

    public void begin() {
        transactionMap = new HashMap<>();
        inTransaction = true;
    }

    public void set(K key, V value) {
        if (inTransaction) {
            transactionMap.put(key, new Transaction(key, value));
            return;
        }
        committedMap.put(key, value);
    }

    public void delete(K key) {
        if (!committedMap.containsKey(key)) {
            throw new RuntimeException("key not found : " + key);
        }
        if (inTransaction) {
            transactionMap.put(key, new Transaction(key, null));
            return;
        }
        committedMap.remove(key);
    }

    public V get(K key) {
        if (inTransaction && transactionMap.get(key) != null) {
            return transactionMap.get(key).value;
        }
        return committedMap.get(key);
    }

    public void rollback() {
        if (!inTransaction) {
            throw new RuntimeException("Nothing to rollback, not in transaction");
        }
        inTransaction = false;
        transactionMap = null;
    }

    public void commit() {
        if (!inTransaction) {
            throw new RuntimeException("Nothing to commit, not in transaction");
        }
        if (transactionMap != null && !transactionMap.isEmpty()) {
            for (K key : transactionMap.keySet()) {
                Transaction t = transactionMap.get(key);
                if (t.value != null) {
                    committedMap.put(key, t.value);
                } else {
                    committedMap.remove(key);
                }
            }
            inTransaction = false;
            transactionMap = null;
        }
    }

    class Transaction {
        public K key;
        public V value;

        public Transaction(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}


/* 
Your previous Plain Text content is preserved below:

At Roblox, we have a DataStore API that allows game developers to
store simple key/value information for their games. One common feature
that developers frequently request is the ability to update two
dependent keys simultaneously, or not at all.
 
For example, let's say a developer is building a trading system for
their game. If Person A wants to trade his cool hat for Person B's
shirt, the developer has to:
 
1)  Transfer the hat from Person A to Person B
2)  Transfer the shirt from Person B to Person A
 
If Person B receives the hat, but Person A does not receive
the shirt, then Person B owns both the hat and the shirt while Person A owns nothing!
One way we could solve this is by implementing transactions.
This means when A and B agree to trade, the developer "Begins" the
transaction. All operations that take place after the transaction begins are
said to exist only inside the transaction. When the transaction is "Committed",
all of these operations are added to the main DataStore simultaneously.
If something goes wrong, the developer can "Roll back" the transaction to
discard every operation that occurred since the beginning of that transaction.
 
 
Your task is to implement a DataStore class with the following methods:
 
Example 1:
set("user1", "shirt")
set("user2", "hat")
get("user1") # shirt
begin()
set("user1", "pants")
get("user1") # pants
delete("user2")
get("user2") # None or Null
rollback()
get("user1") # shirt
get("user2") # hat

 
Example 2:
set("user1", "shirt")
set("user2", "hat")
get("user1") # shirt
get("user2") # hat
begin()
set("user1", "pants")
get("user1") # pants
delete("user2")
get("user2") # None or Null
commit()
get("user1") # pants
get("user2") # None or Null



--------
user1 = pants/update
user2 = null/deleted



 */