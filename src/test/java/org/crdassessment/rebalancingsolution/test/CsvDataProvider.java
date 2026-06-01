package org.crdassessment.rebalancingsolution.test;
import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvDataProvider {

    @DataProvider(name = "portfolioCsvData")
    public Object[][] getCsvData() throws Exception 
    {
        List<Object[]> records = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader("src/test/java/org/crdassessment/rebalancingsolution/resources/portfolio-data.csv"))) 
        {
			String[] line;
			
			//Skip header row
			reader.readNext();

			while ((line = reader.readNext()) != null) 
			{

                Object[] rowData = new Object[line.length];

                for (int i = 0; i < line.length; i++) 
                {
                    String value = line[i];

                    if (value == null || value.isEmpty()) 
                    {
                        rowData[i] = null;
                        continue;
                    }

                    try 
                    {
                        rowData[i] = Double.parseDouble(value);
                    } 
                    catch (NumberFormatException e) 
                    {
                        rowData[i] = value;
                    }
                }

                records.add(rowData);
			}
		}
        //Convert ResultSet to Object[][]
        return records.toArray(new Object[0][]);
    }
}