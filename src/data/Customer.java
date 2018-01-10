package data;

import fileextract.FileUtilities;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Roberto Santin
 */
public class Customer {

    private long CNPJ;
    private String Name;
    private String BusinessArea;

    public Customer(long CNPJ, String Name, String BusinessArea) {
        this.CNPJ = CNPJ;
        this.Name = Name;
        this.BusinessArea = BusinessArea;
    }
    
    public Customer( String information )
    {
        /* Customer data
            Delimiter = ç
            Tokenizer 0 = idType
                      1 = CNPJ
                      2 = Name
                      3 = Business Area
         */
        
        List<String> alist = new ArrayList();
        StringTokenizer st = new StringTokenizer(information, FileUtilities.delimiter );
        while (st.hasMoreTokens()) {
            alist.add(st.nextToken());
        }

        this.CNPJ = Long.valueOf((alist.get(1)));
        this.Name = String.valueOf((alist.get(2)));
        this.BusinessArea = String.valueOf((alist.get(3)));
    }

    public long getCNPJ() {
        return CNPJ;
    }

    public String getName() {
        return Name;
    }

    public String getBusinessArea() {
        return BusinessArea;
    }
    
    @Override
    public String toString() {
        return "CNPJ: " + CNPJ + ", Nome: " + Name + ", Área de Negócio: " + BusinessArea;
    }
    
}
