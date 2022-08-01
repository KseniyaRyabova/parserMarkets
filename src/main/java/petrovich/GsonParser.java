package petrovich;

import com.google.gson.Gson;
import petrovich.model.Root;

import java.io.FileReader;
import java.io.IOException;

public class GsonParser {

    public Root parse() throws IOException {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("src/main/resources/petrovich.json")) {
            return gson.fromJson(reader, Root.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
