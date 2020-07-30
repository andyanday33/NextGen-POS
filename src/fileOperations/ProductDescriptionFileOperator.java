package fileOperations;

import domainlayer.ItemID;
import domainlayer.Money;
import domainlayer.ProductDescription;
import ui.ProcessSaleJFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ProductDescriptionFileOperator {
    private final String filePath;
    private final Map<String, ProductDescription> descriptions = new HashMap<>();

    public ProductDescriptionFileOperator(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, ProductDescription> getDescriptions() {
        return descriptions;
    }

    public void readFromFile() {// loadProdSpecs olarak adını değiştir çünkü sd de öyle

        ProductDescription description;
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
                int itemId = Integer.parseInt(parts[0]);
                int type = Integer.parseInt(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                String desc = parts[3];

                ItemID id = new ItemID(itemId);

                Money price = new Money(amount);

                ProcessSaleJFrame.jComboBoxItemID.addItem(id);

                // Initialize description object with the key,price,desc values.
                description = new ProductDescription(id, price, desc, type);

                descriptions.put(id.toString(), description);

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

        descriptions.entrySet().forEach((entry) -> {
            System.out.println(entry.getKey() + " => " + entry.getValue().getDescription());
        });

    }

    /*private void addToMap(int id,int type, String price, String description, String moneyCurrency) {
        try {
            ItemID itemID = new ItemID(id);
            Money money;
            if (moneyCurrency == null) {
                money = new Money(Float.parseFloat(price));
            } else {
                money = new Money(Float.parseFloat(price));
            }

            descriptions.put(id + "", new ProductDescription(itemID,money, description, type));
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: File could not be read (catalog could not be created due to wrong number formats).");
            System.exit(-1);
        } catch (IllegalArgumentException iae) {
            System.out.println("IllegalArgumentException: File could not be read (catalog could not be created due to wrong currency formats)");
            System.exit(-1);
        }
    }*/

}
