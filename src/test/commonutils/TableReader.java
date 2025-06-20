package commonutils;

import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.By;

public class TableReader {

    static WebElement table;

    public static void getAllTableValues(WebElement table){
        
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement rowElement : rows){
            List<WebElement> cells = rowElement.findElements(By.tagName("td"));
            for (WebElement cell : cells){
                System.out.print(cell.getText() + " | ");
            } 
        }
    }

    public static void getTableValuesByRow(WebElement table, int rowIndex){
        
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            System.out.println("Invalid row index: " + rowIndex);
            return;
        }
        
        WebElement rowElement = rows.get(rowIndex);
        List<WebElement> cells = rowElement.findElements(By.tagName("td"));
        
        for (WebElement cell : cells){
            System.out.print(cell.getText() + " | ");
        }
    }

    public static void getTableValuesByColumn(WebElement table, int columnIndex){
        
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        
        for (WebElement rowElement : rows){
            List<WebElement> cells = rowElement.findElements(By.tagName("td"));
            if (columnIndex < 0 || columnIndex >= cells.size()) {
                System.out.println("Invalid column index: " + columnIndex);
                return;
            }
            WebElement cell = cells.get(columnIndex);
            System.out.print(cell.getText() + " | ");
        }
    }

    public static void getTableValuesByCell(WebElement table, int rowIndex, int columnIndex){
        
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            System.out.println("Invalid row index: " + rowIndex);
            return;
        }
        
        WebElement rowElement = rows.get(rowIndex);
        List<WebElement> cells = rowElement.findElements(By.tagName("td"));
        if (columnIndex < 0 || columnIndex >= cells.size()) {
            System.out.println("Invalid column index: " + columnIndex);
            return;
        }
        
        WebElement cell = cells.get(columnIndex);
        System.out.print(cell.getText() + " | ");
    }

    public static void getTableValuesByHeader(WebElement table, String headerName){
        
        List<WebElement> headers = table.findElements(By.tagName("th"));
        int columnIndex = -1;
        
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().equalsIgnoreCase(headerName)) {
                columnIndex = i;
                break;
            }
        }
        
        if (columnIndex == -1) {
            System.out.println("Header not found: " + headerName);
            return;
        }
        
        getTableValuesByColumn(table, columnIndex);
    }
    
}
