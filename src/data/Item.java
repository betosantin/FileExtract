/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Roberto Santin
 */
public class Item {
    
    
    private int Id;
    private int Quantity;
    private double Price;

    public Item(int ID, int Quantity, double Price) {
        this.Id = ID;
        this.Quantity = Quantity;
        this.Price = Price;
    }
    
    public Item( String information )
    {
        /* Sales data
            Delimiter = -
            Tokenizer 0 = Id
                      1 = Quantity
                      2 = Price
         */
        
        List<String> item = new ArrayList();
        StringTokenizer st = new StringTokenizer(information.replace("[", "").replace("]", ""), "-");
        while (st.hasMoreTokens()) {
            item.add(st.nextToken());
        }

        this.Id = Integer.valueOf((item.get(0)));
        this.Quantity = Integer.valueOf((item.get(1)));
        this.Price = Double.valueOf((item.get(2)));
    }    

    public int getId() {
        return Id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public double getPrice() {
        return Price;
    }

    @Override
    public String toString() {
        return "ID: " + Id + ", Quantidade: " + Quantity + ", Preço: " + Price;
    }
}
