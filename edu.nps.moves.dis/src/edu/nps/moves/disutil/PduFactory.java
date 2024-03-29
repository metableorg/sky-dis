package edu.nps.moves.disutil;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import edu.nps.moves.dis.AcknowledgePdu;
import edu.nps.moves.dis.ActionRequestPdu;
import edu.nps.moves.dis.CollisionPdu;
import edu.nps.moves.dis.CommentPdu;
import edu.nps.moves.dis.CreateEntityPdu;
import edu.nps.moves.dis.DataPdu;
import edu.nps.moves.dis.DataQueryPdu;
import edu.nps.moves.dis.DetonationPdu;
import edu.nps.moves.dis.ElectronicEmissionsPdu;
import edu.nps.moves.dis.EntityStatePdu;
import edu.nps.moves.dis.FastEntityStatePdu;
import edu.nps.moves.dis.FirePdu;
import edu.nps.moves.dis.Pdu;
import edu.nps.moves.dis.RemoveEntityPdu;
import edu.nps.moves.dis.RepairCompletePdu;
import edu.nps.moves.dis.RepairResponsePdu;
import edu.nps.moves.dis.ResupplyCancelPdu;
import edu.nps.moves.dis.ResupplyOfferPdu;
import edu.nps.moves.dis.ResupplyReceivedPdu;
import edu.nps.moves.dis.ServiceRequestPdu;
import edu.nps.moves.dis.StartResumePdu;
import edu.nps.moves.dis.StopFreezePdu;
import edu.nps.moves.disenum.PduType;

/**
 * Simple factory for PDUs. When bytes are received on the wire, they're passed off to us
 * and the correct constructor called to return the correct PDU type.<p>
 *
 * This should be reworked to use the separate enumerations package, which is generated
 * from the XML EBV file. That's included with the open-dis distribution, but it's still
 * a little new.
 *
 * @author DMcG
 */
public class PduFactory {

    private boolean useFastPdu = false;

    /** Creates a new instance of PduFactory */
    public PduFactory() {
        this(false);
    }

    /** 
     * Create a new PDU factory; if true is passed in, we use "fast PDUs",
     * which minimize the memory garbage generated at the cost of being
     * somewhat less pleasant to work with.
     * @param useFastPdu
     */
    public PduFactory(boolean useFastPdu) {
        this.useFastPdu = useFastPdu;
    }

    public void setUseFastPdu(boolean use) {
        this.useFastPdu = use;
    }

    public boolean getUseFastPdu() {
        return this.useFastPdu;
    }

    /** 
     * PDU factory. Pass in an array of bytes, get the correct type of pdu back,
     * based on the PDU type field contained in the byte array.
     * @param data
     * @return A PDU of the appropriate concrete subclass of PDU
     */
    public Pdu createPdu(byte data[]) {
        // Promote a signed byte to an int, then do a bitwise AND to wipe out everthing but the 
        // first eight bits. This effectively lets us read this as an unsigned byte
        int pduType = 0x000000FF & (int) data[2]; // The pdu type is a one-byte, unsigned byte in the third byte position.

        // Do a lookup to get the enumeration instance that corresponds to this value. 
        PduType pduTypeEnum = PduType.lookup[pduType];

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        Pdu aPdu = null;

        switch (pduTypeEnum) {
            case ENTITY_STATE:
                // if the user has created the factory requesting that he get fast espdus back, give him those.
                if (useFastPdu) {
                    aPdu = new FastEntityStatePdu();
                } else {
                    aPdu = new EntityStatePdu();
                }

                aPdu.unmarshal(dis);
                break;

//            case ACKNOWLEDGE:
//                aPdu = new AcknowledgePdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case ACTION_REQUEST:
//                aPdu = new ActionRequestPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case COLLISION:
//                aPdu = new CollisionPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case COMMENT:
//                aPdu = new CommentPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case CREATE_ENTITY:
//                aPdu = new CreateEntityPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case DATA:
//                aPdu = new DataPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case DATA_QUERY:
//                aPdu = new DataQueryPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case DETONATION:
//                aPdu = new DetonationPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case FIRE:
//                aPdu = new FirePdu();
//                aPdu.unmarshal(dis);
//                break;
//                
//            case ELECTROMAGNETIC_EMISSION:
//              aPdu = new ElectronicEmissionsPdu(); 
//              aPdu.unmarshal(dis);
//              break;
//
//            case SERVICE_REQUEST:
//                aPdu = new ServiceRequestPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case REMOVE_ENTITY:
//                aPdu = new RemoveEntityPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case REPAIR_COMPLETE:
//                aPdu = new RepairCompletePdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case RESUPPLY_CANCEL:
//                aPdu = new ResupplyCancelPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case RESUPPLY_OFFER:
//                aPdu = new ResupplyOfferPdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case REPAIR_RESPONSE:
//                aPdu = new RepairResponsePdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case START_RESUME:
//                aPdu = new StartResumePdu();
//                aPdu.unmarshal(dis);
//                break;
//
//            case STOP_FREEZE:
//                aPdu = new StopFreezePdu();
//                aPdu.unmarshal(dis);
//                break;

            default:
                System.out.print("PDU not implemented. Type = " + pduType + "\n");
                if (pduTypeEnum != null) {
                    System.out.print("  PDU not implemented name is: " + pduTypeEnum.getDescription());
                }
                System.out.println();

        }

        return aPdu;
    }

    /**
     * PDU factory. Pass in an array of bytes, get the correct type of pdu back,
     * based on the PDU type field contained in the byte buffer.
     * @param buff
     * @return null if there was an error creating the Pdu
     */
    public Pdu createPdu(java.nio.ByteBuffer buff) {
        int pos = buff.position();          // Save buffer's position
        if (pos + 2 > buff.limit()) {       // Make sure there's enough space in buffer
            return null;                    // Else return null
        }   // end if: buffer too short
        buff.position(pos + 2);             // Advance to third byte
        int pduType = buff.get() & 0xFF;    // Read Pdu type
        buff.position(pos);                 // Reset buffer
        
        if (pduType >= PduType.lookup.length)
        {
          return null;
        }

        // Do a lookup to get the enumeration instance that corresponds to this value.
        PduType pduTypeEnum = PduType.lookup[pduType];
        Pdu aPdu = null;

        switch (pduTypeEnum) {
            case ENTITY_STATE:
                // if the user has created the factory requesting that he get fast espdus back, give him those.
                if (useFastPdu) {
                    aPdu = new FastEntityStatePdu();
                } else {
                    aPdu = new EntityStatePdu();
                }
                break;

//            case FIRE:
//                aPdu = new FirePdu();
//                break;
//
//            case DETONATION:
//                aPdu = new DetonationPdu();
//                break;
//
//            case COLLISION:
//                aPdu = new CollisionPdu();
//                break;
//
//            case SERVICE_REQUEST:
//                aPdu = new ServiceRequestPdu();
//                break;
//
//            case RESUPPLY_OFFER:
//                aPdu = new ResupplyOfferPdu();
//                break;
//
//            case RESUPPLY_RECEIVED:
//                aPdu = new ResupplyReceivedPdu();
//                break;
//
//            case RESUPPLY_CANCEL:
//                aPdu = new ResupplyCancelPdu();
//                break;
//
//            case REPAIR_COMPLETE:
//                aPdu = new RepairCompletePdu();
//                break;
//
//            case REPAIR_RESPONSE:
//                aPdu = new RepairResponsePdu();
//                break;
//
//            case CREATE_ENTITY:
//                aPdu = new CreateEntityPdu();
//                break;
//
//            case REMOVE_ENTITY:
//                aPdu = new RemoveEntityPdu();
//                break;
//
//            case START_RESUME:
//                aPdu = new StartResumePdu();
//                break;
//
//            case STOP_FREEZE:
//                aPdu = new StopFreezePdu();
//                break;
//
//            case ACKNOWLEDGE:
//                aPdu = new AcknowledgePdu();
//                break;
//
//            case ACTION_REQUEST:
//                aPdu = new ActionRequestPdu();
//                break;
//                
//            case ELECTROMAGNETIC_EMISSION:
//                aPdu = new ElectronicEmissionsPdu();
//                break;

            default:
//                System.out.print("PDU not implemented. Type = " + pduType + "\n");
//                if (pduTypeEnum != null) {
//                    System.out.print("PDU not implemented name is: " + pduTypeEnum.getDescription());
//                }
//                System.out.println();

        }   // end switch
        
        if (aPdu == null)
        {
          return null;
        }

        try {
            aPdu.unmarshal(buff);
        } catch (Exception exc) {
            //System.err.println("Could not unmarshal Pdu: " + exc.getMessage());
            aPdu = null;
        }

        return aPdu;
    }
}
