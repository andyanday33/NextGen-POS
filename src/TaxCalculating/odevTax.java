package TaxCalculating;

import domainlayer.Money;
import domainlayer.Sale;
import domainlayer.SalesLineItem;

import java.util.List;

public class odevTax {

    public List<TaxLineItem> calcTax(Sale s) {

        final int icecek = 1;
        final int alkolluIcecek = 2;
        final int yiyecek = 3;

        Money m = new Money(0);
        //s.getTaxLineItem().add(new TaxLineItem("Tax1", 0.1, m.getAmount().floatValue()));
        List<SalesLineItem> lineItems = s.getLineItems();
        for(SalesLineItem lineItem : lineItems){
            //int taxAmount;
            int type = lineItem.getType();
            Money mTemp = lineItem.getSubTotal();
            if(type == icecek){
                m = m.add(mTemp.times(1.1));
                s.getTaxLineItem().add(new TaxLineItem("icecek vergisi: " + lineItem.getDescription(), 0.1, mTemp.times(1.1).getAmount().floatValue()));
            }else if(type == alkolluIcecek){
                m = m.add(mTemp.times(1.2));
                s.getTaxLineItem().add(new TaxLineItem("alkollu icecek vergisi: " + lineItem.getDescription(), 0.2, mTemp.times(1.2).getAmount().floatValue()));
            }else{
                m = m.add(mTemp.times(1.05));
                s.getTaxLineItem().add(new TaxLineItem("yiyecek vergisi: " + lineItem.getDescription(), 0.05, mTemp.times(1.05).getAmount().floatValue()));
            }
            System.out.println("Tax: " + m);
        }
        return s.getTaxLineItem();
    }

}
