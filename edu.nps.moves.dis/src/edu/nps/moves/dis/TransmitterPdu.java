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
 * Section 5.3.8.1. Detailed information about a radio transmitter. This PDU requires manually         written code to complete, since the modulation parameters are of variable length. UNFINISHED
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class TransmitterPdu extends RadioCommunicationsFamilyPdu implements Serializable
{
   /** linear accelleration of entity */
   protected RadioEntityType  radioEntityType = new RadioEntityType(); 

   /** transmit state */
   protected short  transmitState;

   /** input source */
   protected short  inputSource;

   /** padding */
   protected int  padding1;

   /** Location of antenna */
   protected Vector3Double  antennaLocation = new Vector3Double(); 

   /** relative location of antenna */
   protected Vector3Float  relativeAntennaLocation = new Vector3Float(); 

   /** antenna pattern type */
   protected int  antennaPatternType;

   /** atenna pattern length */
   protected int  antennaPatternCount;

   /** frequency */
   protected double  frequency;

   /** transmit frequency Bandwidth */
   protected float  transmitFrequencyBandwidth;

   /** transmission power */
   protected float  power;

   /** modulation */
   protected ModulationType  modulationType = new ModulationType(); 

   /** crypto system enumeration */
   protected int  cryptoSystem;

   /** crypto system key identifer */
   protected int  cryptoKeyId;

   /** how many modulation parameters we have */
   protected short  modulationParameterCount;

   /** padding2 */
   protected int  padding2 = 0;

   /** padding3 */
   protected short  padding3 = 0;

   /** variable length list of modulation parameters */
   protected List< Vector3Float> modulationParametersList = new ArrayList<Vector3Float>(); 
   /** variable length list of antenna pattern records */
   protected List< Vector3Float> antennaPatternList = new ArrayList<Vector3Float>(); 

/** Constructor */
 public TransmitterPdu()
 {
    setPduType( (short)25 );
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + radioEntityType.getMarshalledSize();  // radioEntityType
   marshalSize = marshalSize + 1;  // transmitState
   marshalSize = marshalSize + 1;  // inputSource
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + antennaLocation.getMarshalledSize();  // antennaLocation
   marshalSize = marshalSize + relativeAntennaLocation.getMarshalledSize();  // relativeAntennaLocation
   marshalSize = marshalSize + 2;  // antennaPatternType
   marshalSize = marshalSize + 2;  // antennaPatternCount
   marshalSize = marshalSize + 8;  // frequency
   marshalSize = marshalSize + 4;  // transmitFrequencyBandwidth
   marshalSize = marshalSize + 4;  // power
   marshalSize = marshalSize + modulationType.getMarshalledSize();  // modulationType
   marshalSize = marshalSize + 2;  // cryptoSystem
   marshalSize = marshalSize + 2;  // cryptoKeyId
   marshalSize = marshalSize + 1;  // modulationParameterCount
   marshalSize = marshalSize + 2;  // padding2
   marshalSize = marshalSize + 1;  // padding3
   for(int idx=0; idx < modulationParametersList.size(); idx++)
   {
        Vector3Float listElement = modulationParametersList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < antennaPatternList.size(); idx++)
   {
        Vector3Float listElement = antennaPatternList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setRadioEntityType(RadioEntityType pRadioEntityType)
{ radioEntityType = pRadioEntityType;
}

@XmlElement
public RadioEntityType getRadioEntityType()
{ return radioEntityType; 
}

public void setTransmitState(short pTransmitState)
{ transmitState = pTransmitState;
}

@XmlAttribute
public short getTransmitState()
{ return transmitState; 
}

public void setInputSource(short pInputSource)
{ inputSource = pInputSource;
}

@XmlAttribute
public short getInputSource()
{ return inputSource; 
}

public void setPadding1(int pPadding1)
{ padding1 = pPadding1;
}

@XmlAttribute
public int getPadding1()
{ return padding1; 
}

public void setAntennaLocation(Vector3Double pAntennaLocation)
{ antennaLocation = pAntennaLocation;
}

@XmlElement
public Vector3Double getAntennaLocation()
{ return antennaLocation; 
}

public void setRelativeAntennaLocation(Vector3Float pRelativeAntennaLocation)
{ relativeAntennaLocation = pRelativeAntennaLocation;
}

@XmlElement
public Vector3Float getRelativeAntennaLocation()
{ return relativeAntennaLocation; 
}

public void setAntennaPatternType(int pAntennaPatternType)
{ antennaPatternType = pAntennaPatternType;
}

@XmlAttribute
public int getAntennaPatternType()
{ return antennaPatternType; 
}

@XmlAttribute
public int getAntennaPatternCount()
{ return (int)antennaPatternList.size();
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getantennaPatternCount method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setAntennaPatternCount(int pAntennaPatternCount)
{ antennaPatternCount = pAntennaPatternCount;
}

public void setFrequency(double pFrequency)
{ frequency = pFrequency;
}

@XmlAttribute
public double getFrequency()
{ return frequency; 
}

public void setTransmitFrequencyBandwidth(float pTransmitFrequencyBandwidth)
{ transmitFrequencyBandwidth = pTransmitFrequencyBandwidth;
}

@XmlAttribute
public float getTransmitFrequencyBandwidth()
{ return transmitFrequencyBandwidth; 
}

public void setPower(float pPower)
{ power = pPower;
}

@XmlAttribute
public float getPower()
{ return power; 
}

public void setModulationType(ModulationType pModulationType)
{ modulationType = pModulationType;
}

@XmlElement
public ModulationType getModulationType()
{ return modulationType; 
}

public void setCryptoSystem(int pCryptoSystem)
{ cryptoSystem = pCryptoSystem;
}

@XmlAttribute
public int getCryptoSystem()
{ return cryptoSystem; 
}

public void setCryptoKeyId(int pCryptoKeyId)
{ cryptoKeyId = pCryptoKeyId;
}

@XmlAttribute
public int getCryptoKeyId()
{ return cryptoKeyId; 
}

@XmlAttribute
public short getModulationParameterCount()
{ return (short)modulationParametersList.size();
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getmodulationParameterCount method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setModulationParameterCount(short pModulationParameterCount)
{ modulationParameterCount = pModulationParameterCount;
}

public void setPadding2(int pPadding2)
{ padding2 = pPadding2;
}

@XmlAttribute
public int getPadding2()
{ return padding2; 
}

public void setPadding3(short pPadding3)
{ padding3 = pPadding3;
}

@XmlAttribute
public short getPadding3()
{ return padding3; 
}

public void setModulationParametersList(List<Vector3Float> pModulationParametersList)
{ modulationParametersList = pModulationParametersList;
}

@XmlElementWrapper(name="modulationParametersListList" )
public List<Vector3Float> getModulationParametersList()
{ return modulationParametersList; }

public void setAntennaPatternList(List<Vector3Float> pAntennaPatternList)
{ antennaPatternList = pAntennaPatternList;
}

@XmlElementWrapper(name="antennaPatternListList" )
public List<Vector3Float> getAntennaPatternList()
{ return antennaPatternList; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       radioEntityType.marshal(dos);
       dos.writeByte( (byte)transmitState);
       dos.writeByte( (byte)inputSource);
       dos.writeShort( (short)padding1);
       antennaLocation.marshal(dos);
       relativeAntennaLocation.marshal(dos);
       dos.writeShort( (short)antennaPatternType);
       dos.writeShort( (short)antennaPatternList.size());
       dos.writeDouble( (double)frequency);
       dos.writeFloat( (float)transmitFrequencyBandwidth);
       dos.writeFloat( (float)power);
       modulationType.marshal(dos);
       dos.writeShort( (short)cryptoSystem);
       dos.writeShort( (short)cryptoKeyId);
       dos.writeByte( (byte)modulationParametersList.size());
       dos.writeShort( (short)padding2);
       dos.writeByte( (byte)padding3);

       for(int idx = 0; idx < modulationParametersList.size(); idx++)
       {
            Vector3Float aVector3Float = modulationParametersList.get(idx);
            aVector3Float.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < antennaPatternList.size(); idx++)
       {
            Vector3Float aVector3Float = antennaPatternList.get(idx);
            aVector3Float.marshal(dos);
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
       radioEntityType.unmarshal(dis);
       transmitState = (short)dis.readUnsignedByte();
       inputSource = (short)dis.readUnsignedByte();
       padding1 = (int)dis.readUnsignedShort();
       antennaLocation.unmarshal(dis);
       relativeAntennaLocation.unmarshal(dis);
       antennaPatternType = (int)dis.readUnsignedShort();
       antennaPatternCount = (int)dis.readUnsignedShort();
       frequency = dis.readDouble();
       transmitFrequencyBandwidth = dis.readFloat();
       power = dis.readFloat();
       modulationType.unmarshal(dis);
       cryptoSystem = (int)dis.readUnsignedShort();
       cryptoKeyId = (int)dis.readUnsignedShort();
       modulationParameterCount = (short)dis.readUnsignedByte();
       padding2 = (int)dis.readUnsignedShort();
       padding3 = (short)dis.readUnsignedByte();
       for(int idx = 0; idx < modulationParameterCount; idx++)
       {
           Vector3Float anX = new Vector3Float();
           anX.unmarshal(dis);
           modulationParametersList.add(anX);
       }

       for(int idx = 0; idx < antennaPatternCount; idx++)
       {
           Vector3Float anX = new Vector3Float();
           anX.unmarshal(dis);
           antennaPatternList.add(anX);
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
       radioEntityType.marshal(buff);
       buff.put( (byte)transmitState);
       buff.put( (byte)inputSource);
       buff.putShort( (short)padding1);
       antennaLocation.marshal(buff);
       relativeAntennaLocation.marshal(buff);
       buff.putShort( (short)antennaPatternType);
       buff.putShort( (short)antennaPatternList.size());
       buff.putDouble( (double)frequency);
       buff.putFloat( (float)transmitFrequencyBandwidth);
       buff.putFloat( (float)power);
       modulationType.marshal(buff);
       buff.putShort( (short)cryptoSystem);
       buff.putShort( (short)cryptoKeyId);
       buff.put( (byte)modulationParametersList.size());
       buff.putShort( (short)padding2);
       buff.put( (byte)padding3);

       for(int idx = 0; idx < modulationParametersList.size(); idx++)
       {
            Vector3Float aVector3Float = (Vector3Float)modulationParametersList.get(idx);
            aVector3Float.marshal(buff);
       } // end of list marshalling


       for(int idx = 0; idx < antennaPatternList.size(); idx++)
       {
            Vector3Float aVector3Float = (Vector3Float)antennaPatternList.get(idx);
            aVector3Float.marshal(buff);
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

       radioEntityType.unmarshal(buff);
       transmitState = (short)(buff.get() & 0xFF);
       inputSource = (short)(buff.get() & 0xFF);
       padding1 = (int)(buff.getShort() & 0xFFFF);
       antennaLocation.unmarshal(buff);
       relativeAntennaLocation.unmarshal(buff);
       antennaPatternType = (int)(buff.getShort() & 0xFFFF);
       antennaPatternCount = (int)(buff.getShort() & 0xFFFF);
       frequency = buff.getDouble();
       transmitFrequencyBandwidth = buff.getFloat();
       power = buff.getFloat();
       modulationType.unmarshal(buff);
       cryptoSystem = (int)(buff.getShort() & 0xFFFF);
       cryptoKeyId = (int)(buff.getShort() & 0xFFFF);
       modulationParameterCount = (short)(buff.get() & 0xFF);
       padding2 = (int)(buff.getShort() & 0xFFFF);
       padding3 = (short)(buff.get() & 0xFF);
       for(int idx = 0; idx < modulationParameterCount; idx++)
       {
            Vector3Float anX = new Vector3Float();
            anX.unmarshal(buff);
            modulationParametersList.add(anX);
       }

       for(int idx = 0; idx < antennaPatternCount; idx++)
       {
            Vector3Float anX = new Vector3Float();
            anX.unmarshal(buff);
            antennaPatternList.add(anX);
       }

 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(TransmitterPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (radioEntityType.equals( rhs.radioEntityType) )) ivarsEqual = false;
     if( ! (transmitState == rhs.transmitState)) ivarsEqual = false;
     if( ! (inputSource == rhs.inputSource)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (antennaLocation.equals( rhs.antennaLocation) )) ivarsEqual = false;
     if( ! (relativeAntennaLocation.equals( rhs.relativeAntennaLocation) )) ivarsEqual = false;
     if( ! (antennaPatternType == rhs.antennaPatternType)) ivarsEqual = false;
     if( ! (antennaPatternCount == rhs.antennaPatternCount)) ivarsEqual = false;
     if( ! (frequency == rhs.frequency)) ivarsEqual = false;
     if( ! (transmitFrequencyBandwidth == rhs.transmitFrequencyBandwidth)) ivarsEqual = false;
     if( ! (power == rhs.power)) ivarsEqual = false;
     if( ! (modulationType.equals( rhs.modulationType) )) ivarsEqual = false;
     if( ! (cryptoSystem == rhs.cryptoSystem)) ivarsEqual = false;
     if( ! (cryptoKeyId == rhs.cryptoKeyId)) ivarsEqual = false;
     if( ! (modulationParameterCount == rhs.modulationParameterCount)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;
     if( ! (padding3 == rhs.padding3)) ivarsEqual = false;

     for(int idx = 0; idx < modulationParametersList.size(); idx++)
     {
        if( ! ( modulationParametersList.get(idx).equals(rhs.modulationParametersList.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < antennaPatternList.size(); idx++)
     {
        if( ! ( antennaPatternList.get(idx).equals(rhs.antennaPatternList.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
