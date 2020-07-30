/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaxCalculating;

import domainlayer.Money;
import domainlayer.Sale;
import domainlayer.SalesLineItem;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public class GoodAsGoldTaxPro {

    public GoodAsGoldTaxPro() {
        System.out.println("GoodAsGoldTaxPro");
    }

    public List<TaxLineItem> calculateTax(Sale s) {
        Money m = new Money(0);
        List<SalesLineItem> lineItems = s.getLineItems();
        for(SalesLineItem lineItem : lineItems){
            //int taxAmount;
            Money mTemp = lineItem.getSubTotal().times(1.2);
            m = m.add(mTemp);
            s.getTaxLineItem().add(new TaxLineItem("Tax: " + lineItem.getDescription(), 0.2, mTemp.getAmount().floatValue()));

            System.out.println("Tax: " + m);
        }

        return s.getTaxLineItem();
    }
}
