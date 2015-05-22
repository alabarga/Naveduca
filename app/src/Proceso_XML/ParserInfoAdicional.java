package Proceso_XML;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import nav.naveduca.Centro;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;


//Desarrollado por Mikel San Martin Huarte
//Parser para la informacion adicional
public class ParserInfoAdicional extends AsyncTask<String,Integer,Boolean> {
	
	private static final String ns = null;
	public int num;
	Centro c;
	Boolean ready;
	
	public Centro getCentro() {
		return c;
	}
	public Boolean getReady() {
		return ready;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		
		try {
			ready = false;
			c = parse(new ByteArrayInputStream(params[0].toString().getBytes("UTF-8")));
			ready=true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	   
    public Centro parse(InputStream in)  throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return procesarEtiquetas(parser);
        } finally {
            in.close();
        }
    }
    
    
	private Centro procesarEtiquetas(XmlPullParser parser) throws XmlPullParserException, IOException {
	    //Centro entries = new Centro();
		Centro entry = null;
		
	    parser.require(XmlPullParser.START_TAG, ns, "soap:Envelope");
	    //parser.require(XmlPullParser.END_TAG, ns, "soap:Envelope");
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        
	        	parser.require(XmlPullParser.START_TAG, ns, "soap:Body");
	        	while (parser.next() != XmlPullParser.END_TAG) {
	    	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	    	            continue;
	    	        }
	        
	        
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("ns2:detalleCentroResponse")) {
//	            entries.add(readEntry(parser));
	        	entry = readEntry(parser);
	        } else {
	            skip(parser);
	        }
	        }
	//        }
	        }
	  // }
	    return entry;

}
	
	
	  
	// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
	// to their respective "read" methods for processing. Otherwise, skips the tag.
	private Centro readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "ns2:detalleCentroResponse");
	    String clave = null;
	    String id = null;
	    String nombre = null;
	    String localidad = null;
	    String telefono = null;
	    double latitud = 0.00;
	    double longitud = 0.00;
	    String direccion = null;
	    String cp = null;
	    String fax= null;
	    String email= null;
	    String web= null;
	    String naturaleza= null;
	    String modelo= null;
	    String tipo= null;
	    String servicios= null;
	    String atencionnee= null;
	    String programas= null;
	    String tanos= null;
	    String sitna= null;
	    String plan= null;
	    String matriculas= null;
	    String objetivos= null;
	    String valores= null;
	    String distinciones= null;
	    String jornada= null;
	    String proyectos= null;
	    String reconocimientos= null;
	    String zona = null;
	    while (parser.next() != XmlPullParser.END_TAG) {
	    	try{
	        String name = parser.getName();
	        Log.d("entra -2", parser.getName());
	        if (name.equals("return")) {
	        	//parser.nextToken();
	        	parser.nextTag();
	        	Log.d("entra -1", parser.getName());
	        	//Log.d("entra -0.5", parser.getName());
	        	String tag_clave = parser.getName();
	        	if (tag_clave.equals("clave")){
	        	Log.d("entra0",parser.getName());
	            clave = readClave(parser);
	            //parser.nextTag();
	            if (clave.equalsIgnoreCase("1")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("2")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("3")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("4")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("5")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("6")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("7")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("8")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("9")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("10")) {
	            	modelo = readStringLista(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("11")) {
	            	tipo = readStringLista(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("12")) {
	            	servicios = readStringLista(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("13")) {
	            	atencionnee = readStringLista(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("14")) {
	            	programas = readStringLista(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("15")) {
	            	readLatLong(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("16")) {
	            	readLatLong(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("17")) {
	            	tanos = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("18")) {
	            	plan = read18(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("19")) {
	            	sitna = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("20")) {
	            	matriculas = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("21")) {
	            	objetivos = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("22")) {
	            	valores = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("23")) {
	            	distinciones = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("24")) {
	            	jornada = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("25")) {
	            	proyectos = readStringLista(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("26")) {
	            	reconocimientos = readStringLista(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("27")) {
	            	zona = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("28")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("29")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else {
	            		//skip(parser);
	            	readString(parser);
	        	
	        }
	        	}
	        	else{
	        			skip(parser);
	            	}
	        		
	        	}
	        
	        else {
	        		skip(parser);
	        }
	    
	        	}catch(Exception e){
	    	Log.d("ex parser", e.toString());
	    }
	    }
	    return new Centro( id,  nombre,  direccion,  cp,  localidad,  telefono,  fax,  email,  web,  naturaleza,  modelo,  tipo,  servicios,  atencionnee,  programas,  latitud,  longitud,  tanos,  plan,  sitna,  matriculas,  objetivos,  valores,  distinciones,  jornada,  proyectos,  reconocimientos,  zona);
	    
	}
	
	// Processes title tags in the feed.
	private String readClave(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "clave");
	    String clave = readText(parser);
	    Log.d("---",clave);
	    parser.require(XmlPullParser.END_TAG, ns, "clave");
	    parser.nextTag();
	    return clave;
	}
	  
	// Processes link tags in the feed.
	private String readStringLista(XmlPullParser parser) throws IOException, XmlPullParserException {
		String string = "";
		while (parser.getName().equals("lista")) {    
		    parser.require(XmlPullParser.START_TAG, ns, "lista");
		    string = string + "<br />" + readText(parser);
		    parser.require(XmlPullParser.END_TAG, ns, "lista");
		    parser.nextTag();
		}
	    return string;
	}
	private String read18(XmlPullParser parser) throws IOException, XmlPullParserException {
		String string = "";
		//while (parser.getName().equals("lista")) {    
		try{
		    parser.require(XmlPullParser.START_TAG, ns, "pe");
		    //string = string + "\n" + readText(parser);
		    readText(parser);
		    while(!parser.getName().equals("pe")){
		    	try{
		    	if (parser.getName().equals("nombre")){
		    		string = string + "<br />" + readText(parser);
		    	}
		    	else{
		    		readText(parser);
		    	}
		    	Log.d("---",parser.getName());
		    	}catch(Exception e){
		    		Log.d("1","1"+e.toString());
		    	}
		    }		    
		    parser.require(XmlPullParser.END_TAG, ns, "pe");
		    parser.nextTag();
		    Log.d("---",parser.getName());
		//}
	    Log.d("parserrr","parser"+string);
	    //return string;
		}catch(Exception e){
			Log.d("excpar", e.toString());
		}
		string = string.replace("<br />PLAN_ESTUDIOS<br />", "");
		return string;
	    
	}
	
	private String readString(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String string = "";
	    try{
	    parser.require(XmlPullParser.START_TAG, ns, "valor");
	    string = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "valor");
	    parser.nextTag();
	    Log.d("---",parser.getName());
	    Log.d("4","4"+string);
	    }
	    catch(Exception e){
	    	Log.d("2", "2"+e.toString());
	    }
	    return string;
	}
	
	private double readLatLong(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "valor");
	    double latitud = Double.parseDouble(readText(parser));
	    parser.require(XmlPullParser.END_TAG, ns, "valor");
	    parser.nextTag();
	    return latitud;
	}

	// For the tags title and summary, extracts their text values.
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    try{
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    Log.d("6", "6" + result);
	    }catch(Exception e){
	    	Log.d("5", "5"+e.toString());
	    }
	    return result;
	}
	
	
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }



	
}


