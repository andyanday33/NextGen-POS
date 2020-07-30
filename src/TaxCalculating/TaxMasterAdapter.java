/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaxCalculating;

import domainlayer.Sale;

import java.util.List;


public class TaxMasterAdapter implements ITaxCalculatorAdapter {
    //List<TaxLineItem> TaxlineItems=new ArrayList<TaxLineItem>();

    public TaxMasterAdapter() {
        System.out.println("Tax_Master");
    }

    @Override
    public List<TaxLineItem> getTaxes(Sale s) {
        
        TaxMaster tm = new TaxMaster();
        List<TaxLineItem> x = tm.calcTax(s);


        return x;
    }
}
