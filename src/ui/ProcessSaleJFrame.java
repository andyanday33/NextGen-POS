/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;



import domainlayer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
public class ProcessSaleJFrame extends JFrame implements ActionListener, PropertyListener {

    
    private GridBagConstraints c;

    public Container contentPane = getContentPane();
    public JButton jButtonMakeNewSale = new JButton("1. Make New Sale");
    public JLabel jLabelItemID = new JLabel("Item ID:");

    public JLabel jLabelQuantity = new JLabel("Quantity:");
    public static JTextField jTextFieldQuantity = new JTextField(8);
    public static JButton jButtonEnterItem = new JButton("2. Enter Item");
    //(반복)
    public static JButton jButtonEndSale = new JButton("3. End Sale()");

    public JLabel jLabelCustomerID = new JLabel("Customer Id (for Discount): ");
    public static JTextField jTextFieldCustomerID = new JTextField(5);
    public static JButton jButtonCustomerID = new JButton("6.Enter Customer ID");

    public JLabel jLabelTotal = new JLabel("Total after Discount:");
    public static JTextField jTextFieldTotal = new JTextField(5);
    public JLabel jLabelAmount = new JLabel("Amount:");
    public static JTextField jTextFieldAmount = new JTextField(5);
    public static JButton jButtonMakePayment = new JButton("7. Make Payment");
    public JLabel jLabelBalance = new JLabel("Balance");
    public static JTextField jTextFieldBalance = new JTextField(5);

    
    public JTextArea jTextFieldContent = new JTextArea(20, 30);
    public JScrollPane jScrollContent = new JScrollPane(jTextFieldContent);
    public JLabel jLabelDesc = new JLabel("Description:");
    public static JTextField jTextFieldDesc = new JTextField(5);
    public JLabel jLabelCurrentTotal = new JLabel("CurrentTotal:");
    public static JTextField jTextFieldCurrentTotal = new JTextField(5);
    public ButtonGroup taxgroup = new ButtonGroup();
    public static JRadioButton Tax_Master = new JRadioButton("TaxMaster");
    public static JRadioButton Tax_Good = new JRadioButton("GoodAsGoldTaxPro");
    public static JRadioButton Tax_Odev = new JRadioButton("OdevTax");
    public static JButton jButtonCalculateTax = new JButton("4. calculateTax()");
    public JLabel jLabelToal_Tax = new JLabel("Total with Tax:");
    public static JTextField jTextFieldToal_Tax = new JTextField(5);
    public ButtonGroup totalgroup = new ButtonGroup();
    public static JRadioButton total_Customer = new JRadioButton("BestForCustomer");
    public static JRadioButton total_Store = new JRadioButton("BestForStore");
    public static JRadioButton total_AllIncluded = new JRadioButton("AllIncluded");
    public static JButton jButtonApplyDiscount = new JButton("5. applyDiscount()");
    public static JComboBox jComboBoxItemID = new JComboBox();

    private final Register register;
    private int count;

    public ProcessSaleJFrame(Register register) {
        this.register = register;
        initGUI();
        pack();
        setTitle("POS System");
        setVisible(true);
    }

    private void initGUI() {

        contentPane.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;

        taxgroup.add(Tax_Master);
        taxgroup.add(Tax_Good);
        taxgroup.add(Tax_Odev);

        totalgroup.add(total_Customer);
        totalgroup.add(total_Store);
        totalgroup.add(total_AllIncluded);

        layout(jButtonMakeNewSale, 0, 1, 2, 1, 1);
        layout(jLabelItemID, 0, 2, 1, 1, 1);
        layout(jComboBoxItemID, 1, 2, 1, 1, 0);
        layout(jLabelQuantity, 0, 3, 1, 1, 1);
        layout(jTextFieldQuantity, 1, 3, 1, 1, 0);
        layout(jLabelDesc, 0, 4, 1, 1, 1);
        layout(jTextFieldDesc, 1, 4, 1, 1, 0);
        layout(jButtonEnterItem, 0, 5, 2, 1, 0);
        layout(jLabelCurrentTotal, 0, 6, 1, 1, 1);
        layout(jTextFieldCurrentTotal, 1, 6, 1, 1, 0);
        layout(jButtonEndSale, 0, 7, 2, 1, 0);
        layout(Tax_Master, 0, 8, 1, 1, 0);
        layout(Tax_Good, 1, 8, 1, 1, 0);
        layout(Tax_Odev,1,9,1,1,0);
        layout(jButtonCalculateTax, 0, 10, 2, 1, 0);
        layout(jLabelToal_Tax, 0, 11, 1, 1, 1);

        layout(jTextFieldToal_Tax, 1, 11, 1, 1, 0);
        layout(jLabelCustomerID, 0, 15,1,1,1);
        layout(jTextFieldCustomerID, 1, 15,1,1,0);
        layout(jButtonCustomerID,0,16,2,1,0);

        layout(total_Customer, 0, 12, 1, 1, 0);
        layout(total_Store, 1, 12, 1, 1, 0);
        layout(total_AllIncluded,0,13,1,1,0);
        layout(jButtonApplyDiscount, 0, 14, 2, 1, 0);
        layout(jLabelTotal, 0, 17, 1, 1, 1);
        layout(jTextFieldTotal, 1, 17, 1, 1, 0);//Total after Discount
        layout(jLabelAmount, 0, 18, 1, 1, 1);
        layout(jTextFieldAmount, 1, 18, 1, 1, 0);
        layout(jButtonMakePayment, 0, 19, 2, 1, 0);
        layout(jLabelBalance, 0, 20, 1, 1, 1);
        layout(jTextFieldBalance, 1, 20, 1, 1, 0);

        //TextArea
        layout(jScrollContent, 3, 1, 30, 20, 0);

        //desc
        jTextFieldDesc.setText("");


        jButtonMakeNewSale.addActionListener(this);
        jButtonEnterItem.addActionListener(this);
        jButtonEndSale.addActionListener(this);
        jButtonMakePayment.addActionListener(this);
        jButtonCustomerID.addActionListener(this);


        jButtonCalculateTax.addActionListener(this);
        jButtonApplyDiscount.addActionListener(this);
        Tax_Master.addItemListener(new RadioItemListener());
        Tax_Good.addItemListener(new RadioItemListener());
        Tax_Odev.addItemListener(new RadioItemListener());
        total_Customer.addItemListener(new RadioItemListener());
        total_Store.addItemListener(new RadioItemListener());
        total_AllIncluded.addItemListener(new RadioItemListener());

        jTextFieldAmount.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                if (jTextFieldAmount.getText().matches("^[0-9.]*$")) {
                    //MakePayment 활성화
                    jButtonMakePayment.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Yanlış Giriş", "Hata", JOptionPane.WARNING_MESSAGE);
                    jTextFieldAmount.setText("");
                    jTextFieldAmount.requestFocus();
                    jTextFieldContent.append("Noktalama işareti girildi\n");
                    return;
                }
            }

        });

        jComboBoxItemID.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JComboBox cb = (JComboBox) e.getSource();
                ItemID x = (ItemID) cb.getSelectedItem();
                jTextFieldDesc.setText("" + register.getDescription(x).getDescription());
            }

        });

    }

    public void layout(Component obj, int x, int y, int width, int height, int type) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;

        if (obj.equals(jButtonMakeNewSale) || obj.equals(jScrollContent)) {
            c.insets = new Insets(30, 15, 2, 15);
        } else {
            c.insets = new Insets(2, 15, 2, 15);
        }

        add(obj, c);

        if (type == 0) {
            obj.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == jButtonMakeNewSale) {//MakeNewSale
            System.out.println("Make New Sale-> register");
            jTextFieldContent.append("\n");
            register.makeNewSale();
            register.setActiveGUI(1);
            //observer
            initialize(register.getSale());
            count = 0;
        } else if (e.getSource() == jButtonEnterItem) {//enterItem
            System.out.println("Enter Item->register");

            if (jTextFieldQuantity.getText().matches("^[0-9]*$") && !jTextFieldQuantity.getText().equals("")) {

                int id = Integer.parseInt(jComboBoxItemID.getSelectedItem().toString());
                //int id=Integer.parseInt(jTextFieldItemID.getText());
                int quantity = Integer.parseInt(jTextFieldQuantity.getText());
                String desc = jTextFieldDesc.getText();

                register.enterItem(new ItemID(id), quantity);

                jTextFieldContent.append(desc + "/ id: " + id + " quantity: " + quantity + "->" + register.getSale().getSubSale(count) + "\n");

                count++;
                register.setActiveGUI(2);
            } else {

                jTextFieldQuantity.setText("");
                jTextFieldQuantity.requestFocus();
                jTextFieldContent.append("\n");
            }
        } else if (e.getSource() == jButtonEndSale) {
            System.out.println("EndSale->register");

            register.endSale();

            Sale sale = register.getSale();
            jTextFieldContent.append("Total: " + sale.getTotal() + "\n");
            jTextFieldCurrentTotal.setText("" + sale.getTotal());//Total
            register.setActiveGUI(3);
        } else if (e.getSource() == jButtonCalculateTax) {
            jTextFieldContent.append("Tax: ");
            List<String[]> amounts = new ArrayList<>();
            if (Tax_Master.isSelected() == true) {
                amounts = register.calculateTax("TaxCalculating.TaxMasterAdapter");
                jTextFieldContent.append("Tax_Master->\n");
            } else if (Tax_Good.isSelected() == true) {
                amounts = register.calculateTax("TaxCalculating.GoodAsGoldTaxProAdapter");
                jTextFieldContent.append("Tax_Good->\n");
            } else if (Tax_Odev.isSelected() == true){
                amounts = register.calculateTax("TaxCalculating.odevTaxAdapter");
                jTextFieldContent.append("Tax_Odev->\n");

            } else{
                return;
            }
            String[] splitted = amounts.get(amounts.size() - 1)[0].split(" ");
            Money total = new Money(Float.parseFloat(splitted[0].replace(",", ".")));

            for(int i = 0; i < amounts.size() -1; i++){
                String[] icerikler = amounts.get(i);
                jTextFieldContent.append(icerikler[0] + "\nPercent: " + icerikler[1] + "\nAmount (Price + Tax): " + icerikler[2] + "\n");
            }

            jTextFieldToal_Tax.setText("" + total);
            jTextFieldContent.append("Total with Tax->" + total + "\n");
            register.setActiveGUI(4);
        } else if (e.getSource() == jButtonApplyDiscount) {//ApplayDiscount
            jTextFieldContent.append("Discount Type : ");
            if (total_Customer.isSelected() == true) {
                jTextFieldContent.append("BestForCustomer->");
            } else if (total_Store.isSelected() == true) {
                jTextFieldContent.append("BestForStore->");
            } else if (total_AllIncluded.isSelected() == true){
                jTextFieldContent.append("AllIncluded->");

            }else{
                return;
            }
            
            register.setActiveGUI(5);
            register.addItemDiscounts(); //Somon baligi gibi indirimleri listeden eklemek için
            
            Money discountedPrice = register.applyDiscount();
            //Sale sale = register.getSale();
            jTextFieldTotal.setText("" + discountedPrice);
            jTextFieldContent.append("" + discountedPrice + "\n");
        } else if (e.getSource() == jButtonMakePayment) {
            System.out.println("jButtonMakePayment->register");


            double amount = Double.parseDouble(jTextFieldAmount.getText());
            String totalS = jTextFieldTotal.getText().replace(",", ".");

            double total = Double.parseDouble(totalS.split(" ")[0]);
            if (amount < total) {
                JOptionPane.showMessageDialog(null, "Yetersiz Bakiye", "Dikkat", JOptionPane.WARNING_MESSAGE);
            } else {

                register.makePayment(new Money(amount));

                Money balance = register.getSale().getBalance();
                jTextFieldContent.append("Amount: " + amount);
                jTextFieldContent.append("\nBalance " + balance + "\n");
                jTextFieldBalance.setText("" + balance);//Balance
                register.setActiveGUI(7);
            }
        }else if (e.getSource() == jButtonCustomerID){
            String x = jTextFieldCustomerID.getText();
            if(jTextFieldCustomerID.getText().equalsIgnoreCase("")) {
                register.enterCustomerForDiscount("0");
            }else{
                register.enterCustomerForDiscount(jTextFieldCustomerID.getText());
            }
            Money discountedPrice = register.applyDiscount();

            jTextFieldTotal.setText("" + discountedPrice);
            jTextFieldContent.append("Total: With Customer Discount->" + discountedPrice + "\n");
            register.setActiveGUI(6);
        }
    }


    class RadioItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            // TODO Auto-generated method stub
            if (Tax_Master.isSelected()) {
                jButtonCalculateTax.setEnabled(true);
                System.setProperty("taxcalculator.class.name", "POS_final.DomainLayer.TaxMasterAdapter");
            } else if (Tax_Good.isSelected()) {
                jButtonCalculateTax.setEnabled(true);
                System.setProperty("taxcalculator.class.name", "POS_final.DomainLayer.GoodAsGoldTaxProAdapter");
            }else if(Tax_Odev.isSelected()){
                jButtonCalculateTax.setEnabled(true);
                System.setProperty("taxcalculator.class.name", "POS_final.DomainLayer.odevTaxAdapter");
            }

            if (total_Customer.isSelected()) {
                jButtonApplyDiscount.setEnabled(true);
                register.setPricingStrategyType(1);
            } else if (total_Store.isSelected()) {
                jButtonApplyDiscount.setEnabled(true);
                register.setPricingStrategyType(2);
            }else if(total_AllIncluded.isEnabled()){
                jButtonApplyDiscount.setEnabled(true);
                register.setPricingStrategyType(3);
            }
        }

    }

    @Override
    public void onPropertyEvent(Sale source, String name, Money value) {
        // TODO Auto-generated method stub
        if (name.equals("sale.total")) {
            jTextFieldCurrentTotal.setText(value.toString());
        }
    }

    public void initialize(Sale sale) {
        sale.addPropertyListener(this);
    }
}
