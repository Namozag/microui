package microui.utils.rms;


import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simtec
 */
public class RMSRecord
{
    private String REC_NAME = "";
    RecordStore record;

    public RMSRecord(String name)
    {
        this.REC_NAME = name;
        record = null;
        F.echo("Record Store: " + REC_NAME + " created");
        open();
    }

    public boolean open()
    {
        try {
            record = RecordStore.openRecordStore(REC_NAME, true);
            F.echo("Record Store: " + REC_NAME + " opened");
        } catch (Exception ex)
        {
            F.error("open", ex);
            return false;
        }
        return true;
    }

    public boolean close()
    {
        try {
            record.closeRecordStore();
            F.echo("Record Store: " + REC_NAME + " closed");
        } catch (Exception ex)
        {
            F.error("close", ex);
            return false;
        }
        return true;
    }

    public boolean delete()
    {
        close();
        try {
            RecordStore.deleteRecordStore(REC_NAME);
            F.echo("Record Store: " + REC_NAME + " deleted");
        } catch (Exception ex)
        {
            F.error("delete", ex);
            return false;
        }
        return true;
    }


    public Vector getRecords()
    {
        Vector items = new Vector();
        F.echo("Record Store: " + REC_NAME + " - Begin Reading");
        try
        {
            RecordEnumeration records = record.enumerateRecords(null, null, false);
            while(records.hasNextElement())
            {
                String recordString = new String(records.nextRecord());
                items.addElement(recordString);
                System.out.println("Record - added :" + recordString);
            }

        } catch (Exception ex)
        {
            F.error("read", ex);
            return null;
        }
        F.echo("Record Store: " + REC_NAME + " - end Reading");
        return items;
    }

    public boolean read()
    {
        byte[] bytes = new byte[5];
        int count = 0;
        F.echo("Record Store: " + REC_NAME + " - Begin Reading");
        try
        {
            count = record.getNumRecords();
            F.echo("records count: " + count);
            int len = 0;
            for (int i = 1; i < count+1; i++)
            {
                if (record.getRecordSize(i) > bytes.length)
                {
                    bytes = new byte[record.getRecordSize(i)];
                }
                len = record.getRecord(i, bytes, 0);
                System.out.println("Record " + i + " : " + new String(bytes, 0, len));
            }
        } catch (Exception ex)
        {
            F.error("read", ex);
            return false;
        }
        F.echo("Record Store: " + REC_NAME + " - end Reading");
        return true;
    }

    public boolean saveRecord(String str)
    {
        byte[] bytes = str.getBytes();
        try{
            record.addRecord(bytes, 0, bytes.length);
            F.echo("Record : [" + str + "] saved in " + REC_NAME);
        }catch(Exception ex)
        {
            F.error("save", ex);
            return false;
        }
        return true;
    }

    public boolean saveRecord(int recordId, String str)
    {
        byte[] bytes = str.getBytes();
        try{
            record.setRecord(recordId, bytes, 0, bytes.length);
            F.echo("Record : [" + str + "] saved in " + REC_NAME);
        }catch(Exception ex)
        {
            F.error("save", ex);
            return false;
        }
        return true;
    }

    public boolean deleteRecord(int recordId)
    {
        try {
            record.deleteRecord(recordId);
            F.echo("Record : [" + recordId + "] deleted in " + REC_NAME);
        } catch (Exception ex)
        {
            F.error("deleteRecord", ex);
            return false;
        }
        return true;
    }

    public int getRecordId(String prefix)
    {
        byte[] bytes;
        F.echo("Record Store: " + REC_NAME + " - Begin Reading comparing with: " + prefix);
        try
        {
            int count = record.getNumRecords();
            F.echo("records count: " + count);
//            int len = 0;
            for (int i = 1; i < count+1; i++)
            {
//                bytes = new byte[record.getRecordSize(i)];
//                if (record.getRecordSize(i) > bytes.length)
//                {
//                    bytes = new byte[record.getRecordSize(i)];
//                }
//                len = record.getRecord(i, bytes, 0);
//                record.getR
//                System.out.println("Record " + i + " : " + new String(bytes, 0, len));
                bytes = record.getRecord(i);
                String recordString = new String(bytes, 0, bytes.length);
                System.out.println(i + ": " + recordString);
                if ( recordString.startsWith(prefix) )
                    return i;
            }
        } catch (Exception ex)
        {
            F.error("read", ex);
            return -1;
        }
        F.echo("Record Store: " + REC_NAME + " - end Reading");
        return -1;
    }

    public String readRecord(int recordId)
    {
        byte[] bytes = null;
        int len = 0;
        try
        {
            bytes = new byte[record.getRecordSize(recordId)];
            len = record.getRecord(recordId, bytes, 0);
            System.out.println("Record " + recordId + " : " + new String(bytes, 0, len));
        }
        catch(Exception ex) {
            F.error("readRecord", ex);
            return null;
        }
        return new String(bytes, 0, len);
    }
}