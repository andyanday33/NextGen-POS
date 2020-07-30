/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlayer;

import Strategies.ISalePricingStrategy;
import TaxCalculating.TaxLineItem;
import factories.PricingStrategyFactory;
import ui.PropertyListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Sale {

    private final List<SalesLineItem> lineItems = new ArrayList<SalesLineItem>();
    private Boolean isComplete = false;
    private final Date date = new Date();
    private ProductCatalog catalog;
    private Register register;
    private Payment payment;
    private Money Saletotal = null;
    private Money SaleTaxtotal = null;
    private Customer customer;

    //추가
    private final List<TaxLineItem> TaxlineItems = new ArrayList<TaxLineItem>();
    private ISalePricingStrategy pricingStrategy;
    private final List<PropertyListener> propertyListeners = new ArrayList<PropertyListener>();

    public Sale() {

    }

    public Money getBalance() {

        return payment.getAmount().minus(Saletotal);
    }

    public void becomeComplete() {
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void makeLineItem(ProductDescription desc, int quantity) {
        lineItems.add(new SalesLineItem(desc, quantity));
    }



    public Money getTotal() {//currentTotal
        Money total = new Money();
        Money subTotal = null;

        for (SalesLineItem lineItem : lineItems) {

            subTotal = lineItem.getSubTotal();

            total = total.add(subTotal);
        }
        //this.Saletotal = total;

        return total;
    }

    public void makePayment(Money cashTendred) {
        payment = new Payment(cashTendred);
    }

    public void SalesLineItem() {

    }

    public Register getRegister() {
        return register;
    }


    public List<TaxLineItem> getTaxLineItem() {
        return TaxlineItems;
    }

    public Money getTaxTotal() {//TaxTotal

        if(SaleTaxtotal != null){
            return SaleTaxtotal;
        }

        Money total = new Money();
        Money subTotal = null;

        for (TaxLineItem lineItem : TaxlineItems) {
            subTotal = lineItem.getamount();

            total = total.add(subTotal);
        }
        this.SaleTaxtotal = total;
        return total;
    }
    public Money getpreDiscountTotal() {
        Money total = new Money();
        Money subTotal = null;

        for (SalesLineItem lineItem : lineItems) {
            subTotal = lineItem.getSubTotal();

            total = total.add(subTotal);
        }

        return total;
    }


    public void setSaleTaxtotal(Money newTotal){
        SaleTaxtotal = newTotal;
    }


    public void enterCustomerForDiscount(Customer c){
        this.customer = c;
        PricingStrategyFactory.getInstance().addCustomerPricingStrategy(this);
    }

    public Money getDiscountTotal() {
        pricingStrategy = PricingStrategyFactory.getInstance().getSalePricingStrategy();
        return Saletotal = pricingStrategy.getTotal(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getSalePricingStrategy() {
        return customer.getCustomerType();
    }

    public void addPropertyListener(PropertyListener lis) {
        propertyListeners.add(lis);
    }

    public void setTotal(Money newTotal) {
        Saletotal = newTotal;
        //publishPropertyEvent("sale.total", newTotal);
    }

    public void publishPropertyEvent(String name, Money value) {//propertyListeners에 통보

        for (Iterator i = propertyListeners.iterator(); i.hasNext();) {
            PropertyListener strategy = (PropertyListener) i.next();
            strategy.onPropertyEvent(this, name, value);
        }
    }

    public List<SalesLineItem> getLineItems() {
        return lineItems;
    }

    public Money getSubSale(int index) {
        return lineItems.get(index).getSubTotal();
    }
}
