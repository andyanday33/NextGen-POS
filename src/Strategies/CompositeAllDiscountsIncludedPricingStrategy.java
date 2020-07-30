package Strategies;

import domainlayer.*;

import java.util.Iterator;
import java.util.PriorityQueue;
public class CompositeAllDiscountsIncludedPricingStrategy extends CompositePricingStrategy{

    public CompositeAllDiscountsIncludedPricingStrategy() {
        //add(new DiscountByItemPricingStrategy("Somon Baligi", (float) 0.1));

        add(new AbsoluteDiscountOverThresholdPricingStrategy(new Money(1000), new Money(20000)));
        add(new AbsoluteDiscountOverThresholdPricingStrategy(new Money(500), new Money(15000)));
        add(new PercentDiscountPricingStrategy((float) 0.2));
        add(new PercentDiscountPricingStrategy((float) 0.1));
    }
    @Override
    public Money getTotal(Sale sale) {

        Money allTotal = sale.getTaxTotal();
        Money firstTotal = sale.getTaxTotal();
        PriorityQueue<ISalePricingStrategy> queue = new PriorityQueue<>();


        for(Iterator i = strategies.iterator();i.hasNext();){
            ISalePricingStrategy strategy = (ISalePricingStrategy) i.next();
            queue.add(strategy);
        }

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            ISalePricingStrategy strategy = queue.poll();
            Money total = strategy.getTotal(sale);

            String className = strategy.getClass().getName();
            if(allTotal != total)
            if(className.equalsIgnoreCase("Strategies.DiscountByItemPricingStrategy")){
                System.out.println("\n\nİndirim uygulandı, tipi: " + "Ürün bazında Yüzdelik\n" + "İndirim sonrası fiyat: " + total);
            }else if(className.equalsIgnoreCase("Strategies.AbsoluteDiscountOverThresholdPricingStrategy")){
                System.out.println("İndirim uygulandı, tipi: " + "Kesin\n" + "İndirim sonrası fiyat: " + total);
            }else if(className.equalsIgnoreCase("Strategies.PercentDiscountPricingStrategy")){
                System.out.println("İndirim uygulandı, tipi: " + "Yüzdelik\n" + "İndirim sonrası fiyat: " + total);
            }
            allTotal = total;
            sale.setSaleTaxtotal(allTotal);
            //System.out.println("Alltotal = " + allTotal);
        }

        return allTotal;
    }
}
