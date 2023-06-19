import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MovieClass {
    public static void main(String args[]) {
        List<String> sortedTitles = getMovieTitles("harry potter");

        System.out.println(sortedTitles);
    }

    public static List<String> getMovieTitles(String title) {
        String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=%s";
        url = String.format(url, title);

        List<String> result = new ArrayList<String>();

        URL obj;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine = in.readLine();
            if ( inputLine == null) {
                throw new RuntimeException("response is null");
            }
            in.close();
            result = getTitlesArray(inputLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(result);

        return result;
    }

    public static List<String> getTitlesArray(String str) {

        List<String> result = new ArrayList<String>();

        try {
            // Parse JSON
            JsonObject obj1 = new JsonParser().parse(str).getAsJsonObject();
            JsonArray data =  obj1.getAsJsonArray("data");
            for (int i = 0; i < data.size(); i++) {
                JsonObject item =  data.get(i).getAsJsonObject();
                String theTitle = item.get("Title").getAsString();
                result.add(theTitle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}