/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlayer;

import ui.ProcessSaleJFrame;


public class Main {

    public static void main(String[] args) {
        
        Store store = new Store();

        Register register = store.getRegister();
        new ProcessSaleJFrame(register);
        
		

    }
}
