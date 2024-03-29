package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Section 5.3.3.2. Information about a collision. COMPLETE
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class CollisionPdu extends EntityInformationFamilyPdu implements Serializable
{
   /** ID of the entity that issued the collision PDU */
   protected EntityID  issuingEntityID = new EntityID(); 

   /** ID of entity that has collided with the issuing entity ID */
   protected EntityID  collidingEntityID = new EntityID(); 

   /** ID of event */
   protected EventID  eventID = new EventID(); 

   /** ID of event */
   protected short  collisionType;

   /** some padding */
   protected byte  pad = 0;

   /** velocity at collision */
   protected Vector3Float  velocity = new Vector3Float(); 

   /** mass of issuing entity */
   protected float  mass;

   /** Location with respect to entity the issuing entity collided with */
   protected Vector3Float  location = new Vector3Float(); 


/** Constructor */
 public CollisionPdu()
 {
    setPduType( (short)4 );
    setProtocolFamily( (short)1 );
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + issuingEntityID.getMarshalledSize();  // issuingEntityID
   marshalSize = marshalSize + collidingEntityID.getMarshalledSize();  // collidingEntityID
   marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
   marshalSize = marshalSize + 1;  // collisionType
   marshalSize = marshalSize + 1;  // pad
   marshalSize = marshalSize + velocity.getMarshalledSize();  // velocity
   marshalSize = marshalSize + 4;  // mass
   marshalSize = marshalSize + location.getMarshalledSize();  // location

   return marshalSize;
}


public void setIssuingEntityID(EntityID pIssuingEntityID)
{ issuingEntityID = pIssuingEntityID;
}

@XmlElement
public EntityID getIssuingEntityID()
{ return issuingEntityID; 
}

public void setCollidingEntityID(EntityID pCollidingEntityID)
{ collidingEntityID = pCollidingEntityID;
}

@XmlElement
public EntityID getCollidingEntityID()
{ return collidingEntityID; 
}

public void setEventID(EventID pEventID)
{ eventID = pEventID;
}

@XmlElement
public EventID getEventID()
{ return eventID; 
}

public void setCollisionType(short pCollisionType)
{ collisionType = pCollisionType;
}

@XmlAttribute
public short getCollisionType()
{ return collisionType; 
}

public void setPad(byte pPad)
{ pad = pPad;
}

@XmlAttribute
public byte getPad()
{ return pad; 
}

public void setVelocity(Vector3Float pVelocity)
{ velocity = pVelocity;
}

@XmlElement
public Vector3Float getVelocity()
{ return velocity; 
}

public void setMass(float pMass)
{ mass = pMass;
}

@XmlAttribute
public float getMass()
{ return mass; 
}

public void setLocation(Vector3Float pLocation)
{ location = pLocation;
}

@XmlElement
public Vector3Float getLocation()
{ return location; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       issuingEntityID.marshal(dos);
       collidingEntityID.marshal(dos);
       eventID.marshal(dos);
       dos.writeByte( (byte)collisionType);
       dos.writeByte( (byte)pad);
       velocity.marshal(dos);
       dos.writeFloat( (float)mass);
       location.marshal(dos);
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
       issuingEntityID.unmarshal(dis);
       collidingEntityID.unmarshal(dis);
       eventID.unmarshal(dis);
       collisionType = (short)dis.readUnsignedByte();
       pad = dis.readByte();
       velocity.unmarshal(dis);
       mass = dis.readFloat();
       location.unmarshal(dis);
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
       issuingEntityID.marshal(buff);
       collidingEntityID.marshal(buff);
       eventID.marshal(buff);
       buff.put( (byte)collisionType);
       buff.put( (byte)pad);
       velocity.marshal(buff);
       buff.putFloat( (float)mass);
       location.marshal(buff);
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

       issuingEntityID.unmarshal(buff);
       collidingEntityID.unmarshal(buff);
       eventID.unmarshal(buff);
       collisionType = (short)(buff.get() & 0xFF);
       pad = buff.get();
       velocity.unmarshal(buff);
       mass = buff.getFloat();
       location.unmarshal(buff);
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(CollisionPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (issuingEntityID.equals( rhs.issuingEntityID) )) ivarsEqual = false;
     if( ! (collidingEntityID.equals( rhs.collidingEntityID) )) ivarsEqual = false;
     if( ! (eventID.equals( rhs.eventID) )) ivarsEqual = false;
     if( ! (collisionType == rhs.collisionType)) ivarsEqual = false;
     if( ! (pad == rhs.pad)) ivarsEqual = false;
     if( ! (velocity.equals( rhs.velocity) )) ivarsEqual = false;
     if( ! (mass == rhs.mass)) ivarsEqual = false;
     if( ! (location.equals( rhs.location) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
