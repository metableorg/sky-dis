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
 * Section 5.3.3.1. Represents the postion and state of one entity in the world. COMPLETE
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class EntityStatePdu extends EntityInformationFamilyPdu implements Serializable
{
   /** Unique ID for an entity that is tied to this state information */
   protected EntityID  entityID = new EntityID(); 

   /** What force this entity is affiliated with, eg red, blue, neutral, etc */
   protected short  forceId;

   /** How many articulation parameters are in the variable length list */
   protected byte  numberOfArticulationParameters;

   /** Describes the type of entity in the world */
   protected EntityType  entityType = new EntityType(); 

   protected EntityType  alternativeEntityType = new EntityType(); 

   /** Describes the speed of the entity in the world */
   protected Vector3Float  entityLinearVelocity = new Vector3Float(); 

   /** describes the location of the entity in the world */
   protected Vector3Double  entityLocation = new Vector3Double(); 

   /** describes the orientation of the entity, in euler angles */
   protected Orientation  entityOrientation = new Orientation(); 

   /** a series of bit flags that are used to help draw the entity, such as smoking, on fire, etc. */
   protected int  entityAppearance;

   /** parameters used for dead reckoning */
   protected DeadReckoningParameter  deadReckoningParameters = new DeadReckoningParameter(); 

   /** characters that can be used for debugging, or to draw unique strings on the side of entities in the world */
   protected Marking  marking = new Marking(); 

   /** a series of bit flags */
   protected int  capabilities;

   /** variable length list of articulation parameters */
   protected List< ArticulationParameter> articulationParameters = new ArrayList<ArticulationParameter>(); 

/** Constructor */
 public EntityStatePdu()
 {
    setPduType( (short)1 );
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + entityID.getMarshalledSize();  // entityID
   marshalSize = marshalSize + 1;  // forceId
   marshalSize = marshalSize + 1;  // numberOfArticulationParameters
   marshalSize = marshalSize + entityType.getMarshalledSize();  // entityType
   marshalSize = marshalSize + alternativeEntityType.getMarshalledSize();  // alternativeEntityType
   marshalSize = marshalSize + entityLinearVelocity.getMarshalledSize();  // entityLinearVelocity
   marshalSize = marshalSize + entityLocation.getMarshalledSize();  // entityLocation
   marshalSize = marshalSize + entityOrientation.getMarshalledSize();  // entityOrientation
   marshalSize = marshalSize + 4;  // entityAppearance
   marshalSize = marshalSize + deadReckoningParameters.getMarshalledSize();  // deadReckoningParameters
   marshalSize = marshalSize + marking.getMarshalledSize();  // marking
   marshalSize = marshalSize + 4;  // capabilities
   for(int idx=0; idx < articulationParameters.size(); idx++)
   {
        ArticulationParameter listElement = articulationParameters.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setEntityID(EntityID pEntityID)
{ entityID = pEntityID;
}

@XmlElement
public EntityID getEntityID()
{ return entityID; 
}

public void setForceId(short pForceId)
{ forceId = pForceId;
}

@XmlAttribute
public short getForceId()
{ return forceId; 
}

@XmlAttribute
public byte getNumberOfArticulationParameters()
{ return (byte)articulationParameters.size();
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfArticulationParameters method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfArticulationParameters(byte pNumberOfArticulationParameters)
{ numberOfArticulationParameters = pNumberOfArticulationParameters;
}

public void setEntityType(EntityType pEntityType)
{ entityType = pEntityType;
}

@XmlElement
public EntityType getEntityType()
{ return entityType; 
}

public void setAlternativeEntityType(EntityType pAlternativeEntityType)
{ alternativeEntityType = pAlternativeEntityType;
}

@XmlElement
public EntityType getAlternativeEntityType()
{ return alternativeEntityType; 
}

public void setEntityLinearVelocity(Vector3Float pEntityLinearVelocity)
{ entityLinearVelocity = pEntityLinearVelocity;
}

@XmlElement
public Vector3Float getEntityLinearVelocity()
{ return entityLinearVelocity; 
}

public void setEntityLocation(Vector3Double pEntityLocation)
{ entityLocation = pEntityLocation;
}

@XmlElement
public Vector3Double getEntityLocation()
{ return entityLocation; 
}

public void setEntityOrientation(Orientation pEntityOrientation)
{ entityOrientation = pEntityOrientation;
}

@XmlElement
public Orientation getEntityOrientation()
{ return entityOrientation; 
}

public void setEntityAppearance(int pEntityAppearance)
{ entityAppearance = pEntityAppearance;
}

@XmlAttribute
public int getEntityAppearance()
{ return entityAppearance; 
}

public void setDeadReckoningParameters(DeadReckoningParameter pDeadReckoningParameters)
{ deadReckoningParameters = pDeadReckoningParameters;
}

@XmlElement
public DeadReckoningParameter getDeadReckoningParameters()
{ return deadReckoningParameters; 
}

public void setMarking(Marking pMarking)
{ marking = pMarking;
}

@XmlElement
public Marking getMarking()
{ return marking; 
}

public void setCapabilities(int pCapabilities)
{ capabilities = pCapabilities;
}

@XmlAttribute
public int getCapabilities()
{ return capabilities; 
}

public void setArticulationParameters(List<ArticulationParameter> pArticulationParameters)
{ articulationParameters = pArticulationParameters;
}

@XmlElementWrapper(name="articulationParametersList" )
public List<ArticulationParameter> getArticulationParameters()
{ return articulationParameters; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       entityID.marshal(dos);
       dos.writeByte( (byte)forceId);
       dos.writeByte( (byte)articulationParameters.size());
       entityType.marshal(dos);
       alternativeEntityType.marshal(dos);
       entityLinearVelocity.marshal(dos);
       entityLocation.marshal(dos);
       entityOrientation.marshal(dos);
       dos.writeInt( (int)entityAppearance);
       deadReckoningParameters.marshal(dos);
       marking.marshal(dos);
       dos.writeInt( (int)capabilities);

       for(int idx = 0; idx < articulationParameters.size(); idx++)
       {
            ArticulationParameter aArticulationParameter = articulationParameters.get(idx);
            aArticulationParameter.marshal(dos);
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
       entityID.unmarshal(dis);
       forceId = (short)dis.readUnsignedByte();
       numberOfArticulationParameters = dis.readByte();
       entityType.unmarshal(dis);
       alternativeEntityType.unmarshal(dis);
       entityLinearVelocity.unmarshal(dis);
       entityLocation.unmarshal(dis);
       entityOrientation.unmarshal(dis);
       entityAppearance = dis.readInt();
       deadReckoningParameters.unmarshal(dis);
       marking.unmarshal(dis);
       capabilities = dis.readInt();
       for(int idx = 0; idx < numberOfArticulationParameters; idx++)
       {
           ArticulationParameter anX = new ArticulationParameter();
           anX.unmarshal(dis);
           articulationParameters.add(anX);
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
       entityID.marshal(buff);
       buff.put( (byte)forceId);
       buff.put( (byte)articulationParameters.size());
       entityType.marshal(buff);
       alternativeEntityType.marshal(buff);
       entityLinearVelocity.marshal(buff);
       entityLocation.marshal(buff);
       entityOrientation.marshal(buff);
       buff.putInt( (int)entityAppearance);
       deadReckoningParameters.marshal(buff);
       marking.marshal(buff);
       buff.putInt( (int)capabilities);

       for(int idx = 0; idx < articulationParameters.size(); idx++)
       {
            ArticulationParameter aArticulationParameter = (ArticulationParameter)articulationParameters.get(idx);
            aArticulationParameter.marshal(buff);
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

       entityID.unmarshal(buff);
       forceId = (short)(buff.get() & 0xFF);
       numberOfArticulationParameters = buff.get();
       entityType.unmarshal(buff);
       alternativeEntityType.unmarshal(buff);
       entityLinearVelocity.unmarshal(buff);
       entityLocation.unmarshal(buff);
       entityOrientation.unmarshal(buff);
       entityAppearance = buff.getInt();
       deadReckoningParameters.unmarshal(buff);
       marking.unmarshal(buff);
       capabilities = buff.getInt();
       for(int idx = 0; idx < numberOfArticulationParameters; idx++)
       {
            ArticulationParameter anX = new ArticulationParameter();
            anX.unmarshal(buff);
            articulationParameters.add(anX);
       }

 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(EntityStatePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (entityID.equals( rhs.entityID) )) ivarsEqual = false;
     if( ! (forceId == rhs.forceId)) ivarsEqual = false;
     if( ! (numberOfArticulationParameters == rhs.numberOfArticulationParameters)) ivarsEqual = false;
     if( ! (entityType.equals( rhs.entityType) )) ivarsEqual = false;
     if( ! (alternativeEntityType.equals( rhs.alternativeEntityType) )) ivarsEqual = false;
     if( ! (entityLinearVelocity.equals( rhs.entityLinearVelocity) )) ivarsEqual = false;
     if( ! (entityLocation.equals( rhs.entityLocation) )) ivarsEqual = false;
     if( ! (entityOrientation.equals( rhs.entityOrientation) )) ivarsEqual = false;
     if( ! (entityAppearance == rhs.entityAppearance)) ivarsEqual = false;
     if( ! (deadReckoningParameters.equals( rhs.deadReckoningParameters) )) ivarsEqual = false;
     if( ! (marking.equals( rhs.marking) )) ivarsEqual = false;
     if( ! (capabilities == rhs.capabilities)) ivarsEqual = false;

     for(int idx = 0; idx < articulationParameters.size(); idx++)
     {
        if( ! ( articulationParameters.get(idx).equals(rhs.articulationParameters.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
