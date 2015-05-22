package SOAP;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;


//Peticion SOAP para el listado completo de centros
//Desarrollado por Mikel San Martin Huarte
public class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {
	public String resSoap;
	public boolean ready = false;
	
	public Boolean doInBackground(String... params) {
		 
        boolean resul = true;
 
    final String URL = " https://educa.educacion.navarra.es/Educa/services/ConsultaCentros?wsdl";
    final String NAMESPACE="http://ctr.ws.educa.cein.com/";
    final String METHOD_NAME = "buscadorSimple";
    final String SOAP_ACTION = "";
 
    /* <ctr:buscadorSimple>
   <!--Optional:-->
   <texto>peralta</texto>
   <orden>1</orden>
   <numPagina>1</numPagina>
   <numElementos>10</numElementos>
   <!--Optional:-->
   <idioma>es</idioma>
</ctr:buscadorSimple>*/

    
    try
    {
		
    SSLConnection.allowAllSSL();
    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    //request.addProperty("texto","aoiz");
    request.addProperty("orden","1");
    request.addProperty("numPagina","1");
    request.addProperty("numElementos","1000");
    request.addProperty("idioma",Locale.getDefault().getLanguage());
    
    SoapSerializationEnvelope envelope =new SoapSerializationEnvelope(SoapEnvelope.VER11);
 
    envelope.setOutputSoapObject(request);
 
    HttpTransportSE transporte = new HttpTransportSE(URL);
    transporte.debug = true;
    //resSoap = transporte.requestDump;	
    
        transporte.call(SOAP_ACTION, envelope);
        //resSoap = envelope.getResponse().toString();
        resSoap = transporte.responseDump;
        //SoapObject response = (SoapObject)envelope.getResponse();
        //resSoap = response.toString();

        Log.d("Consulta----", resSoap);
        ready=true;
    }
    catch (Exception e)
    {
    	Log.d("---------------Fallo", e.toString());
        resul = false;
    }
    
    
 
       return resul;
   }      
	
	public boolean getReady(){
		return ready;
	}


}
