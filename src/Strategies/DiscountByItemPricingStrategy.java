package Strategies;

import domainlayer.Money;
import domainlayer.Sale;
import TaxCalculating.TaxLineItem;

import java.util.List;

public class DiscountByItemPricingStrategy implements ISalePricingStrategy, Comparable, IPriority {

    float percent;
    String itemName;
    final int priority = 1;

    public DiscountByItemPricingStrategy(String itemName, float percent){
        this.itemName = itemName;
        this.percent = percent;
    }

    @Override
    public Money getTotal(Sale s) {
        Money m = new Money(0);
        List<TaxLineItem> lineItems = s.getTaxLineItem();

        for(TaxLineItem lineItem: lineItems){

            if(lineItem.getDescription().contains(itemName)){
                m = m.add(lineItem.getamount().times(1 - percent));
                System.out.println(itemName + " %10 indirim uygulandı");
            }else{
                m = m.add(lineItem.getamount());
            }

        }



        return m;
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
