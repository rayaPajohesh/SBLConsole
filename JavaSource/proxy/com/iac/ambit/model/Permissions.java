/**
 * Permissions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class Permissions  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private boolean dataValid;

    private java.lang.String permissionActionTypeId;

    private int permissionId;

    private int permissionParentId;

    private java.lang.String permissionStatus;

    private java.lang.String permissionTitle;

    private java.lang.String permissionTitleFA;

    public Permissions() {
    }

    public Permissions(
           boolean dataValid,
           java.lang.String permissionActionTypeId,
           int permissionId,
           int permissionParentId,
           java.lang.String permissionStatus,
           java.lang.String permissionTitle,
           java.lang.String permissionTitleFA) {
           this.dataValid = dataValid;
           this.permissionActionTypeId = permissionActionTypeId;
           this.permissionId = permissionId;
           this.permissionParentId = permissionParentId;
           this.permissionStatus = permissionStatus;
           this.permissionTitle = permissionTitle;
           this.permissionTitleFA = permissionTitleFA;
    }


    /**
     * Gets the dataValid value for this Permissions.
     * 
     * @return dataValid
     */
    public boolean isDataValid() {
        return dataValid;
    }


    /**
     * Sets the dataValid value for this Permissions.
     * 
     * @param dataValid
     */
    public void setDataValid(boolean dataValid) {
        this.dataValid = dataValid;
    }


    /**
     * Gets the permissionActionTypeId value for this Permissions.
     * 
     * @return permissionActionTypeId
     */
    public java.lang.String getPermissionActionTypeId() {
        return permissionActionTypeId;
    }


    /**
     * Sets the permissionActionTypeId value for this Permissions.
     * 
     * @param permissionActionTypeId
     */
    public void setPermissionActionTypeId(java.lang.String permissionActionTypeId) {
        this.permissionActionTypeId = permissionActionTypeId;
    }


    /**
     * Gets the permissionId value for this Permissions.
     * 
     * @return permissionId
     */
    public int getPermissionId() {
        return permissionId;
    }


    /**
     * Sets the permissionId value for this Permissions.
     * 
     * @param permissionId
     */
    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }


    /**
     * Gets the permissionParentId value for this Permissions.
     * 
     * @return permissionParentId
     */
    public int getPermissionParentId() {
        return permissionParentId;
    }


    /**
     * Sets the permissionParentId value for this Permissions.
     * 
     * @param permissionParentId
     */
    public void setPermissionParentId(int permissionParentId) {
        this.permissionParentId = permissionParentId;
    }


    /**
     * Gets the permissionStatus value for this Permissions.
     * 
     * @return permissionStatus
     */
    public java.lang.String getPermissionStatus() {
        return permissionStatus;
    }


    /**
     * Sets the permissionStatus value for this Permissions.
     * 
     * @param permissionStatus
     */
    public void setPermissionStatus(java.lang.String permissionStatus) {
        this.permissionStatus = permissionStatus;
    }


    /**
     * Gets the permissionTitle value for this Permissions.
     * 
     * @return permissionTitle
     */
    public java.lang.String getPermissionTitle() {
        return permissionTitle;
    }


    /**
     * Sets the permissionTitle value for this Permissions.
     * 
     * @param permissionTitle
     */
    public void setPermissionTitle(java.lang.String permissionTitle) {
        this.permissionTitle = permissionTitle;
    }


    /**
     * Gets the permissionTitleFA value for this Permissions.
     * 
     * @return permissionTitleFA
     */
    public java.lang.String getPermissionTitleFA() {
        return permissionTitleFA;
    }


    /**
     * Sets the permissionTitleFA value for this Permissions.
     * 
     * @param permissionTitleFA
     */
    public void setPermissionTitleFA(java.lang.String permissionTitleFA) {
        this.permissionTitleFA = permissionTitleFA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Permissions)) return false;
        Permissions other = (Permissions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.dataValid == other.isDataValid() &&
            ((this.permissionActionTypeId==null && other.getPermissionActionTypeId()==null) || 
             (this.permissionActionTypeId!=null &&
              this.permissionActionTypeId.equals(other.getPermissionActionTypeId()))) &&
            this.permissionId == other.getPermissionId() &&
            this.permissionParentId == other.getPermissionParentId() &&
            ((this.permissionStatus==null && other.getPermissionStatus()==null) || 
             (this.permissionStatus!=null &&
              this.permissionStatus.equals(other.getPermissionStatus()))) &&
            ((this.permissionTitle==null && other.getPermissionTitle()==null) || 
             (this.permissionTitle!=null &&
              this.permissionTitle.equals(other.getPermissionTitle()))) &&
            ((this.permissionTitleFA==null && other.getPermissionTitleFA()==null) || 
             (this.permissionTitleFA!=null &&
              this.permissionTitleFA.equals(other.getPermissionTitleFA())));
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
        if (getPermissionActionTypeId() != null) {
            _hashCode += getPermissionActionTypeId().hashCode();
        }
        _hashCode += getPermissionId();
        _hashCode += getPermissionParentId();
        if (getPermissionStatus() != null) {
            _hashCode += getPermissionStatus().hashCode();
        }
        if (getPermissionTitle() != null) {
            _hashCode += getPermissionTitle().hashCode();
        }
        if (getPermissionTitleFA() != null) {
            _hashCode += getPermissionTitleFA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Permissions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "Permissions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataValid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissionActionTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permissionActionTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permissionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissionParentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permissionParentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permissionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissionTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permissionTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissionTitleFA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permissionTitleFA"));
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
