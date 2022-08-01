import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;
import utils.FileReaderAndWriter;
import utils.StringUtils;

import java.io.IOException;
import java.util.*;

import static utils.FileReaderAndWriter.nomenclatureOfOwner;

public class VimosParser extends BaseParser{
    private String categoryUrl = "https://vimos.ru%s";

    private HashMap<String, String> nomenclatureListWithPrice = new HashMap<>();

    private final String priceXpath = "//a[contains(@title, '%s')]/parent::div[contains(@class,'product-card__content')]" +
            "//span[contains(@class,'product-card__price')]/strong";

    private final ArrayList<String> categoriesList = new ArrayList<>(Arrays.asList("/catalog/profnastil-list-ocinkovannyy",
            "/catalog/penopolistirol-ekstrudirovannyy", "/catalog/ondulin", "/catalog/setka-svarnaa", "/catalog/stolby-i-svai",
            "/catalog/mineralnaa-vata","/catalog/mezhvencovyy-uteplitel"));

    //сбор цен и товаров по всему сайту в мапу nomenclatureListWithPrice
    public void parseNomenclatureList() {
        String nomenclatureTitle;
        String priceXpath;
        for (String categoryUrl : categoriesList) {
            try {
                Document listNomenclatureOfCatalogUrl = Jsoup.connect(String.format(this.categoryUrl, categoryUrl)).get();
                Element bodyPage = listNomenclatureOfCatalogUrl.body();
                Elements pages = bodyPage
                        .getElementsByAttributeValueContaining("href", "/page");
                Elements nomenclatures = bodyPage.getElementsByAttribute("title");

                for (Element page:pages) {
                    Document pagesList = Jsoup.connect(String.format(this.categoryUrl, page.attr("href"))).get();
                    Element bodyPages = pagesList.body();
                    Elements noms = bodyPages.getElementsByAttribute("title");
                    for (Element nom:noms) {
                        String price = null;
                        nomenclatureTitle = nom.attr("title");
                        priceXpath = String.format(this.priceXpath, nomenclatureTitle);
                        try {
                            price = bodyPages.selectXpath(priceXpath).text();
                            System.out.println(price);
                            nomenclatureListWithPrice.put(nomenclatureTitle, price);
                        } catch (Selector.SelectorParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                for (Element nomenclature : nomenclatures) {
                    String price = null;
                    nomenclatureTitle = nomenclature.attr("title");
                    priceXpath = String.format(this.priceXpath, nomenclatureTitle);
                    try {
                        price = bodyPage.selectXpath(priceXpath).text();
                        System.out.println(price);
                        nomenclatureListWithPrice.put(nomenclatureTitle, price);
                    } catch (Selector.SelectorParseException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Запись в файл цены, если товар из списка товаров заказчика есть в мапе nomenclatureListWithPrice
    public void parsePriceByListNomenclature() {
        for (var ownerNomenclature : nomenclatureOfOwner) {
            for (Map.Entry<String, String> entry : nomenclatureListWithPrice.entrySet()) {
                String siteNomenclature = entry.getKey();
                String value = entry.getValue();
                System.out.println("заказчик: " + ownerNomenclature);
                System.out.println("сайт: " + siteNomenclature);
                System.out.println(StringUtils.nomenclatureIsExist(ownerNomenclature, siteNomenclature));
                if (StringUtils.nomenclatureIsExist(ownerNomenclature, siteNomenclature)) {
                    try {
                        int cellNumber = 3;
                        FileReaderAndWriter.priceWriter(value, cellNumber,
                                nomenclatureOfOwner.indexOf(ownerNomenclature) + 1);
                        break;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /*
    Создание списка paths категорий магазина для дальнейшего парсинга
    */
    public void createCatalogList() {
        try {
            String baseUrl = "https://vimos.ru/catalog";
            Document vimosCatalogPage = Jsoup.connect(baseUrl).get();
            Elements categoriesPath = vimosCatalogPage.body()
                    .getElementsByAttributeValueContaining("href", "/catalog/");
            for (Element categoryPath : categoriesPath) {
                categoriesList.add(categoryPath.attr("href"));
            }
            System.out.println(categoriesList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "VimosParser{" +
                "nomenclatureListWithPrice=" + nomenclatureListWithPrice +
                "}\n";
    }

}
