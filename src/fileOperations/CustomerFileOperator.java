package fileOperations;

import domainlayer.ItemID;
import domainlayer.Money;
import domainlayer.ProductDescription;
import ui.ProcessSaleJFrame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerFileOperator {

    private final String filePath;
    private List<String> lines;
    public CustomerFileOperator(String filePath){
        this.filePath = filePath;
    }

    public void readFromFile() {
        ProductDescription description;
        BufferedReader br = null;
        lines = new ArrayList<>();

        try {

            //create file object
            File file = new File(filePath);

            //create BufferedReader object from the File
            br = new BufferedReader(new FileReader(file));

            String line = null;

            //read file line by line
            while ((line = br.readLine()) != null) {

                lines.add(line);


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
    }

    public List<String> getLines() {
        return lines;
    }
}
