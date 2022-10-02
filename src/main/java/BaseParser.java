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
    private String searchUrlsXpath;

    public final List<String> nomenclatureOfOwner = FileReaderAndWriter.nomenclatureOfOwner;

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

    public void parsePriceByListNomenclature(int cellNumberForResource, HashMap <String, String> nomenclatureListWithPrice) {
        for (var ownerNomenclature : nomenclatureOfOwner) {
            Map<List<String>, String> existNomenclatureListWithPrice = new HashMap<>();
            for (Map.Entry<String, String> entry : nomenclatureListWithPrice.entrySet()) {
                String siteNomenclature = entry.getKey();
                String priceValue = entry.getValue();
                var ownerNomenclatureWordList = StringUtils.splitStringIntoSubstrings(ownerNomenclature);
                var siteNomenclatureWordList = StringUtils.splitStringIntoSubstrings(siteNomenclature);
                if (StringUtils.nomenclatureIsExist(ownerNomenclatureWordList, siteNomenclatureWordList)) {
                    existNomenclatureListWithPrice.put(siteNomenclatureWordList, priceValue);
                }
            }
            int minSize = 0;
            for (Map.Entry<List<String>, String> entry : existNomenclatureListWithPrice.entrySet()) {
                List<String> nomenclatureWords = entry.getKey();
                if (minSize == 0) {
                    minSize = nomenclatureWords.size();
                } else if (minSize > nomenclatureWords.size()) {
                    minSize = nomenclatureWords.size();
                }
            }
            if (!existNomenclatureListWithPrice.isEmpty()) {
                for (Map.Entry<List<String>, String> entry : existNomenclatureListWithPrice.entrySet()) {
                    String price = entry.getValue();
                    List<String> nomenclatureWords = entry.getKey();
                    if (nomenclatureWords.size() == minSize) {
                        try {
                            System.out.println("петрович: " + ownerNomenclature);
                            FileReaderAndWriter.priceWriter(price, cellNumberForResource,
                                    nomenclatureOfOwner.indexOf(ownerNomenclature) + 1);
                            break;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                try {
                    FileReaderAndWriter.priceWriter("товар не найден", cellNumberForResource,
                            nomenclatureOfOwner.indexOf(ownerNomenclature) + 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
