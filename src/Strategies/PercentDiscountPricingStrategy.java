package Strategies;

import domainlayer.Money;
import domainlayer.Sale;


public class PercentDiscountPricingStrategy implements ISalePricingStrategy, Comparable, IPriority {

    float percent;
    final int priority = 3;

    public PercentDiscountPricingStrategy(float percent) {
        this.percent = percent;
        //System.out.println("PercentDiscount>" + percent);
    }

    @Override
    public Money getTotal(Sale s) {
        double val = 1 - percent;
        Money SalePrice = s.getTaxTotal().times(val);
        return SalePrice;
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
