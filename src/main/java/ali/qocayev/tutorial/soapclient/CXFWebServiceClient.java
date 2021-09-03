/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ali.qocayev.tutorial.soapclient;

import ali.qocayev.cxf.client.NumberConversion;
import ali.qocayev.cxf.client.NumberConversionSoapType;


import java.math.BigDecimal;
import javax.xml.ws.BindingProvider;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;


/**
 *
 * @author ali
 */
public class CXFWebServiceClient extends WebServiceClient{

    public CXFWebServiceClient(String serviceUrl, String certificatePath, String keyStorePass) {
        super(serviceUrl, certificatePath, keyStorePass);
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getClientFactoryBean().getServiceFactory().setWsdlURL(NumberConversion.WSDL_LOCATION);
        factory.setServiceName(NumberConversion.SERVICE);
        factory.setEndpointName(NumberConversion.NumberConversionSoap);
        service =factory.create(NumberConversionSoapType.class);
    }

    private NumberConversionSoapType service;

    @Override
    public String sampleCall(String arg) {
        return service.numberToDollars(new BigDecimal(arg));
    }

    @Override
    protected void init() {
        BindingProvider prov = (BindingProvider) service;
        prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        Client client = ClientProxy.getClient(service);
        HTTPConduit http = (HTTPConduit) client.getConduit();       
        TLSClientParameters p = new TLSClientParameters();
        p.setSSLSocketFactory(sslContext.getSocketFactory());
        http.setTlsClientParameters(p);
    }
    
}
