package microui.utils;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author h
 */
public class LocManager
{
    String code;
    Hashtable words;
    private LocManager(String code)
    {
        this.code   = code;
        getLocalizedFile();
    }

    // singleton
    private static LocManager locManager;
    public static LocManager getInstance(String code)
    {
        // if first call
        // if we want another language
        if(locManager == null || !locManager.code.equals(code))
            locManager = new LocManager(code);

        // return the current object
        return locManager;
    }

    public String get(String name)
    {
        return (String) words.get(name);
    }

    private Hashtable getLocalizedFile() {
        words = new Hashtable();
        // file stream
        String url = "/loc/" + code + ".txt";
        System.out.println("@ " + url);

        try {
            InputStream inStream = url.getClass().getResourceAsStream(url);
            InputStreamReader inReader = new InputStreamReader(inStream, "UTF-8");

            int chReader;
            StringBuffer sb = new StringBuffer();
            String wordName = "", wordValue = "";

            while (true) {
                chReader = inReader.read();
                char ch = (char) chReader;

                if (ch == '\t' || ch == '=') {
                    wordName = sb.toString();
                    sb = new StringBuffer();
                } else if (ch == '\r' || chReader == -1) {
                    wordValue = sb.toString();
                    // make word pair
//                    if(wordName.length() > 0){
                        words.put(wordName, wordValue);
                        System.out.println("+ [" + wordName + "]\t\t[" + wordValue + "]");
//                    }

                    if (chReader == -1) {
                        break;
                    }

                    // clear
                    wordName = "";
                    wordValue = "";
                    sb = new StringBuffer();
                    
                } else if (ch == '\n' || chReader == 65279) {
                } else {
                    sb.append((char) ch);
                }
            }
            System.out.println("# " + words.size());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.toString());
        }
        return words;
    }

}
