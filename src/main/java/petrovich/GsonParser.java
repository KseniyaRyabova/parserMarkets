package petrovich;

import com.google.gson.Gson;
import petrovich.model.Root;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GsonParser {

//    public void setUp() {
//        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
//    }

    public GsonParser() throws IOException {
    }

    public Root parse() throws IOException {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("src/main/resources/petrovich.json")) {
            return gson.fromJson(reader, Root.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setConnection(URL url) throws IOException {

        url = new URL("");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        try {
            BufferedReader json;
            json = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    connection.connect(); BufferedReader json;

}
