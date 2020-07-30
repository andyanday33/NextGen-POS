/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fileOperations.*;

import ui.ProcessSaleJFrame;
//DB

/**
 *
 * @author LENOVO
 */
public class ProductCatalog {

    //private Map<String, ProductDescription> descriptions = new HashMap<String, ProductDescription>();

    private final static String filePath = "descriptions.txt";


    private Map<String,ProductDescription> descriptions = new HashMap<>();
    ProductDescriptionFileOperator pfdo = new ProductDescriptionFileOperator("..\\POS2\\descriptions.txt");

    public ProductCatalog(){
        loadProdSpecs();
    }

    //public ProductDescription getProductDescription(ItemID id){
      //  return descriptions.get(id);
    //}

    public void loadProdSpecs(){
        pfdo.readFromFile();
        descriptions = pfdo.getDescriptions();
    }

    public ArrayList<String> getProductIds() {
        ArrayList<String> ids = new ArrayList<>();
        descriptions.forEach((key, value) -> ids.add(key));
        return ids;
    }

    public ProductDescription getProductDescription(ItemID id) {
        return descriptions.get(id.toString());
    }

    
}
