package edu.nps.moves.dis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

import edu.nps.moves.disenum.PduType;
import edu.nps.moves.disutil.DisTime;

/**
 * The superclass for all PDUs. This incorporates the PduHeader record, section 5.2.29.
 *
 * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * This has been patched from the generated source code to handle unmarhsalling an unsigned long.
 * @author DMcG
 * @version $Id:$
 */
public class Pdu implements Serializable {

    /** The DIS absolute timestamp mask; used to ensure the LSB in timestamps is always set to 1 */
    public static final int ABSOLUTE_TIMESTAMP_MASK = 0x00000001;

    /** The DIS relative timestamp mask; used to ensure the LSB in timestamps is always set to 0 */
    public static final int RELATIVE_TIMESTAMP_MASK = 0xFFFFFFFE;

    /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
    protected short protocolVersion = 6;

    /** Exercise ID */
    protected short exerciseID = 0;

    /** Type of pdu, unique for each PDU class */
    protected short pduType;

    /** value that refers to the protocol family, eg SimulationManagement, et */
    protected short protocolFamily;

    /** Timestamp value */
    protected long timestamp;

    /** Length, in bytes, of the PDU */
    protected int length;

    /** zero-filled array of padding */
    protected short padding = 0;

    /** Constructor */
    public Pdu() {}

    /** @return the size of this fully marshalled Pdu */
    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = marshalSize + 1;  // protocolVersion
        marshalSize = marshalSize + 1;  // exerciseID
        marshalSize = marshalSize + 1;  // pduType
        marshalSize = marshalSize + 1;  // protocolFamily
        marshalSize = marshalSize + 4;  // timestamp
        marshalSize = marshalSize + 2;  // length
        marshalSize = marshalSize + 2;  // padding

        return marshalSize;
    }

    public void setProtocolVersion(short pProtocolVersion) {
        protocolVersion = pProtocolVersion;
    }

    @XmlAttribute
    public short getProtocolVersion() {
        return protocolVersion;
    }

    public void setExerciseID(short pExerciseID) {
        exerciseID = pExerciseID;
    }

    @XmlAttribute
    public short getExerciseID() {
        return exerciseID;
    }

    public void setPduType(short pPduType) {
        pduType = pPduType;
    }

    @XmlAttribute
    public short getPduType() {
        return pduType;
    }

    /**
     * Returns the PduType, an enumeration from the disenum jar file. This is an enumerated
     * java type, rather than a simple short integer. This should NOT be marshalled to DIS
     * or XML.
     * @return this Pdu's type enumeration
     */
    public PduType getPduTypeEnum() {
        return PduType.lookup[pduType];
    }

    public void setProtocolFamily(short pProtocolFamily) {
        protocolFamily = pProtocolFamily;
    }

    @XmlAttribute
    public short getProtocolFamily() {
        return protocolFamily;
    }

    public void setTimestamp(long pTimestamp) {
        timestamp = pTimestamp;
    }

    @XmlAttribute
    public long getTimestamp() {
        return timestamp;
    }

    /** @param pLength the length of the entire Pdu */
    public void setLength(int pLength) {
        length = pLength;
    }

    @XmlAttribute
    /** @return the length of this entire Pdu
     * Post-processing patch fix for getting entire calculated length of this
     * Pdu
     */
    public int getLength() {
        return getMarshalledSize();
    }

    public void setPadding(short pPadding) {
        padding = pPadding;
    }

    @XmlAttribute
    public short getPadding() {
        return padding;
    }

    public void marshal(DataOutputStream dos) {
        try {
            dos.writeByte((byte) protocolVersion);
            dos.writeByte((byte) exerciseID);
            dos.writeByte((byte) pduType);
            dos.writeByte((byte) protocolFamily);
            dos.writeInt((int) timestamp);
            dos.writeShort((short) getLength()); // post-processing patch
            dos.writeShort(padding);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void unmarshal(DataInputStream dis) {
        try {
            protocolVersion = (short) dis.readUnsignedByte();
            exerciseID = (short) dis.readUnsignedByte();
            pduType = (short) dis.readUnsignedByte();
            protocolFamily = (short) dis.readUnsignedByte();
            timestamp = dis.readInt();
            length = dis.readUnsignedShort();
            padding = dis.readShort();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Packs a Pdu into the ByteBuffer.
     * @throws java.nio.BufferOverflowException if buff is too small
     * @throws java.nio.ReadOnlyBufferException if buff is read only
     * @see java.nio.ByteBuffer
     * @param buff The ByteBuffer at the position to begin writing
     * @since ??
     */
    public void marshal(java.nio.ByteBuffer buff) {
        buff.put((byte) protocolVersion);
        buff.put((byte) exerciseID);
        buff.put((byte) pduType);
        buff.put((byte) protocolFamily);
        buff.putInt((int) timestamp);
        buff.putShort((short) getLength()); // post-processing patch
        buff.putShort(padding);
    }

    /**
     * Unpacks a Pdu from the underlying data.
     * @throws java.nio.BufferUnderflowException if buff is too small
     * @see java.nio.ByteBuffer
     * @param buff The ByteBuffer at the position to begin reading
     * @since ??
     */
    public void unmarshal(java.nio.ByteBuffer buff) {
        protocolVersion = (short) (buff.get() & 0xFF);
        exerciseID = (short) (buff.get() & 0xFF);
        pduType = (short) (buff.get() & 0xFF);
        protocolFamily = (short) (buff.get() & 0xFF);
        timestamp = buff.getInt();
        length = (buff.getShort() & 0xFFFF);
        padding = buff.getShort();
    }

    /**
     * A convenience method for marshalling to a byte array. The method will marshal
     * the PDU as is.
     * This is not as efficient as reusing a ByteBuffer, but it <em>is</em> easy.
     * @return a byte array with the marshalled {@link Pdu}.
     * @since ??
     */
    public byte[] marshal() {
        byte[] data = new byte[getMarshalledSize()];
        java.nio.ByteBuffer buff = java.nio.ByteBuffer.wrap(data);
        marshal(buff);
        return data;
    }

    /**
     * A convieneince method to marshal to a byte array with the timestamp set to
     * the DIS standard for absolute timestamps (which works only if the host is
     * slaved to NTP). This means the timestamp will roll over every hour.
     * @return IEEE format byte array, with the timestamp set to the current DIS time
     */
    public byte[] marshalWithDisAbsoluteTimestamp() {
        DisTime disTime = DisTime.getInstance();
        this.setTimestamp(disTime.getDisAbsoluteTimestamp());
        return this.marshal();
    }

     public void marshalWithDisAbsoluteTimestamp(java.nio.ByteBuffer buff) {
        DisTime disTime = DisTime.getInstance();
        this.setTimestamp(disTime.getDisAbsoluteTimestamp());
        this.marshal(buff);
    }


    /**
     * A convieneince method to marshal to a byte array with the timestamp set to
     * the DIS standard for relative timestamps. The timestamp will roll over every
     * hour
     * @return IEEE format byte array, with the timestamp set to relative DIS time
     */
    public byte[] marshalWithDisRelativeTimestamp() {
        DisTime disTime = DisTime.getInstance();
        this.setTimestamp(disTime.getDisRelativeTimestamp());
        return this.marshal();
    }

    public void marshalWithDisRelativeTimestamp(java.nio.ByteBuffer buff) {
        DisTime disTime = DisTime.getInstance();
        this.setTimestamp(disTime.getDisRelativeTimestamp());
        this.marshal(buff);
    }

    /**
     * A convienience method to marshal a PDU using the NPS-specific format for
     * timestamps, which is hundredths of a second since the start of the year.
     * This effectively eliminates the rollover issues from a practical standpoint.
     * @return IEEE format byte array, with the timestamp set to hundredths of a second since the start of the year
     */
    public byte[] marshalWithNpsTimestamp() {
        DisTime disTime = DisTime.getInstance();
        this.setTimestamp(disTime.getNpsTimestamp());
        return this.marshal();
    }

    public void marshalWithNpsTimestamp(java.nio.ByteBuffer buff) {
        DisTime disTime = DisTime.getInstance();
        this.setTimestamp(disTime.getNpsTimestamp());
        this.marshal(buff);
    }

    /**
     * Another option for marshalling with the timestamp field set automatically. The UNIX
     * time is conventionally seconds since January 1, 1970. UTC time is used, and leap seconds
     * are excluded. This approach is popular in the wild, but the time resolution is not very
     * good for high frequency updates, such as aircraft. An entity updating at 30 PDUs/second
     * would see 30 PDUs sent out with the same timestamp, and have 29 of them discarded as
     * duplicate packets.
     *
     * Note that there are other "Unix times", such milliseconds since 1/1/1970, saved in a long.
     * This cannot be used, since the value is saved in a long. Java's System.getCurrentTimeMillis()
     * uses this value.
     * @return IEEE format byte array, with the timetamp set to seconds since 1970
     */
    public byte[] marshalWithUnixTimestamp() {
        DisTime disTime = DisTime.getInstance();
        this.setTimestamp(disTime.getUnixTimestamp());
        return this.marshal();
    }

     public void marshalWithUnixTimestamp(java.nio.ByteBuffer buff) {
        DisTime disTime = DisTime.getInstance();
        this.setTimestamp(disTime.getUnixTimestamp());
        this.marshal(buff);
    }

    /**
     * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
     * @param rhs
     * @return true if the instance variables are equal
     */
    public boolean equals(Pdu rhs) {
        boolean ivarsEqual = true;

        if (rhs.getClass() != this.getClass()) {
            return false;
        }

        if (!(protocolVersion == rhs.protocolVersion)) {
            ivarsEqual = false;
        }
        if (!(exerciseID == rhs.exerciseID)) {
            ivarsEqual = false;
        }
        if (!(pduType == rhs.pduType)) {
            ivarsEqual = false;
        }
        if (!(protocolFamily == rhs.protocolFamily)) {
            ivarsEqual = false;
        }
        if (!(timestamp == rhs.timestamp)) {
            ivarsEqual = false;
        }
        if (!(length == rhs.length)) {
            ivarsEqual = false;
        }
        if (!(padding == rhs.padding)) {
            ivarsEqual = false;
        }

        return ivarsEqual;
    }
} // end of class file Pdu.java
