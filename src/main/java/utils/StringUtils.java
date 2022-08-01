package utils;

import java.util.*;

public class StringUtils {
    static String nomenclatureOfOwner;
    static String nomenclatureOfSite;

    public static ArrayList<String> splitStringIntoSubstrings(String nomenclatureOfOwner) {
        ArrayList<String> list = new ArrayList(Arrays.asList(replaceString(nomenclatureOfOwner).split("[ ,хx*-]")));
        list.removeIf(space -> space.equals(""));
        return list;
//        return new ArrayList<>(Arrays.asList(replaceString(nomenclatureOfOwner).split("[ ,хx*-]")));
//                return new ArrayList<>(Arrays.asList(replaceString(nomenclatureOfOwner).split("[ *|]")));
//        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(replaceString(nomenclatureOfOwner).split(" xх")));
//        for (String str : stringList) {
//            if (str.matches("\\dх")) {
//                stringList.addAll(new ArrayList<>(Arrays.asList(str.split("х"))));
//            }
//        }
//        return stringList;
////        return new ArrayList<>(Arrays.asList(replaceString(nomenclatureOfOwner).split("[* ,]")));

    }

    public static String replaceString(String replacementString) {
        List<String> list = Arrays.asList(" м ", ",", ".", "0", "d", "(", ")", "Яч", "яч", "кг", "л", "мм", "м2");
//        List<String> list = Arrays.asList();
        String regex = "\\dмм |кг|л\\.|м2";
        String regex1 = "\\dх";
        if (replacementString.matches(regex1)) {
            replacementString = replacementString.replace("x", " ");
        }

//            replacementString = replacementString.replace(symbol, "");
//        replacementString = replacementString.replaceAll(regex, "");

        for (String symbol : list) {
//            String regex = "[\\sмм\\s|\\d+л|\\d+кг| м2 м  * ]";
//            String regex = "мм |кг.|л\\.";

            replacementString = replacementString.replace(symbol, "");
//            replacementString = replacementString.replaceAll(regex, "");
        }
        return replacementString.toLowerCase(Locale.ROOT);
    }

    public static void setOwnerNewString(String oldString) {
        nomenclatureOfOwner = replaceString(oldString);
    }

    public static void setSiteNewString(String oldString) {
        nomenclatureOfSite = replaceString(oldString);
    }

    /*Тесты в классе StringUtilsTest
     * */
    public static boolean nomenclatureIsExist(String nomOfOwner, String nomOfSite) {
        setOwnerNewString(nomOfOwner);
        setSiteNewString(nomOfSite);
        var substringsOwnerNom = splitStringIntoSubstrings(nomenclatureOfOwner);
        var substringsSiteNom = splitStringIntoSubstrings(nomenclatureOfSite);
        System.out.println(substringsOwnerNom + " " + substringsSiteNom);
        if (substringsOwnerNom.size() < substringsSiteNom.size()) {
            for (String word : substringsOwnerNom) {
                if (!substringsSiteNom.contains(word)) {
                    return false;
                }
            }
            return true;
        } else {
//            System.out.println(substringsOwnerNom + " " + substringsSiteNom);
            for (String word : substringsSiteNom) {
                if (!substringsOwnerNom.contains(word)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.splitStringIntoSubstrings(StringUtils.replaceString("[террасная, доска, (хвоя,, вельвет), 28х140х6000мм, сорт, аb] мм м2 18л")));
//        StringUtils.splitStringIntoSubstrings(StringUtils.replaceString("[террасная, доска, (хвоя,, вельвет), 28х140х6000мм, сорт, аb] мм м2 18л"));
//        System.out.println(StringUtils.replaceString("[террасная, доска, (хвоя,, вельвет), 28х140х6000мм, сорт, аb] мм м2 18л"));

    }
}
