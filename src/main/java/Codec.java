import java.util.*;

public class Codec {
    public static void main(String[] args) {
        String encode = encode(Arrays.asList(""));
        System.out.println(decode(encode));
    }

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str.replace("#", "##")).append(" # ");
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> result = new ArrayList();
        String[] strings = s.split(" # " ,-1);
        for (int i = 0 ; i < strings.length-1 ; i++){

            result.add(strings[i].replace("##", "#"));
        }
        return result;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));