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

public class LenwoodParser extends BaseParser{
//    1. сбор категорий - не требуется
//    2. сбор цен - по урлу https://lenwood.ru/catalog/
//    по этому урлу все цены представлены в виде таблицы
//    их можно достать по xpath за шт:
//    //a[contains(text(), 'Блок-хаус крашеный 37*145*6000 мм (сорт АВ, сухая)')]/parent::td/following-sibling::td
//    однако выбавют случае, что в td есть 2 span - старая и новая цена, тогда xpath будет такой
//    //a[contains(text(), 'Блок-хаус')]/parent::td/following-sibling::td/span[@class='red']

    private String categoryUrl = "https://lenwood.ru%s";
    private final List<String> categoriesList = Arrays.asList("/catalog/utepliteli_i_membrany/");
    private HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();


    public void parse() {
        String price;
        for (String categoryUrl : categoriesList) {
            System.out.println("работает parse");
            try {
                Document listNomenclatureOfCatalogUrl = Jsoup.connect(String.format(this.categoryUrl, categoryUrl)).get();
                Element body = listNomenclatureOfCatalogUrl.body();
                Elements tit = body.getElementsByAttributeValueContaining("href", "catalog/items");
                var titles = tit.eachText();
                for (String title : titles) {
                    price = body.getElementsContainingOwnText(title).parents().get(1).getElementsByTag("dd").text();
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
                        int cellNumber = 6;
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
        LenwoodParser lenwoodParser = new LenwoodParser();
        lenwoodParser.parse();
        lenwoodParser.parsePriceByListNomenclature();
    }
}
