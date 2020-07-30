/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlayer;

import TaxCalculating.ITaxCalculatorAdapter;
import TaxCalculating.TaxLineItem;
import factories.PricingStrategyFactory;
import factories.ServicesFactory;
import ui.ProcessSaleJFrame;

import java.util.ArrayList;
import java.util.List;

public class Register {

    private final ProductCatalog catalog;
    private Sale currentSale;
    private final Store store;

    ITaxCalculatorAdapter taxCalculatorAdapter;
    static final int makeNewSaleNum = 1;
    static final int enterItemNum = 2;
    static final int endSaleNum = 3;

    static final int calculateTaxNum = 4;
    static final int applyDiscountNum = 5;
    static final int enterCustNum = 6;
    static final int makePaymentNum = 7;
    private int state;

    public Register(ProductCatalog catalog, Store store){
        this.catalog=catalog;
        this.store =store;
    }

    public void endSale() {
        currentSale.becomeComplete();
    }

    public void enterCustomerForDiscount(String customerID){
        Customer c = store.getCustomer(customerID);
        currentSale.enterCustomerForDiscount(c);

    }

    public void enterItem(ItemID id, int quantity) {
        ProductDescription desc = catalog.getProductDescription(id);
        currentSale.makeLineItem(desc, quantity);
        currentSale.setTotal(currentSale.getTotal());
    }

    public void makeNewSale() {
        currentSale = new Sale();
        setPricingStrategyType(1); //Resetting to default
    }

    public void makePayment(Money cashTendered) {
        currentSale.makePayment(cashTendered);
    }

    public Sale getSale() {
        return currentSale;
    }

    public List<String[]> calculateTax(String path) {
        
        taxCalculatorAdapter = ServicesFactory.getInstance().getTaxCalculatorAdapter(path);
        List<TaxLineItem> taxLineItems = taxCalculatorAdapter.getTaxes(currentSale);
        Money total = currentSale.getTaxTotal();
        List<String[]> amounts = new ArrayList<>();

        for(TaxLineItem taxLineItem: taxLineItems){
            String[] tax = new String[3];
            tax[0] = taxLineItem.getDescription();
            tax[1] = taxLineItem.getPercentage() + "";
            tax[2] = taxLineItem.getamount() + "";
            amounts.add(tax);
        }
        String[] x = new String[1];
        x[0] = total.toString();
        amounts.add(x);

        return amounts;//return Money
    }

    public void setPricingStrategyType(int strategyType) {//PricingStrategyFactory
        PricingStrategyFactory.getInstance().setPricingStrategyType(strategyType);
    }

    public void addItemDiscounts(){
        List<String[]> discounts = store.getDiscounts();
        //PricingStrategyFactory.getInstance().getSalePricingStrategy(); //Factory içindeki ps objesinin oluşturulması için çağırdık.
        PricingStrategyFactory.getInstance().addItemDiscounts(discounts);


    }

    public Money applyDiscount() {
        return currentSale.getDiscountTotal();
    }

    public void setActiveGUI(int num) {
        state = num;
        switch (state) {

            case makeNewSaleNum:
                ProcessSaleJFrame.jComboBoxItemID.setSelectedIndex(0);
                ProcessSaleJFrame.jTextFieldQuantity.setText("");
                ProcessSaleJFrame.total_Customer.setSelected(false);
                ProcessSaleJFrame.total_Store.setSelected(false);
                ProcessSaleJFrame.jTextFieldCurrentTotal.setText("");
                ProcessSaleJFrame.Tax_Master.setSelected(false);
                ProcessSaleJFrame.Tax_Good.setSelected(false);
                ProcessSaleJFrame.jTextFieldToal_Tax.setText("");
                ProcessSaleJFrame.jTextFieldDesc.setText("");
                ProcessSaleJFrame.jTextFieldTotal.setText("");
                ProcessSaleJFrame.jTextFieldDesc.setText("");
                ProcessSaleJFrame.jTextFieldAmount.setText("");
                ProcessSaleJFrame.jTextFieldBalance.setText("");
                ProcessSaleJFrame.jButtonCustomerID.setEnabled(false);
                ProcessSaleJFrame.jTextFieldCustomerID.setText("");
                ProcessSaleJFrame.jTextFieldCustomerID.setEnabled(false);
               
                ProcessSaleJFrame.jButtonMakePayment.setEnabled(false);
                ProcessSaleJFrame.jButtonEnterItem.setEnabled(true);
                ProcessSaleJFrame.jComboBoxItemID.setEnabled(true);
                ProcessSaleJFrame.jTextFieldQuantity.setEnabled(true);
                break;
            case enterItemNum:
                ProcessSaleJFrame.jButtonEndSale.setEnabled(true);
                break;
            case endSaleNum:
                ProcessSaleJFrame.jButtonEndSale.setEnabled(false);
                ProcessSaleJFrame.jButtonEnterItem.setEnabled(false);
                ProcessSaleJFrame.jComboBoxItemID.setEnabled(false);
                ProcessSaleJFrame.jTextFieldQuantity.setEnabled(false);
                ProcessSaleJFrame.Tax_Master.setEnabled(true);
                ProcessSaleJFrame.Tax_Good.setEnabled(true);
                ProcessSaleJFrame.Tax_Odev.setEnabled(true);
                break;
            case calculateTaxNum:
                ProcessSaleJFrame.jButtonCalculateTax.setEnabled(false);
                ProcessSaleJFrame.Tax_Odev.setSelected(false);
                ProcessSaleJFrame.Tax_Good.setSelected(false);
                ProcessSaleJFrame.Tax_Master.setSelected(false);

                ProcessSaleJFrame.Tax_Odev.setEnabled(false);
                ProcessSaleJFrame.jButtonEndSale.setEnabled(false);
                ProcessSaleJFrame.Tax_Master.setEnabled(false);
                ProcessSaleJFrame.Tax_Good.setEnabled(false);
                ProcessSaleJFrame.Tax_Odev.setSelected(false);
                ProcessSaleJFrame.Tax_Good.setSelected(false);
                ProcessSaleJFrame.Tax_Master.setSelected(false);

                //ProcessSaleJFrame.total_Customer.setEnabled(true);
                //ProcessSaleJFrame.total_Store.setEnabled(true);
                ProcessSaleJFrame.total_Customer.setEnabled(true);
                ProcessSaleJFrame.total_Store.setEnabled(true);
                ProcessSaleJFrame.total_AllIncluded.setEnabled(true);
                break;


            case applyDiscountNum:
                ProcessSaleJFrame.total_AllIncluded.setSelected(false);
                ProcessSaleJFrame.total_Customer.setSelected(false);
                ProcessSaleJFrame.total_Store.setSelected(false);

                ProcessSaleJFrame.total_Customer.setEnabled(false);
                ProcessSaleJFrame.total_AllIncluded.setEnabled(false);



                ProcessSaleJFrame.jButtonApplyDiscount.setEnabled(false);
                ProcessSaleJFrame.jButtonCalculateTax.setEnabled(false);


                ProcessSaleJFrame.total_Store.setEnabled(false);

                ProcessSaleJFrame.jButtonCustomerID.setEnabled(true);
                ProcessSaleJFrame.jTextFieldCustomerID.setEnabled(true);
                break;

            case enterCustNum:
                ProcessSaleJFrame.jButtonApplyDiscount.setEnabled(false);
                ProcessSaleJFrame.jButtonCustomerID.setEnabled(false);
                ProcessSaleJFrame.jTextFieldAmount.setEnabled(true);
                break;

            case makePaymentNum:
                

                ProcessSaleJFrame.jTextFieldAmount.setEnabled(false);
                ProcessSaleJFrame.jButtonMakePayment.setEnabled(false);
                break;
            default:
                break;
        }
    }

    public ProductDescription getDescription(ItemID x) {
        return catalog.getProductDescription(x);
    }
}
