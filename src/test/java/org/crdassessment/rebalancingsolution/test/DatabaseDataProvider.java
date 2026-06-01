package org.crdassessment.rebalancingsolution.test;

import org.testng.annotations.DataProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDataProvider 
{
    @DataProvider(name = "portfolioDatabaseData")
    public Object[][] getDatabaseData() throws Exception 
    {
    	//Create connection
        List<Object[]> data = new ArrayList<>();

        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

        try (
                Connection connection = DriverManager.getConnection(url,"sa","");

                Statement statement = connection.createStatement()
        ) {

            //Create table
            statement.execute("""
                    
                    CREATE TABLE IF NOT EXISTS portfolio_test_data (
                    
                        
                        symbol VARCHAR(10),
                        target_percent DOUBLE,
                        current_percent DOUBLE,
                        unit_price DOUBLE,
                        expected_shares DOUBLE
                    )
                    
                    """);

            //Clear old rows
            statement.execute("DELETE FROM portfolio_test_data");

            //Insert test data
            statement.execute("""
                    
                    INSERT INTO portfolio_test_data
                    VALUES
                    ('IBM', 20, 10, 150, 66.67),
                    ('MSFT', 20, 20, 90, 0),
                    ('ORCL', 20, 30, 220, 45.45),
                    ('AAPL', 20, 20, 450, 0),
                    ('HD', 20, 20, 70, 0)
                    
                    """);

            //Query test data
            ResultSet resultSet =statement.executeQuery("SELECT * FROM portfolio_test_data");

            while (resultSet.next()) 
            {
                data.add(new Object[]
                {

                        resultSet.getString("symbol"),

                        resultSet.getDouble("target_percent"),

                        resultSet.getDouble("current_percent"),

                        resultSet.getDouble("unit_price"),

                        resultSet.getDouble("expected_shares")
                });
            }
        }
        //Convert ResultSet to Object[][]
        return data.toArray(new Object[0][]);
    }
}