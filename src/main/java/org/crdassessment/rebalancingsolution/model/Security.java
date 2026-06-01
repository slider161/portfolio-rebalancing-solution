package org.crdassessment.rebalancingsolution.model;

public class Security {

    private String symbol;
    private double targetPercent;
    private double currentPercent;
    private double unitPrice;

    public Security(String symbol, double targetPercent, double currentPercent, double unitPrice) 
    {

        this.symbol = symbol;
        this.targetPercent = targetPercent;
        this.currentPercent = currentPercent;
        this.unitPrice = unitPrice;
    }

    public String getSymbol() 
    {
        return symbol;
    }

    public double getTargetPercent() 
    {
        return targetPercent;
    }

    public double getCurrentPercent() 
    {
        return currentPercent;
    }

    public double getUnitPrice() 
    {
        return unitPrice;
    }
    
    public static void main(String arg[]) 
    {
    	Security security = new Security("Test", 0, 0, 0);
    	
    	System.out.println(security.getSymbol());
    	System.out.println(security.getTargetPercent());
    	System.out.println(security.getCurrentPercent());
    	System.out.println(security.getUnitPrice());
    	
    }
}
