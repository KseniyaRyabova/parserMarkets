package utils;

import java.util.*;

public class StringUtils {

    public static ArrayList<String> splitStringIntoSubstrings(String nomenclatureOfOwner) {
        ArrayList<String> list = new ArrayList(Arrays.asList(replaceString(nomenclatureOfOwner).split("[ ]")));
        list.removeIf(space -> space.equals(""));
        return list;
    }

    public static String replaceString(String replacementString) {
        List<String> list = Arrays.asList(" м ", ",", ".", "d", "(", ")",
                "кг", "м2", " мм", " мл", "h", "гост", "8486-86", "деловая");
        for (String symbol : list) {
            replacementString = replacementString.toLowerCase(Locale.ROOT).replace(symbol, "");
        }
        return replacementString;
    }

//    public static void setOwnerNewString(String oldString) {
//        nomenclatureOfOwner = replaceString(oldString);
//    }

//    public static void setSiteNewString(String oldString) {
//        nomenclatureOfSite = replaceString(oldString);
//    }

    /*Тесты в классе StringUtilsTest
     * */
//    public static boolean nomenclatureIsExist(String nomOfOwner, String nomOfSite) {
//        setOwnerNewString(nomOfOwner);
//        setSiteNewString(nomOfSite);
//        var substringsOwnerNom = splitStringIntoSubstrings(nomenclatureOfOwner);
//        var substringsSiteNom = splitStringIntoSubstrings(nomenclatureOfSite);
//        System.out.println(substringsOwnerNom + " " + substringsSiteNom);
//        if (substringsOwnerNom.size() < substringsSiteNom.size()) {
//            for (String word : substringsOwnerNom) {
//                if (!substringsSiteNom.contains(word)) {
//                    return false;
//                }
//            }
//            return true;
//        } else {
////            System.out.println(substringsOwnerNom + " " + substringsSiteNom);
//            for (String word : substringsSiteNom) {
//                if (!substringsOwnerNom.contains(word)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//    }

    //в условиях всегда возвращать false
    public static boolean nomenclatureIsExist(List<String> nomOfOwner, List<String> nomOfSite) {
        List <ArrayList> list = new ArrayList<>();
//        setOwnerNewString(nomOfOwner);
//        setSiteNewString(nomOfSite);
        Map<String, List<String>> map = DictionaryCreater.setDictionaryWithNomenclature();
//        var substringsOwnerNom = splitStringIntoSubstrings(nomenclatureOfOwner);
//        var substringsSiteNom = splitStringIntoSubstrings(nomenclatureOfSite);
        System.out.println(nomOfOwner + " " + nomOfSite);
//        if (nomOfOwner.size() <= nomOfSite.size()) {
                for (String word : nomOfOwner) {
                    if (!nomOfSite.contains(word)) {
                        List<Boolean> bool = new ArrayList<>();
                        try {
                            if (map.containsKey(word)) {
                                //получение списка ассоциативных слов
                                List<String> wordList = map.get(word);
                                for (String dictionaryWord : wordList) {
                                    bool.add(nomOfSite.contains(dictionaryWord));
                                }
                                if (!bool.contains(true)) {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
//
//        } else {
////            System.out.println(substringsOwnerNom + " " + substringsSiteNom);
//            for (String word : substringsSiteNom) {
//                if (!substringsOwnerNom.contains(word)) {
//                    List<Boolean> bool = new ArrayList<>();
//                    try {
//                        if (map.containsKey(word)) {
//                            //получение списка ассоциативных слов
//                            List<String> wordList = map.get(word);
//                            for (String dictionaryWord : wordList) {
//                                bool.add(substringsOwnerNom.contains(dictionaryWord));
//                            }
//                            if (!bool.contains(true)) {
//                                return false;
//                            }
//                        }
//                        else {
//                            return false;
//                        }
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        }

        System.out.println("тут будет true");
        return true;
    }

    public static void main(String[] args) {

    }
}
