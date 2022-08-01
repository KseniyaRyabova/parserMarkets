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

public class ElementsmParser extends BaseParser{
    /**парсит не все товары, разобраться (баттс)
     * */

//    1. сбор категорий url https://gatchina.elementsm.ru/catalog/
//    xpath для сбора всех категорий //figcaption//a
//    2. сбор цен по xpath //a[contains(text(), 'Доска')]/ancestor::li/div[@class='price']/meta[@itemprop='price']
//    для сбора цен в конец урла добавить ?limit=1000,
//    пример:
//    https://tdlesovik.ru/catalog/pilomaterialy/doska/?limit=1000
//    xpath для сбора цен //a[contains(text(), 'Доска')]/ancestor::li/div[@class='price']/meta[@itemprop='price']

    private String categoryUrl = "https://gatchina.elementsm.ru/catalog%s";
    private final List<String> categoriesList = Arrays.asList("/svai_/", "/teploizolyatsiya_/", "/krovlya_i_vodostochnye_sistemy/",
            "/kladochnye_setki/", "/ondulin/index.php?count=96");

    private String priceXpath = "//a[text()='%s']/ancestor::div[@class='item']//span[@class='price']";

    private HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();

    public void parse() {
        String price;
        for (String categoryUrl : categoriesList) {
            System.out.println("работает parse");
            try {
                Document listNomenclatureOfCatalogUrl = Jsoup.connect(String.format(this.categoryUrl, categoryUrl)).get();
                Element body = listNomenclatureOfCatalogUrl.body();
                Elements tit = body.getElementsByClass("product-name");
                System.out.println(tit);
                var titles = tit.eachText();
                for (String title : titles) {
                    price = body.selectXpath(String.format(priceXpath, title)).text();
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
                if (StringUtils.nomenclatureIsExist(ownerNomenclature, siteNomenclature)) {
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
        ElementsmParser elementsmParser = new ElementsmParser();
        elementsmParser.parse();
        elementsmParser.parsePriceByListNomenclature();
    }

}
