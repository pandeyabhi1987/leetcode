// Java Program for above approach

import java.util.*;

class KPairsWithMaxProduct {

    // Program to count number of substrings
    public static int Divisible(String s, int k) {

        // To count substrings
        int num_of_substrings = 0;

        // To store the remainders
        int rem[] = new int[k];

        rem[0] = 1;
        StringBuffer curr = new StringBuffer();

        // Iterate from s.length() - 1 to 0
        for (int i = s.length() - 1; i >= 0; i--) {

            // to Calculate suffix string
            curr.insert(0, s.charAt(i));

            // convert to number
            long num = Long.parseLong(curr.
                    toString());
            num_of_substrings += rem[(int) num % k];

            // Keep track of visited remainders
            rem[(int) num % k]++;
        }

        // Return number of substrings
        return num_of_substrings;
    }

    // Driver Code
    public static void main(String args[]) {
        String s = "303";


        // Function Call
        System.out.println("Number of sub strings : "
                + Divisible(s, 3));
    }
}
