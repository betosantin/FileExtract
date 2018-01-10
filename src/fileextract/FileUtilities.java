/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileextract;

/**
 *
 * @author Roberto Santin
 */
public class FileUtilities {
    
    public static String DataInPath = "/data/in/";
    public static String DataOutPath = "/data/out/";
    public static String delimiter = "ç";
    
    private String getHomePath()
    {
        return System.getProperty("user.home");
    }
    
    public String getDataInPath()
    {
        return getHomePath() + DataInPath;
    }
    
    public String getDataOutPath()
    {
        return getHomePath() + DataOutPath;
    }
    
}
