import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0)
            return "";

        if (n == 1)
            return s;

        String maxPalindrome = "";
        for (int i = 0; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = s.substring(i, j);
                if (isPalindrome(substring)) {
                    if (substring.length() > maxPalindrome.length())
                        maxPalindrome = substring;
                }
            }
        }
        System.out.println(maxPalindrome);
        return maxPalindrome;
    }

    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--))
                return false;
        }
        return true;
    }

    private HashSet<String> validDays = new HashSet<String>(Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun"));
    private boolean validateInput(String input) {
        String[] inputSplit = input.strip().split(" ");
        if (inputSplit.length < 1 || inputSplit.length > 3) {
            System.out.println("Invalid input format");
            return false;
        }
        String day = inputSplit[0];
        if (!validDays.contains(day.toLowerCase())) {
            System.out.println("Invalid day in the input format. It has to be one of : " + validDays);
            return false;
        }
        String time = inputSplit[1];
        String[] timeSplit = time.strip().split(":");
        String hourString = timeSplit[0];

        String minuteString = timeSplit[1];
        int hour = Integer.parseInt(hourString);
        if (hour < 0 || hour > 24) {
            System.out.println("Invalid hour in the input format. It has to be between 00 and 24 inclusive. Input has : " + hourString);
            return false;
        }
        int min = Integer.parseInt(minuteString);

        if (min < 0 || min > 60) {
            System.out.println("Invalid min in the input format. It has to be between 00 and 60 inclusive. Input has : " + minuteString);
            return false;
        }

        String ampm = inputSplit[2].strip();
        if (!ampm.equalsIgnoreCase("am") && !ampm.equalsIgnoreCase("pm")) {
            System.out.println("Invalid am/pm in the input format. It has to be am or pm. Input has : " + ampm);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
        System.out.println(lp.validateInput("mon 10:00 am"));
//        lp.longestPalindrome("aracecar");
    }
}
