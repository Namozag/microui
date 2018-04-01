package microui.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.microedition.io.Connector;
import javax.microedition.io.ContentConnection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simtec
 */
public class TextReader
{
    String url;

    public TextReader(String url)
    {
        this.url = url;
    }

    public String getText()
    {
        int inputCh;
        String text = "";
        InputStream inStream = getInputStream();
        InputStreamReader reader = null;

        F.echo("reading text stream: " + url);

        try {
            reader = new InputStreamReader(inStream, "UTF-8");
            while ((inputCh = reader.read()) != -1) {
                text += (char) inputCh;
            }
        } catch (IOException ex) {
            return "";
        }
        return text;
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
//                error   = ERROR_CONNECT;
                return  null;
            }
        }else{
            // Reading from local file
            return getClass().getResourceAsStream(url);
        }
    }
}
