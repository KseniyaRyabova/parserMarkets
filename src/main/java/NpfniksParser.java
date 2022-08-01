import java.util.Arrays;
import java.util.List;

public class NpfniksParser extends BaseParser {
//    1. сбор категорий по урлу https://npfniks.ru/pilomaterialy.php
//    с помощью xpath //div[@class='card']/a
//    2. сбор цен
//    сначала нужно собрать урлы товаров и названий в мапу
//    во всех категориях по xpath //div[@class='fotoblck']/a
//    цены на этом сайте хранятся в таблицах, поэтому xpath будет примерно такой
//    //td[contains(text(), 'ель')]/following-sibling::td[2]
    private String categoryUrl = "https://npfniks.ru/pilomaterialy.php";


    private final List<String> categoriesList = Arrays.asList("");

    private final String priceXpath = "";
}
