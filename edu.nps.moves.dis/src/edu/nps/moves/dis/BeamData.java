package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Section 5.2.39. Specification of the data necessary to  describe the scan volume of an emitter.
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class BeamData extends Object implements Serializable
{
   /** Specifies the beam azimuth an elevation centers and corresponding half-angles     to describe the scan volume */
   protected float  beamAzimuthCenter;

   /** Specifies the beam azimuth sweep to determine scan volume */
   protected float  beamAzimuthSweep;

   /** Specifies the beam elevation center to determine scan volume */
   protected float  beamElevationCenter;

   /** Specifies the beam elevation sweep to determine scan volume */
   protected float  beamElevationSweep;

   /** allows receiver to synchronize its regenerated scan pattern to     that of the emmitter. Specifies the percentage of time a scan is through its pattern from its origion. */
   protected float  beamSweepSync;


/** Constructor */
 public BeamData()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // beamAzimuthCenter
   marshalSize = marshalSize + 4;  // beamAzimuthSweep
   marshalSize = marshalSize + 4;  // beamElevationCenter
   marshalSize = marshalSize + 4;  // beamElevationSweep
   marshalSize = marshalSize + 4;  // beamSweepSync

   return marshalSize;
}


public void setBeamAzimuthCenter(float pBeamAzimuthCenter)
{ beamAzimuthCenter = pBeamAzimuthCenter;
}

@XmlAttribute
public float getBeamAzimuthCenter()
{ return beamAzimuthCenter; 
}

public void setBeamAzimuthSweep(float pBeamAzimuthSweep)
{ beamAzimuthSweep = pBeamAzimuthSweep;
}

@XmlAttribute
public float getBeamAzimuthSweep()
{ return beamAzimuthSweep; 
}

public void setBeamElevationCenter(float pBeamElevationCenter)
{ beamElevationCenter = pBeamElevationCenter;
}

@XmlAttribute
public float getBeamElevationCenter()
{ return beamElevationCenter; 
}

public void setBeamElevationSweep(float pBeamElevationSweep)
{ beamElevationSweep = pBeamElevationSweep;
}

@XmlAttribute
public float getBeamElevationSweep()
{ return beamElevationSweep; 
}

public void setBeamSweepSync(float pBeamSweepSync)
{ beamSweepSync = pBeamSweepSync;
}

@XmlAttribute
public float getBeamSweepSync()
{ return beamSweepSync; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeFloat( (float)beamAzimuthCenter);
       dos.writeFloat( (float)beamAzimuthSweep);
       dos.writeFloat( (float)beamElevationCenter);
       dos.writeFloat( (float)beamElevationSweep);
       dos.writeFloat( (float)beamSweepSync);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       beamAzimuthCenter = dis.readFloat();
       beamAzimuthSweep = dis.readFloat();
       beamElevationCenter = dis.readFloat();
       beamElevationSweep = dis.readFloat();
       beamSweepSync = dis.readFloat();
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
       buff.putFloat( (float)beamAzimuthCenter);
       buff.putFloat( (float)beamAzimuthSweep);
       buff.putFloat( (float)beamElevationCenter);
       buff.putFloat( (float)beamElevationSweep);
       buff.putFloat( (float)beamSweepSync);
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
       beamAzimuthCenter = buff.getFloat();
       beamAzimuthSweep = buff.getFloat();
       beamElevationCenter = buff.getFloat();
       beamElevationSweep = buff.getFloat();
       beamSweepSync = buff.getFloat();
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(BeamData rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (beamAzimuthCenter == rhs.beamAzimuthCenter)) ivarsEqual = false;
     if( ! (beamAzimuthSweep == rhs.beamAzimuthSweep)) ivarsEqual = false;
     if( ! (beamElevationCenter == rhs.beamElevationCenter)) ivarsEqual = false;
     if( ! (beamElevationSweep == rhs.beamElevationSweep)) ivarsEqual = false;
     if( ! (beamSweepSync == rhs.beamSweepSync)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
