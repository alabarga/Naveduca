package Proceso_XML;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nav.naveduca.Centro;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;


//Parser para la informacion general de los centros
//Desarrollado por Mikel San Martin Huarte
public class Parser {
	
	private static final String ns = null;
	public int num;
	   
    public List<Centro> parse(InputStream in) throws XmlPullParserException, IOException {
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
    
    
	private List<Centro> procesarEtiquetas(XmlPullParser parser) throws XmlPullParserException, IOException {
	    ArrayList<Centro> entries = new ArrayList<Centro>();

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
	        	
	        	
	        	parser.require(XmlPullParser.START_TAG, ns, "ns2:buscadorSimpleResponse");
	        	while (parser.next() != XmlPullParser.END_TAG) {
	    	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	    	            continue;
	    	        }
	        	
	        	
	        	parser.require(XmlPullParser.START_TAG, ns, "return");
	        	while (parser.next() != XmlPullParser.END_TAG) {
	    	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	    	            continue;
	    	        }
	        	
	        
	        
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("listaSedes")) {
	            entries.add(readEntry(parser));
	        } else {
	            skip(parser);
	        }
	        }
	        }
	        }
	   }
	    return entries;

}
	
	
	  
	// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
	// to their respective "read" methods for processing. Otherwise, skips the tag.
	private Centro readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "listaSedes");
	    String id = null;
	    String nombre = null;
	    String localidad = null;
	    String telefono = null;
	    double latitud = 0.00;
	    double longitud = 0.00;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("nombre")) {
	            nombre = readNombre(parser);
	        } else if (name.equals("localidad")) {
	            localidad = readLocalidad(parser);
	        } else if (name.equals("id")) {
		            id = readId(parser);
	        } else if (name.equals("telefono")) {
	            telefono = readTelefono(parser);
	        } else if (name.equals("latitud")) {
	        	latitud = readLatitud(parser);
	        } else if (name.equals("longitud")) {
	        	longitud = readLongitud(parser);
	        } else {
	            skip(parser);
	        }
	    }
	    return new Centro(id, nombre, localidad, telefono, latitud, longitud);
	}

	private String readId(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "id");
	    String id = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "id");
	    return id;
	}
	
	// Processes title tags in the feed.
	private String readNombre(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "nombre");
	    String nombre = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "nombre");
	    return nombre;
	}
	  
	// Processes link tags in the feed.
	private String readLocalidad(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String localidad = "";
	    parser.require(XmlPullParser.START_TAG, ns, "localidad");
//	    String tag = parser.getName();
//	    String relType = parser.getAttributeValue(null, "rel");  
//	    if (tag.equals("link")) {
//	        if (relType.equals("alternate")){
//	            link = parser.getAttributeValue(null, "href");
//	            parser.nextTag();
//	        } 
//	    }
	    localidad = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "localidad");
	    return localidad;
	}

	// Processes summary tags in the feed.
	private String readTelefono(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "telefono");
	    String telefono = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "telefono");
	    return telefono;
	}
	
	private double readLatitud(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "latitud");
	    double latitud = Double.parseDouble(readText(parser));
	    parser.require(XmlPullParser.END_TAG, ns, "latitud");
	    return latitud;
	}
	
	private double readLongitud(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "longitud");
	    double longitud = Double.parseDouble(readText(parser));
	    parser.require(XmlPullParser.END_TAG, ns, "longitud");
	    return longitud;
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
