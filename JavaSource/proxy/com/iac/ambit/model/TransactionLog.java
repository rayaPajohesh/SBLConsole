/**
 * TransactionLog.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.com.iac.ambit.model;

public class TransactionLog  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private java.lang.String acqSubNetworkId;

    private java.lang.String acquirerIMD;

    private java.lang.String acquirerNetworkId;

    private java.lang.String additionalData;

    private java.lang.String amount;

    private java.lang.String appearanceStatus;

    private java.lang.String branchCode;

    private java.lang.String currencyCode;

    private java.lang.String destActualBal;

    private java.lang.String destAvailabeBal;

    private java.lang.String destinationAccount;

    private java.lang.String errorCode;

    private java.lang.String feeAmount;

    private java.lang.String foreignCurrencyCod;

    private java.lang.String foreign_Amount_Tran;

    private java.lang.String forwardingIMD;

    private java.lang.String forwardingNetworkId;

    private java.lang.String logId;

    private java.lang.String merchantId;

    private java.lang.String merchantNameAndLocation;

    private java.lang.String messageType;

    private java.lang.String pan;

    private java.lang.String pan2;

    private java.lang.String receiveTime;

    private java.lang.String receivingIMD;

    private java.lang.String receivingNetworkId;

    private java.lang.String responseCode;

    private java.lang.String retrievalReferenceNumber;

    private java.lang.String settlementDate;

    private java.lang.String sourceAccount;

    private java.lang.String srcActualBal;

    private java.lang.String srcAvailableBal;

    private java.lang.String stan;

    private java.lang.String statusId;

    private java.lang.String terminalId;

    private java.lang.String terminalTypeId;

    private java.lang.String transactionCode;

    private java.lang.String transactionDate;

    private java.lang.String transactionLog;

    private java.lang.String transactionTime;

    private proxy.com.iac.ambit.model.TransactionLog[] value;

    public TransactionLog() {
    }

    public TransactionLog(
           java.lang.String acqSubNetworkId,
           java.lang.String acquirerIMD,
           java.lang.String acquirerNetworkId,
           java.lang.String additionalData,
           java.lang.String amount,
           java.lang.String appearanceStatus,
           java.lang.String branchCode,
           java.lang.String currencyCode,
           java.lang.String destActualBal,
           java.lang.String destAvailabeBal,
           java.lang.String destinationAccount,
           java.lang.String errorCode,
           java.lang.String feeAmount,
           java.lang.String foreignCurrencyCod,
           java.lang.String foreign_Amount_Tran,
           java.lang.String forwardingIMD,
           java.lang.String forwardingNetworkId,
           java.lang.String logId,
           java.lang.String merchantId,
           java.lang.String merchantNameAndLocation,
           java.lang.String messageType,
           java.lang.String pan,
           java.lang.String pan2,
           java.lang.String receiveTime,
           java.lang.String receivingIMD,
           java.lang.String receivingNetworkId,
           java.lang.String responseCode,
           java.lang.String retrievalReferenceNumber,
           java.lang.String settlementDate,
           java.lang.String sourceAccount,
           java.lang.String srcActualBal,
           java.lang.String srcAvailableBal,
           java.lang.String stan,
           java.lang.String statusId,
           java.lang.String terminalId,
           java.lang.String terminalTypeId,
           java.lang.String transactionCode,
           java.lang.String transactionDate,
           java.lang.String transactionLog,
           java.lang.String transactionTime,
           proxy.com.iac.ambit.model.TransactionLog[] value) {
           this.acqSubNetworkId = acqSubNetworkId;
           this.acquirerIMD = acquirerIMD;
           this.acquirerNetworkId = acquirerNetworkId;
           this.additionalData = additionalData;
           this.amount = amount;
           this.appearanceStatus = appearanceStatus;
           this.branchCode = branchCode;
           this.currencyCode = currencyCode;
           this.destActualBal = destActualBal;
           this.destAvailabeBal = destAvailabeBal;
           this.destinationAccount = destinationAccount;
           this.errorCode = errorCode;
           this.feeAmount = feeAmount;
           this.foreignCurrencyCod = foreignCurrencyCod;
           this.foreign_Amount_Tran = foreign_Amount_Tran;
           this.forwardingIMD = forwardingIMD;
           this.forwardingNetworkId = forwardingNetworkId;
           this.logId = logId;
           this.merchantId = merchantId;
           this.merchantNameAndLocation = merchantNameAndLocation;
           this.messageType = messageType;
           this.pan = pan;
           this.pan2 = pan2;
           this.receiveTime = receiveTime;
           this.receivingIMD = receivingIMD;
           this.receivingNetworkId = receivingNetworkId;
           this.responseCode = responseCode;
           this.retrievalReferenceNumber = retrievalReferenceNumber;
           this.settlementDate = settlementDate;
           this.sourceAccount = sourceAccount;
           this.srcActualBal = srcActualBal;
           this.srcAvailableBal = srcAvailableBal;
           this.stan = stan;
           this.statusId = statusId;
           this.terminalId = terminalId;
           this.terminalTypeId = terminalTypeId;
           this.transactionCode = transactionCode;
           this.transactionDate = transactionDate;
           this.transactionLog = transactionLog;
           this.transactionTime = transactionTime;
           this.value = value;
    }


    /**
     * Gets the acqSubNetworkId value for this TransactionLog.
     * 
     * @return acqSubNetworkId
     */
    public java.lang.String getAcqSubNetworkId() {
        return acqSubNetworkId;
    }


    /**
     * Sets the acqSubNetworkId value for this TransactionLog.
     * 
     * @param acqSubNetworkId
     */
    public void setAcqSubNetworkId(java.lang.String acqSubNetworkId) {
        this.acqSubNetworkId = acqSubNetworkId;
    }


    /**
     * Gets the acquirerIMD value for this TransactionLog.
     * 
     * @return acquirerIMD
     */
    public java.lang.String getAcquirerIMD() {
        return acquirerIMD;
    }


    /**
     * Sets the acquirerIMD value for this TransactionLog.
     * 
     * @param acquirerIMD
     */
    public void setAcquirerIMD(java.lang.String acquirerIMD) {
        this.acquirerIMD = acquirerIMD;
    }


    /**
     * Gets the acquirerNetworkId value for this TransactionLog.
     * 
     * @return acquirerNetworkId
     */
    public java.lang.String getAcquirerNetworkId() {
        return acquirerNetworkId;
    }


    /**
     * Sets the acquirerNetworkId value for this TransactionLog.
     * 
     * @param acquirerNetworkId
     */
    public void setAcquirerNetworkId(java.lang.String acquirerNetworkId) {
        this.acquirerNetworkId = acquirerNetworkId;
    }


    /**
     * Gets the additionalData value for this TransactionLog.
     * 
     * @return additionalData
     */
    public java.lang.String getAdditionalData() {
        return additionalData;
    }


    /**
     * Sets the additionalData value for this TransactionLog.
     * 
     * @param additionalData
     */
    public void setAdditionalData(java.lang.String additionalData) {
        this.additionalData = additionalData;
    }


    /**
     * Gets the amount value for this TransactionLog.
     * 
     * @return amount
     */
    public java.lang.String getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this TransactionLog.
     * 
     * @param amount
     */
    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }


    /**
     * Gets the appearanceStatus value for this TransactionLog.
     * 
     * @return appearanceStatus
     */
    public java.lang.String getAppearanceStatus() {
        return appearanceStatus;
    }


    /**
     * Sets the appearanceStatus value for this TransactionLog.
     * 
     * @param appearanceStatus
     */
    public void setAppearanceStatus(java.lang.String appearanceStatus) {
        this.appearanceStatus = appearanceStatus;
    }


    /**
     * Gets the branchCode value for this TransactionLog.
     * 
     * @return branchCode
     */
    public java.lang.String getBranchCode() {
        return branchCode;
    }


    /**
     * Sets the branchCode value for this TransactionLog.
     * 
     * @param branchCode
     */
    public void setBranchCode(java.lang.String branchCode) {
        this.branchCode = branchCode;
    }


    /**
     * Gets the currencyCode value for this TransactionLog.
     * 
     * @return currencyCode
     */
    public java.lang.String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Sets the currencyCode value for this TransactionLog.
     * 
     * @param currencyCode
     */
    public void setCurrencyCode(java.lang.String currencyCode) {
        this.currencyCode = currencyCode;
    }


    /**
     * Gets the destActualBal value for this TransactionLog.
     * 
     * @return destActualBal
     */
    public java.lang.String getDestActualBal() {
        return destActualBal;
    }


    /**
     * Sets the destActualBal value for this TransactionLog.
     * 
     * @param destActualBal
     */
    public void setDestActualBal(java.lang.String destActualBal) {
        this.destActualBal = destActualBal;
    }


    /**
     * Gets the destAvailabeBal value for this TransactionLog.
     * 
     * @return destAvailabeBal
     */
    public java.lang.String getDestAvailabeBal() {
        return destAvailabeBal;
    }


    /**
     * Sets the destAvailabeBal value for this TransactionLog.
     * 
     * @param destAvailabeBal
     */
    public void setDestAvailabeBal(java.lang.String destAvailabeBal) {
        this.destAvailabeBal = destAvailabeBal;
    }


    /**
     * Gets the destinationAccount value for this TransactionLog.
     * 
     * @return destinationAccount
     */
    public java.lang.String getDestinationAccount() {
        return destinationAccount;
    }


    /**
     * Sets the destinationAccount value for this TransactionLog.
     * 
     * @param destinationAccount
     */
    public void setDestinationAccount(java.lang.String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }


    /**
     * Gets the errorCode value for this TransactionLog.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this TransactionLog.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the feeAmount value for this TransactionLog.
     * 
     * @return feeAmount
     */
    public java.lang.String getFeeAmount() {
        return feeAmount;
    }


    /**
     * Sets the feeAmount value for this TransactionLog.
     * 
     * @param feeAmount
     */
    public void setFeeAmount(java.lang.String feeAmount) {
        this.feeAmount = feeAmount;
    }


    /**
     * Gets the foreignCurrencyCod value for this TransactionLog.
     * 
     * @return foreignCurrencyCod
     */
    public java.lang.String getForeignCurrencyCod() {
        return foreignCurrencyCod;
    }


    /**
     * Sets the foreignCurrencyCod value for this TransactionLog.
     * 
     * @param foreignCurrencyCod
     */
    public void setForeignCurrencyCod(java.lang.String foreignCurrencyCod) {
        this.foreignCurrencyCod = foreignCurrencyCod;
    }


    /**
     * Gets the foreign_Amount_Tran value for this TransactionLog.
     * 
     * @return foreign_Amount_Tran
     */
    public java.lang.String getForeign_Amount_Tran() {
        return foreign_Amount_Tran;
    }


    /**
     * Sets the foreign_Amount_Tran value for this TransactionLog.
     * 
     * @param foreign_Amount_Tran
     */
    public void setForeign_Amount_Tran(java.lang.String foreign_Amount_Tran) {
        this.foreign_Amount_Tran = foreign_Amount_Tran;
    }


    /**
     * Gets the forwardingIMD value for this TransactionLog.
     * 
     * @return forwardingIMD
     */
    public java.lang.String getForwardingIMD() {
        return forwardingIMD;
    }


    /**
     * Sets the forwardingIMD value for this TransactionLog.
     * 
     * @param forwardingIMD
     */
    public void setForwardingIMD(java.lang.String forwardingIMD) {
        this.forwardingIMD = forwardingIMD;
    }


    /**
     * Gets the forwardingNetworkId value for this TransactionLog.
     * 
     * @return forwardingNetworkId
     */
    public java.lang.String getForwardingNetworkId() {
        return forwardingNetworkId;
    }


    /**
     * Sets the forwardingNetworkId value for this TransactionLog.
     * 
     * @param forwardingNetworkId
     */
    public void setForwardingNetworkId(java.lang.String forwardingNetworkId) {
        this.forwardingNetworkId = forwardingNetworkId;
    }


    /**
     * Gets the logId value for this TransactionLog.
     * 
     * @return logId
     */
    public java.lang.String getLogId() {
        return logId;
    }


    /**
     * Sets the logId value for this TransactionLog.
     * 
     * @param logId
     */
    public void setLogId(java.lang.String logId) {
        this.logId = logId;
    }


    /**
     * Gets the merchantId value for this TransactionLog.
     * 
     * @return merchantId
     */
    public java.lang.String getMerchantId() {
        return merchantId;
    }


    /**
     * Sets the merchantId value for this TransactionLog.
     * 
     * @param merchantId
     */
    public void setMerchantId(java.lang.String merchantId) {
        this.merchantId = merchantId;
    }


    /**
     * Gets the merchantNameAndLocation value for this TransactionLog.
     * 
     * @return merchantNameAndLocation
     */
    public java.lang.String getMerchantNameAndLocation() {
        return merchantNameAndLocation;
    }


    /**
     * Sets the merchantNameAndLocation value for this TransactionLog.
     * 
     * @param merchantNameAndLocation
     */
    public void setMerchantNameAndLocation(java.lang.String merchantNameAndLocation) {
        this.merchantNameAndLocation = merchantNameAndLocation;
    }


    /**
     * Gets the messageType value for this TransactionLog.
     * 
     * @return messageType
     */
    public java.lang.String getMessageType() {
        return messageType;
    }


    /**
     * Sets the messageType value for this TransactionLog.
     * 
     * @param messageType
     */
    public void setMessageType(java.lang.String messageType) {
        this.messageType = messageType;
    }


    /**
     * Gets the pan value for this TransactionLog.
     * 
     * @return pan
     */
    public java.lang.String getPan() {
        return pan;
    }


    /**
     * Sets the pan value for this TransactionLog.
     * 
     * @param pan
     */
    public void setPan(java.lang.String pan) {
        this.pan = pan;
    }


    /**
     * Gets the pan2 value for this TransactionLog.
     * 
     * @return pan2
     */
    public java.lang.String getPan2() {
        return pan2;
    }


    /**
     * Sets the pan2 value for this TransactionLog.
     * 
     * @param pan2
     */
    public void setPan2(java.lang.String pan2) {
        this.pan2 = pan2;
    }


    /**
     * Gets the receiveTime value for this TransactionLog.
     * 
     * @return receiveTime
     */
    public java.lang.String getReceiveTime() {
        return receiveTime;
    }


    /**
     * Sets the receiveTime value for this TransactionLog.
     * 
     * @param receiveTime
     */
    public void setReceiveTime(java.lang.String receiveTime) {
        this.receiveTime = receiveTime;
    }


    /**
     * Gets the receivingIMD value for this TransactionLog.
     * 
     * @return receivingIMD
     */
    public java.lang.String getReceivingIMD() {
        return receivingIMD;
    }


    /**
     * Sets the receivingIMD value for this TransactionLog.
     * 
     * @param receivingIMD
     */
    public void setReceivingIMD(java.lang.String receivingIMD) {
        this.receivingIMD = receivingIMD;
    }


    /**
     * Gets the receivingNetworkId value for this TransactionLog.
     * 
     * @return receivingNetworkId
     */
    public java.lang.String getReceivingNetworkId() {
        return receivingNetworkId;
    }


    /**
     * Sets the receivingNetworkId value for this TransactionLog.
     * 
     * @param receivingNetworkId
     */
    public void setReceivingNetworkId(java.lang.String receivingNetworkId) {
        this.receivingNetworkId = receivingNetworkId;
    }


    /**
     * Gets the responseCode value for this TransactionLog.
     * 
     * @return responseCode
     */
    public java.lang.String getResponseCode() {
        return responseCode;
    }


    /**
     * Sets the responseCode value for this TransactionLog.
     * 
     * @param responseCode
     */
    public void setResponseCode(java.lang.String responseCode) {
        this.responseCode = responseCode;
    }


    /**
     * Gets the retrievalReferenceNumber value for this TransactionLog.
     * 
     * @return retrievalReferenceNumber
     */
    public java.lang.String getRetrievalReferenceNumber() {
        return retrievalReferenceNumber;
    }


    /**
     * Sets the retrievalReferenceNumber value for this TransactionLog.
     * 
     * @param retrievalReferenceNumber
     */
    public void setRetrievalReferenceNumber(java.lang.String retrievalReferenceNumber) {
        this.retrievalReferenceNumber = retrievalReferenceNumber;
    }


    /**
     * Gets the settlementDate value for this TransactionLog.
     * 
     * @return settlementDate
     */
    public java.lang.String getSettlementDate() {
        return settlementDate;
    }


    /**
     * Sets the settlementDate value for this TransactionLog.
     * 
     * @param settlementDate
     */
    public void setSettlementDate(java.lang.String settlementDate) {
        this.settlementDate = settlementDate;
    }


    /**
     * Gets the sourceAccount value for this TransactionLog.
     * 
     * @return sourceAccount
     */
    public java.lang.String getSourceAccount() {
        return sourceAccount;
    }


    /**
     * Sets the sourceAccount value for this TransactionLog.
     * 
     * @param sourceAccount
     */
    public void setSourceAccount(java.lang.String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }


    /**
     * Gets the srcActualBal value for this TransactionLog.
     * 
     * @return srcActualBal
     */
    public java.lang.String getSrcActualBal() {
        return srcActualBal;
    }


    /**
     * Sets the srcActualBal value for this TransactionLog.
     * 
     * @param srcActualBal
     */
    public void setSrcActualBal(java.lang.String srcActualBal) {
        this.srcActualBal = srcActualBal;
    }


    /**
     * Gets the srcAvailableBal value for this TransactionLog.
     * 
     * @return srcAvailableBal
     */
    public java.lang.String getSrcAvailableBal() {
        return srcAvailableBal;
    }


    /**
     * Sets the srcAvailableBal value for this TransactionLog.
     * 
     * @param srcAvailableBal
     */
    public void setSrcAvailableBal(java.lang.String srcAvailableBal) {
        this.srcAvailableBal = srcAvailableBal;
    }


    /**
     * Gets the stan value for this TransactionLog.
     * 
     * @return stan
     */
    public java.lang.String getStan() {
        return stan;
    }


    /**
     * Sets the stan value for this TransactionLog.
     * 
     * @param stan
     */
    public void setStan(java.lang.String stan) {
        this.stan = stan;
    }


    /**
     * Gets the statusId value for this TransactionLog.
     * 
     * @return statusId
     */
    public java.lang.String getStatusId() {
        return statusId;
    }


    /**
     * Sets the statusId value for this TransactionLog.
     * 
     * @param statusId
     */
    public void setStatusId(java.lang.String statusId) {
        this.statusId = statusId;
    }


    /**
     * Gets the terminalId value for this TransactionLog.
     * 
     * @return terminalId
     */
    public java.lang.String getTerminalId() {
        return terminalId;
    }


    /**
     * Sets the terminalId value for this TransactionLog.
     * 
     * @param terminalId
     */
    public void setTerminalId(java.lang.String terminalId) {
        this.terminalId = terminalId;
    }


    /**
     * Gets the terminalTypeId value for this TransactionLog.
     * 
     * @return terminalTypeId
     */
    public java.lang.String getTerminalTypeId() {
        return terminalTypeId;
    }


    /**
     * Sets the terminalTypeId value for this TransactionLog.
     * 
     * @param terminalTypeId
     */
    public void setTerminalTypeId(java.lang.String terminalTypeId) {
        this.terminalTypeId = terminalTypeId;
    }


    /**
     * Gets the transactionCode value for this TransactionLog.
     * 
     * @return transactionCode
     */
    public java.lang.String getTransactionCode() {
        return transactionCode;
    }


    /**
     * Sets the transactionCode value for this TransactionLog.
     * 
     * @param transactionCode
     */
    public void setTransactionCode(java.lang.String transactionCode) {
        this.transactionCode = transactionCode;
    }


    /**
     * Gets the transactionDate value for this TransactionLog.
     * 
     * @return transactionDate
     */
    public java.lang.String getTransactionDate() {
        return transactionDate;
    }


    /**
     * Sets the transactionDate value for this TransactionLog.
     * 
     * @param transactionDate
     */
    public void setTransactionDate(java.lang.String transactionDate) {
        this.transactionDate = transactionDate;
    }


    /**
     * Gets the transactionLog value for this TransactionLog.
     * 
     * @return transactionLog
     */
    public java.lang.String getTransactionLog() {
        return transactionLog;
    }


    /**
     * Sets the transactionLog value for this TransactionLog.
     * 
     * @param transactionLog
     */
    public void setTransactionLog(java.lang.String transactionLog) {
        this.transactionLog = transactionLog;
    }


    /**
     * Gets the transactionTime value for this TransactionLog.
     * 
     * @return transactionTime
     */
    public java.lang.String getTransactionTime() {
        return transactionTime;
    }


    /**
     * Sets the transactionTime value for this TransactionLog.
     * 
     * @param transactionTime
     */
    public void setTransactionTime(java.lang.String transactionTime) {
        this.transactionTime = transactionTime;
    }


    /**
     * Gets the value value for this TransactionLog.
     * 
     * @return value
     */
    public proxy.com.iac.ambit.model.TransactionLog[] getValue() {
        return value;
    }


    /**
     * Sets the value value for this TransactionLog.
     * 
     * @param value
     */
    public void setValue(proxy.com.iac.ambit.model.TransactionLog[] value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransactionLog)) return false;
        TransactionLog other = (TransactionLog) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.acqSubNetworkId==null && other.getAcqSubNetworkId()==null) || 
             (this.acqSubNetworkId!=null &&
              this.acqSubNetworkId.equals(other.getAcqSubNetworkId()))) &&
            ((this.acquirerIMD==null && other.getAcquirerIMD()==null) || 
             (this.acquirerIMD!=null &&
              this.acquirerIMD.equals(other.getAcquirerIMD()))) &&
            ((this.acquirerNetworkId==null && other.getAcquirerNetworkId()==null) || 
             (this.acquirerNetworkId!=null &&
              this.acquirerNetworkId.equals(other.getAcquirerNetworkId()))) &&
            ((this.additionalData==null && other.getAdditionalData()==null) || 
             (this.additionalData!=null &&
              this.additionalData.equals(other.getAdditionalData()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.appearanceStatus==null && other.getAppearanceStatus()==null) || 
             (this.appearanceStatus!=null &&
              this.appearanceStatus.equals(other.getAppearanceStatus()))) &&
            ((this.branchCode==null && other.getBranchCode()==null) || 
             (this.branchCode!=null &&
              this.branchCode.equals(other.getBranchCode()))) &&
            ((this.currencyCode==null && other.getCurrencyCode()==null) || 
             (this.currencyCode!=null &&
              this.currencyCode.equals(other.getCurrencyCode()))) &&
            ((this.destActualBal==null && other.getDestActualBal()==null) || 
             (this.destActualBal!=null &&
              this.destActualBal.equals(other.getDestActualBal()))) &&
            ((this.destAvailabeBal==null && other.getDestAvailabeBal()==null) || 
             (this.destAvailabeBal!=null &&
              this.destAvailabeBal.equals(other.getDestAvailabeBal()))) &&
            ((this.destinationAccount==null && other.getDestinationAccount()==null) || 
             (this.destinationAccount!=null &&
              this.destinationAccount.equals(other.getDestinationAccount()))) &&
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.feeAmount==null && other.getFeeAmount()==null) || 
             (this.feeAmount!=null &&
              this.feeAmount.equals(other.getFeeAmount()))) &&
            ((this.foreignCurrencyCod==null && other.getForeignCurrencyCod()==null) || 
             (this.foreignCurrencyCod!=null &&
              this.foreignCurrencyCod.equals(other.getForeignCurrencyCod()))) &&
            ((this.foreign_Amount_Tran==null && other.getForeign_Amount_Tran()==null) || 
             (this.foreign_Amount_Tran!=null &&
              this.foreign_Amount_Tran.equals(other.getForeign_Amount_Tran()))) &&
            ((this.forwardingIMD==null && other.getForwardingIMD()==null) || 
             (this.forwardingIMD!=null &&
              this.forwardingIMD.equals(other.getForwardingIMD()))) &&
            ((this.forwardingNetworkId==null && other.getForwardingNetworkId()==null) || 
             (this.forwardingNetworkId!=null &&
              this.forwardingNetworkId.equals(other.getForwardingNetworkId()))) &&
            ((this.logId==null && other.getLogId()==null) || 
             (this.logId!=null &&
              this.logId.equals(other.getLogId()))) &&
            ((this.merchantId==null && other.getMerchantId()==null) || 
             (this.merchantId!=null &&
              this.merchantId.equals(other.getMerchantId()))) &&
            ((this.merchantNameAndLocation==null && other.getMerchantNameAndLocation()==null) || 
             (this.merchantNameAndLocation!=null &&
              this.merchantNameAndLocation.equals(other.getMerchantNameAndLocation()))) &&
            ((this.messageType==null && other.getMessageType()==null) || 
             (this.messageType!=null &&
              this.messageType.equals(other.getMessageType()))) &&
            ((this.pan==null && other.getPan()==null) || 
             (this.pan!=null &&
              this.pan.equals(other.getPan()))) &&
            ((this.pan2==null && other.getPan2()==null) || 
             (this.pan2!=null &&
              this.pan2.equals(other.getPan2()))) &&
            ((this.receiveTime==null && other.getReceiveTime()==null) || 
             (this.receiveTime!=null &&
              this.receiveTime.equals(other.getReceiveTime()))) &&
            ((this.receivingIMD==null && other.getReceivingIMD()==null) || 
             (this.receivingIMD!=null &&
              this.receivingIMD.equals(other.getReceivingIMD()))) &&
            ((this.receivingNetworkId==null && other.getReceivingNetworkId()==null) || 
             (this.receivingNetworkId!=null &&
              this.receivingNetworkId.equals(other.getReceivingNetworkId()))) &&
            ((this.responseCode==null && other.getResponseCode()==null) || 
             (this.responseCode!=null &&
              this.responseCode.equals(other.getResponseCode()))) &&
            ((this.retrievalReferenceNumber==null && other.getRetrievalReferenceNumber()==null) || 
             (this.retrievalReferenceNumber!=null &&
              this.retrievalReferenceNumber.equals(other.getRetrievalReferenceNumber()))) &&
            ((this.settlementDate==null && other.getSettlementDate()==null) || 
             (this.settlementDate!=null &&
              this.settlementDate.equals(other.getSettlementDate()))) &&
            ((this.sourceAccount==null && other.getSourceAccount()==null) || 
             (this.sourceAccount!=null &&
              this.sourceAccount.equals(other.getSourceAccount()))) &&
            ((this.srcActualBal==null && other.getSrcActualBal()==null) || 
             (this.srcActualBal!=null &&
              this.srcActualBal.equals(other.getSrcActualBal()))) &&
            ((this.srcAvailableBal==null && other.getSrcAvailableBal()==null) || 
             (this.srcAvailableBal!=null &&
              this.srcAvailableBal.equals(other.getSrcAvailableBal()))) &&
            ((this.stan==null && other.getStan()==null) || 
             (this.stan!=null &&
              this.stan.equals(other.getStan()))) &&
            ((this.statusId==null && other.getStatusId()==null) || 
             (this.statusId!=null &&
              this.statusId.equals(other.getStatusId()))) &&
            ((this.terminalId==null && other.getTerminalId()==null) || 
             (this.terminalId!=null &&
              this.terminalId.equals(other.getTerminalId()))) &&
            ((this.terminalTypeId==null && other.getTerminalTypeId()==null) || 
             (this.terminalTypeId!=null &&
              this.terminalTypeId.equals(other.getTerminalTypeId()))) &&
            ((this.transactionCode==null && other.getTransactionCode()==null) || 
             (this.transactionCode!=null &&
              this.transactionCode.equals(other.getTransactionCode()))) &&
            ((this.transactionDate==null && other.getTransactionDate()==null) || 
             (this.transactionDate!=null &&
              this.transactionDate.equals(other.getTransactionDate()))) &&
            ((this.transactionLog==null && other.getTransactionLog()==null) || 
             (this.transactionLog!=null &&
              this.transactionLog.equals(other.getTransactionLog()))) &&
            ((this.transactionTime==null && other.getTransactionTime()==null) || 
             (this.transactionTime!=null &&
              this.transactionTime.equals(other.getTransactionTime()))) &&
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
        if (getAcqSubNetworkId() != null) {
            _hashCode += getAcqSubNetworkId().hashCode();
        }
        if (getAcquirerIMD() != null) {
            _hashCode += getAcquirerIMD().hashCode();
        }
        if (getAcquirerNetworkId() != null) {
            _hashCode += getAcquirerNetworkId().hashCode();
        }
        if (getAdditionalData() != null) {
            _hashCode += getAdditionalData().hashCode();
        }
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getAppearanceStatus() != null) {
            _hashCode += getAppearanceStatus().hashCode();
        }
        if (getBranchCode() != null) {
            _hashCode += getBranchCode().hashCode();
        }
        if (getCurrencyCode() != null) {
            _hashCode += getCurrencyCode().hashCode();
        }
        if (getDestActualBal() != null) {
            _hashCode += getDestActualBal().hashCode();
        }
        if (getDestAvailabeBal() != null) {
            _hashCode += getDestAvailabeBal().hashCode();
        }
        if (getDestinationAccount() != null) {
            _hashCode += getDestinationAccount().hashCode();
        }
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getFeeAmount() != null) {
            _hashCode += getFeeAmount().hashCode();
        }
        if (getForeignCurrencyCod() != null) {
            _hashCode += getForeignCurrencyCod().hashCode();
        }
        if (getForeign_Amount_Tran() != null) {
            _hashCode += getForeign_Amount_Tran().hashCode();
        }
        if (getForwardingIMD() != null) {
            _hashCode += getForwardingIMD().hashCode();
        }
        if (getForwardingNetworkId() != null) {
            _hashCode += getForwardingNetworkId().hashCode();
        }
        if (getLogId() != null) {
            _hashCode += getLogId().hashCode();
        }
        if (getMerchantId() != null) {
            _hashCode += getMerchantId().hashCode();
        }
        if (getMerchantNameAndLocation() != null) {
            _hashCode += getMerchantNameAndLocation().hashCode();
        }
        if (getMessageType() != null) {
            _hashCode += getMessageType().hashCode();
        }
        if (getPan() != null) {
            _hashCode += getPan().hashCode();
        }
        if (getPan2() != null) {
            _hashCode += getPan2().hashCode();
        }
        if (getReceiveTime() != null) {
            _hashCode += getReceiveTime().hashCode();
        }
        if (getReceivingIMD() != null) {
            _hashCode += getReceivingIMD().hashCode();
        }
        if (getReceivingNetworkId() != null) {
            _hashCode += getReceivingNetworkId().hashCode();
        }
        if (getResponseCode() != null) {
            _hashCode += getResponseCode().hashCode();
        }
        if (getRetrievalReferenceNumber() != null) {
            _hashCode += getRetrievalReferenceNumber().hashCode();
        }
        if (getSettlementDate() != null) {
            _hashCode += getSettlementDate().hashCode();
        }
        if (getSourceAccount() != null) {
            _hashCode += getSourceAccount().hashCode();
        }
        if (getSrcActualBal() != null) {
            _hashCode += getSrcActualBal().hashCode();
        }
        if (getSrcAvailableBal() != null) {
            _hashCode += getSrcAvailableBal().hashCode();
        }
        if (getStan() != null) {
            _hashCode += getStan().hashCode();
        }
        if (getStatusId() != null) {
            _hashCode += getStatusId().hashCode();
        }
        if (getTerminalId() != null) {
            _hashCode += getTerminalId().hashCode();
        }
        if (getTerminalTypeId() != null) {
            _hashCode += getTerminalTypeId().hashCode();
        }
        if (getTransactionCode() != null) {
            _hashCode += getTransactionCode().hashCode();
        }
        if (getTransactionDate() != null) {
            _hashCode += getTransactionDate().hashCode();
        }
        if (getTransactionLog() != null) {
            _hashCode += getTransactionLog().hashCode();
        }
        if (getTransactionTime() != null) {
            _hashCode += getTransactionTime().hashCode();
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
        new org.apache.axis.description.TypeDesc(TransactionLog.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "TransactionLog"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acqSubNetworkId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "acqSubNetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acquirerIMD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "acquirerIMD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acquirerNetworkId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "acquirerNetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additionalData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appearanceStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appearanceStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branchCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branchCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currencyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destActualBal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destActualBal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destAvailabeBal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destAvailabeBal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinationAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "feeAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreignCurrencyCod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foreignCurrencyCod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreign_Amount_Tran");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foreign_Amount_Tran"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forwardingIMD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "forwardingIMD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forwardingNetworkId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "forwardingNetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "merchantId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantNameAndLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "merchantNameAndLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageType"));
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
        elemField.setFieldName("pan2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pan2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiveTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivingIMD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receivingIMD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receivingNetworkId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receivingNetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retrievalReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "retrievalReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "settlementDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srcActualBal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "srcActualBal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srcAvailableBal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "srcAvailableBal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminalId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminalId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminalTypeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminalTypeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionLog");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionLog"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:model.ambit.iac.com", "TransactionLog"));
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
