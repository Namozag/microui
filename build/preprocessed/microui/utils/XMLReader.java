package microui.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.kxml.kdom.*;
import org.kxml.parser.XmlParser;
import java.io.*;
import javax.microedition.io.*;

/**
 *
 * @author simtec
 */
public class XMLReader
{
    public static final int ERROR_NONE = 0, ERROR_CONNECT = 1,  ERROR_PARSE = 2;

    private String  url;
    private Element root;
    private Document document;
    public  int     error = ERROR_NONE;
    public boolean isProcessed = false;

    public XMLReader(String url)
    {
        this.url    = url;
//        if(doProcess)
//            process();
    }

    public boolean process()
    {
        System.out.println("Processing " + url);
        document = getDocument();
        isProcessed = true;
        if(document != null){
            this.root   = document.getRootElement();
            return true;
        }
        else{
            return false;
        }
    }

    public void silentProcess()
    {
        Thread t = new Thread()
        {
            public void run()
            {
                process();
            }
        }; t.start();
    }

    public Element getRoot()
    {
        return this.root;
    }

    public String getErrorString()
    {
        switch(error)
        {
            case    ERROR_NONE      : return "No errors";
            case    ERROR_CONNECT   : return "Error in connecting to the file";
            case    ERROR_PARSE     : return "Error ni parsing the file";
        }
        return "";
    }

    public Document getDocument()
    {
        Document    document    = new Document();
        InputStream inStream    = getInputStream();
        InputStreamReader   reader;
        try {
            reader = new InputStreamReader(inStream, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            reader = new InputStreamReader(inStream);
        }

        try{
        XmlParser   parser      = new XmlParser(reader);
        document.parse(parser);
        }catch (Exception ex) {
            error   = ERROR_PARSE;
            return null;
        }

        return document;
    }

    public InputStream getInputStream()
    {
        if(url.indexOf(':') != -1)
        {
            try{
            // Readign from HTTP file
            ContentConnection connection    = (ContentConnection) Connector.open(url);
            return connection.openInputStream();
            }catch (Exception ex) {
                error   = ERROR_CONNECT;
                return  null;
            }

        }else{
            // Reading from local file
            return getClass().getResourceAsStream(url);
        }
    }

}
