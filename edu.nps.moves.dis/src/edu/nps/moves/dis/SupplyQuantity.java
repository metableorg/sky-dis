package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Section 5.2.30. A supply, and the amount of that supply. Similar to an entity kind but with the addition of a quantity.
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SupplyQuantity extends Object implements Serializable
{
   /** Type of supply */
   protected EntityType  supplyType = new EntityType(); 

   /** quantity to be supplied */
   protected short  quantity;


/** Constructor */
 public SupplyQuantity()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + supplyType.getMarshalledSize();  // supplyType
   marshalSize = marshalSize + 1;  // quantity

   return marshalSize;
}


public void setSupplyType(EntityType pSupplyType)
{ supplyType = pSupplyType;
}

@XmlElement
public EntityType getSupplyType()
{ return supplyType; 
}

public void setQuantity(short pQuantity)
{ quantity = pQuantity;
}

@XmlAttribute
public short getQuantity()
{ return quantity; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       supplyType.marshal(dos);
       dos.writeByte( (byte)quantity);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       supplyType.unmarshal(dis);
       quantity = (short)dis.readUnsignedByte();
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
       supplyType.marshal(buff);
       buff.put( (byte)quantity);
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
       supplyType.unmarshal(buff);
       quantity = (short)(buff.get() & 0xFF);
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(SupplyQuantity rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (supplyType.equals( rhs.supplyType) )) ivarsEqual = false;
     if( ! (quantity == rhs.quantity)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
