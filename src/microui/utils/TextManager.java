package microui.utils;


import java.util.Vector;
import javax.microedition.lcdui.Font;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simtec
 */
public class TextManager
{

    int width;
    Font font;
//    Vector data = new Vector();
    Vector lines = new Vector();
    
    public TextManager(Font font, int width)
    {
        this.width = width;
        this.font = font;
        System.out.println("start");
//        data.addElement("Formulated new product lines and conducted the relevant laboratory experiments.");
//        data.addElement("Performed a study of the shelf life of various canned foods.");
//        data.addElement("Tested new products to check that they met EEC guidelines for bacteriological content.");
//        data.addElement("CVTips has a large collection of sample resumes to choose from. Job seekers sending out a resume in hope of gaining a particular position might want to ...");
//
//        generateLines(200, f);
    }

    public void insertLine(String line)
    {
        processLine(line);
        F.echo("line added\t+ " + line);
    }

    public void insertText(String lines)
    {
        int lineStart = 0, lineEnd = 0;
        F.echo("block insert - start");
        while(true)
        {
            lineStart   = lineEnd;
            lineEnd     = lines.indexOf("\n", lineStart+1);

            F.echo("line: " + lineStart + "\t" + lineEnd);

            // check : end of document
            if(lineEnd == -1)
            {
                F.echo("last line");
//                F.echo(lines.substring(lineStart).trim());
                insertLine(lines.substring(lineStart).trim());
                break;
            }
            // normal line process
            insertLine(lines.substring(lineStart, lineEnd).trim());

        }
        F.echo("block insert - end");
    }

    public void insertFile(String url)
    {
        TextReader tr   = new TextReader(url);
        String text     =tr.getText();
        insertText(text);
    }

    private void processLine(String lineStr)
    {
        System.out.println("process: " + lineStr);
        System.out.println(font.stringWidth(lineStr) + " < " + width);
        if (font.stringWidth(lineStr) < width)
        {
            lines.addElement(lineStr);
            System.out.println("+" + lineStr);
        } else
        {
            String sample = "";
            int spaceIndex = 0;
            int nextSpaceIndex = 0;
            while (true)
            {
                nextSpaceIndex = lineStr.indexOf(" ", spaceIndex + 1);
                // if line end
                if(nextSpaceIndex == -1)
                {
                    lines.addElement( lineStr.substring(0, spaceIndex).trim() );
                    System.out.println("+" + lineStr.substring(0, spaceIndex).trim());
                    lines.addElement( lineStr.substring(spaceIndex).trim() );
                    System.out.println("+" + lineStr.substring(spaceIndex).trim());
                    break;
                }
                sample = lineStr.substring(0, nextSpaceIndex);
                System.out.println(font.stringWidth(sample) + " > " + width + "\t" + sample);
                if(font.stringWidth(sample) > width)
                {
                    lines.addElement( lineStr.substring(0, spaceIndex).trim() );
                    System.out.println("+" + lineStr.substring(0, spaceIndex).trim());
                    // recursive : process remain line words
                    processLine(lineStr.substring(spaceIndex).trim());
                    break;
                }
                spaceIndex = nextSpaceIndex;
            }
        }
    }

    public void print() {
        System.out.println("\n\n");
        for(int i=0; i<lines.size(); i++)
        {
            System.out.println( (String) lines.elementAt(i) );
        }
    }

    // fast static methods

    public static Vector getParagraphLines(String lineStr, Font font, int width)
    {
        TextManager manager = new TextManager(font, width);
        manager.insertLine(lineStr);

        if(font == null)
            font = Font.getDefaultFont();

        return manager.lines;
    }

    public static String[] getParagraphArray(String lineStr, Font font, int width) {
        Vector lines = getParagraphLines(lineStr, font, width);
        String[] array = new String[lines.size()];
        for(int i=0; i<array.length; i++) {
            array[i] = (String) lines.elementAt(i);
        }
        return array;
    }
}
