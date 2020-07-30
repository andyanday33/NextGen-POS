/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainlayer;


public class ItemID {

    String id;

    public ItemID(int id) {
        this.id = String.valueOf(id);
    }

    public String toString() {
        return id;
    }
}
