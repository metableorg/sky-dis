package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Section 5.3.6.5. Acknowledge the receiptof a start/resume, stop/freeze, or RemoveEntityPDU. COMPLETE
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@SuppressWarnings("serial")
public class AcknowledgePdu extends SimulationManagementFamilyPdu implements Serializable
{
   /** type of message being acknowledged */
   protected int  acknowledgeFlag;

   /** Whether or not the receiving entity was able to comply with the request */
   protected int  responseFlag;

   /** Request ID that is unique */
   protected long  requestID;


/** Constructor */
 public AcknowledgePdu()
 {
    setPduType( (short)15 );
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 2;  // acknowledgeFlag
   marshalSize = marshalSize + 2;  // responseFlag
   marshalSize = marshalSize + 4;  // requestID

   return marshalSize;
}


public void setAcknowledgeFlag(int pAcknowledgeFlag)
{ acknowledgeFlag = pAcknowledgeFlag;
}

@XmlAttribute
public int getAcknowledgeFlag()
{ return acknowledgeFlag; 
}

public void setResponseFlag(int pResponseFlag)
{ responseFlag = pResponseFlag;
}

@XmlAttribute
public int getResponseFlag()
{ return responseFlag; 
}

public void setRequestID(long pRequestID)
{ requestID = pRequestID;
}

@XmlAttribute
public long getRequestID()
{ return requestID; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeShort( (short)acknowledgeFlag);
       dos.writeShort( (short)responseFlag);
       dos.writeInt( (int)requestID);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
     super.unmarshal(dis);

    try 
    {
       acknowledgeFlag = (int)dis.readUnsignedShort();
       responseFlag = (int)dis.readUnsignedShort();
       requestID = dis.readInt();
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
       super.marshal(buff);
       buff.putShort( (short)acknowledgeFlag);
       buff.putShort( (short)responseFlag);
       buff.putInt( (int)requestID);
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
       super.unmarshal(buff);

       acknowledgeFlag = (int)(buff.getShort() & 0xFFFF);
       responseFlag = (int)(buff.getShort() & 0xFFFF);
       requestID = buff.getInt();
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(AcknowledgePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (acknowledgeFlag == rhs.acknowledgeFlag)) ivarsEqual = false;
     if( ! (responseFlag == rhs.responseFlag)) ivarsEqual = false;
     if( ! (requestID == rhs.requestID)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
