/**
 * CardInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class CardInfo  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private java.lang.String cardStatusDesc;

    private int cardStatusId;

    private boolean dataValid;

    private java.lang.String nameAndFamilyName;

    private java.lang.String pan;

    private proxy.com.iac.ambit.model.CardInfo value;

    public CardInfo() {
    }

    public CardInfo(
           java.lang.String cardStatusDesc,
           int cardStatusId,
           boolean dataValid,
           java.lang.String nameAndFamilyName,
           java.lang.String pan,
           proxy.com.iac.ambit.model.CardInfo value) {
           this.cardStatusDesc = cardStatusDesc;
           this.cardStatusId = cardStatusId;
           this.dataValid = dataValid;
           this.nameAndFamilyName = nameAndFamilyName;
           this.pan = pan;
           this.value = value;
    }


    /**
     * Gets the cardStatusDesc value for this CardInfo.
     * 
     * @return cardStatusDesc
     */
    public java.lang.String getCardStatusDesc() {
        return cardStatusDesc;
    }


    /**
     * Sets the cardStatusDesc value for this CardInfo.
     * 
     * @param cardStatusDesc
     */
    public void setCardStatusDesc(java.lang.String cardStatusDesc) {
        this.cardStatusDesc = cardStatusDesc;
    }


    /**
     * Gets the cardStatusId value for this CardInfo.
     * 
     * @return cardStatusId
     */
    public int getCardStatusId() {
        return cardStatusId;
    }


    /**
     * Sets the cardStatusId value for this CardInfo.
     * 
     * @param cardStatusId
     */
    public void setCardStatusId(int cardStatusId) {
        this.cardStatusId = cardStatusId;
    }


    /**
     * Gets the dataValid value for this CardInfo.
     * 
     * @return dataValid
     */
    public boolean isDataValid() {
        return dataValid;
    }


    /**
     * Sets the dataValid value for this CardInfo.
     * 
     * @param dataValid
     */
    public void setDataValid(boolean dataValid) {
        this.dataValid = dataValid;
    }


    /**
     * Gets the nameAndFamilyName value for this CardInfo.
     * 
     * @return nameAndFamilyName
     */
    public java.lang.String getNameAndFamilyName() {
        return nameAndFamilyName;
    }


    /**
     * Sets the nameAndFamilyName value for this CardInfo.
     * 
     * @param nameAndFamilyName
     */
    public void setNameAndFamilyName(java.lang.String nameAndFamilyName) {
        this.nameAndFamilyName = nameAndFamilyName;
    }


    /**
     * Gets the pan value for this CardInfo.
     * 
     * @return pan
     */
    public java.lang.String getPan() {
        return pan;
    }


    /**
     * Sets the pan value for this CardInfo.
     * 
     * @param pan
     */
    public void setPan(java.lang.String pan) {
        this.pan = pan;
    }


    /**
     * Gets the value value for this CardInfo.
     * 
     * @return value
     */
    public proxy.com.iac.ambit.model.CardInfo getValue() {
        return value;
    }


    /**
     * Sets the value value for this CardInfo.
     * 
     * @param value
     */
    public void setValue(proxy.com.iac.ambit.model.CardInfo value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CardInfo)) return false;
        CardInfo other = (CardInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cardStatusDesc==null && other.getCardStatusDesc()==null) || 
             (this.cardStatusDesc!=null &&
              this.cardStatusDesc.equals(other.getCardStatusDesc()))) &&
            this.cardStatusId == other.getCardStatusId() &&
            this.dataValid == other.isDataValid() &&
            ((this.nameAndFamilyName==null && other.getNameAndFamilyName()==null) || 
             (this.nameAndFamilyName!=null &&
              this.nameAndFamilyName.equals(other.getNameAndFamilyName()))) &&
            ((this.pan==null && other.getPan()==null) || 
             (this.pan!=null &&
              this.pan.equals(other.getPan()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
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
        if (getCardStatusDesc() != null) {
            _hashCode += getCardStatusDesc().hashCode();
        }
        _hashCode += getCardStatusId();
        _hashCode += (isDataValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNameAndFamilyName() != null) {
            _hashCode += getNameAndFamilyName().hashCode();
        }
        if (getPan() != null) {
            _hashCode += getPan().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CardInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "CardInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardStatusDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardStatusDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardStatusId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardStatusId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataValid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameAndFamilyName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nameAndFamilyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "CardInfo"));
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
