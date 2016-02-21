/**
 * SpringWSEndPointServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package proxy.localhost.SBLService.axis.SpringWS;

import com.iac.ambit.utils.Config;

public class SpringWSEndPointServiceLocator extends org.apache.axis.client.Service implements proxy.localhost.SBLService.axis.SpringWS.SpringWSEndPointService {

    public SpringWSEndPointServiceLocator() {
    }


    public SpringWSEndPointServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SpringWSEndPointServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SpringWS
    private java.lang.String SpringWS_address = Config.getProperty("SpringWSAddress");

    public java.lang.String getSpringWSAddress() {
        return SpringWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SpringWSWSDDServiceName = "SpringWS";

    public java.lang.String getSpringWSWSDDServiceName() {
        return SpringWSWSDDServiceName;
    }

    public void setSpringWSWSDDServiceName(java.lang.String name) {
        SpringWSWSDDServiceName = name;
    }

    public proxy.localhost.SBLService.axis.SpringWS.SpringWSEndPoint getSpringWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SpringWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSpringWS(endpoint);
    }

    public proxy.localhost.SBLService.axis.SpringWS.SpringWSEndPoint getSpringWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub _stub = new proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getSpringWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSpringWSEndpointAddress(java.lang.String address) {
        SpringWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (proxy.localhost.SBLService.axis.SpringWS.SpringWSEndPoint.class.isAssignableFrom(serviceEndpointInterface)) {
                proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub _stub = new proxy.localhost.SBLService.axis.SpringWS.SpringWSSoapBindingStub(new java.net.URL(SpringWS_address), this);
                _stub.setPortName(getSpringWSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SpringWS".equals(inputPortName)) {
            return getSpringWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName(Config.getProperty("SpringWSAddress"), "SpringWSEndPointService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName(Config.getProperty("SpringWSAddress"), "SpringWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SpringWS".equals(portName)) {
            setSpringWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
