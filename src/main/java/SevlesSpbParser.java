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

public class SevlesSpbParser extends BaseParser{
//    1. сбор категорий по урлу https://sevles-spb.ru/catalog/
//    xpath для сбора урлов //div[@id='main_catalog']/a
//    собираться будет path урла, поэтому нужна будет конкатенация, пример элемента
//    <a href="/catalog/utsenka/" class="mcat_item" title="РАСПРОДАЖА" id="bx_4145281613_1477"></a>
//    и нужно собрать для всех категорий подкатегории по xpath //div[@class='subcategories']/a

//    2. сбор цен xpath //a[contains(text(), '')]/ancestor::div[@class='catalog_item']//span[@class = 'price']
//    для получения всех товаров без переключения на страницы использовать
//    ?limit=1000, пример: https://sevles-spb.ru/catalog/doska_obreznaya_khvoya/?limit=1000

    private String categoryUrl = "https://sevles-spb.ru%s";

    private HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();

    private final List<String> categoriesList = Arrays.asList("/catalog/svai_vintovye/", "/catalog/ondulin/",
            "/catalog/profilirovannyy_list/", "/catalog/rockwool/", "/catalog/penopleks/", "/catalog/roklayt/");

    private final String priceXpath = "//a[text()='%s']/ancestor::div[@class='catalog_item']" +
            "//span[@class='price']";

    public void parse() {
        String price;
        for (String categoryUrl : categoriesList) {
            System.out.println("работает parse");
            try {
                Document listNomenclatureOfCatalogUrl = Jsoup.connect(String.format(this.categoryUrl, categoryUrl)).get();
                Element body = listNomenclatureOfCatalogUrl.body();
                Elements tit = body.getElementsByClass("g_name");
                var titles = tit.eachText();
                for (String title : titles) {
                    price = body.getElementsContainingOwnText(title).parents().get(2)
                            .getElementsByAttributeValueContaining("class", "price").text();
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
                        int cellNumber = 8;
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

    public static void main(String[] args) throws IOException {
        FileReaderAndWriter.removeOldValues();
        SevlesSpbParser spbParser = new SevlesSpbParser();
        spbParser.parse();
        spbParser.parsePriceByListNomenclature();
    }

}
