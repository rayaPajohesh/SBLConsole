/**
 * Customer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class Customer  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private java.lang.String FAX;

    private java.lang.String GMTOffset;

    private java.util.HashMap access;

    private java.lang.String country;

    private boolean dataValid;

    private java.lang.String emailAddress;

    private java.lang.String isLogoutMsg;

    private java.lang.String language;

    private java.lang.Object[] messages;

    private java.lang.String terminalId;

    private java.lang.String userActive;

    private java.lang.String userId;

    private java.lang.String userName;

    private java.lang.String userNameFA;

    private java.lang.String userPassword;

    public Customer() {
    }

    public Customer(
           java.lang.String FAX,
           java.lang.String GMTOffset,
           java.util.HashMap access,
           java.lang.String country,
           boolean dataValid,
           java.lang.String emailAddress,
           java.lang.String isLogoutMsg,
           java.lang.String language,
           java.lang.Object[] messages,
           java.lang.String terminalId,
           java.lang.String userActive,
           java.lang.String userId,
           java.lang.String userName,
           java.lang.String userNameFA,
           java.lang.String userPassword) {
           this.FAX = FAX;
           this.GMTOffset = GMTOffset;
           this.access = access;
           this.country = country;
           this.dataValid = dataValid;
           this.emailAddress = emailAddress;
           this.isLogoutMsg = isLogoutMsg;
           this.language = language;
           this.messages = messages;
           this.terminalId = terminalId;
           this.userActive = userActive;
           this.userId = userId;
           this.userName = userName;
           this.userNameFA = userNameFA;
           this.userPassword = userPassword;
    }


    /**
     * Gets the FAX value for this Customer.
     * 
     * @return FAX
     */
    public java.lang.String getFAX() {
        return FAX;
    }


    /**
     * Sets the FAX value for this Customer.
     * 
     * @param FAX
     */
    public void setFAX(java.lang.String FAX) {
        this.FAX = FAX;
    }


    /**
     * Gets the GMTOffset value for this Customer.
     * 
     * @return GMTOffset
     */
    public java.lang.String getGMTOffset() {
        return GMTOffset;
    }


    /**
     * Sets the GMTOffset value for this Customer.
     * 
     * @param GMTOffset
     */
    public void setGMTOffset(java.lang.String GMTOffset) {
        this.GMTOffset = GMTOffset;
    }


    /**
     * Gets the access value for this Customer.
     * 
     * @return access
     */
    public java.util.HashMap getAccess() {
        return access;
    }


    /**
     * Sets the access value for this Customer.
     * 
     * @param access
     */
    public void setAccess(java.util.HashMap access) {
        this.access = access;
    }


    /**
     * Gets the country value for this Customer.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this Customer.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the dataValid value for this Customer.
     * 
     * @return dataValid
     */
    public boolean isDataValid() {
        return dataValid;
    }


    /**
     * Sets the dataValid value for this Customer.
     * 
     * @param dataValid
     */
    public void setDataValid(boolean dataValid) {
        this.dataValid = dataValid;
    }


    /**
     * Gets the emailAddress value for this Customer.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this Customer.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the isLogoutMsg value for this Customer.
     * 
     * @return isLogoutMsg
     */
    public java.lang.String getIsLogoutMsg() {
        return isLogoutMsg;
    }


    /**
     * Sets the isLogoutMsg value for this Customer.
     * 
     * @param isLogoutMsg
     */
    public void setIsLogoutMsg(java.lang.String isLogoutMsg) {
        this.isLogoutMsg = isLogoutMsg;
    }


    /**
     * Gets the language value for this Customer.
     * 
     * @return language
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this Customer.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the messages value for this Customer.
     * 
     * @return messages
     */
    public java.lang.Object[] getMessages() {
        return messages;
    }


    /**
     * Sets the messages value for this Customer.
     * 
     * @param messages
     */
    public void setMessages(java.lang.Object[] messages) {
        this.messages = messages;
    }


    /**
     * Gets the terminalId value for this Customer.
     * 
     * @return terminalId
     */
    public java.lang.String getTerminalId() {
        return terminalId;
    }


    /**
     * Sets the terminalId value for this Customer.
     * 
     * @param terminalId
     */
    public void setTerminalId(java.lang.String terminalId) {
        this.terminalId = terminalId;
    }


    /**
     * Gets the userActive value for this Customer.
     * 
     * @return userActive
     */
    public java.lang.String getUserActive() {
        return userActive;
    }


    /**
     * Sets the userActive value for this Customer.
     * 
     * @param userActive
     */
    public void setUserActive(java.lang.String userActive) {
        this.userActive = userActive;
    }


    /**
     * Gets the userId value for this Customer.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this Customer.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the userName value for this Customer.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this Customer.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the userNameFA value for this Customer.
     * 
     * @return userNameFA
     */
    public java.lang.String getUserNameFA() {
        return userNameFA;
    }


    /**
     * Sets the userNameFA value for this Customer.
     * 
     * @param userNameFA
     */
    public void setUserNameFA(java.lang.String userNameFA) {
        this.userNameFA = userNameFA;
    }


    /**
     * Gets the userPassword value for this Customer.
     * 
     * @return userPassword
     */
    public java.lang.String getUserPassword() {
        return userPassword;
    }


    /**
     * Sets the userPassword value for this Customer.
     * 
     * @param userPassword
     */
    public void setUserPassword(java.lang.String userPassword) {
        this.userPassword = userPassword;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.FAX==null && other.getFAX()==null) || 
             (this.FAX!=null &&
              this.FAX.equals(other.getFAX()))) &&
            ((this.GMTOffset==null && other.getGMTOffset()==null) || 
             (this.GMTOffset!=null &&
              this.GMTOffset.equals(other.getGMTOffset()))) &&
            ((this.access==null && other.getAccess()==null) || 
             (this.access!=null &&
              this.access.equals(other.getAccess()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            this.dataValid == other.isDataValid() &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.isLogoutMsg==null && other.getIsLogoutMsg()==null) || 
             (this.isLogoutMsg!=null &&
              this.isLogoutMsg.equals(other.getIsLogoutMsg()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.messages==null && other.getMessages()==null) || 
             (this.messages!=null &&
              java.util.Arrays.equals(this.messages, other.getMessages()))) &&
            ((this.terminalId==null && other.getTerminalId()==null) || 
             (this.terminalId!=null &&
              this.terminalId.equals(other.getTerminalId()))) &&
            ((this.userActive==null && other.getUserActive()==null) || 
             (this.userActive!=null &&
              this.userActive.equals(other.getUserActive()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.userNameFA==null && other.getUserNameFA()==null) || 
             (this.userNameFA!=null &&
              this.userNameFA.equals(other.getUserNameFA()))) &&
            ((this.userPassword==null && other.getUserPassword()==null) || 
             (this.userPassword!=null &&
              this.userPassword.equals(other.getUserPassword())));
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
        if (getFAX() != null) {
            _hashCode += getFAX().hashCode();
        }
        if (getGMTOffset() != null) {
            _hashCode += getGMTOffset().hashCode();
        }
        if (getAccess() != null) {
            _hashCode += getAccess().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        _hashCode += (isDataValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getIsLogoutMsg() != null) {
            _hashCode += getIsLogoutMsg().hashCode();
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getMessages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessages(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTerminalId() != null) {
            _hashCode += getTerminalId().hashCode();
        }
        if (getUserActive() != null) {
            _hashCode += getUserActive().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getUserNameFA() != null) {
            _hashCode += getUserNameFA().hashCode();
        }
        if (getUserPassword() != null) {
            _hashCode += getUserPassword().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Customer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "Customer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FAX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FAX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GMTOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GMTOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("access");
        elemField.setXmlName(new javax.xml.namespace.QName("", "access"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "country"));
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
        elemField.setFieldName("emailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isLogoutMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isLogoutMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messages");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminalId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminalId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userActive");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userActive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userNameFA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userNameFA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userPassword"));
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
