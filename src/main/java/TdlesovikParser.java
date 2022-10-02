import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.FileReaderAndWriter;
import utils.StringUtils;

import java.io.IOException;
import java.util.*;

public class TdlesovikParser extends BaseParser{
    String xpathTitle = "//a[@class='link_name']";
    String xpathPrice = "//a[text()='%s']/ancestor::div[1]/following-sibling::div[1]";
    private HashMap<String, String> nomenclatureAndPrice = new HashMap<>();
    private ArrayList<String> categoriesUrlList = new ArrayList<>();
    private String categoryUrl = "https://tdlesovik.ru/catalog%s";
    private String searchUrlsXpath = "//div[@class='gallery']//a";

    private final List<String> categoriesList = Arrays.asList("/krovlya_1/volnistyy_list/?limit=1000", "/utepliteli/penopleks/",
            "/utepliteli/rokvul/", "/obshchestroitelnye_materialy/profilirovannyy_list/?limit=1000", "/utepliteli/roklayt/");

    private HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();


    public void parse() {
        String price;
        for (String categoryUrl : categoriesList) {
            System.out.println("работает parse");
            try {
                Document listNomenclatureOfCatalogUrl = Jsoup.connect(String.format(this.categoryUrl, categoryUrl)).get();
                Element body = listNomenclatureOfCatalogUrl.body();
                Elements tit = body.selectXpath(xpathTitle);
                var titles = tit.eachText();
                System.out.println(titles);
                for (String title : titles) {
                    price = body.selectXpath(String.format(xpathPrice, title)).text();
                    nomenclatureListWithPrice.put(title, price);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("список товаров "+nomenclatureListWithPrice);
        }
    }
    public void parsePriceByListNomenclature() {
        System.out.println("работает parsePriceByListNomenclature");
        for (var ownerNomenclature : nomenclatureOfOwner) {
            for (Map.Entry<String, String> entry : nomenclatureListWithPrice.entrySet()) {
                String siteNomenclature = entry.getKey();
                String value = entry.getValue();
                var ownerNomenclatureWordList = StringUtils.splitStringIntoSubstrings(ownerNomenclature);
                var siteNomenclatureWordList =  StringUtils.splitStringIntoSubstrings(siteNomenclature);
                List <ArrayList> existNomenclatureList = new ArrayList<>();
                if (StringUtils.nomenclatureIsExist(ownerNomenclatureWordList, siteNomenclatureWordList)) {
                    try {
                        int cellNumber = 9;
                        System.out.println("петрович: " + ownerNomenclature);
                        FileReaderAndWriter.priceWriter(value, cellNumber,
                                nomenclatureOfOwner.indexOf(ownerNomenclature) + 1);
                        System.out.println(nomenclatureOfOwner.indexOf(ownerNomenclature));
                        break;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TdlesovikParser tdlesovikParser = new TdlesovikParser();
        tdlesovikParser.parse();
        tdlesovikParser.parsePriceByListNomenclature();
    }
}
