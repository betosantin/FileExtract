
package fileextract;

import data.Customer;
import data.Item;
import data.Sales;
import data.Salesman;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Roberto Santin
 */
public class FileRules {
    
   private List<Salesman> salesmans = new ArrayList();
   private List<Customer> customers = new ArrayList();
   private List<Sales> sales = new ArrayList();

    public FileRules() {
    }
   
   public void readFile( File f ) throws FileNotFoundException, IOException
   {
       if (f.exists() && f.isFile() && f.canRead()) 
       {
           FileReader reader = new FileReader( f );
           BufferedReader inFile = new BufferedReader(reader);
           
           String nextLine = inFile.readLine();

           while (nextLine != null) {
               if (nextLine.startsWith("001")) 
               {
                   Salesman s = new Salesman(nextLine);
                   
                   salesmans.add(s);
               } 
               
               else if (nextLine.startsWith("002")) 
               {
                   Customer c = new Customer(nextLine);
                   
                   customers.add(c);
               } 
               
               if (nextLine.startsWith("003")) 
               {
                   Sales s = new Sales(nextLine);
                   
                   sales.add(s);
               }
               
               nextLine = inFile.readLine();
           }
           
           inFile.close();
       }
   }

    public int getCustomerSize() {
        return customers.size();
    }

    public int getSalesmanSize() {
        return salesmans.size();
    }
    
    public int getMostExpensiveSale() {
        
        Map<Integer, Double> expansiveSales = new HashMap<>();

        for (Sales sales : sales ) 
        {
            double value = 0;
            
            for ( Item item : sales.getListItems() ) 
            {
                value += item.getPrice() * item.getQuantity();
            }
            
            expansiveSales.put(sales.getSaleId(), value);
        }
        
        return (int) sortMapByValue( expansiveSales, true ).keySet().stream().findFirst().get();
    }

    public String getWorstSalesman()
    {
        Map<String, Double> worstSalesman = new HashMap<>();

        for (Sales sales : sales ) 
        {
            double value = 0;
            
            for ( Item item : sales.getListItems() ) 
            {
                value += item.getPrice() * item.getQuantity();
            }
            
            worstSalesman.put(sales.getNameSalesman(), value);
        }
        
        return (String) sortMapByValue( worstSalesman, false ).keySet().stream().findFirst().get();
    }
    
    private Map sortMapByValue(Map map, boolean reverse ) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        
        if ( reverse )
        {
           Collections.reverse(list); 
        }
        
        Map<Integer, Double> result = new LinkedHashMap<>();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry<Integer, Double> entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
