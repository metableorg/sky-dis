package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Used in UaPdu
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@SuppressWarnings("serial")
public class AcousticBeamFundamentalParameter extends Object implements Serializable
{
   /** parameter index */
   protected int  activeEmissionParameterIndex;

   /** scan pattern */
   protected int  scanPattern;

   /** beam center azimuth */
   protected float  beamCenterAzimuth;

   /** azimuthal beamwidth */
   protected float  azimuthalBeamwidth;

   /** beam center */
   protected float  beamCenterDE;

   /** DE beamwidth (vertical beamwidth) */
   protected float  deBeamwidth;


/** Constructor */
 public AcousticBeamFundamentalParameter()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // activeEmissionParameterIndex
   marshalSize = marshalSize + 2;  // scanPattern
   marshalSize = marshalSize + 4;  // beamCenterAzimuth
   marshalSize = marshalSize + 4;  // azimuthalBeamwidth
   marshalSize = marshalSize + 4;  // beamCenterDE
   marshalSize = marshalSize + 4;  // deBeamwidth

   return marshalSize;
}


public void setActiveEmissionParameterIndex(int pActiveEmissionParameterIndex)
{ activeEmissionParameterIndex = pActiveEmissionParameterIndex;
}

@XmlAttribute
public int getActiveEmissionParameterIndex()
{ return activeEmissionParameterIndex; 
}

public void setScanPattern(int pScanPattern)
{ scanPattern = pScanPattern;
}

@XmlAttribute
public int getScanPattern()
{ return scanPattern; 
}

public void setBeamCenterAzimuth(float pBeamCenterAzimuth)
{ beamCenterAzimuth = pBeamCenterAzimuth;
}

@XmlAttribute
public float getBeamCenterAzimuth()
{ return beamCenterAzimuth; 
}

public void setAzimuthalBeamwidth(float pAzimuthalBeamwidth)
{ azimuthalBeamwidth = pAzimuthalBeamwidth;
}

@XmlAttribute
public float getAzimuthalBeamwidth()
{ return azimuthalBeamwidth; 
}

public void setBeamCenterDE(float pBeamCenterDE)
{ beamCenterDE = pBeamCenterDE;
}

@XmlAttribute
public float getBeamCenterDE()
{ return beamCenterDE; 
}

public void setDeBeamwidth(float pDeBeamwidth)
{ deBeamwidth = pDeBeamwidth;
}

@XmlAttribute
public float getDeBeamwidth()
{ return deBeamwidth; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)activeEmissionParameterIndex);
       dos.writeShort( (short)scanPattern);
       dos.writeFloat( (float)beamCenterAzimuth);
       dos.writeFloat( (float)azimuthalBeamwidth);
       dos.writeFloat( (float)beamCenterDE);
       dos.writeFloat( (float)deBeamwidth);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       activeEmissionParameterIndex = (int)dis.readUnsignedShort();
       scanPattern = (int)dis.readUnsignedShort();
       beamCenterAzimuth = dis.readFloat();
       azimuthalBeamwidth = dis.readFloat();
       beamCenterDE = dis.readFloat();
       deBeamwidth = dis.readFloat();
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
       buff.putShort( (short)activeEmissionParameterIndex);
       buff.putShort( (short)scanPattern);
       buff.putFloat( (float)beamCenterAzimuth);
       buff.putFloat( (float)azimuthalBeamwidth);
       buff.putFloat( (float)beamCenterDE);
       buff.putFloat( (float)deBeamwidth);
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
       activeEmissionParameterIndex = (int)(buff.getShort() & 0xFFFF);
       scanPattern = (int)(buff.getShort() & 0xFFFF);
       beamCenterAzimuth = buff.getFloat();
       azimuthalBeamwidth = buff.getFloat();
       beamCenterDE = buff.getFloat();
       deBeamwidth = buff.getFloat();
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(AcousticBeamFundamentalParameter rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (activeEmissionParameterIndex == rhs.activeEmissionParameterIndex)) ivarsEqual = false;
     if( ! (scanPattern == rhs.scanPattern)) ivarsEqual = false;
     if( ! (beamCenterAzimuth == rhs.beamCenterAzimuth)) ivarsEqual = false;
     if( ! (azimuthalBeamwidth == rhs.azimuthalBeamwidth)) ivarsEqual = false;
     if( ! (beamCenterDE == rhs.beamCenterDE)) ivarsEqual = false;
     if( ! (deBeamwidth == rhs.deBeamwidth)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
