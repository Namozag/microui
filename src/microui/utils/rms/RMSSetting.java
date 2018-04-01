package microui.utils.rms;


import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreNotOpenException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simtec
 */
public class RMSSetting
{
    RecordStore record;

    public static boolean hasValue(String name)
    {
        boolean is = false;
        RecordStore record = null;
        try {
            record = RecordStore.openRecordStore(name, true);   echo("Record Store: " + name + " opened");
        } catch (Exception ex)  { error("open", ex); }
        
        try {
            is = record.getNumRecords() > 0;
        } catch (RecordStoreNotOpenException ex) {}

        try {
            record.closeRecordStore();  echo("Record Store: " + name + " closed");
        } catch (Exception ex)  { error("close", ex);}

        return is;
    }

    public static String getValue(String name)
    {
        RecordStore record = null;
        try {
            record = RecordStore.openRecordStore(name, false);   echo("Record Store: " + name + " opened");
        } catch (Exception ex)  { error("open", ex); return "";}

        byte[] bytes = null;
        int len = 0;
        try {
            bytes = new byte[record.getRecordSize(1)];
            len = record.getRecord(1, bytes, 0);
            System.out.println("Record :" + new String(bytes, 0, len));
        }
        catch(Exception ex) { error("readRecord", ex);return null;}

        try {
            record.closeRecordStore();  echo("Record Store: " + name + " closed");
        } catch (Exception ex)  { error("close", ex);}
        System.out.println("RMSSetting get \t [" + name + "]\t= [" + new String(bytes, 0, len) + "]");
        return new String(bytes, 0, len);
    }

    public static void setValue(String name, String value)
    {
        RecordStore record = null;
        try {
            record = RecordStore.openRecordStore(name, true);   echo("Record Store: " + name + " opened");
        } catch (Exception ex)  { error("open", ex); }


        byte[] bytes = value.getBytes();
        try{
            if(record.getNumRecords() > 0)
                record.setRecord(1, bytes, 0, bytes.length);
            else
                record.addRecord(bytes, 0, bytes.length);
            echo("Record saved : " + name + " = " + value);
        }catch(Exception ex){error("save", ex);}

        try {
            record.closeRecordStore();  echo("Record Store: " + name + " closed");
        } catch (Exception ex)  { error("close", ex);}

        System.out.println("RMSSetting set \t [" + name + "]\t= [" + value + "]");
    }

    public boolean delete(String name)
    {
        try {
            RecordStore.deleteRecordStore(name); echo("Record Store: " + name + " deleted");
        } catch (Exception ex)
        {
            error("delete", ex);
            return false;
        }
        return true;
    }   

    public static void echo(String s)
    {
        System.out.println(s);
    }

    public static void error(String message, Exception ex)
    {
        System.out.println("ERROR\t" + message + "\t\t" + ex.getMessage());
    }
}
