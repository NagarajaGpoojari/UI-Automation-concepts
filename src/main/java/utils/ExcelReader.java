package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;

public class ExcelReader {

    private static ExcelReader instance;
    private Workbook workbook;

    
    private ExcelReader(String excelPath) {
        try (FileInputStream fis = new FileInputStream(excelPath)) {
            workbook = WorkbookFactory.create(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + excelPath, e);
        }
    }

    
    public static ExcelReader getInstance(String excelPath) {
        if (instance == null) {
            synchronized (ExcelReader.class) {
                if (instance == null) {
                    instance = new ExcelReader(excelPath);
                }
            }
        }
        return instance;
    }

    public String getCellData(String sheetName, int row, int col) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row r = sheet.getRow(row);
        Cell c = r.getCell(col);
        return c.toString();
    }

	public static List<Map<String, String>> getTestData(String string) {
		
		return null;
	}
}
