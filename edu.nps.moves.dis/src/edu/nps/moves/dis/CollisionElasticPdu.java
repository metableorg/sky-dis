package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 5.3.3.3. Information about elastic collisions in a DIS exercise shall be communicated using a Collision-Elastic PDU. COMPLETE
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class CollisionElasticPdu extends EntityInformationFamilyPdu implements Serializable
{
   /** ID of the entity that issued the collision PDU */
   protected EntityID  issuingEntityID = new EntityID(); 

   /** ID of entity that has collided with the issuing entity ID */
   protected EntityID  collidingEntityID = new EntityID(); 

   /** ID of event */
   protected EventID  collisionEventID = new EventID(); 

   /** some padding */
   protected short  pad = 0;

   /** velocity at collision */
   protected Vector3Float  contactVelocity = new Vector3Float(); 

   /** mass of issuing entity */
   protected float  mass;

   /** Location with respect to entity the issuing entity collided with */
   protected Vector3Float  location = new Vector3Float(); 

   /** tensor values */
   protected float  collisionResultXX;

   /** tensor values */
   protected float  collisionResultXY;

   /** tensor values */
   protected float  collisionResultXZ;

   /** tensor values */
   protected float  collisionResultYY;

   /** tensor values */
   protected float  collisionResultYZ;

   /** tensor values */
   protected float  collisionResultZZ;

   /** This record shall represent the normal vector to the surface at the point of collision detection. The surface normal shall be represented in world coordinates. */
   protected Vector3Float  unitSurfaceNormal = new Vector3Float(); 

   /** This field shall represent the degree to which energy is conserved in a collision */
   protected float  coefficientOfRestitution;


/** Constructor */
 public CollisionElasticPdu()
 {
    setPduType( (short)66 );
    setProtocolFamily( (short)1 );
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + issuingEntityID.getMarshalledSize();  // issuingEntityID
   marshalSize = marshalSize + collidingEntityID.getMarshalledSize();  // collidingEntityID
   marshalSize = marshalSize + collisionEventID.getMarshalledSize();  // collisionEventID
   marshalSize = marshalSize + 2;  // pad
   marshalSize = marshalSize + contactVelocity.getMarshalledSize();  // contactVelocity
   marshalSize = marshalSize + 4;  // mass
   marshalSize = marshalSize + location.getMarshalledSize();  // location
   marshalSize = marshalSize + 4;  // collisionResultXX
   marshalSize = marshalSize + 4;  // collisionResultXY
   marshalSize = marshalSize + 4;  // collisionResultXZ
   marshalSize = marshalSize + 4;  // collisionResultYY
   marshalSize = marshalSize + 4;  // collisionResultYZ
   marshalSize = marshalSize + 4;  // collisionResultZZ
   marshalSize = marshalSize + unitSurfaceNormal.getMarshalledSize();  // unitSurfaceNormal
   marshalSize = marshalSize + 4;  // coefficientOfRestitution

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

public void setCollisionEventID(EventID pCollisionEventID)
{ collisionEventID = pCollisionEventID;
}

@XmlElement
public EventID getCollisionEventID()
{ return collisionEventID; 
}

public void setPad(short pPad)
{ pad = pPad;
}

@XmlAttribute
public short getPad()
{ return pad; 
}

public void setContactVelocity(Vector3Float pContactVelocity)
{ contactVelocity = pContactVelocity;
}

@XmlElement
public Vector3Float getContactVelocity()
{ return contactVelocity; 
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

public void setCollisionResultXX(float pCollisionResultXX)
{ collisionResultXX = pCollisionResultXX;
}

@XmlAttribute
public float getCollisionResultXX()
{ return collisionResultXX; 
}

public void setCollisionResultXY(float pCollisionResultXY)
{ collisionResultXY = pCollisionResultXY;
}

@XmlAttribute
public float getCollisionResultXY()
{ return collisionResultXY; 
}

public void setCollisionResultXZ(float pCollisionResultXZ)
{ collisionResultXZ = pCollisionResultXZ;
}

@XmlAttribute
public float getCollisionResultXZ()
{ return collisionResultXZ; 
}

public void setCollisionResultYY(float pCollisionResultYY)
{ collisionResultYY = pCollisionResultYY;
}

@XmlAttribute
public float getCollisionResultYY()
{ return collisionResultYY; 
}

public void setCollisionResultYZ(float pCollisionResultYZ)
{ collisionResultYZ = pCollisionResultYZ;
}

@XmlAttribute
public float getCollisionResultYZ()
{ return collisionResultYZ; 
}

public void setCollisionResultZZ(float pCollisionResultZZ)
{ collisionResultZZ = pCollisionResultZZ;
}

@XmlAttribute
public float getCollisionResultZZ()
{ return collisionResultZZ; 
}

public void setUnitSurfaceNormal(Vector3Float pUnitSurfaceNormal)
{ unitSurfaceNormal = pUnitSurfaceNormal;
}

@XmlElement
public Vector3Float getUnitSurfaceNormal()
{ return unitSurfaceNormal; 
}

public void setCoefficientOfRestitution(float pCoefficientOfRestitution)
{ coefficientOfRestitution = pCoefficientOfRestitution;
}

@XmlAttribute
public float getCoefficientOfRestitution()
{ return coefficientOfRestitution; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       issuingEntityID.marshal(dos);
       collidingEntityID.marshal(dos);
       collisionEventID.marshal(dos);
       dos.writeShort( (short)pad);
       contactVelocity.marshal(dos);
       dos.writeFloat( (float)mass);
       location.marshal(dos);
       dos.writeFloat( (float)collisionResultXX);
       dos.writeFloat( (float)collisionResultXY);
       dos.writeFloat( (float)collisionResultXZ);
       dos.writeFloat( (float)collisionResultYY);
       dos.writeFloat( (float)collisionResultYZ);
       dos.writeFloat( (float)collisionResultZZ);
       unitSurfaceNormal.marshal(dos);
       dos.writeFloat( (float)coefficientOfRestitution);
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
       collisionEventID.unmarshal(dis);
       pad = dis.readShort();
       contactVelocity.unmarshal(dis);
       mass = dis.readFloat();
       location.unmarshal(dis);
       collisionResultXX = dis.readFloat();
       collisionResultXY = dis.readFloat();
       collisionResultXZ = dis.readFloat();
       collisionResultYY = dis.readFloat();
       collisionResultYZ = dis.readFloat();
       collisionResultZZ = dis.readFloat();
       unitSurfaceNormal.unmarshal(dis);
       coefficientOfRestitution = dis.readFloat();
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
       collisionEventID.marshal(buff);
       buff.putShort( (short)pad);
       contactVelocity.marshal(buff);
       buff.putFloat( (float)mass);
       location.marshal(buff);
       buff.putFloat( (float)collisionResultXX);
       buff.putFloat( (float)collisionResultXY);
       buff.putFloat( (float)collisionResultXZ);
       buff.putFloat( (float)collisionResultYY);
       buff.putFloat( (float)collisionResultYZ);
       buff.putFloat( (float)collisionResultZZ);
       unitSurfaceNormal.marshal(buff);
       buff.putFloat( (float)coefficientOfRestitution);
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
       collisionEventID.unmarshal(buff);
       pad = buff.getShort();
       contactVelocity.unmarshal(buff);
       mass = buff.getFloat();
       location.unmarshal(buff);
       collisionResultXX = buff.getFloat();
       collisionResultXY = buff.getFloat();
       collisionResultXZ = buff.getFloat();
       collisionResultYY = buff.getFloat();
       collisionResultYZ = buff.getFloat();
       collisionResultZZ = buff.getFloat();
       unitSurfaceNormal.unmarshal(buff);
       coefficientOfRestitution = buff.getFloat();
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(CollisionElasticPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (issuingEntityID.equals( rhs.issuingEntityID) )) ivarsEqual = false;
     if( ! (collidingEntityID.equals( rhs.collidingEntityID) )) ivarsEqual = false;
     if( ! (collisionEventID.equals( rhs.collisionEventID) )) ivarsEqual = false;
     if( ! (pad == rhs.pad)) ivarsEqual = false;
     if( ! (contactVelocity.equals( rhs.contactVelocity) )) ivarsEqual = false;
     if( ! (mass == rhs.mass)) ivarsEqual = false;
     if( ! (location.equals( rhs.location) )) ivarsEqual = false;
     if( ! (collisionResultXX == rhs.collisionResultXX)) ivarsEqual = false;
     if( ! (collisionResultXY == rhs.collisionResultXY)) ivarsEqual = false;
     if( ! (collisionResultXZ == rhs.collisionResultXZ)) ivarsEqual = false;
     if( ! (collisionResultYY == rhs.collisionResultYY)) ivarsEqual = false;
     if( ! (collisionResultYZ == rhs.collisionResultYZ)) ivarsEqual = false;
     if( ! (collisionResultZZ == rhs.collisionResultZZ)) ivarsEqual = false;
     if( ! (unitSurfaceNormal.equals( rhs.unitSurfaceNormal) )) ivarsEqual = false;
     if( ! (coefficientOfRestitution == rhs.coefficientOfRestitution)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
