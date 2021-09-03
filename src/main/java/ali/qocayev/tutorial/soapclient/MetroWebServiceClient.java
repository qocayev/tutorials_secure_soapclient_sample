/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ali.qocayev.tutorial.soapclient;


import ali.qocayev.imported.*;
import com.sun.xml.ws.developer.JAXWSProperties;
import java.math.BigDecimal;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author ali
 */
public class MetroWebServiceClient extends WebServiceClient{

    private NumberConversionSoapType client;
    public MetroWebServiceClient(String serviceUrl, String certificatePath, String keyStorePass) {
        super(serviceUrl, certificatePath, keyStorePass);
        client = new NumberConversion().getNumberConversionSoap();
        
    }

    
    @Override
    protected String sampleCall(String arg) {
        return client.numberToDollars(new BigDecimal(arg));
    }

    @Override
    protected void init() {
         BindingProvider prov = (BindingProvider) client;
         prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
            //    TODO:comment following line if you don't want client authentification
         prov.getRequestContext().put(  JAXWSProperties.SSL_SOCKET_FACTORY,sslContext.getSocketFactory());
    }
    
}
