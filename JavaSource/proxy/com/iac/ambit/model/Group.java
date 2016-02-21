/**
 * Group.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class Group  implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
    private boolean dataValid;

    private java.lang.String groupActive;

    private java.lang.String groupDescription;

    private java.lang.String groupDescriptionFA;

    private java.lang.String groupId;

    private java.lang.String groupName;

    private java.lang.String groupNameFA;

    public Group() {
    }

    public Group(
           boolean dataValid,
           java.lang.String groupActive,
           java.lang.String groupDescription,
           java.lang.String groupDescriptionFA,
           java.lang.String groupId,
           java.lang.String groupName,
           java.lang.String groupNameFA) {
           this.dataValid = dataValid;
           this.groupActive = groupActive;
           this.groupDescription = groupDescription;
           this.groupDescriptionFA = groupDescriptionFA;
           this.groupId = groupId;
           this.groupName = groupName;
           this.groupNameFA = groupNameFA;
    }


    /**
     * Gets the dataValid value for this Group.
     * 
     * @return dataValid
     */
    public boolean isDataValid() {
        return dataValid;
    }


    /**
     * Sets the dataValid value for this Group.
     * 
     * @param dataValid
     */
    public void setDataValid(boolean dataValid) {
        this.dataValid = dataValid;
    }


    /**
     * Gets the groupActive value for this Group.
     * 
     * @return groupActive
     */
    public java.lang.String getGroupActive() {
        return groupActive;
    }


    /**
     * Sets the groupActive value for this Group.
     * 
     * @param groupActive
     */
    public void setGroupActive(java.lang.String groupActive) {
        this.groupActive = groupActive;
    }


    /**
     * Gets the groupDescription value for this Group.
     * 
     * @return groupDescription
     */
    public java.lang.String getGroupDescription() {
        return groupDescription;
    }


    /**
     * Sets the groupDescription value for this Group.
     * 
     * @param groupDescription
     */
    public void setGroupDescription(java.lang.String groupDescription) {
        this.groupDescription = groupDescription;
    }


    /**
     * Gets the groupDescriptionFA value for this Group.
     * 
     * @return groupDescriptionFA
     */
    public java.lang.String getGroupDescriptionFA() {
        return groupDescriptionFA;
    }


    /**
     * Sets the groupDescriptionFA value for this Group.
     * 
     * @param groupDescriptionFA
     */
    public void setGroupDescriptionFA(java.lang.String groupDescriptionFA) {
        this.groupDescriptionFA = groupDescriptionFA;
    }


    /**
     * Gets the groupId value for this Group.
     * 
     * @return groupId
     */
    public java.lang.String getGroupId() {
        return groupId;
    }


    /**
     * Sets the groupId value for this Group.
     * 
     * @param groupId
     */
    public void setGroupId(java.lang.String groupId) {
        this.groupId = groupId;
    }


    /**
     * Gets the groupName value for this Group.
     * 
     * @return groupName
     */
    public java.lang.String getGroupName() {
        return groupName;
    }


    /**
     * Sets the groupName value for this Group.
     * 
     * @param groupName
     */
    public void setGroupName(java.lang.String groupName) {
        this.groupName = groupName;
    }


    /**
     * Gets the groupNameFA value for this Group.
     * 
     * @return groupNameFA
     */
    public java.lang.String getGroupNameFA() {
        return groupNameFA;
    }


    /**
     * Sets the groupNameFA value for this Group.
     * 
     * @param groupNameFA
     */
    public void setGroupNameFA(java.lang.String groupNameFA) {
        this.groupNameFA = groupNameFA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Group)) return false;
        Group other = (Group) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.dataValid == other.isDataValid() &&
            ((this.groupActive==null && other.getGroupActive()==null) || 
             (this.groupActive!=null &&
              this.groupActive.equals(other.getGroupActive()))) &&
            ((this.groupDescription==null && other.getGroupDescription()==null) || 
             (this.groupDescription!=null &&
              this.groupDescription.equals(other.getGroupDescription()))) &&
            ((this.groupDescriptionFA==null && other.getGroupDescriptionFA()==null) || 
             (this.groupDescriptionFA!=null &&
              this.groupDescriptionFA.equals(other.getGroupDescriptionFA()))) &&
            ((this.groupId==null && other.getGroupId()==null) || 
             (this.groupId!=null &&
              this.groupId.equals(other.getGroupId()))) &&
            ((this.groupName==null && other.getGroupName()==null) || 
             (this.groupName!=null &&
              this.groupName.equals(other.getGroupName()))) &&
            ((this.groupNameFA==null && other.getGroupNameFA()==null) || 
             (this.groupNameFA!=null &&
              this.groupNameFA.equals(other.getGroupNameFA())));
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
        _hashCode += (isDataValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getGroupActive() != null) {
            _hashCode += getGroupActive().hashCode();
        }
        if (getGroupDescription() != null) {
            _hashCode += getGroupDescription().hashCode();
        }
        if (getGroupDescriptionFA() != null) {
            _hashCode += getGroupDescriptionFA().hashCode();
        }
        if (getGroupId() != null) {
            _hashCode += getGroupId().hashCode();
        }
        if (getGroupName() != null) {
            _hashCode += getGroupName().hashCode();
        }
        if (getGroupNameFA() != null) {
            _hashCode += getGroupNameFA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Group.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "Group"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataValid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupActive");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupActive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupDescriptionFA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupDescriptionFA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupNameFA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupNameFA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
