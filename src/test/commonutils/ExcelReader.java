package commonutils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelReader {

    private ExcelReader() {
    }

    public static String readData(String filePath, String sheetName, int row, int column) {
        try (FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return null;
            Row rowData = sheet.getRow(row);
            if (rowData == null) return null;
            Cell cell = rowData.getCell(column);
            if (cell == null) return null;
            return cell.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
