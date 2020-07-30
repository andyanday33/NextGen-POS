package TaxCalculating;

import domainlayer.Sale;

import java.util.List;

public class odevTaxAdapter implements ITaxCalculatorAdapter {

    @Override
    public List<TaxLineItem> getTaxes(Sale s) {
        odevTax tm = new odevTax();
        List<TaxLineItem> x = tm.calcTax(s);

        return x;
    }

}