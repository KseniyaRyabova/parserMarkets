import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StringUtils {
    static String nomenclatureOfOwner;
    static String nomenclatureOfSite;

    public static ArrayList<String> splitStringIntoSubstrings(String nomenclatureOfOwner) {
        return new ArrayList<>(Arrays.asList(replaceString(nomenclatureOfOwner).split("[ х]")));
    }

    public static String replaceString(String replacementString) {
        List<String> list = Arrays.asList("м", ",", "0", "-", ".", "на", "d");
        for (String symbol : list) {
            replacementString = replacementString.replace(symbol, "");
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
        if (substringsOwnerNom.size() < substringsSiteNom.size()) {
            ArrayList<String> wordListOwner = splitStringIntoSubstrings(nomenclatureOfOwner);
            for (String word : wordListOwner) {
                if (!nomenclatureOfSite.contains(word)) {
                    return false;
                }
            }
            return true;
        } else {
            ArrayList<String> wordListSite = splitStringIntoSubstrings(nomenclatureOfSite);
            for (String word : wordListSite) {
                if (!nomenclatureOfOwner.contains(word)) {
                    return false;
                }
            }
            return true;
        }
    }
}
