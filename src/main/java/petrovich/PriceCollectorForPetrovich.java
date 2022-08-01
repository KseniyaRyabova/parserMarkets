package petrovich;

//import model.Section;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import petrovich.model.Section;

import java.io.IOException;
import java.util.*;
//import java.util.Map;

public class PriceCollectorForPetrovich  {
    private String baseUrl = "https://petrovich.ru/catalog/%s";
    private Map<Integer, String> idAndTitle = new HashMap<>();
    private static final String CSSPRICE = "retail-price";
    private ArrayList<Integer> catalogList = new ArrayList<>();
    private static final String PRICE = "//span[contains(text(),'Доска обрезная антисептированная 25х100х6000 мм естественной влажности сорт 1-2 хвойные породы')]/ancestor::div" +
            "[@class='description']/" +
            "following-sibling::div[contains(@class,'card-block')]" +
            "//div[contains(@class, price-details)]" +
            "/p[contains(@class, 'gold-price')]";

//    List<String> categories = Arrays.asList("https://petrovich.ru/catalog/1290/", "https://petrovich.ru/catalog/783");

    List<String> nomenclature = Arrays.asList("Доска обрезная антисептированная");

    public String getPriceByXpath(String nomenclatureName) {
        return String.format("//span[contains(text(),'%s')]/ancestor::div" +
                "[@class='description']/" +
                "following-sibling::div[contains(@class,'card-block')]" +
                "//div[contains(@class, price-details)]" +
                "/p[contains(@class, 'gold-price')]", nomenclatureName);
    }
//    public Map<Integer, String> getIdAndTitle() {
//        return idAndTitle;
//    }

    public ArrayList<Integer> getCatalogList() {
        return catalogList;
    }

    public String getBaseUrl() {
        return baseUrl;
    }


    public void connectToProvider(String baseUrl) {
        Document doc = null;
        try {
            for (Integer url : catalogList) {
                doc = Jsoup.connect(String.format(baseUrl, url)).get();
                System.out.println(doc.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.selectXpath(getPriceByXpath(nomenclature.get(0))).text();
        System.out.println(title);
    }

    public String findNomenclature(String nomenclature) {
        return null;
    }


    public int checkPrice(String pathToNomenclature) {
        return 0;
    }

//    public void createMap(ArrayList<Section> sc) {
//        for (Section section : sc) {
//            idAndTitle.put(section.code, section.title);
//            if (section.getSections() != null) {
//                createMap(section.getSections());
//            }
//        }
//    }

    public void createMap(ArrayList<Section> sc) {
        for (Section section : sc) {
            System.out.println(section.code);
            catalogList.add(section.code);
//            idAndTitle.put(section.code, section.title);
            if (section.getSections() != null) {
                createMap(section.getSections());
            }
        }
    }
}
