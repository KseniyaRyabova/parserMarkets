import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.FileReaderAndWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoodWood {
    /**сделать запись в общую таблицу*/
    private final String titleXpath = "//h2";
    private final String sizeXpath = "//p[contains(text(), 'Параметры')]";
    private final String priceXpath = "//p[contains(text(), 'Цена')]";

    static List<String> listOfNomenclature = new ArrayList<>();
    static String filename = System.getProperty("user.dir") + "\\src\\main\\resources\\nomenclatures.xlsx";
    public static final List<String> nomenclatureOfOwner = FileReaderAndWriter.getListOfNomenclature();

    public static void priceWriter(String priceValue, int cellNumber, int rowNumber) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        var workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet1 = workbook.getSheetAt(1);
        Row row = sheet1.getRow(rowNumber);
        row.createCell(cellNumber).setCellValue(priceValue);
        FileOutputStream outputStream = new FileOutputStream(new File(filename));
        workbook.write(outputStream);
        outputStream.close();
        fileInputStream.close();
    }

    public void parseToFile() {
        try {
            Document listNomenclatureOfCatalogUrl = Jsoup.connect("https://www.gwd.ru/uslugi/sawdust-sale/fuel-briquettes-ruf/").get();
            Element body = listNomenclatureOfCatalogUrl.body();
            String price = body.selectXpath("//i[@class='fa fa-rub']/ancestor::p").text();
            System.out.println(price);
            String title = body.getElementsByTag("h1").text();
            FileInputStream fileInputStream = new FileInputStream(filename);
            var workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet1 = workbook.getSheetAt(1);
            sheet1.createRow(2);
            Row row = sheet1.getRow(2);
            row.createCell(0).setCellValue(title);
            row.createCell(2).setCellValue(price);
            FileOutputStream outputStream = new FileOutputStream(new File(filename));
            workbook.write(outputStream);
            outputStream.close();
            fileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GoodWood goodWood = new GoodWood();
        goodWood.parseToFile();
    }
}
