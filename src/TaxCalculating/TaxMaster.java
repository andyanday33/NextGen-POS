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


public class TaxMaster {

    public List<TaxLineItem> calcTax(Sale s) {

        Money m = new Money(0);
        //s.getTaxLineItem().add(new TaxLineItem("Tax1", 0.1, m.getAmount().floatValue()));
        List<SalesLineItem> lineItems = s.getLineItems();
        for(SalesLineItem lineItem : lineItems){
            //int taxAmount;
            Money mTemp = lineItem.getSubTotal().times(1.1);
            m = m.add(mTemp);
            s.getTaxLineItem().add(new TaxLineItem("Tax: " + lineItem.getDescription(), 0.1, mTemp.getAmount().floatValue()));

            System.out.println("Tax: " + m);
        }

        return s.getTaxLineItem();
    }
}
