package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Record sets, used in transfer control request PDU
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class RecordSet extends Object implements Serializable
{
   /** record ID */
   protected long  recordID;

   /** record set serial number */
   protected long  recordSetSerialNumber;

   /** record length */
   protected int  recordLength;

   /** record count */
   protected int  recordCount;

   /** ^^^This is wrong--variable sized data records */
   protected int  recordValues;

   /** ^^^This is wrong--variable sized padding */
   protected short  pad4;


/** Constructor */
 public RecordSet()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // recordID
   marshalSize = marshalSize + 4;  // recordSetSerialNumber
   marshalSize = marshalSize + 2;  // recordLength
   marshalSize = marshalSize + 2;  // recordCount
   marshalSize = marshalSize + 2;  // recordValues
   marshalSize = marshalSize + 1;  // pad4

   return marshalSize;
}


public void setRecordID(long pRecordID)
{ recordID = pRecordID;
}

@XmlAttribute
public long getRecordID()
{ return recordID; 
}

public void setRecordSetSerialNumber(long pRecordSetSerialNumber)
{ recordSetSerialNumber = pRecordSetSerialNumber;
}

@XmlAttribute
public long getRecordSetSerialNumber()
{ return recordSetSerialNumber; 
}

public void setRecordLength(int pRecordLength)
{ recordLength = pRecordLength;
}

@XmlAttribute
public int getRecordLength()
{ return recordLength; 
}

public void setRecordCount(int pRecordCount)
{ recordCount = pRecordCount;
}

@XmlAttribute
public int getRecordCount()
{ return recordCount; 
}

public void setRecordValues(int pRecordValues)
{ recordValues = pRecordValues;
}

@XmlAttribute
public int getRecordValues()
{ return recordValues; 
}

public void setPad4(short pPad4)
{ pad4 = pPad4;
}

@XmlAttribute
public short getPad4()
{ return pad4; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeInt( (int)recordID);
       dos.writeInt( (int)recordSetSerialNumber);
       dos.writeShort( (short)recordLength);
       dos.writeShort( (short)recordCount);
       dos.writeShort( (short)recordValues);
       dos.writeByte( (byte)pad4);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       recordID = dis.readInt();
       recordSetSerialNumber = dis.readInt();
       recordLength = (int)dis.readUnsignedShort();
       recordCount = (int)dis.readUnsignedShort();
       recordValues = (int)dis.readUnsignedShort();
       pad4 = (short)dis.readUnsignedByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


/**
 * Packs a Pdu into the ByteBuffer.
 * @throws java.nio.BufferOverflowException if buff is too small
 * @throws java.nio.ReadOnlyBufferException if buff is read only
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin writing
 * @since ??
 */
public void marshal(java.nio.ByteBuffer buff)
{
       buff.putInt( (int)recordID);
       buff.putInt( (int)recordSetSerialNumber);
       buff.putShort( (short)recordLength);
       buff.putShort( (short)recordCount);
       buff.putShort( (short)recordValues);
       buff.put( (byte)pad4);
    } // end of marshal method

/**
 * Unpacks a Pdu from the underlying data.
 * @throws java.nio.BufferUnderflowException if buff is too small
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin reading
 * @since ??
 */
public void unmarshal(java.nio.ByteBuffer buff)
{
       recordID = buff.getInt();
       recordSetSerialNumber = buff.getInt();
       recordLength = (int)(buff.getShort() & 0xFFFF);
       recordCount = (int)(buff.getShort() & 0xFFFF);
       recordValues = (int)(buff.getShort() & 0xFFFF);
       pad4 = (short)(buff.get() & 0xFF);
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(RecordSet rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (recordID == rhs.recordID)) ivarsEqual = false;
     if( ! (recordSetSerialNumber == rhs.recordSetSerialNumber)) ivarsEqual = false;
     if( ! (recordLength == rhs.recordLength)) ivarsEqual = false;
     if( ! (recordCount == rhs.recordCount)) ivarsEqual = false;
     if( ! (recordValues == rhs.recordValues)) ivarsEqual = false;
     if( ! (pad4 == rhs.pad4)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
