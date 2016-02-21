/**
 * CodeActiveFlag.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class CodeActiveFlag  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private java.lang.String codeActiveDescription;

    private java.lang.String codeActiveDescriptionFA;

    private java.lang.String codeActiveFlag;

    private proxy.com.iac.ambit.model.CodeActiveFlag[] value;

    public CodeActiveFlag() {
    }

    public CodeActiveFlag(
           java.lang.String codeActiveDescription,
           java.lang.String codeActiveDescriptionFA,
           java.lang.String codeActiveFlag,
           proxy.com.iac.ambit.model.CodeActiveFlag[] value) {
           this.codeActiveDescription = codeActiveDescription;
           this.codeActiveDescriptionFA = codeActiveDescriptionFA;
           this.codeActiveFlag = codeActiveFlag;
           this.value = value;
    }


    /**
     * Gets the codeActiveDescription value for this CodeActiveFlag.
     * 
     * @return codeActiveDescription
     */
    public java.lang.String getCodeActiveDescription() {
        return codeActiveDescription;
    }


    /**
     * Sets the codeActiveDescription value for this CodeActiveFlag.
     * 
     * @param codeActiveDescription
     */
    public void setCodeActiveDescription(java.lang.String codeActiveDescription) {
        this.codeActiveDescription = codeActiveDescription;
    }


    /**
     * Gets the codeActiveDescriptionFA value for this CodeActiveFlag.
     * 
     * @return codeActiveDescriptionFA
     */
    public java.lang.String getCodeActiveDescriptionFA() {
        return codeActiveDescriptionFA;
    }


    /**
     * Sets the codeActiveDescriptionFA value for this CodeActiveFlag.
     * 
     * @param codeActiveDescriptionFA
     */
    public void setCodeActiveDescriptionFA(java.lang.String codeActiveDescriptionFA) {
        this.codeActiveDescriptionFA = codeActiveDescriptionFA;
    }


    /**
     * Gets the codeActiveFlag value for this CodeActiveFlag.
     * 
     * @return codeActiveFlag
     */
    public java.lang.String getCodeActiveFlag() {
        return codeActiveFlag;
    }


    /**
     * Sets the codeActiveFlag value for this CodeActiveFlag.
     * 
     * @param codeActiveFlag
     */
    public void setCodeActiveFlag(java.lang.String codeActiveFlag) {
        this.codeActiveFlag = codeActiveFlag;
    }


    /**
     * Gets the value value for this CodeActiveFlag.
     * 
     * @return value
     */
    public proxy.com.iac.ambit.model.CodeActiveFlag[] getValue() {
        return value;
    }


    /**
     * Sets the value value for this CodeActiveFlag.
     * 
     * @param value
     */
    public void setValue(proxy.com.iac.ambit.model.CodeActiveFlag[] value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodeActiveFlag)) return false;
        CodeActiveFlag other = (CodeActiveFlag) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codeActiveDescription==null && other.getCodeActiveDescription()==null) || 
             (this.codeActiveDescription!=null &&
              this.codeActiveDescription.equals(other.getCodeActiveDescription()))) &&
            ((this.codeActiveDescriptionFA==null && other.getCodeActiveDescriptionFA()==null) || 
             (this.codeActiveDescriptionFA!=null &&
              this.codeActiveDescriptionFA.equals(other.getCodeActiveDescriptionFA()))) &&
            ((this.codeActiveFlag==null && other.getCodeActiveFlag()==null) || 
             (this.codeActiveFlag!=null &&
              this.codeActiveFlag.equals(other.getCodeActiveFlag()))) &&
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
        if (getCodeActiveDescription() != null) {
            _hashCode += getCodeActiveDescription().hashCode();
        }
        if (getCodeActiveDescriptionFA() != null) {
            _hashCode += getCodeActiveDescriptionFA().hashCode();
        }
        if (getCodeActiveFlag() != null) {
            _hashCode += getCodeActiveFlag().hashCode();
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
        new org.apache.axis.description.TypeDesc(CodeActiveFlag.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "CodeActiveFlag"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeActiveDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codeActiveDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeActiveDescriptionFA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codeActiveDescriptionFA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeActiveFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codeActiveFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "CodeActiveFlag"));
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
