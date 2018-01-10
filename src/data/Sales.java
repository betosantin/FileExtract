/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import fileextract.FileUtilities;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Roberto Santin
 */
public class Sales {
    
    private int SaleId;
    private List<Item> ListItems = new ArrayList<Item>();
    private String NameSalesman;

    public Sales(int SaleId, List<Item> ListItems, String NameSalesman) {
        this.SaleId = SaleId;
        this.ListItems = ListItems;
        this.NameSalesman = NameSalesman;
    }
    
    public Sales(String information) {
        /* Sales data
            Delimiter = ç
            Tokenizer 0 = idType
                      1 = SalesID
                      2 = ListItems
                      3 = Salesman name
         */
        
        List<String> object = new ArrayList();
        StringTokenizer st = new StringTokenizer(information, FileUtilities.delimiter );
        while (st.hasMoreTokens()) {
            object.add(st.nextToken());
        }

        List<String> itemsInfo = new ArrayList();
        StringTokenizer st2 = new StringTokenizer(object.get(2), ",");
        while (st2.hasMoreTokens()) {
            itemsInfo.add(st2.nextToken());
        }
        
        for (int i = 0; i < itemsInfo.size(); i++) {
            Item it = new Item(itemsInfo.get(i));
            
            this.ListItems.add(it);
        }
        
        this.SaleId = Integer.valueOf((object.get(1)));
        this.NameSalesman = String.valueOf((object.get(3)));
    }


    public int getSaleId() {
        return SaleId;
    }

    public List<Item> getListItems() {
        return ListItems;
    }

    public String getNameSalesman() {
        return NameSalesman;
    }

    @Override
    public String toString() {
        return "Id: " + SaleId + ", Itens: " + ListItems + ", Vendedor: " + NameSalesman;
    }

}
