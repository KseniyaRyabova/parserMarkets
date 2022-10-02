import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utils.StringUtils;

@RunWith(Parameterized.class)
public class StringUtilsTest {
    private final String nomenclatureOfOwner;
    private final String nomenclatureOfSite;
    private final boolean expected;

    public StringUtilsTest(String nomenclatureOfOwner, String nomenclatureOfSite, boolean expected) {
        this.nomenclatureOfOwner = nomenclatureOfOwner;
        this.nomenclatureOfSite = nomenclatureOfSite;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getManePositive() {
        return new Object[][]{
                {"Профилированный лист МП-20х1100 1800х1150 (RAL 6005 0,4) зеленый мох",
                        "Утеплитель Rockwool 100х600х800 Лайт Баттс",
                        false},
                {"Экструдированный пенополистирол Пеноплэкс Комфорт 20х585х1185 мм",
                        "Экструдированный пенополистирол Пеноплэкс Комфорт 20х585х1185 мм",
                        true},
                {"Профнастил С20 Зеленый 1150х2000х0.45мм RAL 6005",
                        "Профнастил С20 1,15х2 м 0,45 мм зеленый RAL 6005",
                        true},
                {"Оголовок для сваи 150х150 мм на 57мм",
                        "Оголовок для сваи d57 мм",
                        false}
        };
    }

//    @Test
//    public void splitNomByWordWhenSplitExist() {
//        boolean actual = StringUtils.nomenclatureIsExist(nomenclatureOfOwner, nomenclatureOfSite);
//        Assert.assertEquals(String.format("\nстроки '%s' \nи '%s' выдают \nложно -положительный или -отрицательный результат", nomenclatureOfOwner, nomenclatureOfSite), expected, actual);
//    }
}
