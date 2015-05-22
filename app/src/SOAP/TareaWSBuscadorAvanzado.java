package SOAP;

import java.util.Locale;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;


//Peticion SOAP para el buscador avanzado
//Desarrollado por Mikel San Martin Huarte
public class TareaWSBuscadorAvanzado extends AsyncTask<String,Integer,Boolean> {
	public String resSoap;
	public boolean ready = false;
	public Boolean doInBackground(String... params) {
		 
        boolean resul = true;
 
    final String URL = " https://educa.educacion.navarra.es/Educa/services/ConsultaCentros?wsdl";
    final String NAMESPACE="http://ctr.ws.educa.cein.com/";
    final String METHOD_NAME = "buscadorAvanzado";
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
    if (params[0].equalsIgnoreCase("1")){
    	request.addProperty("cursoEscolar",127);
    }
    if (params[1].equalsIgnoreCase("1")){
    	request.addProperty("cursoEscolar",126);
    }
    if (params[2].equalsIgnoreCase("1")){
    	request.addProperty("naturaleza",1);
    }
    if (params[3].equalsIgnoreCase("1")){
    	request.addProperty("naturaleza",2);
    }
    if (params[4].equalsIgnoreCase("1")){
    	request.addProperty("naturaleza",3);
    }
    if (params[5].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1);
    }
    if (params[6].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1180);
    }
    if (params[7].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",5);
    }
    if (params[8].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1220);
    }
    if (params[9].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1282);
    }
    if (params[10].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",4);
    }
    if (params[11].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1200);
    }
    if (params[12].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1241);
    }
    if (params[13].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1284);
    }
    if (params[14].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",3);
    }
    if (params[15].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",2);
    }
    if (params[16].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1181);
    }
    if (params[17].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",6);
    }
    if (params[18].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1240);
    }
    if (params[19].equalsIgnoreCase("1")){
    	request.addProperty("modeloLinguistico",1283);
    }

    if (params[20].equalsIgnoreCase("1")){
    	request.addProperty("servicios",1);
    }
    if (params[21].equalsIgnoreCase("1")){
    	request.addProperty("servicios",2);
    }
    if (params[22].equalsIgnoreCase("1")){
    	request.addProperty("servicios",3);
    }
    if (params[23].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",1531);
    }
    if (params[24].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",1530);
    }
    if (params[25].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",401);
    }
    if (params[26].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",402);
    }
    if (params[27].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",403);
    }
    if (params[28].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",404);
    }
    if (params[29].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",405);
    }
    if (params[30].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",406);
    }
    if (params[31].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",407);
    }
    if (params[32].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",408);
    }
    if (params[33].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",409);
    }
    if (params[34].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",410);
    }
    if (params[35].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",411);
    }
    if (params[36].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",412);
    }
    if (params[37].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",413);
    }
    if (params[38].equalsIgnoreCase("1")){
    	request.addProperty("ensenanzas",441);
    }
    //request.addProperty("modeloLinguistico",1180);

    
    //request.addProperty("ensenanzas", 1);
    request.addProperty("orden",1);
    request.addProperty("numPagina",1);
    request.addProperty("numElementos",1000);
    request.addProperty("idioma",Locale.getDefault().getLanguage());
    
 
    SoapSerializationEnvelope envelope =new SoapSerializationEnvelope(SoapEnvelope.VER11);
 

    envelope.setOutputSoapObject(request);
 
    HttpTransportSE transporte = new HttpTransportSE(URL);
    transporte.debug = true;
    	
        transporte.call(SOAP_ACTION, envelope);
        resSoap = transporte.responseDump;
        
        

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
