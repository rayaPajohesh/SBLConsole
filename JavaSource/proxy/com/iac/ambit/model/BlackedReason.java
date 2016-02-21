/**
 * BlackedReason.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class BlackedReason  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private java.lang.String blackedReasonDescription;

    private java.lang.String blackedReasonDescriptionFA;

    private java.lang.String blackedReasonId;

    private proxy.com.iac.ambit.model.BlackedReason[] value;

    public BlackedReason() {
    }

    public BlackedReason(
           java.lang.String blackedReasonDescription,
           java.lang.String blackedReasonDescriptionFA,
           java.lang.String blackedReasonId,
           proxy.com.iac.ambit.model.BlackedReason[] value) {
           this.blackedReasonDescription = blackedReasonDescription;
           this.blackedReasonDescriptionFA = blackedReasonDescriptionFA;
           this.blackedReasonId = blackedReasonId;
           this.value = value;
    }


    /**
     * Gets the blackedReasonDescription value for this BlackedReason.
     * 
     * @return blackedReasonDescription
     */
    public java.lang.String getBlackedReasonDescription() {
        return blackedReasonDescription;
    }


    /**
     * Sets the blackedReasonDescription value for this BlackedReason.
     * 
     * @param blackedReasonDescription
     */
    public void setBlackedReasonDescription(java.lang.String blackedReasonDescription) {
        this.blackedReasonDescription = blackedReasonDescription;
    }


    /**
     * Gets the blackedReasonDescriptionFA value for this BlackedReason.
     * 
     * @return blackedReasonDescriptionFA
     */
    public java.lang.String getBlackedReasonDescriptionFA() {
        return blackedReasonDescriptionFA;
    }


    /**
     * Sets the blackedReasonDescriptionFA value for this BlackedReason.
     * 
     * @param blackedReasonDescriptionFA
     */
    public void setBlackedReasonDescriptionFA(java.lang.String blackedReasonDescriptionFA) {
        this.blackedReasonDescriptionFA = blackedReasonDescriptionFA;
    }


    /**
     * Gets the blackedReasonId value for this BlackedReason.
     * 
     * @return blackedReasonId
     */
    public java.lang.String getBlackedReasonId() {
        return blackedReasonId;
    }


    /**
     * Sets the blackedReasonId value for this BlackedReason.
     * 
     * @param blackedReasonId
     */
    public void setBlackedReasonId(java.lang.String blackedReasonId) {
        this.blackedReasonId = blackedReasonId;
    }


    /**
     * Gets the value value for this BlackedReason.
     * 
     * @return value
     */
    public proxy.com.iac.ambit.model.BlackedReason[] getValue() {
        return value;
    }


    /**
     * Sets the value value for this BlackedReason.
     * 
     * @param value
     */
    public void setValue(proxy.com.iac.ambit.model.BlackedReason[] value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BlackedReason)) return false;
        BlackedReason other = (BlackedReason) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.blackedReasonDescription==null && other.getBlackedReasonDescription()==null) || 
             (this.blackedReasonDescription!=null &&
              this.blackedReasonDescription.equals(other.getBlackedReasonDescription()))) &&
            ((this.blackedReasonDescriptionFA==null && other.getBlackedReasonDescriptionFA()==null) || 
             (this.blackedReasonDescriptionFA!=null &&
              this.blackedReasonDescriptionFA.equals(other.getBlackedReasonDescriptionFA()))) &&
            ((this.blackedReasonId==null && other.getBlackedReasonId()==null) || 
             (this.blackedReasonId!=null &&
              this.blackedReasonId.equals(other.getBlackedReasonId()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              java.util.Arrays.equals(this.value, other.getValue())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBlackedReasonDescription() != null) {
            _hashCode += getBlackedReasonDescription().hashCode();
        }
        if (getBlackedReasonDescriptionFA() != null) {
            _hashCode += getBlackedReasonDescriptionFA().hashCode();
        }
        if (getBlackedReasonId() != null) {
            _hashCode += getBlackedReasonId().hashCode();
        }
        if (getValue() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValue());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValue(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BlackedReason.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "BlackedReason"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blackedReasonDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blackedReasonDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blackedReasonDescriptionFA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blackedReasonDescriptionFA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blackedReasonId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blackedReasonId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "BlackedReason"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
