/**
 */
package com.sky.dis.om.rpr;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>World Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.sky.dis.om.rpr.WorldLocation#getX <em>X</em>}</li>
 *   <li>{@link com.sky.dis.om.rpr.WorldLocation#getY <em>Y</em>}</li>
 *   <li>{@link com.sky.dis.om.rpr.WorldLocation#getZ <em>Z</em>}</li>
 *   <li>{@link com.sky.dis.om.rpr.WorldLocation#getEcefString <em>Ecef String</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.sky.dis.om.rpr.RprPackage#getWorldLocation()
 * @model
 * @generated
 */
public interface WorldLocation extends EObject {
    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>X</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(double)
     * @see com.sky.dis.om.rpr.RprPackage#getWorldLocation_X()
     * @model required="true"
     * @generated
     */
    double getX();

    /**
     * Sets the value of the '{@link com.sky.dis.om.rpr.WorldLocation#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(double value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Y</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(double)
     * @see com.sky.dis.om.rpr.RprPackage#getWorldLocation_Y()
     * @model required="true"
     * @generated
     */
    double getY();

    /**
     * Sets the value of the '{@link com.sky.dis.om.rpr.WorldLocation#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(double value);

    /**
     * Returns the value of the '<em><b>Z</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Z</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Z</em>' attribute.
     * @see #setZ(double)
     * @see com.sky.dis.om.rpr.RprPackage#getWorldLocation_Z()
     * @model required="true"
     * @generated
     */
    double getZ();

    /**
     * Sets the value of the '{@link com.sky.dis.om.rpr.WorldLocation#getZ <em>Z</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Z</em>' attribute.
     * @see #getZ()
     * @generated
     */
    void setZ(double value);

    /**
     * Returns the value of the '<em><b>Ecef String</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ecef String</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ecef String</em>' attribute.
     * @see com.sky.dis.om.rpr.RprPackage#getWorldLocation_EcefString()
     * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    String getEcefString();

} // WorldLocation
