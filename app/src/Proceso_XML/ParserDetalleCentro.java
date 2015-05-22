package Proceso_XML;

import java.io.IOException;
import java.io.InputStream;

import nav.naveduca.Centro;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;


//Parser para la informacion especifica de los centros
//Desarrollado por Mikel San Martin Huarte
public class ParserDetalleCentro {
	
	private static final String ns = null;
	public int num;
	   
    public Centro parse(InputStream in) throws XmlPullParserException, IOException {
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
	        	
	        	
//	        	parser.require(XmlPullParser.START_TAG, ns, "ns2:detalleCentroResponse");
//	        	while (parser.next() != XmlPullParser.END_TAG) {
//	    	        if (parser.getEventType() != XmlPullParser.START_TAG) {
//	    	            continue;
//	    	        }
	        	
	        	
//	        	parser.require(XmlPullParser.START_TAG, ns, "return");
//	        	while (parser.next() != XmlPullParser.END_TAG) {
//	    	        if (parser.getEventType() != XmlPullParser.START_TAG) {
//	    	            continue;
//	    	        }
	        	
	        
	        
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
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("return")) {
	        	//parser.nextToken();
	        	//Log.d("entra -1", parser.getName());
	        	parser.nextTag();
	        	//Log.d("entra -0.5", parser.getName());
	        	String tag_clave = parser.getName();
	        	if (tag_clave.equals("clave")){
	        	//Log.d("entra0","allo");
	            clave = readClave(parser);
	            //parser.nextTag();
	            if (clave.equalsIgnoreCase("1")){
	            	//Log.d("entraaqui", "hello");	
	            	nombre = readString(parser);
	            	//Log.d("entra1",nombre);
	            }
	            if (clave.equalsIgnoreCase("2")){
	            	//Log.d("entraaqui", "hello");	
	            	direccion = readString(parser);
	            	//Log.d("entra1",nombre);
	            }
	            else if (clave.equalsIgnoreCase("3")){
	            	//Log.d("entraaqui", "hello");	
	            	cp = readString(parser);
	            	//Log.d("entra1",nombre);
	            }
	            else if (clave.equalsIgnoreCase("4")){
	            	//Log.d("entraaqui", "hello");	
	            	localidad = readString(parser);
	            	//Log.d("entra1",nombre);
	            }
	            else if (clave.equalsIgnoreCase("5")){
	            	//Log.d("entraaqui", "hello");	
	            	telefono = readString(parser);
	            	//Log.d("entra1",nombre);
	            }
	            else if (clave.equalsIgnoreCase("6")){
	            	//Log.d("entraaqui", "hello");	
	            	fax = readString(parser);
	            	//Log.d("entra1",nombre);
	            }
	            else if (clave.equalsIgnoreCase("7")){
	            	//Log.d("entraaqui", "hello");	
	            	email = readString(parser);
	            	//Log.d("entra1",nombre);
	            }
	            else if (clave.equalsIgnoreCase("8")){
	            	//Log.d("entraaqui", "hello");	
	            	web = readString(parser);
	            	//Log.d("entra1",nombre);
	            }
	            else if (clave.equalsIgnoreCase("9")) {
	            	naturaleza = readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("29")) {
	            	readString(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("15")) {
	            	latitud = readLatLong(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else if (clave.equalsIgnoreCase("16")) {
	            	longitud = readLatLong(parser);
	            	//Log.d("entra2",naturaleza);
	            }
	            else {
	            	
		        	if (parser.getEventType() != XmlPullParser.START_TAG) {
		        		parser.next();
		        		//parser.nextTag();
		        	}
	            	
	            	skip(parser);
	            	
	            	}
	            
	        	}

	        } else {
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
	private String readString(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String string = "";
	    parser.require(XmlPullParser.START_TAG, ns, "valor");
	    string = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "valor");
	    parser.nextTag();
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
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
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

