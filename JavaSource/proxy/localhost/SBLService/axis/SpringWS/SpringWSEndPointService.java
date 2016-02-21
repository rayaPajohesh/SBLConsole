/**
 * SpringWSEndPointService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.localhost.SBLService.axis.SpringWS;

public interface SpringWSEndPointService extends javax.xml.rpc.Service {
    public java.lang.String getSpringWSAddress();

    public proxy.localhost.SBLService.axis.SpringWS.SpringWSEndPoint getSpringWS() throws javax.xml.rpc.ServiceException;

    public proxy.localhost.SBLService.axis.SpringWS.SpringWSEndPoint getSpringWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
