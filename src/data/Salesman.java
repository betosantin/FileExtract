package data;

import fileextract.FileUtilities;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Roberto Santin
 */
public class Salesman {

    private long CPF;
    private String Name;
    private double Salary;

    public Salesman(long CPF, String Name, double Salary) {
        this.CPF = CPF;
        this.Name = Name;
        this.Salary = Salary;
    }

    public Salesman(String information) {
        /* Salesman data
            Delimiter = ç
            Tokenizer 0 = idType
                      1 = CPF
                      2 = Name
                      3 = Salary
         */

        List<String> alist = new ArrayList();
        StringTokenizer st = new StringTokenizer(information, FileUtilities.delimiter );
        while (st.hasMoreTokens()) {
            alist.add(st.nextToken());
        }

        this.CPF = Long.valueOf((alist.get(1)));
        this.Name = String.valueOf((alist.get(2)));
        this.Salary = Double.valueOf((alist.get(3)));
    }

    public long getCPF() {
        return CPF;
    }

    public String getName() {
        return Name;
    }

    public double getSalary() {
        return Salary;
    }

    @Override
    public String toString() {
        return "CPF: " + CPF + ", Nome: " + Name + ", Salário: " + Salary;
    }
}
