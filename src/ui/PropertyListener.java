/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import domainlayer.Money;
import domainlayer.Sale;

public interface PropertyListener {
    void onPropertyEvent(Sale source, String name, Money value);
}
