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


    private final List<String> categoriesList = Arrays.asList("https://npfniks.ru/brus_suhoj_100_100_6000.php",
            "https://npfniks.ru/brus_suhoj_150_150_6000.php",
            "https://npfniks.ru/brus_suhoj_200_200_6000.php",
            "https://npfniks.ru/brus_strogannyj_suhoj_140_140_6000.php",
            "https://npfniks.ru/brus_strogannyj_suhoj_90_90_6000.php",
            "https://npfniks.ru/brus_strogannyj_suhoj_100_100_6000.php",
            "https://npfniks.ru/brus_strogannyj_suhoj_200_200_6000.php",
            "https://npfniks.ru/brusok_obreznoj_50_50_6000.php",
            "https://npfniks.ru/brusok_strogannyj_20_40_3000.php"
            );

    private final String priceXpath = "";

}
