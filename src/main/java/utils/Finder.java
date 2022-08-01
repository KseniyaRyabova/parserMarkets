package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Finder {
    static String petrovichXpath = "//input[@class='header-search-input']";
    static String petrovichNomName = "//span[@data-test='product-title']";
    public static void findNomenclature() {
        Configuration.timeout = 10;
        Configuration.headless = true;
        Selenide.open("https://petrovich.ru");
        List <String> list = FileReaderAndWriter.getListOfNomenclature();
        list.forEach(str ->
        {
            try {
                $(byXpath(petrovichXpath)).setValue(str).pressEnter();
                sleep(3000);
                System.out.println(str + ": " + $$(byXpath(petrovichNomName)).texts());
            }catch (Exception ex) {
                System.out.println("Элемент не найден");
            }

        });
    }

    public static void main(String[] args) {
        Finder.findNomenclature();
    }
}
