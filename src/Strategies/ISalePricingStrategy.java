/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategies;

import domainlayer.Money;
import domainlayer.Sale;


public interface ISalePricingStrategy {
    Money getTotal(Sale s);

}
