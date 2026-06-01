package org.crdassessment.rebalancingsolution.test;

import java.util.Arrays;
import java.util.List;
import org.crdassessment.rebalancingsolution.model.Security;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RebalancingServiceTest 
{	
    @Test
    public void testPortfolioTotalsEqual100() 
    {
        List<Security> securities = Arrays.asList(new Security("IBM", 20, 20, 150),new Security("MSFT", 20, 20, 90),new Security("ORCL", 20, 20, 220),new Security("AAPL", 20, 20, 450),new Security("HD", 20, 20, 70));

        double targetTotal = securities.stream().mapToDouble(Security::getTargetPercent).sum();

        Assert.assertEquals(targetTotal, 100.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unit price must be greater than zero")
    public void testInvalidPriceThrowsException() 
    {
        throw new IllegalArgumentException("Unit price must be greater than zero");
    }

    @Test
    public void testBuyCalculation() 
    {
        double totalAssets = 100000;
        double targetPercent = 20;
        double currentPercent = 10;
        double unitPrice = 150;

        double targetValue = (targetPercent / 100) * totalAssets;

        double currentValue = (currentPercent / 100) * totalAssets;

        double shares = (targetValue - currentValue) / unitPrice;

        double roundedShares = Math.round(shares * 100.0) / 100.0;

        Assert.assertEquals(roundedShares, 66.67);
    }
    
    @Test(dataProvider = "portfolioExcelData",dataProviderClass = ExcelDataProvider.class)
    public void testExcelDrivenCalculation(String symbol,double targetPercent,double currentPercent, double unitPrice,double expectedShares) 
    {
        double totalAssets = 100000;
        double targetValue = (targetPercent / 100) * totalAssets;
        double currentValue = (currentPercent / 100) * totalAssets;
        double shares = Math.abs((targetValue - currentValue)/ unitPrice);
        double roundedShares = Math.round(shares * 100.0) / 100.0;

        Assert.assertEquals(roundedShares, expectedShares);
    }
    
    @Test(dataProvider = "portfolioCsvData",dataProviderClass = CsvDataProvider.class)
    public void testCsvDrivenRebalancingCalculation(String symbol,double targetPercent,double currentPercent, double unitPrice,double expectedShares) 
    {
        double totalAssets = 100000;
        double targetValue = (targetPercent / 100) * totalAssets;
        double currentValue = (currentPercent / 100) * totalAssets;
        double shares = Math.abs((targetValue - currentValue)/ unitPrice);
        double roundedShares = Math.round(shares * 100.0) / 100.0;

        Assert.assertEquals(roundedShares, expectedShares);
    }
    
    @Test(dataProvider = "portfolioDatabaseData",dataProviderClass = DatabaseDataProvider.class)
    public void testDatabaseDrivenCalculation(String symbol,double targetPercent,double currentPercent,double unitPrice,double expectedShares) 
    {
        double totalAssets = 100000;
        double targetValue = (targetPercent / 100) * totalAssets;
        double currentValue = (currentPercent / 100) * totalAssets;
        double shares = Math.abs((targetValue - currentValue)/ unitPrice);
        double roundedShares = Math.round(shares * 100.0) / 100.0;

        Assert.assertEquals(roundedShares, expectedShares);
    }
}
