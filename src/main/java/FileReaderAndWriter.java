import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderAndWriter {

    static List<String> listOfNomenclature = new ArrayList<>();
    static String filename = System.getProperty("user.dir") + "\\src\\main\\resources\\nomenclatures.xlsx";

/*
    заполнение списка listOfNomenclature значениями номенклатур из файла
 */
    public static void setListOfNomenclature(String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        var workbook = new XSSFWorkbook(fileInputStream);
        var sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            var rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                listOfNomenclature.add(row.getCell(1).toString());
            }
        }
        fileInputStream.close();
        listOfNomenclature.remove(0);
        System.out.println(listOfNomenclature);
    }

    /* Получение списка номенклатур в виде ArrayList */
    public static List<String> getListOfNomenclature() {
        try {
            setListOfNomenclature(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfNomenclature;
    }


    /* запись в файл цен в определенную ячейку определенного столбца.
    cellNumber - final номер столбца для конкретного магазина;
    rowNumber - строка таблицы в зависимости от определенной номенклатуры;
    */
    public static void priceWriter(String priceValue, int cellNumber, int rowNumber) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        var workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet1 = workbook.getSheetAt(0);
        Row row = sheet1.getRow(rowNumber);
        row.createCell(cellNumber).setCellValue(priceValue);
        FileOutputStream outputStream = new FileOutputStream(new File(filename));
        workbook.write(outputStream);
        outputStream.close();
        fileInputStream.close();
    }
}
