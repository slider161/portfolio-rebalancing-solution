package org.crdassessment.rebalancingsolution.service;

import org.crdassessment.rebalancingsolution.model.Security;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RebalancingService 
{
    public void rebalancePortfolio(List<Security> securities,double totalAssets) 
    {
    	validatePortfolio(securities);

        System.out.println("-------------------------------------------------");
        System.out.printf("%-10s %-10s %-15s\n","Security","Action","Shares");
        System.out.println("-------------------------------------------------");
        
        for (Security security : securities) 
        {
        	//Calculate target monetary value
            double targetValue = (security.getTargetPercent() / 100) * totalAssets;
            
            //Calculate current monetary value
            double currentValue = (security.getCurrentPercent() / 100) * totalAssets;
            
            //Difference between target and current allocation
            double difference = targetValue - currentValue;

            //Convert value difference into shares
            double shares = difference / security.getUnitPrice();

            //Set action to BUY,SELL or NONE
            String action;

            if (difference > 0) 
            {
                action = "BUY";
            } 
            else if (difference < 0) 
            {
                action = "SELL";
            } 
            else 
            {
                action = "NONE";
            }

            //Makes positive and round shares to 2 decimal places
            shares = round(Math.abs(shares), 2);

            System.out.printf("%-10s %-10s %-15.2f\n",security.getSymbol(),action,shares);
        }

        System.out.println("-------------------------------------------------");        
    }
    
    private void validatePortfolio(List<Security> securities) 
    {
        double totalTarget = securities.stream().mapToDouble(Security::getTargetPercent).sum();

        double totalCurrent = securities.stream().mapToDouble(Security::getCurrentPercent).sum();

        if (Math.round(totalTarget) != 100) 
        {
            throw new IllegalArgumentException("Target percentages must equal 100%");
        }

        if (Math.round(totalCurrent) != 100) 
        {
            throw new IllegalArgumentException("Current percentages must equal 100%");
        }

        for (Security security : securities) 
        {
            if (security.getUnitPrice() <= 0) 
            {
                throw new IllegalArgumentException("Unit price must be greater than zero");
            }
        }
    }
    
    private double round(double value, int places) 
    {
        return BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static void main(String[] args) 
    {
        //Create sample portfolio
        double totalAssets = 100000;

        List<Security> securities = new ArrayList<>();

        securities.add(new Security("IBM", 20, 10, 150));
        securities.add(new Security("MSFT", 20, 20, 90));
        securities.add(new Security("ORCL", 20, 30, 220));
        securities.add(new Security("AAPL", 20, 20, 450));
        securities.add(new Security("HD", 20, 20, 70));

        RebalancingService service = new RebalancingService();
        
        //Execute rebalancing service
        service.rebalancePortfolio(securities, totalAssets);
    }
}