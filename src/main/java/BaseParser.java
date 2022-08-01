import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import utils.FileReaderAndWriter;
import utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseParser {
    private String categoryUrl;
    private String priceXpath;
    private String attributeElement;
    private ArrayList<String> categoriesUrlList;
//    private HashMap<String, String> nomenclatureAndPrice;
    private String searchUrlsXpath;

    public final List<String> nomenclatureOfOwner = FileReaderAndWriter.nomenclatureOfOwner;
//    public HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();


    public BaseParser(String categoryUrl, String priceXpath, String attributeElement) {
        this.categoryUrl = categoryUrl;
        this.priceXpath = priceXpath;
        this.attributeElement = attributeElement;
    }

    public BaseParser() {

    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public void setSearchUrlsXpath(String searchUrlsXpath) {
        this.searchUrlsXpath = searchUrlsXpath;
    }

    public ArrayList<String> getCategoriesUrlList() {
        return categoriesUrlList;
    }

    /**
     * Метод парсит список урлов у ресурса. Заложен на будущее.
     * В текущей реалищации не требуется
     */
    public void setCategoriesUrlList() {
        try {
            Document catalogPage = Jsoup.connect(this.categoryUrl).get();
            Elements catPath = catalogPage.body().selectXpath(this.searchUrlsXpath);

            for (Element categoryPath : catPath) {
                categoriesUrlList.add(categoryPath.attr("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public HashMap<String, String> getNomenclatureAndPrice() {
//        return nomenclatureAndPrice;
//    }

//    /**
//     * Метод заполняется мапу ценами и товарами.
//     *
//     * @param nomenclatureAndPrice Сначала происходит поиск всех элементов с названием товара. Все наименования записываются в список.
//     *                             Далее для каждого товара из списка происходит поиск цены.
//     *                             Этот метод индивидуальный для каждого ресурса, тк у всех ресурсов разное наполнение.
//     */
//    public void setNomenclatureAndPrice(HashMap<String, String> nomenclatureAndPrice) {
//        this.nomenclatureAndPrice = nomenclatureAndPrice;
//        String nomenclatureTitle;
//        String priceXpath;
//        for (String categoryUrl : this.categoriesUrlList) {
//            try {
//                Document listNomenclatureOfCatalogUrl = Jsoup.connect(categoryUrl).get();
//                Element bodyPage = listNomenclatureOfCatalogUrl.body();
//                Elements nomenclatures = bodyPage.getElementsByAttribute("title");
//                for (Element nomenclature : nomenclatures) {
//                    String price = null;
//                    nomenclatureTitle = nomenclature.attr("title");
//                    priceXpath = String.format(this.priceXpath, nomenclatureTitle);
//                    try {
//                        price = bodyPage.selectXpath(priceXpath).text();
//                        nomenclatureAndPrice.put(nomenclatureTitle, price);
//                    } catch (Selector.SelectorParseException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void parsePriceByListNomenclature(int cellNumberForResource, HashMap <String, String> nomenclatureListWithPrice) {
        for (var ownerNomenclature : nomenclatureOfOwner) {
            for (Map.Entry<String, String> entry : nomenclatureListWithPrice.entrySet()) {
                String siteNomenclature = entry.getKey();
                String value = entry.getValue();
                if (StringUtils.nomenclatureIsExist(ownerNomenclature, siteNomenclature)) {
                    try {
                        FileReaderAndWriter.priceWriter(value, cellNumberForResource,
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
        String str = "//div[@class='gallery']//a";
        String url = "http://imitaciya-brusa.ru/";
        ArrayList<String> arrayList = new ArrayList<>();
        BaseParser baseParser = new BaseParser();
        HashMap<String, String> hm = new HashMap<>();
        baseParser.setCategoryUrl(url);
        baseParser.setSearchUrlsXpath(str);
        baseParser.setCategoriesUrlList();
        baseParser.getCategoriesUrlList();
//        baseParser.setNomenclatureAndPrice(hm);

    }
}
