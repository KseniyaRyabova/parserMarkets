package utils;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;

import java.util.*;

public class DictionaryCreater {
    static Map<String, List<String>> dictionary = new HashMap<>();

    public static Map<String, List<String>> setDictionaryWithNomenclature() {
        dictionary.put("brontek", List.of("brontek", "бронтек"));
        dictionary.put("пеноплекс", List.of("пеноплэкс", "пеноплекс", "penoplex"));
        dictionary.put("smart", List.of("smart", "смарт"));
        dictionary.put("knauf", List.of("knauf", "кнауф"));
        dictionary.put("оцинк.", List.of("оцинкованная", "оцинк."));
        dictionary.put("техноплекс", List.of("техноплекс", "technoplex"));
        dictionary.put("роквул", List.of("роквул", "rockwool"));
        dictionary.put("rockwool", List.of("роквул", "rockwool"));
        dictionary.put("TOP", List.of("", "TOP"));
        dictionary.put("1185х585х50", List.of("50х585х1185"));
        dictionary.put("1185х585х100", List.of("100х585х1185"));
        dictionary.put("1185х585х20", List.of("20х585х1185"));
        dictionary.put("1185х585х30", List.of("30х585х1185"));
        dictionary.put("обрезная", List.of("доска"));
        dictionary.put("е/вл", List.of("естественной","ев", "естественная", "ествл", "е/в", "ест/вл", "естест", "ест"));
        dictionary.put("сухой", List.of("сух.", "сухой"));
        dictionary.put("хв/п", List.of("хвоя", "стхв/п", "хвойные", "хвойн"));
        dictionary.put("2490х590х20", List.of("20х590х2490"));
        dictionary.put("2490х590х25", List.of("25х590х2490"));
        dictionary.put("950х1950", List.of("195х0953"));
        dictionary.put("57", List.of("57d", "d57"));
        dictionary.put("76", List.of("76d", "d76"));
        dictionary.put("108", List.of("108d", "d108"));
        dictionary.put("89", List.of("89d", "d89"));
        dictionary.put("2000", List.of("2000h"));
        dictionary.put("1500", List.of("1500h"));
        dictionary.put("профнастил", List.of("профилированный"));
        dictionary.put("C8", List.of("C-8"));
        dictionary.put("лен", List.of("льняной"));
        dictionary.put("200х20", List.of("02х20"));
        dictionary.put("150х20", List.of("015х20"));
        dictionary.put("100х20", List.of("01х20"));
        dictionary.put("джут", List.of("джутовый"));
        dictionary.put("обрезной", List.of("брус"));
        dictionary.put("АВ", List.of("АВ"));
        dictionary.put("ветро-влагоизоляция", List.of("ветро-влагозащита"));
        dictionary.put("quickdeck", List.of("quick"));
        dictionary.put("16х2440х600", List.of("16х600х2440"));
        dictionary.put("березовая", List.of("береза", "нешлифованная"));
        dictionary.put("1525х1525х10", List.of("10х1525х1525"));
        dictionary.put("1525х1525х21", List.of("21х1525х1525"));
        dictionary.put("шпунт", List.of("шпунтованная"));
        dictionary.put("половая", List.of("доска"));
        dictionary.put("М-500", List.of("М500"));
        dictionary.put("М-300", List.of("М300"));
        dictionary.put("М-150", List.of("М150"));
        dictionary.put("цемент", List.of("цпс"));
        dictionary.put("125х96х2700", List.of("125х96х2700"));
        dictionary.put("125х96х2500", List.of("125х96х2500"));
        dictionary.put("125х96х3000", List.of("125х96х3000"));
        dictionary.put("в", List.of("b"));
        dictionary.put("ав", List.of("ab"));
        dictionary.put("25х100х6000", List.of("25х100х6000"));
        dictionary.put("40х100х6000", List.of("40х100х6000"));
        dictionary.put("50х100х6000", List.of("50х100х6000"));
        return dictionary;
    }

    public static void main(String[] args) {

    }
}
