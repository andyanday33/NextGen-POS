/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;


import domainlayer.Money;
import domainlayer.Sale;

import java.util.ArrayList;
import java.util.List;

//복합 할인 전략
public abstract class CompositePricingStrategy implements ISalePricingStrategy {

    protected List<ISalePricingStrategy> strategies = new ArrayList<ISalePricingStrategy>();

    public void add(ISalePricingStrategy s) {
        strategies.add(s);
    }

    //public void order(){
      //  for(int i = 0; i < strategies.size() - 1; i ++){
        //    for(int j = 0; j < strategies.size() - 1 - i; j++){

          //  }
       // }
   // }

    @Override
    public abstract Money getTotal(Sale s);

}
