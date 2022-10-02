import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import utils.FileReaderAndWriter;
import utils.StringUtils;

import java.io.IOException;
import java.util.*;

public class ImitaciyaBrusaParser extends BaseParser {
    String xpathTitle = "//div[@class='product-list']//div[@class='product-name']//a";
    String xpathPrice = "//a[contains(text(), '%s')]/ancestor::div[@class ='product-side-r-description']/following-sibling::div[@class='product-side-r-management']//div[@class='product-price']/div[1]/strong";
    private HashMap<String, String> nomenclatureAndPrice = new HashMap<>();
    private ArrayList<String> categoriesUrlList = new ArrayList<>();
    private String categoryUrl = "http://imitaciya-brusa.ru/";
    private String searchUrlsXpath = "//div[@class='gallery']//a";
    private HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();


//    1. сбор урлов
//    по xpath //div[@class='gallery']/a
//    url with catalog = http://imitaciya-brusa.ru/
//    вложенных категорий нет, но может быть несколько страниц.
//    Нужно доставать номер последней страницы по тексту и добавлять в урл /p/(номер-1)

//    2. cбор цен
//    //a[contains(text(), 'ИМИТАЦИЯ БРУСА 21х146х6000 мм')]/ancestor::div[@class ='product-side-r-description']
//    /following-sibling::div[@class='product-side-r-management']//div[@class='product-price']/div[1]/strong

    /**
    * для дальнейшего парсинга нужен список товаров*/
    public void parse() {

    }

    public void setCategoriesUrlList() {
        try {
            Document catalogPage = Jsoup.connect(this.categoryUrl).get();
//            Elements categoriesPath = catalogPage.body()
//                    .getElementsByAttributeValueContaining("href", "/catalog/");
            Elements catalogPath = catalogPage.body().selectXpath(this.searchUrlsXpath);

            for (Element categoryPath : catalogPath) {
                categoriesUrlList.add(categoryPath.attr("href"));
            }
//            System.out.println(categoriesUrlList.toString());
//            System.out.println(categoriesUrlList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        categoriesUrlList.set(2, categoryUrl + categoriesUrlList.get(2));
        System.out.println(categoriesUrlList);
    }

    public void setNomenclatureAndPrice() {
        String nomenclatureTitle;
        String priceXpath;
        for (String categoryUrl : categoriesUrlList) {
            try {
                Document listNomenclatureOfCatalogUrl = Jsoup.connect(categoryUrl).get();
                Element bodyPage = listNomenclatureOfCatalogUrl.body();
                Elements nomenclatures = bodyPage.selectXpath(xpathTitle);
//                System.out.println(nomenclatures);
//                Elements prices = bodyPage.selectXpath(xpathPrice);
                for (Element nomenclature : nomenclatures) {
//                    nomenclatureAndPrice.put(nomenclature, prices);
                    String price = null;
//                    nomenclatureTitle = nomenclature.attr("title");
                    nomenclatureTitle = nomenclature.text();
//                    System.out.println(nomenclatureTitle);
                    priceXpath = String.format(this.xpathPrice, nomenclatureTitle);
                    try {
                        price = bodyPage.selectXpath(priceXpath).text();
                        System.out.println(price);
                        nomenclatureAndPrice.put(nomenclatureTitle, price);
                    } catch (Selector.SelectorParseException ex) {
                        ex.printStackTrace();
                    }
                }
//                System.out.println(elements);
//                System.out.println(listNomenclatureOfCatalogUrl.body().selectXpath());
//                System.out.println(listNomenclatureOfCatalogUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nomenclatureAndPrice);
    }

    public void parsePriceByListNomenclature(int cellNumberForResource) {
        for (var ownerNomenclature : nomenclatureOfOwner) {
            for (Map.Entry<String, String> entry : nomenclatureListWithPrice.entrySet()) {
                String siteNomenclature = entry.getKey();
                String value = entry.getValue();
                var ownerNomenclatureWordList = StringUtils.splitStringIntoSubstrings(ownerNomenclature);
                var siteNomenclatureWordList =  StringUtils.splitStringIntoSubstrings(siteNomenclature);
                List <ArrayList> existNomenclatureList = new ArrayList<>();
                if (StringUtils.nomenclatureIsExist(ownerNomenclatureWordList, siteNomenclatureWordList)) {
                    try {
                        int cellNumber = 4;
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
        ImitaciyaBrusaParser imitaciyaBrusaParser = new ImitaciyaBrusaParser();
        imitaciyaBrusaParser.setCategoriesUrlList();
        imitaciyaBrusaParser.setNomenclatureAndPrice();
//        imitaciyaBrusaParser.getNomenclatureAndPrice();
//        imitaciyaBrusaParser.parsePriceByListNomenclature();
    }
}
