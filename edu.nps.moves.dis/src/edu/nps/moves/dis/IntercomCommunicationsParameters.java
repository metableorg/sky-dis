package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 5.2.46.  Intercom communcations parameters
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IntercomCommunicationsParameters extends Object implements Serializable
{
   /** Type of intercom parameters record */
   protected int  recordType;

   /** length of record */
   protected int  recordLength;

   /** Jerks. Looks like the committee is forcing a lookup of the record type parameter to find out how long the field is. This is a placeholder. */
   protected long  recordSpecificField;


/** Constructor */
 public IntercomCommunicationsParameters()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // recordType
   marshalSize = marshalSize + 2;  // recordLength
   marshalSize = marshalSize + 4;  // recordSpecificField

   return marshalSize;
}


public void setRecordType(int pRecordType)
{ recordType = pRecordType;
}

@XmlAttribute
public int getRecordType()
{ return recordType; 
}

public void setRecordLength(int pRecordLength)
{ recordLength = pRecordLength;
}

@XmlAttribute
public int getRecordLength()
{ return recordLength; 
}

public void setRecordSpecificField(long pRecordSpecificField)
{ recordSpecificField = pRecordSpecificField;
}

@XmlAttribute
public long getRecordSpecificField()
{ return recordSpecificField; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)recordType);
       dos.writeShort( (short)recordLength);
       dos.writeInt( (int)recordSpecificField);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       recordType = (int)dis.readUnsignedShort();
       recordLength = (int)dis.readUnsignedShort();
       recordSpecificField = dis.readInt();
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
       buff.putShort( (short)recordType);
       buff.putShort( (short)recordLength);
       buff.putInt( (int)recordSpecificField);
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
       recordType = (int)(buff.getShort() & 0xFFFF);
       recordLength = (int)(buff.getShort() & 0xFFFF);
       recordSpecificField = buff.getInt();
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(IntercomCommunicationsParameters rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (recordType == rhs.recordType)) ivarsEqual = false;
     if( ! (recordLength == rhs.recordLength)) ivarsEqual = false;
     if( ! (recordSpecificField == rhs.recordSpecificField)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
