/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaxCalculating;


import domainlayer.Money;

public class TaxLineItem {

    private String description;
    double percentage;
    Money amount;

    public TaxLineItem() {

    }

    public TaxLineItem(String x, double p, float a) {
        description = x;
        percentage = p;
        amount = new Money(a);
    }

    public double getPercentage() {
        return percentage;
    }

    public Money getamount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
