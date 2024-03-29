package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Section 5.3.10.1 Abstract superclass for PDUs relating to minefields. COMPLETE
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class MinefieldStatePdu extends MinefieldFamilyPdu implements Serializable
{
   /** Minefield ID */
   protected EntityID  minefieldID = new EntityID(); 

   /** Minefield sequence */
   protected int  minefieldSequence;

   /** force ID */
   protected short  forceID;

   /** Number of permieter points */
   protected short  numberOfPerimeterPoints;

   /** type of minefield */
   protected EntityType  minefieldType = new EntityType(); 

   /** how many mine types */
   protected int  numberOfMineTypes;

   /** location of minefield in world coords */
   protected Vector3Double  minefieldLocation = new Vector3Double(); 

   /** orientation of minefield */
   protected Orientation  minefieldOrientation = new Orientation(); 

   /** appearance bitflags */
   protected int  appearance;

   /** protocolMode */
   protected int  protocolMode;

   /** perimeter points for the minefield */
   protected List< Point> perimeterPoints = new ArrayList<Point>(); 
   /** Type of mines */
   protected List< EntityType> mineType = new ArrayList<EntityType>(); 

/** Constructor */
 public MinefieldStatePdu()
 {
    setPduType( (short)37 );
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + minefieldID.getMarshalledSize();  // minefieldID
   marshalSize = marshalSize + 2;  // minefieldSequence
   marshalSize = marshalSize + 1;  // forceID
   marshalSize = marshalSize + 1;  // numberOfPerimeterPoints
   marshalSize = marshalSize + minefieldType.getMarshalledSize();  // minefieldType
   marshalSize = marshalSize + 2;  // numberOfMineTypes
   marshalSize = marshalSize + minefieldLocation.getMarshalledSize();  // minefieldLocation
   marshalSize = marshalSize + minefieldOrientation.getMarshalledSize();  // minefieldOrientation
   marshalSize = marshalSize + 2;  // appearance
   marshalSize = marshalSize + 2;  // protocolMode
   for(int idx=0; idx < perimeterPoints.size(); idx++)
   {
        Point listElement = perimeterPoints.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < mineType.size(); idx++)
   {
        EntityType listElement = mineType.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setMinefieldID(EntityID pMinefieldID)
{ minefieldID = pMinefieldID;
}

@XmlElement
public EntityID getMinefieldID()
{ return minefieldID; 
}

public void setMinefieldSequence(int pMinefieldSequence)
{ minefieldSequence = pMinefieldSequence;
}

@XmlAttribute
public int getMinefieldSequence()
{ return minefieldSequence; 
}

public void setForceID(short pForceID)
{ forceID = pForceID;
}

@XmlAttribute
public short getForceID()
{ return forceID; 
}

@XmlAttribute
public short getNumberOfPerimeterPoints()
{ return (short)perimeterPoints.size();
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfPerimeterPoints method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfPerimeterPoints(short pNumberOfPerimeterPoints)
{ numberOfPerimeterPoints = pNumberOfPerimeterPoints;
}

public void setMinefieldType(EntityType pMinefieldType)
{ minefieldType = pMinefieldType;
}

@XmlElement
public EntityType getMinefieldType()
{ return minefieldType; 
}

@XmlAttribute
public int getNumberOfMineTypes()
{ return (int)mineType.size();
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfMineTypes method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfMineTypes(int pNumberOfMineTypes)
{ numberOfMineTypes = pNumberOfMineTypes;
}

public void setMinefieldLocation(Vector3Double pMinefieldLocation)
{ minefieldLocation = pMinefieldLocation;
}

@XmlElement
public Vector3Double getMinefieldLocation()
{ return minefieldLocation; 
}

public void setMinefieldOrientation(Orientation pMinefieldOrientation)
{ minefieldOrientation = pMinefieldOrientation;
}

@XmlElement
public Orientation getMinefieldOrientation()
{ return minefieldOrientation; 
}

public void setAppearance(int pAppearance)
{ appearance = pAppearance;
}

@XmlAttribute
public int getAppearance()
{ return appearance; 
}

public void setProtocolMode(int pProtocolMode)
{ protocolMode = pProtocolMode;
}

@XmlAttribute
public int getProtocolMode()
{ return protocolMode; 
}

public void setPerimeterPoints(List<Point> pPerimeterPoints)
{ perimeterPoints = pPerimeterPoints;
}

@XmlElementWrapper(name="perimeterPointsList" )
public List<Point> getPerimeterPoints()
{ return perimeterPoints; }

public void setMineType(List<EntityType> pMineType)
{ mineType = pMineType;
}

@XmlElementWrapper(name="mineTypeList" )
public List<EntityType> getMineType()
{ return mineType; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       minefieldID.marshal(dos);
       dos.writeShort( (short)minefieldSequence);
       dos.writeByte( (byte)forceID);
       dos.writeByte( (byte)perimeterPoints.size());
       minefieldType.marshal(dos);
       dos.writeShort( (short)mineType.size());
       minefieldLocation.marshal(dos);
       minefieldOrientation.marshal(dos);
       dos.writeShort( (short)appearance);
       dos.writeShort( (short)protocolMode);

       for(int idx = 0; idx < perimeterPoints.size(); idx++)
       {
            Point aPoint = perimeterPoints.get(idx);
            aPoint.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < mineType.size(); idx++)
       {
            EntityType aEntityType = mineType.get(idx);
            aEntityType.marshal(dos);
       } // end of list marshalling

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
       minefieldID.unmarshal(dis);
       minefieldSequence = (int)dis.readUnsignedShort();
       forceID = (short)dis.readUnsignedByte();
       numberOfPerimeterPoints = (short)dis.readUnsignedByte();
       minefieldType.unmarshal(dis);
       numberOfMineTypes = (int)dis.readUnsignedShort();
       minefieldLocation.unmarshal(dis);
       minefieldOrientation.unmarshal(dis);
       appearance = (int)dis.readUnsignedShort();
       protocolMode = (int)dis.readUnsignedShort();
       for(int idx = 0; idx < numberOfPerimeterPoints; idx++)
       {
           Point anX = new Point();
           anX.unmarshal(dis);
           perimeterPoints.add(anX);
       }

       for(int idx = 0; idx < numberOfMineTypes; idx++)
       {
           EntityType anX = new EntityType();
           anX.unmarshal(dis);
           mineType.add(anX);
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
       super.marshal(buff);
       minefieldID.marshal(buff);
       buff.putShort( (short)minefieldSequence);
       buff.put( (byte)forceID);
       buff.put( (byte)perimeterPoints.size());
       minefieldType.marshal(buff);
       buff.putShort( (short)mineType.size());
       minefieldLocation.marshal(buff);
       minefieldOrientation.marshal(buff);
       buff.putShort( (short)appearance);
       buff.putShort( (short)protocolMode);

       for(int idx = 0; idx < perimeterPoints.size(); idx++)
       {
            Point aPoint = (Point)perimeterPoints.get(idx);
            aPoint.marshal(buff);
       } // end of list marshalling


       for(int idx = 0; idx < mineType.size(); idx++)
       {
            EntityType aEntityType = (EntityType)mineType.get(idx);
            aEntityType.marshal(buff);
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
       super.unmarshal(buff);

       minefieldID.unmarshal(buff);
       minefieldSequence = (int)(buff.getShort() & 0xFFFF);
       forceID = (short)(buff.get() & 0xFF);
       numberOfPerimeterPoints = (short)(buff.get() & 0xFF);
       minefieldType.unmarshal(buff);
       numberOfMineTypes = (int)(buff.getShort() & 0xFFFF);
       minefieldLocation.unmarshal(buff);
       minefieldOrientation.unmarshal(buff);
       appearance = (int)(buff.getShort() & 0xFFFF);
       protocolMode = (int)(buff.getShort() & 0xFFFF);
       for(int idx = 0; idx < numberOfPerimeterPoints; idx++)
       {
            Point anX = new Point();
            anX.unmarshal(buff);
            perimeterPoints.add(anX);
       }

       for(int idx = 0; idx < numberOfMineTypes; idx++)
       {
            EntityType anX = new EntityType();
            anX.unmarshal(buff);
            mineType.add(anX);
       }

 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(MinefieldStatePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (minefieldID.equals( rhs.minefieldID) )) ivarsEqual = false;
     if( ! (minefieldSequence == rhs.minefieldSequence)) ivarsEqual = false;
     if( ! (forceID == rhs.forceID)) ivarsEqual = false;
     if( ! (numberOfPerimeterPoints == rhs.numberOfPerimeterPoints)) ivarsEqual = false;
     if( ! (minefieldType.equals( rhs.minefieldType) )) ivarsEqual = false;
     if( ! (numberOfMineTypes == rhs.numberOfMineTypes)) ivarsEqual = false;
     if( ! (minefieldLocation.equals( rhs.minefieldLocation) )) ivarsEqual = false;
     if( ! (minefieldOrientation.equals( rhs.minefieldOrientation) )) ivarsEqual = false;
     if( ! (appearance == rhs.appearance)) ivarsEqual = false;
     if( ! (protocolMode == rhs.protocolMode)) ivarsEqual = false;

     for(int idx = 0; idx < perimeterPoints.size(); idx++)
     {
        if( ! ( perimeterPoints.get(idx).equals(rhs.perimeterPoints.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < mineType.size(); idx++)
     {
        if( ! ( mineType.get(idx).equals(rhs.mineType.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
