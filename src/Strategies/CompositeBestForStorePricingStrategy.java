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

public class CompositeBestForStorePricingStrategy extends CompositePricingStrategy {//add 메소드 상속

    public CompositeBestForStorePricingStrategy() {//생성자구현
        //전략들을 add한다.
        add(new PercentDiscountPricingStrategy((float) 0.2));
        add(new PercentDiscountPricingStrategy((float) 0.1));
        add(new AbsoluteDiscountOverThresholdPricingStrategy(new Money(1000), new Money(20000)));
        add(new AbsoluteDiscountOverThresholdPricingStrategy(new Money(500), new Money(15000)));
    }

    @Override
    public Money getTotal(Sale sale) {
        // TODO Auto-generated method stub
        //가지고 있는 전략 중 최고가 리턴

        Money highestTotal = new Money(0);

        for (Iterator i = strategies.iterator(); i.hasNext();) {
            ISalePricingStrategy strategy = (ISalePricingStrategy) i.next();
            Money total = strategy.getTotal(sale);
            if((highestTotal.getAmount().compareTo(total.getAmount()) <= 0) &&
                    (total.getAmount().compareTo(sale.getpreDiscountTotal().getAmount()) != 0)){
                //we must apply a discount, and select the lowest discount possible. We're checking that if absolute discount
                //over threshold strategy is applying discount or not.

                highestTotal = total;
            }
        }

        return highestTotal;
    }

}
