/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import domainlayer.Money;
import domainlayer.Sale;

import java.math.BigDecimal;

public class AbsoluteDiscountOverThresholdPricingStrategy implements ISalePricingStrategy, Comparable, IPriority {

    Money discount;
    Money threshold;
    final int priority = 2;

    public AbsoluteDiscountOverThresholdPricingStrategy(Money discount, Money threshold) {
        this.discount = discount;
        this.threshold = threshold;
        //System.out.println("AbsoluteDiscount>" + discount);
    }

    @Override
    public Money getTotal(Sale s){
        if(s.getTaxTotal().minus(threshold).getAmount().compareTo(BigDecimal.ZERO) >= 0){
            System.out.println("Absolute discount total (absolute): " +  s.getpreDiscountTotal().minus(discount));
            return s.getTaxTotal().minus(discount);
        }else {
            return s.getTaxTotal();
        }
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Object o) {

        IPriority another = (IPriority) o;
        if(priority > another.getPriority()){
            return 1;
        }else if(priority == another.getPriority()){
            return 0;
        }else{
            return -1;
        }

    }

}

