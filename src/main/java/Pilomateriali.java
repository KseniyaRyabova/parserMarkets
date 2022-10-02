import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pilomateriali {
    String type;
    String vl;
    String size;
    String wood;
    String sort;

    public static void main(String[] args)  {
        Map <String, List<String>>map = new HashMap();
        map.put("доска обрезная", List.of("доска обрезная", "доска"));
        map.put("е/в", List.of("естественной влажности","ев", "естественная влажность", "ест.вл.", "е/в", "ест./вл."));
        map.put("сухой", List.of("сух.", "сухой"));
        map.put("хв/п.", List.of("хв/п.", "хвоя", "ст.хв/п", "хв./п."));
        map.put("брус", List.of("брус", "брус обрезной"));
        map.put("1-2 сорт", List.of("1-2 сорт", "1-2 сорт деловой", "1-3 сорт"));
        map.put("100x100x6000", List.of("100х100х6000"));
        map.get("доска обрезная").forEach(str -> {
            map.get("е/в").forEach(str2 -> System.out.println(str + " " + str2));
        });
//        String str = li.get(0) + " " + ""
    }

//    public List<String> method(LumberProperties lumberProperties) {
//        Map <String, List<String>>map = new HashMap();
//        map.put("доска обрезная", List.of("доска обрезная", "доска"));
//        map.put("е/в", List.of("естественной влажности","ев", "естественная влажность", "ест.вл.", "е/в", "ест./вл."));
//        map.put("сухой", List.of("сух.", "сухой"));
//        map.put("хв/п.", List.of("хв/п.", "хвоя", "ст.хв/п", "хв./п."));
//        map.put("брус", List.of("брус", "брус обрезной"));
//        map.put("1-2 сорт", List.of("1-2 сорт", "1-2 сорт деловой", "1-3 сорт"));
//        map.put("100x100x6000", List.of("100х100х6000"));
//
//        switch (lumberProperties) {
//            case DOSKA:
//                return map.get("доска обрезная");
//            case BRUS:
//                return map.get("брус");
//            case BRUSOK:
//                return map.get("брусок");
//            case VAGONKA:
//                return map.get("вагонка");
//            case IMITACIA_BRUSA:
//                return map.get("имитация бруса");
//        }
//    }

    public void method2(String str) {
        Map <String, List<String>>map = new HashMap();
        map.put("доска обрезная", List.of("доска обрезная", "доска"));
        map.put("е/в", List.of("естественной влажности","ев", "естественная влажность", "ест.вл.", "е/в", "ест./вл."));
        map.put("сухой", List.of("сух.", "сухой"));
        map.put("хв/п.", List.of("хв/п.", "хвоя", "ст.хв/п", "хв./п."));
        map.put("брус", List.of("брус", "брус обрезной"));
        map.put("1-2 сорт", List.of("1-2 сорт", "1-2 сорт деловой", "1-3 сорт"));
        map.put("100x100x6000", List.of("100х100х6000"));

    }
}
