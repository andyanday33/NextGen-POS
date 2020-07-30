/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import TaxCalculating.ITaxCalculatorAdapter;

public class ServicesFactory {

    static ServicesFactory factory = new ServicesFactory();
    ITaxCalculatorAdapter taxCalculatorAdapter = null;

    public static ServicesFactory getInstance() {
        System.out.println("ServiceFactory");
        return factory;
    }

    public ITaxCalculatorAdapter getTaxCalculatorAdapter(String path) {
        String className = path;
        System.out.println("getProperty: " + className);
        try {
            taxCalculatorAdapter = (ITaxCalculatorAdapter) Class.forName(className).newInstance();
            System.out.println("TaxAdapter: " + className);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return taxCalculatorAdapter;
    }
}
