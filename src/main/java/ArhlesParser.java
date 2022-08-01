import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.FileReaderAndWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArhlesParser extends BaseParser{

    private String categoryUrl = "https://arhles.net%s";

    private final List<String> categoriesList = Arrays.asList("/index.php?route=product/search&search=brontek",
            "/toplivnye-brikety","/onduline-krovlya/onduline-listy", "/search&search=балясина",
            "/membrannye-plenki/isospan", "/membrannye-plenki/brontek", "/teploizolyatsiya/mezhventsovyy-uteplitel",
            "/index.php?route=product/search&search=белтермо", "/index.php?route=product/search&search=брус%20сухой",
            "/index.php?route=product/search&search=брусок%20сухой", "/index.php?route=product/search&search=вагонка+евро&limit=100",
            "/index.php?route=product/search&search=вагонка+штиль&limit=100", "/index.php?route=product/search&search=изоспан",
            "/index.php?route=product/search&search=дверь%20дфг", "/index.php?route=product/search&search=доска+обрезная&limit=100",
            "/index.php?route=product/search&search=Мембрана", "/index.php?route=product/search&search=osb",
            "/index.php?route=product/search&search=террасная+доска&limit=100", "/index.php?route=product/search&search=фанера",
            "/index.php?route=product/search&search=шпунт&limit=100");

    private HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();

    public void parse() {
        String price;
        for (String categoryUrl : categoriesList) {
            try {
                Document listNomenclatureOfCatalogUrl = Jsoup.connect(String.format(this.categoryUrl, categoryUrl)).get();
                Element body = listNomenclatureOfCatalogUrl.body();
                Elements titlesElem = body.getElementsByAttributeValueContaining("class", "name").tagName("div");
                System.out.println("список элеметов с титлом: " + titlesElem);
                var titles = titlesElem.eachText();
                System.out.println(titles);
                for (String title : titles) {
                    System.out.println(title);
                    price = body.getElementsContainingOwnText(title).parents().get(1)
                            .getElementsByAttributeValueContaining("class", "price-tax").text();
                    nomenclatureListWithPrice.put(title, price);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(nomenclatureListWithPrice);
        }
        parsePriceByListNomenclature(5, nomenclatureListWithPrice);
    }

//    public void parsePriceByListNomenclature() {
//        System.out.println("работает parsePriceByListNomenclature");
//        for (var ownerNomenclature : nomenclatureOfOwner) {
//            for (Map.Entry<String, String> entry : nomenclatureListWithPrice.entrySet()) {
//                String siteNomenclature = entry.getKey();
//                String value = entry.getValue();
//                if (StringUtils.nomenclatureIsExist(ownerNomenclature, siteNomenclature)) {
//                    try {
//                        int cellNumber = 5;
//                        System.out.println("петрович: " + ownerNomenclature);
//                        FileReaderAndWriter.priceWriter(value, cellNumber,
//                                nomenclatureOfOwner.indexOf(ownerNomenclature) + 1);
//                        System.out.println(nomenclatureOfOwner.indexOf(ownerNomenclature));
//                        break;
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        FileReaderAndWriter.removeOldValues();
        ArhlesParser arhlesParser = new ArhlesParser();
        arhlesParser.parse();
    }



}
