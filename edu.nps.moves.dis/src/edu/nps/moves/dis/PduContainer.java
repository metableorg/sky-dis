package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Used for XML compatability. A container that holds PDUs
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@XmlRootElement
public class PduContainer extends Object implements Serializable
{
   /** Number of PDUs in the container list */
   protected int  numberOfPdus;

   /** record sets */
   protected List< Pdu> pdus = new ArrayList<Pdu>(); 

/** Constructor */
 public PduContainer()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // numberOfPdus
   for(int idx=0; idx < pdus.size(); idx++)
   {
        Pdu listElement = pdus.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


@XmlAttribute
public int getNumberOfPdus()
{ return (int)pdus.size();
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfPdus method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfPdus(int pNumberOfPdus)
{ numberOfPdus = pNumberOfPdus;
}

public void setPdus(List<Pdu> pPdus)
{ pdus = pPdus;
}

@XmlElementWrapper(name="pdusList" )
public List<Pdu> getPdus()
{ return pdus; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeInt( (int)pdus.size());

       for(int idx = 0; idx < pdus.size(); idx++)
       {
            Pdu aPdu = pdus.get(idx);
            aPdu.marshal(dos);
       } // end of list marshalling

    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       numberOfPdus = dis.readInt();
       for(int idx = 0; idx < numberOfPdus; idx++)
       {
           Pdu anX = new Pdu();
           anX.unmarshal(dis);
           pdus.add(anX);
       }

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
       buff.putInt( (int)pdus.size());

       for(int idx = 0; idx < pdus.size(); idx++)
       {
            Pdu aPdu = (Pdu)pdus.get(idx);
            aPdu.marshal(buff);
       } // end of list marshalling

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
       numberOfPdus = buff.getInt();
       for(int idx = 0; idx < numberOfPdus; idx++)
       {
            Pdu anX = new Pdu();
            anX.unmarshal(buff);
            pdus.add(anX);
       }

 } // end of unmarshal method 

/**
* JAXB marshalls (by default) only classes that are marked with @XmlRootElement.
* This is a convienience method for marshalling the top level root element. 
* Note that this requires the presence of jaxb.index in the package directory.
*/
public void marshallToXml(String filename)
{
  try
   {
       JAXBContext context = JAXBContext.newInstance();
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marshaller.marshal(this, new FileOutputStream(filename));
    } // End try
    catch(Exception e) {System.out.println(e);
};
}


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(PduContainer rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (numberOfPdus == rhs.numberOfPdus)) ivarsEqual = false;

     for(int idx = 0; idx < pdus.size(); idx++)
     {
        if( ! ( pdus.get(idx).equals(rhs.pdus.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
