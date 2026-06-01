package org.crdassessment.rebalancingsolution.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataProvider 
{        
    

    @DataProvider(name = "portfolioExcelData")
    public Object[][] getExcelData() throws Exception 
    {
        FileInputStream file = new FileInputStream("src/test/java/org/crdassessment/rebalancingsolution/resources/portfolio-data.xlsx");

        //Read workbook
        Workbook workbook = new XSSFWorkbook(file);

        //Read sheet
        Sheet sheet = workbook.getSheetAt(0);

        List<Object[]> data = new ArrayList<>();

        boolean firstRow = true;

        for (Row row : sheet) 
        {
            if (firstRow) 
            {
                firstRow = false;
                continue;
            }

            int lastColumn = row.getLastCellNum();
            Object[] rowData = new Object[lastColumn];

            for (int col = 0; col < lastColumn; col++) {
                Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                switch (cell.getCellType()) 
                {
                    case STRING:
                        rowData[col] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        rowData[col] = cell.getNumericCellValue();
                        break;
                    case BOOLEAN:
                        rowData[col] = cell.getBooleanCellValue();
                        break;
                    case FORMULA:
                        rowData[col] = cell.getNumericCellValue();
                        break;
                    case BLANK:
                    default:
                        rowData[col] = null;
                }
            }

            data.add(rowData);
        }

        workbook.close();
        
        //Convert rows to Object[][]
        return data.toArray(new Object[0][]);
    }
}