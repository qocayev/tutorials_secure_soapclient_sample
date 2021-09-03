/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ali.qocayev.tutorial.soapclient;


import ali.qocayev.cxf.client.NumberConversion;
import ali.qocayev.cxf.client.NumberConversionSoapType;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.xml.ws.BindingProvider;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;



/**
 *
 * @author ali
 */
public class EntryPoint {
    private static String truststorePass="123456";
    private static String path="/home/ali/javaclient.jks";
    private static final String serviceUrl = "https://aqojayev-azercell-com:9000/mockNumberConversionSoapBinding";
    

     
    public static void  main(String[] args){
      
      System.out.println("Calling via cxf");
      WebServiceClient client = new CXFWebServiceClient(serviceUrl,path,truststorePass);
      System.out.println(client.call("1"));
      System.out.println("Calling via metro");
      client = new MetroWebServiceClient(serviceUrl, path, truststorePass);
      System.out.println(client.call("1"));
    }
   
}
