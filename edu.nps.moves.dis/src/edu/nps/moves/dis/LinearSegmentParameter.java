package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 5.2.48: Linear segment parameters
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class LinearSegmentParameter extends Object implements Serializable
{
   /** number of segments */
   protected short  segmentNumber;

   /** segment appearance */
   protected SixByteChunk  segmentAppearance = new SixByteChunk(); 

   /** location */
   protected Vector3Double  location = new Vector3Double(); 

   /** orientation */
   protected Orientation  orientation = new Orientation(); 

   /** segmentLength */
   protected int  segmentLength;

   /** segmentWidth */
   protected int  segmentWidth;

   /** segmentHeight */
   protected int  segmentHeight;

   /** segment Depth */
   protected int  segmentDepth;

   /** segment Depth */
   protected long  pad1;


/** Constructor */
 public LinearSegmentParameter()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // segmentNumber
   marshalSize = marshalSize + segmentAppearance.getMarshalledSize();  // segmentAppearance
   marshalSize = marshalSize + location.getMarshalledSize();  // location
   marshalSize = marshalSize + orientation.getMarshalledSize();  // orientation
   marshalSize = marshalSize + 2;  // segmentLength
   marshalSize = marshalSize + 2;  // segmentWidth
   marshalSize = marshalSize + 2;  // segmentHeight
   marshalSize = marshalSize + 2;  // segmentDepth
   marshalSize = marshalSize + 4;  // pad1

   return marshalSize;
}


public void setSegmentNumber(short pSegmentNumber)
{ segmentNumber = pSegmentNumber;
}

@XmlAttribute
public short getSegmentNumber()
{ return segmentNumber; 
}

public void setSegmentAppearance(SixByteChunk pSegmentAppearance)
{ segmentAppearance = pSegmentAppearance;
}

@XmlElement
public SixByteChunk getSegmentAppearance()
{ return segmentAppearance; 
}

public void setLocation(Vector3Double pLocation)
{ location = pLocation;
}

@XmlElement
public Vector3Double getLocation()
{ return location; 
}

public void setOrientation(Orientation pOrientation)
{ orientation = pOrientation;
}

@XmlElement
public Orientation getOrientation()
{ return orientation; 
}

public void setSegmentLength(int pSegmentLength)
{ segmentLength = pSegmentLength;
}

@XmlAttribute
public int getSegmentLength()
{ return segmentLength; 
}

public void setSegmentWidth(int pSegmentWidth)
{ segmentWidth = pSegmentWidth;
}

@XmlAttribute
public int getSegmentWidth()
{ return segmentWidth; 
}

public void setSegmentHeight(int pSegmentHeight)
{ segmentHeight = pSegmentHeight;
}

@XmlAttribute
public int getSegmentHeight()
{ return segmentHeight; 
}

public void setSegmentDepth(int pSegmentDepth)
{ segmentDepth = pSegmentDepth;
}

@XmlAttribute
public int getSegmentDepth()
{ return segmentDepth; 
}

public void setPad1(long pPad1)
{ pad1 = pPad1;
}

@XmlAttribute
public long getPad1()
{ return pad1; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)segmentNumber);
       segmentAppearance.marshal(dos);
       location.marshal(dos);
       orientation.marshal(dos);
       dos.writeShort( (short)segmentLength);
       dos.writeShort( (short)segmentWidth);
       dos.writeShort( (short)segmentHeight);
       dos.writeShort( (short)segmentDepth);
       dos.writeInt( (int)pad1);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       segmentNumber = (short)dis.readUnsignedByte();
       segmentAppearance.unmarshal(dis);
       location.unmarshal(dis);
       orientation.unmarshal(dis);
       segmentLength = (int)dis.readUnsignedShort();
       segmentWidth = (int)dis.readUnsignedShort();
       segmentHeight = (int)dis.readUnsignedShort();
       segmentDepth = (int)dis.readUnsignedShort();
       pad1 = dis.readInt();
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
       buff.put( (byte)segmentNumber);
       segmentAppearance.marshal(buff);
       location.marshal(buff);
       orientation.marshal(buff);
       buff.putShort( (short)segmentLength);
       buff.putShort( (short)segmentWidth);
       buff.putShort( (short)segmentHeight);
       buff.putShort( (short)segmentDepth);
       buff.putInt( (int)pad1);
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
       segmentNumber = (short)(buff.get() & 0xFF);
       segmentAppearance.unmarshal(buff);
       location.unmarshal(buff);
       orientation.unmarshal(buff);
       segmentLength = (int)(buff.getShort() & 0xFFFF);
       segmentWidth = (int)(buff.getShort() & 0xFFFF);
       segmentHeight = (int)(buff.getShort() & 0xFFFF);
       segmentDepth = (int)(buff.getShort() & 0xFFFF);
       pad1 = buff.getInt();
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(LinearSegmentParameter rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (segmentNumber == rhs.segmentNumber)) ivarsEqual = false;
     if( ! (segmentAppearance.equals( rhs.segmentAppearance) )) ivarsEqual = false;
     if( ! (location.equals( rhs.location) )) ivarsEqual = false;
     if( ! (orientation.equals( rhs.orientation) )) ivarsEqual = false;
     if( ! (segmentLength == rhs.segmentLength)) ivarsEqual = false;
     if( ! (segmentWidth == rhs.segmentWidth)) ivarsEqual = false;
     if( ! (segmentHeight == rhs.segmentHeight)) ivarsEqual = false;
     if( ! (segmentDepth == rhs.segmentDepth)) ivarsEqual = false;
     if( ! (pad1 == rhs.pad1)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
