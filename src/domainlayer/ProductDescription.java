/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlayer;

/**
 *
 * @author LENOVO
 */
public class ProductDescription {

    private final String description;
    private final Money price;
    private final ItemID itemId;
    private final int type;//type 1 = içecek, 2 = alkollü içecek, 3 = yiyecek

    public ProductDescription(ItemID id, Money price, String discription, int type) {
        this.itemId = id;
        this.price = price;
        this.description = discription;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public ItemID getItemID() {
        return itemId;
    }

    public Money getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
