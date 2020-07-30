/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaxCalculating;


import domainlayer.Money;
import domainlayer.Sale;
import java.util.List;

public class GoodAsGoldTaxProAdapter implements ITaxCalculatorAdapter {

    @Override
    public List<TaxLineItem> getTaxes(Sale s) {
        GoodAsGoldTaxPro tm = new GoodAsGoldTaxPro();
        List<TaxLineItem> x = tm.calculateTax(s);

        return x;
    }

}
