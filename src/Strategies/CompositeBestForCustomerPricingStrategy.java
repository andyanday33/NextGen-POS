/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

/**
 *
 * @author LENOVO
 */
import domainlayer.Money;
import domainlayer.Sale;

import java.util.Iterator;

public class CompositeBestForCustomerPricingStrategy extends CompositePricingStrategy {

    public CompositeBestForCustomerPricingStrategy() {
        add(new PercentDiscountPricingStrategy((float) 0.2));
        add(new PercentDiscountPricingStrategy((float) 0.1));
        add(new AbsoluteDiscountOverThresholdPricingStrategy(new Money(1000), new Money(20000)));
        add(new AbsoluteDiscountOverThresholdPricingStrategy(new Money(500), new Money(15000)));
    }

    @Override
    public Money getTotal(Sale sale) {

        Money lowestTotal = new Money(Integer.MAX_VALUE);

        for (Iterator i = strategies.iterator(); i.hasNext();) {
            ISalePricingStrategy strategy = (ISalePricingStrategy) i.next();
            Money total = strategy.getTotal(sale);
            if(lowestTotal.getAmount().compareTo(total.getAmount()) >= 0){
                lowestTotal = total;
            }
        }

        return lowestTotal;
    }

}
