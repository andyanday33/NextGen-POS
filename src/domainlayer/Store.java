/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlayer;

import fileOperations.CustomerFileOperator;
import fileOperations.DiscountsFileOperator;
import fileOperations.SaleFileOperator;

import java.util.ArrayList;

import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Store {
    private final ProductCatalog catalog = new ProductCatalog();
    private final Register register = new Register(catalog, this);
    private final List<Sale> completedSales = new ArrayList<>();
    //private List<Customer> seniorCustomers = new ArrayList<>();
    private List<String[]> discounts = new ArrayList<>();
    private List<String[]> customerDiscounts = new ArrayList<>();

    CustomerFileOperator cfo = new CustomerFileOperator("..\\POS2\\Customers.txt");
    DiscountsFileOperator dfoItem = new DiscountsFileOperator("..\\POS2\\ItemDiscounts.txt");
    DiscountsFileOperator dfoCust = new DiscountsFileOperator("..\\POS2\\CustomerDiscounts.txt");
    public void applyCustomerDiscounts(){
        dfoCust.readFromFile();

        customerDiscounts = dfoCust.getDiscounts();

    }

    public List<String[]> getDiscounts(){
        dfoItem.readFromFile();

        discounts = dfoItem.getDiscounts();

        return discounts;
    }


    public Customer getCustomer(String custId){
        if(cfo.getLines() == null) {
            cfo.readFromFile();
        }
        List<String> lines = cfo.getLines();

        float percentage = getPercentage();



        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            if(custId.equalsIgnoreCase(line)){
                return new Customer(Integer.parseInt(line), 1, percentage); //Senior customer
            }

        }
        return new Customer(Integer.parseInt(custId), 2, 0); //Regular customer



    }

    public float getPercentage(){
        float percentage = 0;

        applyCustomerDiscounts();

        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        String day = simpleDateformat.format(now);

        for(int i = 0; i < customerDiscounts.size(); i++){
            if(customerDiscounts.get(i)[0].equalsIgnoreCase(day)){
                percentage += Float.parseFloat(customerDiscounts.get(i)[1]);
            }
        }


        return percentage;
    }


    SaleFileOperator sfo = new SaleFileOperator("..\\POS2\\Sales.txt");

    public Register getRegister(){
        return register;
    }



    public void addSale(Sale s){
        completedSales.add(s);
        saveSale(s);
    }

    public void saveSale(Sale s){
        sfo.saveSale(s);
    }
}
