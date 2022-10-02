package utils;

import java.util.*;

public class DictionaryUtils {
    static List<String> listOfNomenclatures = FileReaderAndWriter.nomenclatureOfOwner;

    public static ArrayList<String> setDictionary(String str) {
        System.out.println(new ArrayList<>(Arrays.asList(str.split("(\\S{2,} ?ия)|(\\S \\S+ой.+ *)| "))));

        return new ArrayList<>(Arrays.asList(str.split(" ")));
    }

    public static void main(String[] args) {
        Set<String> words = new HashSet<>();
        List <String> newList = new ArrayList<>();
        for (String s: listOfNomenclatures) {
            newList.add(StringUtils.replaceString(s).toLowerCase(Locale.ROOT));
        }
        System.out.println(newList);

        try {
            newList.forEach(s -> {
                ArrayList<String> wordsOfString = setDictionary(s);
                wordsOfString.forEach(str -> {
                    words.add(str);
                });
            });
        }catch (Exception exception) {
            words.add(null);
        }

        words.removeIf(space -> space.equals(""));
        System.out.println(words);
    }
}
