/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlayer;



public class SalesLineItem  {
    private final int quantity;
    private final ProductDescription description;

    public SalesLineItem(ProductDescription desc, int quantity){
        this.description = desc;
        this.quantity = quantity;
    }

    public Money getSubTotal(){


            return description.getPrice().times(quantity);

    }

    public String getDescription() {
        return description.getDescription();
    }

    public int getType(){return description.getType();}

    @Override
    public String toString(){
        return description.toString() + " (x" +quantity +") " + "(Subtotal: "  + getSubTotal()+")" + "\n";
    }


}
