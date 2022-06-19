import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.FileReaderAndWriter;
import utils.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetrovichParser {
    private String categoryUrl = "https://petrovich.ru/%s";

    private HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();
    private final List<String> nomenclatureOfOwner = FileReaderAndWriter.nomenclatureOfOwner;

    private final List<String> categoriesList = Arrays.asList("/catalog/1525/", "/catalog/1524", "/catalog/1285/rockwool/",
            "/catalog/1526/", "/catalog/58079460/?tip_komplektuyuschih=ogolovok", "/catalog/58079460/?tip_tovara=svaya_vintovaya",
            "/catalog/1566/", "/catalog/1290/penopleks/");

    private final String priceXpath = "//span[contains(text(),'%s')]/ancestor::div" +
            "[@class='description']/" +
            "following-sibling::div[contains(@class,'card-block')]" +
            "//div[contains(@class, price-details)]" +
            "/p[contains(@class, 'gold-price')]";

    public void parse() {
        String price;
        for (String categoryUrl : categoriesList) {
            try {
                Document listNomenclatureOfCatalogUrl = Jsoup.connect(String.format(this.categoryUrl, categoryUrl)).get();
                Element body = listNomenclatureOfCatalogUrl.body();
                Elements titlesEl = body.selectXpath("//a[@class = 'title']//span");
                var titles = titlesEl.eachText();
                for (String title : titles) {
                    price = body.selectXpath(String.format(priceXpath, title)).text();
                    nomenclatureListWithPrice.put(title, price);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(nomenclatureListWithPrice);
        }
    }

    public void parsePriceByListNomenclature() {
        for (var ownerNomenclature : nomenclatureOfOwner) {
            for (Map.Entry<String, String> entry : nomenclatureListWithPrice.entrySet()) {
                String siteNomenclature = entry.getKey();
                String value = entry.getValue();
                if (StringUtils.nomenclatureIsExist(ownerNomenclature, siteNomenclature)) {
                    try {
                        int cellNumber = 2;
                        System.out.println("петрович: " + ownerNomenclature);
                        FileReaderAndWriter.priceWriter(value, cellNumber,
                                nomenclatureOfOwner.indexOf(ownerNomenclature) + 2);
                        System.out.println(nomenclatureOfOwner.indexOf(ownerNomenclature));
                        break;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
