/**
 * BlackList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class BlackList  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private java.lang.String allowedShetab;

    private java.lang.String allowedTerminalTypes;

    private java.lang.String allowedTerminals;

    private java.lang.String allowedTrans;

    private java.lang.String blackedActiveFlag;

    private java.lang.String blackedDate;

    private java.lang.String blackedReasonId;

    private java.lang.String blackedTime;

    private proxy.com.iac.ambit.model.CardInfo cardInfo;

    private java.lang.String comments;

    private boolean dataValid;

    private java.lang.String highlight;

    private java.lang.String pan;

    public BlackList() {
    }

    public BlackList(
           java.lang.String allowedShetab,
           java.lang.String allowedTerminalTypes,
           java.lang.String allowedTerminals,
           java.lang.String allowedTrans,
           java.lang.String blackedActiveFlag,
           java.lang.String blackedDate,
           java.lang.String blackedReasonId,
           java.lang.String blackedTime,
           proxy.com.iac.ambit.model.CardInfo cardInfo,
           java.lang.String comments,
           boolean dataValid,
           java.lang.String highlight,
           java.lang.String pan) {
           this.allowedShetab = allowedShetab;
           this.allowedTerminalTypes = allowedTerminalTypes;
           this.allowedTerminals = allowedTerminals;
           this.allowedTrans = allowedTrans;
           this.blackedActiveFlag = blackedActiveFlag;
           this.blackedDate = blackedDate;
           this.blackedReasonId = blackedReasonId;
           this.blackedTime = blackedTime;
           this.cardInfo = cardInfo;
           this.comments = comments;
           this.dataValid = dataValid;
           this.highlight = highlight;
           this.pan = pan;
    }


    /**
     * Gets the allowedShetab value for this BlackList.
     * 
     * @return allowedShetab
     */
    public java.lang.String getAllowedShetab() {
        return allowedShetab;
    }


    /**
     * Sets the allowedShetab value for this BlackList.
     * 
     * @param allowedShetab
     */
    public void setAllowedShetab(java.lang.String allowedShetab) {
        this.allowedShetab = allowedShetab;
    }


    /**
     * Gets the allowedTerminalTypes value for this BlackList.
     * 
     * @return allowedTerminalTypes
     */
    public java.lang.String getAllowedTerminalTypes() {
        return allowedTerminalTypes;
    }


    /**
     * Sets the allowedTerminalTypes value for this BlackList.
     * 
     * @param allowedTerminalTypes
     */
    public void setAllowedTerminalTypes(java.lang.String allowedTerminalTypes) {
        this.allowedTerminalTypes = allowedTerminalTypes;
    }


    /**
     * Gets the allowedTerminals value for this BlackList.
     * 
     * @return allowedTerminals
     */
    public java.lang.String getAllowedTerminals() {
        return allowedTerminals;
    }


    /**
     * Sets the allowedTerminals value for this BlackList.
     * 
     * @param allowedTerminals
     */
    public void setAllowedTerminals(java.lang.String allowedTerminals) {
        this.allowedTerminals = allowedTerminals;
    }


    /**
     * Gets the allowedTrans value for this BlackList.
     * 
     * @return allowedTrans
     */
    public java.lang.String getAllowedTrans() {
        return allowedTrans;
    }


    /**
     * Sets the allowedTrans value for this BlackList.
     * 
     * @param allowedTrans
     */
    public void setAllowedTrans(java.lang.String allowedTrans) {
        this.allowedTrans = allowedTrans;
    }


    /**
     * Gets the blackedActiveFlag value for this BlackList.
     * 
     * @return blackedActiveFlag
     */
    public java.lang.String getBlackedActiveFlag() {
        return blackedActiveFlag;
    }


    /**
     * Sets the blackedActiveFlag value for this BlackList.
     * 
     * @param blackedActiveFlag
     */
    public void setBlackedActiveFlag(java.lang.String blackedActiveFlag) {
        this.blackedActiveFlag = blackedActiveFlag;
    }


    /**
     * Gets the blackedDate value for this BlackList.
     * 
     * @return blackedDate
     */
    public java.lang.String getBlackedDate() {
        return blackedDate;
    }


    /**
     * Sets the blackedDate value for this BlackList.
     * 
     * @param blackedDate
     */
    public void setBlackedDate(java.lang.String blackedDate) {
        this.blackedDate = blackedDate;
    }


    /**
     * Gets the blackedReasonId value for this BlackList.
     * 
     * @return blackedReasonId
     */
    public java.lang.String getBlackedReasonId() {
        return blackedReasonId;
    }


    /**
     * Sets the blackedReasonId value for this BlackList.
     * 
     * @param blackedReasonId
     */
    public void setBlackedReasonId(java.lang.String blackedReasonId) {
        this.blackedReasonId = blackedReasonId;
    }


    /**
     * Gets the blackedTime value for this BlackList.
     * 
     * @return blackedTime
     */
    public java.lang.String getBlackedTime() {
        return blackedTime;
    }


    /**
     * Sets the blackedTime value for this BlackList.
     * 
     * @param blackedTime
     */
    public void setBlackedTime(java.lang.String blackedTime) {
        this.blackedTime = blackedTime;
    }


    /**
     * Gets the cardInfo value for this BlackList.
     * 
     * @return cardInfo
     */
    public proxy.com.iac.ambit.model.CardInfo getCardInfo() {
        return cardInfo;
    }


    /**
     * Sets the cardInfo value for this BlackList.
     * 
     * @param cardInfo
     */
    public void setCardInfo(proxy.com.iac.ambit.model.CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }


    /**
     * Gets the comments value for this BlackList.
     * 
     * @return comments
     */
    public java.lang.String getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this BlackList.
     * 
     * @param comments
     */
    public void setComments(java.lang.String comments) {
        this.comments = comments;
    }


    /**
     * Gets the dataValid value for this BlackList.
     * 
     * @return dataValid
     */
    public boolean isDataValid() {
        return dataValid;
    }


    /**
     * Sets the dataValid value for this BlackList.
     * 
     * @param dataValid
     */
    public void setDataValid(boolean dataValid) {
        this.dataValid = dataValid;
    }


    /**
     * Gets the highlight value for this BlackList.
     * 
     * @return highlight
     */
    public java.lang.String getHighlight() {
        return highlight;
    }


    /**
     * Sets the highlight value for this BlackList.
     * 
     * @param highlight
     */
    public void setHighlight(java.lang.String highlight) {
        this.highlight = highlight;
    }


    /**
     * Gets the pan value for this BlackList.
     * 
     * @return pan
     */
    public java.lang.String getPan() {
        return pan;
    }


    /**
     * Sets the pan value for this BlackList.
     * 
     * @param pan
     */
    public void setPan(java.lang.String pan) {
        this.pan = pan;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BlackList)) return false;
        BlackList other = (BlackList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.allowedShetab==null && other.getAllowedShetab()==null) || 
             (this.allowedShetab!=null &&
              this.allowedShetab.equals(other.getAllowedShetab()))) &&
            ((this.allowedTerminalTypes==null && other.getAllowedTerminalTypes()==null) || 
             (this.allowedTerminalTypes!=null &&
              this.allowedTerminalTypes.equals(other.getAllowedTerminalTypes()))) &&
            ((this.allowedTerminals==null && other.getAllowedTerminals()==null) || 
             (this.allowedTerminals!=null &&
              this.allowedTerminals.equals(other.getAllowedTerminals()))) &&
            ((this.allowedTrans==null && other.getAllowedTrans()==null) || 
             (this.allowedTrans!=null &&
              this.allowedTrans.equals(other.getAllowedTrans()))) &&
            ((this.blackedActiveFlag==null && other.getBlackedActiveFlag()==null) || 
             (this.blackedActiveFlag!=null &&
              this.blackedActiveFlag.equals(other.getBlackedActiveFlag()))) &&
            ((this.blackedDate==null && other.getBlackedDate()==null) || 
             (this.blackedDate!=null &&
              this.blackedDate.equals(other.getBlackedDate()))) &&
            ((this.blackedReasonId==null && other.getBlackedReasonId()==null) || 
             (this.blackedReasonId!=null &&
              this.blackedReasonId.equals(other.getBlackedReasonId()))) &&
            ((this.blackedTime==null && other.getBlackedTime()==null) || 
             (this.blackedTime!=null &&
              this.blackedTime.equals(other.getBlackedTime()))) &&
            ((this.cardInfo==null && other.getCardInfo()==null) || 
             (this.cardInfo!=null &&
              this.cardInfo.equals(other.getCardInfo()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              this.comments.equals(other.getComments()))) &&
            this.dataValid == other.isDataValid() &&
            ((this.highlight==null && other.getHighlight()==null) || 
             (this.highlight!=null &&
              this.highlight.equals(other.getHighlight()))) &&
            ((this.pan==null && other.getPan()==null) || 
             (this.pan!=null &&
              this.pan.equals(other.getPan())));
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
        if (getAllowedShetab() != null) {
            _hashCode += getAllowedShetab().hashCode();
        }
        if (getAllowedTerminalTypes() != null) {
            _hashCode += getAllowedTerminalTypes().hashCode();
        }
        if (getAllowedTerminals() != null) {
            _hashCode += getAllowedTerminals().hashCode();
        }
        if (getAllowedTrans() != null) {
            _hashCode += getAllowedTrans().hashCode();
        }
        if (getBlackedActiveFlag() != null) {
            _hashCode += getBlackedActiveFlag().hashCode();
        }
        if (getBlackedDate() != null) {
            _hashCode += getBlackedDate().hashCode();
        }
        if (getBlackedReasonId() != null) {
            _hashCode += getBlackedReasonId().hashCode();
        }
        if (getBlackedTime() != null) {
            _hashCode += getBlackedTime().hashCode();
        }
        if (getCardInfo() != null) {
            _hashCode += getCardInfo().hashCode();
        }
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        _hashCode += (isDataValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getHighlight() != null) {
            _hashCode += getHighlight().hashCode();
        }
        if (getPan() != null) {
            _hashCode += getPan().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BlackList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "BlackList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowedShetab");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allowedShetab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowedTerminalTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allowedTerminalTypes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowedTerminals");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allowedTerminals"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowedTrans");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allowedTrans"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blackedActiveFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blackedActiveFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blackedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blackedDate"));
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
        elemField.setFieldName("blackedTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blackedTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "CardInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comments"));
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
        elemField.setFieldName("highlight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "highlight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pan"));
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
