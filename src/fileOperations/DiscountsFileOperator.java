package fileOperations;

import domainlayer.ItemID;
import domainlayer.Money;
import domainlayer.ProductDescription;
import ui.ProcessSaleJFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class DiscountsFileOperator {
    private final String filePath;
    private final List<String[]> discounts = new ArrayList<>();

    public DiscountsFileOperator(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> getDiscounts() {
        return discounts;
    }

    public void readFromFile(){
        BufferedReader br = null;

        try {

            //create file object
            File file = new File(filePath);

            //create BufferedReader object from the File
            br = new BufferedReader(new FileReader(file));

            String line = null;

            //read file line by line
            while ((line = br.readLine()) != null) {

                //split the line by ,
                String[] parts = line.split(",");

                //first part is key, second is value,third is description
                String[] disc = new String[2];
                disc[0] = parts[0];
                disc[1] = parts[1];
                System.out.println(disc[0] + "  " + disc[1]);
                discounts.add(disc);


                //ProcessSaleJFrame.jComboBoxItemID.addItem(id);

                // Initialize description object with the key,price,desc values.
                //description = new ProductDescription(id, price, desc, type);

                //descriptions.put(id.toString(), description);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            //Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
        }

        //descriptions.entrySet().forEach((entry) -> {
            //System.out.println(entry.getKey() + " => " + entry.getValue().getDescription());

    }

}
