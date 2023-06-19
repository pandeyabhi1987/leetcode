public class CountAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }

    public static String countAndSay(int n) {
        String result = "1";
        while (n > 1) {
            int count = 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0, j = i + 1; i < result.length(); i++, j++) {
                if (j < result.length() && result.charAt(i) == result.charAt(j)) {
                    count++;
                } else {
                    sb.append(count).append(result.charAt(i));
                    count = 1;
                }
            }
            result = sb.toString();
            n--;

        }
        return result;
    }
}
