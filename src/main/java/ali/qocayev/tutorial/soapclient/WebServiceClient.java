/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ali.qocayev.tutorial.soapclient;

import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

/**
 *
 * @author ali
 */
public abstract class WebServiceClient {
    protected String address;
    private String certificatePath;
    private String keyStorePass;
    
    public WebServiceClient(String serviceUrl,String certificatePath, String keyStorePass ){
        address = serviceUrl;
        this.certificatePath=certificatePath;
        this.keyStorePass=keyStorePass;
        sslContext = createSSLContext();
    }
    protected abstract String sampleCall(String arg);
    protected abstract void init();
    protected SSLContext sslContext;
    
    public    String call(String arg){
        init();
        return sampleCall(arg);
    }
    
    private SSLContext      createSSLContext() {
        SSLContext sc=null;
        FileInputStream fis = null;
        try {
            sc = SSLContext.getInstance("SSL");
            KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            KeyStore keyStore = KeyStore.getInstance("JKS");
            fis = new FileInputStream(certificatePath);
            keyStore.load(fis, keyStorePass.toCharArray());
            factory.init(keyStore, keyStorePass.toCharArray());
            sc.init(factory.getKeyManagers(), null, null);            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sc;
    }
    
}
