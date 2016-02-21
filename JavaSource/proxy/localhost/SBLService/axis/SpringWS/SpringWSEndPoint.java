/**
 * SpringWSEndPoint.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.localhost.SBLService.axis.SpringWS;

public interface SpringWSEndPoint extends java.rmi.Remote {
    public java.lang.String getAllPermissions(java.lang.String statusPermissions, javax.xml.rpc.holders.ObjectHolder allPermission) throws java.rmi.RemoteException;
    public java.lang.String getAllActivity(javax.xml.rpc.holders.ObjectHolder allActivity) throws java.rmi.RemoteException;
    public java.lang.String getCustomerSysPermissionsIds(java.lang.String userId, javax.xml.rpc.holders.ObjectHolder permissionIdList) throws java.rmi.RemoteException;
    public java.lang.String loadSysPermissionURIs(javax.xml.rpc.holders.ObjectHolder sysPermissionURI) throws java.rmi.RemoteException;
    public java.lang.String isPermissionAvailableToCustomer(java.lang.String userId, java.lang.String permissionName, javax.xml.rpc.holders.BooleanHolder isPermissionAvailable) throws java.rmi.RemoteException;
    public java.lang.String customerAuthenticate(java.lang.String userId, java.lang.String userPassword, javax.xml.rpc.holders.ObjectHolder userInfo) throws java.rmi.RemoteException;
    public java.lang.String activationPermission(java.lang.Object[] permissionId) throws java.rmi.RemoteException;
    public java.lang.String getLastLoginDate(java.lang.String userId, boolean isSuccess, javax.xml.rpc.holders.StringHolder lastLoginDate) throws java.rmi.RemoteException;
    public java.lang.String logLoginActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logLogoutActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logPermissionActivationActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logUserAggregateActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logUserActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logChangePasswordActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logBlackListTransReportActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logSearchPanInformationActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logGetTransForMonitoringActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logChangeRefreshIntervalInSecActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logChangeAlertInfoActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logAddGroupActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logUpdateGroupActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logViewGroupActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logAddUserActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logUpdateUserActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logViewUserActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logUpdateBlackListForLimitationCardActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logSearchBlackListActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logAddInBlackListActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logEditBlackListActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String logDeleteFromBlackListActivity(java.lang.String userId, java.lang.String activityDesc) throws java.rmi.RemoteException;
    public java.lang.String addGroup(proxy.com.iac.ambit.model.Group newGroup, proxy.com.iac.ambit.model.Permissions[] groupPermissions) throws java.rmi.RemoteException;
    public java.lang.String updateGroup(proxy.com.iac.ambit.model.Group newGroup, proxy.com.iac.ambit.model.Permissions[] groupPermissions) throws java.rmi.RemoteException;
    public java.lang.String getAllGroups(java.lang.String statusGroups, javax.xml.rpc.holders.ObjectHolder allGroups) throws java.rmi.RemoteException;
    public java.lang.String getGroupDetail(java.lang.String groupId, javax.xml.rpc.holders.ObjectHolder group, javax.xml.rpc.holders.ObjectHolder allPermission) throws java.rmi.RemoteException;
    public java.lang.String addUser(proxy.com.iac.ambit.model.Customer newUser, proxy.com.iac.ambit.model.Group[] groups) throws java.rmi.RemoteException;
    public java.lang.String changePassword(java.lang.String userId, java.lang.String userPreviousPassword, java.lang.String userNewPassword) throws java.rmi.RemoteException;
    public java.lang.String getUserDetail(java.lang.String userId, javax.xml.rpc.holders.ObjectHolder user, javax.xml.rpc.holders.ObjectHolder allGroups) throws java.rmi.RemoteException;
    public java.lang.String getAllCustomers(java.lang.String statusCustomers, javax.xml.rpc.holders.ObjectHolder allUsers) throws java.rmi.RemoteException;
    public java.lang.String searchLogActivity(proxy.com.iac.ambit.model.ActivityLog activityLog, javax.xml.rpc.holders.ObjectHolder listActivity) throws java.rmi.RemoteException;
    public java.lang.String searchAggregateAllActivities(proxy.com.iac.ambit.model.ActivityLog activityLog, javax.xml.rpc.holders.ObjectHolder aggregateActivityList) throws java.rmi.RemoteException;
    public java.lang.String searchBlackListTrans(java.lang.String fromDate, java.lang.String toDate, java.lang.String fromTime, java.lang.String toTime, java.lang.String pan, java.lang.String stan, java.lang.String rrn, proxy.com.iac.ambit.model.holders.ArrayOf_tns2_TransactionLogHolder transactionLogList) throws java.rmi.RemoteException;
    public java.lang.String searchPanInformation(java.lang.String pan, proxy.com.iac.ambit.model.holders.CardInfoHolder cardInfo) throws java.rmi.RemoteException;
    public java.lang.String getTransForMonitoring(java.lang.String lastLogId, proxy.com.iac.ambit.model.holders.ArrayOf_tns2_TransactionLogHolder transactionLogList) throws java.rmi.RemoteException;
    public java.lang.String getDBCurrentDateTime(javax.xml.rpc.holders.StringHolder currentDateTime) throws java.rmi.RemoteException;
    public java.lang.String getMaxLogId(javax.xml.rpc.holders.StringHolder maxLogId) throws java.rmi.RemoteException;
    public java.lang.String searchBlackList(java.lang.String fromDate, java.lang.String toDate, java.lang.String pan, java.lang.String activeFlag, javax.xml.rpc.holders.ObjectHolder list) throws java.rmi.RemoteException;
    public java.lang.String searchBlackReason(proxy.com.iac.ambit.model.holders.ArrayOf_tns2_BlackedReasonHolder list) throws java.rmi.RemoteException;
    public java.lang.String updateBlackList(proxy.com.iac.ambit.model.BlackList blackList) throws java.rmi.RemoteException;
    public java.lang.String updateBlackListForLimitationCard(proxy.com.iac.ambit.model.BlackList blackList) throws java.rmi.RemoteException;
    public java.lang.String addBlackList(proxy.com.iac.ambit.model.BlackList blackList) throws java.rmi.RemoteException;
    public java.lang.String searchPanInBlackList(java.lang.String pan, javax.xml.rpc.holders.ObjectHolder blackList) throws java.rmi.RemoteException;
    public java.lang.String inactivatePanInBlackList(java.lang.String pan) throws java.rmi.RemoteException;
    public java.lang.String getAllFlags(proxy.com.iac.ambit.model.holders.ArrayOf_tns2_CodeActiveFlagHolder allFlags) throws java.rmi.RemoteException;
    public java.lang.String addLoginInfo(java.lang.String userId, boolean isSuccess) throws java.rmi.RemoteException;
    public java.lang.String updateUser(proxy.com.iac.ambit.model.Customer newUser, proxy.com.iac.ambit.model.Group[] groups) throws java.rmi.RemoteException;
    
}
