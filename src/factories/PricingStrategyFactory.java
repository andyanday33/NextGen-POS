/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;


import Strategies.*;
import domainlayer.Customer;
import domainlayer.Sale;
import java.util.List;

public class PricingStrategyFactory {

    static final int BestForCustomer = 1;
    static final int BestForStore = 2;
    static final int AllIncluded = 3;

    public CompositePricingStrategy ps;

    private static final PricingStrategyFactory instance = new PricingStrategyFactory();

    int currentStrategyType;

    public static PricingStrategyFactory getInstance() {
        return instance;
    }

    public void setPricingStrategyType(int strategyType) {
        currentStrategyType = strategyType;
        if (currentStrategyType == BestForCustomer) {
            ps = new CompositeBestForCustomerPricingStrategy();
        } else if (currentStrategyType == BestForStore) {
            ps = new CompositeBestForStorePricingStrategy();
        } else {
            ps = new CompositeAllDiscountsIncludedPricingStrategy();
        }
        System.out.println("Strategy Factory>" + currentStrategyType);
    }

    public void addItemDiscounts(List<String[]> discounts){
        if(ps == null) {
            if (currentStrategyType == BestForCustomer) {
                ps = new CompositeBestForCustomerPricingStrategy();
            } else if (currentStrategyType == BestForStore) {
                ps = new CompositeBestForStorePricingStrategy();
            } else {
                ps = new CompositeAllDiscountsIncludedPricingStrategy();
            }
        }

        for(int i = 0; i < discounts.size(); i++) {
            String[] item = discounts.get(i);
            ps.add(new DiscountByItemPricingStrategy(item[0], Float.parseFloat(item[1])));
        }
    }

    public ISalePricingStrategy getSalePricingStrategy() {

        if(ps == null) {
            if (currentStrategyType == BestForCustomer) {
                return ps = new CompositeBestForCustomerPricingStrategy();
            } else if (currentStrategyType == BestForStore) {
                return ps = new CompositeBestForStorePricingStrategy();
            } else {
                return ps = new CompositeAllDiscountsIncludedPricingStrategy();
            }
        }else{
            return ps;
        }
    }

    public void addCustomerPricingStrategy(Sale s){
        Customer c = s.getCustomer();
        currentStrategyType = s.getSalePricingStrategy();
        float pct = getCustomerPercentage(c);

        PercentDiscountPricingStrategy pd = new PercentDiscountPricingStrategy(pct);
        System.out.println("Applying customer discount %" +pct);
        ps.add(pd);


    }

    public float getCustomerPercentage(Customer c){


        return c.getPercentage();
    }
}
