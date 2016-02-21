/**
 * ActivityLog.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class ActivityLog  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private java.lang.String activityDate;

    private java.lang.String activityDescription;

    private java.lang.String activityDescriptionFa;

    private java.lang.String activityFromDate;

    private java.lang.String activityTime;

    private java.lang.String activityToDate;

    private java.lang.String activityTypeId;

    private java.lang.String activityTypeName;

    private java.lang.String activityTypeNameFa;

    private java.lang.String aggregate;

    private boolean dataValid;

    private java.lang.String userId;

    public ActivityLog() {
    }

    public ActivityLog(
           java.lang.String activityDate,
           java.lang.String activityDescription,
           java.lang.String activityDescriptionFa,
           java.lang.String activityFromDate,
           java.lang.String activityTime,
           java.lang.String activityToDate,
           java.lang.String activityTypeId,
           java.lang.String activityTypeName,
           java.lang.String activityTypeNameFa,
           java.lang.String aggregate,
           boolean dataValid,
           java.lang.String userId) {
           this.activityDate = activityDate;
           this.activityDescription = activityDescription;
           this.activityDescriptionFa = activityDescriptionFa;
           this.activityFromDate = activityFromDate;
           this.activityTime = activityTime;
           this.activityToDate = activityToDate;
           this.activityTypeId = activityTypeId;
           this.activityTypeName = activityTypeName;
           this.activityTypeNameFa = activityTypeNameFa;
           this.aggregate = aggregate;
           this.dataValid = dataValid;
           this.userId = userId;
    }


    /**
     * Gets the activityDate value for this ActivityLog.
     * 
     * @return activityDate
     */
    public java.lang.String getActivityDate() {
        return activityDate;
    }


    /**
     * Sets the activityDate value for this ActivityLog.
     * 
     * @param activityDate
     */
    public void setActivityDate(java.lang.String activityDate) {
        this.activityDate = activityDate;
    }


    /**
     * Gets the activityDescription value for this ActivityLog.
     * 
     * @return activityDescription
     */
    public java.lang.String getActivityDescription() {
        return activityDescription;
    }


    /**
     * Sets the activityDescription value for this ActivityLog.
     * 
     * @param activityDescription
     */
    public void setActivityDescription(java.lang.String activityDescription) {
        this.activityDescription = activityDescription;
    }


    /**
     * Gets the activityDescriptionFa value for this ActivityLog.
     * 
     * @return activityDescriptionFa
     */
    public java.lang.String getActivityDescriptionFa() {
        return activityDescriptionFa;
    }


    /**
     * Sets the activityDescriptionFa value for this ActivityLog.
     * 
     * @param activityDescriptionFa
     */
    public void setActivityDescriptionFa(java.lang.String activityDescriptionFa) {
        this.activityDescriptionFa = activityDescriptionFa;
    }


    /**
     * Gets the activityFromDate value for this ActivityLog.
     * 
     * @return activityFromDate
     */
    public java.lang.String getActivityFromDate() {
        return activityFromDate;
    }


    /**
     * Sets the activityFromDate value for this ActivityLog.
     * 
     * @param activityFromDate
     */
    public void setActivityFromDate(java.lang.String activityFromDate) {
        this.activityFromDate = activityFromDate;
    }


    /**
     * Gets the activityTime value for this ActivityLog.
     * 
     * @return activityTime
     */
    public java.lang.String getActivityTime() {
        return activityTime;
    }


    /**
     * Sets the activityTime value for this ActivityLog.
     * 
     * @param activityTime
     */
    public void setActivityTime(java.lang.String activityTime) {
        this.activityTime = activityTime;
    }


    /**
     * Gets the activityToDate value for this ActivityLog.
     * 
     * @return activityToDate
     */
    public java.lang.String getActivityToDate() {
        return activityToDate;
    }


    /**
     * Sets the activityToDate value for this ActivityLog.
     * 
     * @param activityToDate
     */
    public void setActivityToDate(java.lang.String activityToDate) {
        this.activityToDate = activityToDate;
    }


    /**
     * Gets the activityTypeId value for this ActivityLog.
     * 
     * @return activityTypeId
     */
    public java.lang.String getActivityTypeId() {
        return activityTypeId;
    }


    /**
     * Sets the activityTypeId value for this ActivityLog.
     * 
     * @param activityTypeId
     */
    public void setActivityTypeId(java.lang.String activityTypeId) {
        this.activityTypeId = activityTypeId;
    }


    /**
     * Gets the activityTypeName value for this ActivityLog.
     * 
     * @return activityTypeName
     */
    public java.lang.String getActivityTypeName() {
        return activityTypeName;
    }


    /**
     * Sets the activityTypeName value for this ActivityLog.
     * 
     * @param activityTypeName
     */
    public void setActivityTypeName(java.lang.String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }


    /**
     * Gets the activityTypeNameFa value for this ActivityLog.
     * 
     * @return activityTypeNameFa
     */
    public java.lang.String getActivityTypeNameFa() {
        return activityTypeNameFa;
    }


    /**
     * Sets the activityTypeNameFa value for this ActivityLog.
     * 
     * @param activityTypeNameFa
     */
    public void setActivityTypeNameFa(java.lang.String activityTypeNameFa) {
        this.activityTypeNameFa = activityTypeNameFa;
    }


    /**
     * Gets the aggregate value for this ActivityLog.
     * 
     * @return aggregate
     */
    public java.lang.String getAggregate() {
        return aggregate;
    }


    /**
     * Sets the aggregate value for this ActivityLog.
     * 
     * @param aggregate
     */
    public void setAggregate(java.lang.String aggregate) {
        this.aggregate = aggregate;
    }


    /**
     * Gets the dataValid value for this ActivityLog.
     * 
     * @return dataValid
     */
    public boolean isDataValid() {
        return dataValid;
    }


    /**
     * Sets the dataValid value for this ActivityLog.
     * 
     * @param dataValid
     */
    public void setDataValid(boolean dataValid) {
        this.dataValid = dataValid;
    }


    /**
     * Gets the userId value for this ActivityLog.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this ActivityLog.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ActivityLog)) return false;
        ActivityLog other = (ActivityLog) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.activityDate==null && other.getActivityDate()==null) || 
             (this.activityDate!=null &&
              this.activityDate.equals(other.getActivityDate()))) &&
            ((this.activityDescription==null && other.getActivityDescription()==null) || 
             (this.activityDescription!=null &&
              this.activityDescription.equals(other.getActivityDescription()))) &&
            ((this.activityDescriptionFa==null && other.getActivityDescriptionFa()==null) || 
             (this.activityDescriptionFa!=null &&
              this.activityDescriptionFa.equals(other.getActivityDescriptionFa()))) &&
            ((this.activityFromDate==null && other.getActivityFromDate()==null) || 
             (this.activityFromDate!=null &&
              this.activityFromDate.equals(other.getActivityFromDate()))) &&
            ((this.activityTime==null && other.getActivityTime()==null) || 
             (this.activityTime!=null &&
              this.activityTime.equals(other.getActivityTime()))) &&
            ((this.activityToDate==null && other.getActivityToDate()==null) || 
             (this.activityToDate!=null &&
              this.activityToDate.equals(other.getActivityToDate()))) &&
            ((this.activityTypeId==null && other.getActivityTypeId()==null) || 
             (this.activityTypeId!=null &&
              this.activityTypeId.equals(other.getActivityTypeId()))) &&
            ((this.activityTypeName==null && other.getActivityTypeName()==null) || 
             (this.activityTypeName!=null &&
              this.activityTypeName.equals(other.getActivityTypeName()))) &&
            ((this.activityTypeNameFa==null && other.getActivityTypeNameFa()==null) || 
             (this.activityTypeNameFa!=null &&
              this.activityTypeNameFa.equals(other.getActivityTypeNameFa()))) &&
            ((this.aggregate==null && other.getAggregate()==null) || 
             (this.aggregate!=null &&
              this.aggregate.equals(other.getAggregate()))) &&
            this.dataValid == other.isDataValid() &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId())));
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
        if (getActivityDate() != null) {
            _hashCode += getActivityDate().hashCode();
        }
        if (getActivityDescription() != null) {
            _hashCode += getActivityDescription().hashCode();
        }
        if (getActivityDescriptionFa() != null) {
            _hashCode += getActivityDescriptionFa().hashCode();
        }
        if (getActivityFromDate() != null) {
            _hashCode += getActivityFromDate().hashCode();
        }
        if (getActivityTime() != null) {
            _hashCode += getActivityTime().hashCode();
        }
        if (getActivityToDate() != null) {
            _hashCode += getActivityToDate().hashCode();
        }
        if (getActivityTypeId() != null) {
            _hashCode += getActivityTypeId().hashCode();
        }
        if (getActivityTypeName() != null) {
            _hashCode += getActivityTypeName().hashCode();
        }
        if (getActivityTypeNameFa() != null) {
            _hashCode += getActivityTypeNameFa().hashCode();
        }
        if (getAggregate() != null) {
            _hashCode += getAggregate().hashCode();
        }
        _hashCode += (isDataValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActivityLog.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "ActivityLog"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDescriptionFa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityDescriptionFa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityFromDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityFromDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityToDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityToDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityTypeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityTypeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityTypeNameFa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activityTypeNameFa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aggregate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aggregate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataValid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
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
