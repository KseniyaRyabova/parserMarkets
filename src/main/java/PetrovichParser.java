import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.FileReaderAndWriter;
import utils.StringUtils;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;

public class PetrovichParser extends BaseParser {
    private final String categoryUrl = "https://petrovich.ru%s";

    private final HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();

//    private final List<String> categoriesList = Arrays.asList("1525", "1524", "1285",
//            "1526", "1566", "1290", "12058", "58079460", "1285", "1286", "1293", "160888532", "21173", "1279", "12124",
//            "21750", "816", "1579", "783", "136205271", "64339332", "778", "1514", "244129136", "1301",
//            "150935536", "786", "245805264", "17286", "1445", "10544");
    private final List<String> categoriesList = Arrays.asList("1290");

    public void parse() {
        RestAssured.baseURI = "https://api.retailrocket.ru/api/2.0";
        for (String categoryIds : categoriesList) {
            Response response = given()
                    .get(String.format("/recommendation/popular/5565b1696636b450d01838a9" +
                            "/?categoryIds=%s&stockId=spb&session=62890001b7ea5200019392bb", categoryIds));
            List<String> names = response.jsonPath().getList("Name");
            List<Object> pricesD = response.jsonPath().getList("Price");
            List<String> prices = new ArrayList<>();
            pricesD.forEach(price -> prices.add(String.valueOf(price)));
            Iterator<String> iNames = names.iterator();
            Iterator<String> iPrices = prices.iterator();
            while (iNames.hasNext() && iPrices.hasNext()) {
                nomenclatureListWithPrice.put(iNames.next(), iPrices.next());
            }
        }
        System.out.println("петрович: " + nomenclatureListWithPrice);
        parsePriceByListNomenclature(2, nomenclatureListWithPrice);
    }
}

