package nav.naveduca;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import Proceso_XML.Parser;
import Proceso_XML.ParserBuscAv;
import SOAP.TareaWSBuscadorAvanzado;
import SOAP.TareaWSConsulta;
import android.app.ProgressDialog;
import android.util.Log;


//Clase de los centros educativos
//Desarrollado por Mikel San Martin Huarte
public class Centro implements Serializable{
	
		protected static final int Parcelable = 0;
		private String id;
	    private String nombre;
	    private String localidad;
	    private String telefono;
	    private double latitud;
	    private double longitud;
	    private String direccion;
	    private String cp;
	    private String fax;
	    private String email;
	    private String web;
	    private String naturaleza;
	    private String modelo;
	    private String tipo;
	    private String servicios;
	    private String atencionnee;
	    private String programas;
	    private String tanos;
	    private String sitna;
	    private String plan;
	    private String matriculas;
	    private String objetivos;
	    private String valores;
	    private String distinciones;
	    private String jornada;
	    private String proyectos;
	    private String reconocimientos;
	    private String zona;
	    
	    //Informacion basica del centro
	    public Centro(String id, String nombre, String localidad, String telefono, double latitud, double longitud) {
	    	this.id = id;
	        this.nombre = nombre;
	        this.localidad = localidad;
	        this.telefono = telefono;
	        this.latitud = latitud;
	        this.longitud = longitud;
	    }
	    
	    //Informacion completa del centro
	    public Centro(String id, String nombre, String direccion, String cp, String localidad, String telefono, String fax, String email, String web, String naturaleza, String modelo, String tipo, String servicios, String atencionnee, String programas, double latitud, double longitud, String tanos, String plan, String sitna, String matriculas, String objetivos, String valores, String distinciones, String jornada, String proyectos, String reconocimientos, String zona){
	    	this.id = id;
	        this.nombre = nombre;
	        this.localidad = localidad;
	        this.telefono = telefono;
	        this.latitud = latitud;
	        this.longitud = longitud;
	        this.zona = zona;
	        this.reconocimientos = reconocimientos;
	        this.proyectos = proyectos;
	        this.jornada = jornada;
	        this.distinciones = distinciones;
	        this.valores = valores;
	        this.objetivos = objetivos;
	        this.matriculas = matriculas;
	        this.plan = plan;
	        this.sitna = sitna;
	        this.tanos = tanos;
	        this.programas = programas;
	        this.atencionnee = atencionnee;
	        this.servicios = servicios;
	        this.tipo = tipo;
	        this.modelo = modelo;
	        this.naturaleza = naturaleza;
	        this.web = web;
	        this.email = email;
	        this.fax = fax;
	        this.cp = cp;
	        this.direccion = direccion;
	    }
	    	
	    
	    public Centro(){
	    	
	    }

		public String toString(){
	    	return  nombre + " " + localidad + " " + telefono;
	    }
	    
	    public String getId(){
	    	return id;
	    }
	    
	    public String getNombre(){
	    	return nombre;
	    }
	    public String getLocalidad(){
	    	return localidad;
	    }
	    public String getTelefono(){
	    	return telefono;
	    }
	    public double getLatitud(){
	    	return latitud;
	    }
	    public double getLongitud(){
	    	return longitud;
	    }
	    public String getDireccion(){
	    	return direccion;
	    }
	    public String getCodigoPostal(){
	    	return cp;
	    }
	    public String getFax(){
	    	return fax;
	    }
	    public String getEmail(){
	    	return email;
	    }
	    public String getWeb(){
	    	return web;
	    }
	    public String getNaturaleza(){
	    	return naturaleza;
	    }
	    
	    public void setId(String id){
	    	this.id = id;
	    }
	    public String getModelo(){
	    	return modelo;
	    }
	    public String getTipo(){
	    	return tipo;
	    }
	    public String getServicios(){
	    	return servicios;
	    }
	    public String getProgramas(){
	    	return programas;
	    }
	    public String getObjetivos(){
	    	return objetivos;
	    }
	    public String getSitna(){
	    	return sitna;
	    }
	    public String getZona(){
	    	return zona;
	    }
	    public String getMatriculas(){
	    	return matriculas;
	    }
	    public String getAtencion(){
	    	return atencionnee;
	    }
	    public String getPlan(){
	    	return plan;
	    }
	    public String getTanos(){
	    	return tanos;
	    }
	    public String getValores(){
	    	return valores;
	    }
	    public String getDistinciones(){
	    	return distinciones;
	    }
	    public String getJornada(){
	    	return jornada;
	    }
	    public String getProyectos(){
	    	return proyectos;
	    }
	    public String getReconocimientos(){
	    	return reconocimientos;
	    }
	    
	
	    
	    public List<Centro> getInfoCentros() {
	    	List<Centro> l = null;
	    	
	    	try{
		    	TareaWSConsulta llam_soap = (TareaWSConsulta) new TareaWSConsulta().execute();
	        	//llam_soap.doInBackground();
	        	//Log.d("hola", llam_soap.resSoap.toString());
	        	//Toast toast = Toast.makeText(a, llam_soap.resSoap, Toast.LENGTH_LONG);
	            //toast.show();
	        	Boolean ready = llam_soap.getReady();
	        	while(!ready){
	        		Log.d("halo", ready.toString());
	        		Thread.sleep(1000);
	        		ready = llam_soap.getReady();
	        	}
	        	
	            Parser p = new Parser();
	            //InputStream in = IOUtils.toInputStream(llam_soap.resSoap.toString(), "UTF-8");
	            //InputStream in = new ByteArrayInputStream(llam_soap.resSoap.toString().getBytes(StandardCharsets.UTF_8));
	            InputStream in = new ByteArrayInputStream(llam_soap.resSoap.toString().getBytes("UTF-8"));
	            l = p.parse(in);
	            
	    	} catch(Exception e){
	    		Log.d("tareawsconsulta" , e.toString());
	    	}
	    	return l;     
	    }
	    
	    public List<Centro> getBuscadorAvanzadoCentros(boolean[] ano, boolean[] naturaleza,boolean[] modelo,boolean[] servicios,boolean[] nivel ) {
	    	List<Centro> l = null;
	    	
	    	try{
	    		String a = "0" ,b = "0";
	    		String c = "0", d = "0",e ="0";
	    		String f = "0", g = "0",h ="0" , i = "0", j = "0", k ="0" , ll="0", m="0", ff="0", gg="0",hh="0",ii="0",jj="0",kk="0",lll="0" ;
	    		String n = "0", o = "0",pp ="0";
	    		String q = "0", r = "0",s ="0" , t = "0", u = "0", v ="0" , w="0", x="0", y = "0", z ="0", aa = "0", ab ="0", ac="0", ad="0", ae ="0", af="0" ;
	    		
	    		if(ano[0]==true) {
	    			a = "1";
	    		}
	    		if(ano[1]==true) {
	    			b = "1";
	    		}
	    		if(naturaleza[0]==true) {
	    			c = "1";
	    		}
	    		if(naturaleza[1]==true) {
	    			d = "1";
	    		}
	    		if(naturaleza[2]==true) {
	    			e = "1";
	    		}
	    		if(modelo[0]==true) {
	    			f = "1";
	    		}
	    		if(modelo[1]==true) {
	    			g = "1";
	    		}
	    		if(modelo[2]==true) {
	    			h = "1";
	    		}
	    		if(modelo[3]==true) {
	    			i = "1";
	    		}
	    		if(modelo[4]==true) {
	    			j = "1";
	    		}
	    		if(modelo[5]==true) {
	    			k = "1";
	    		}
	    		if(modelo[6]==true) {
	    			ll = "1";
	    		}
	    		if(modelo[7]==true) {
	    			m = "1";
	    		}
	    		if(modelo[8]==true) {
	    			ff = "1";
	    		}
	    		if(modelo[9]==true) {
	    			gg = "1";
	    		}
	    		if(modelo[10]==true) {
	    			hh = "1";
	    		}
	    		if(modelo[11]==true) {
	    			ii = "1";
	    		}
	    		if(modelo[12]==true) {
	    			jj = "1";
	    		}
	    		if(modelo[13]==true) {
	    			kk = "1";
	    		}
	    		if(modelo[14]==true) {
	    			lll = "1";
	    		}
	    		if(servicios[0]==true) {
	    			n = "1";
	    		}
	    		if(servicios[1]==true) {
	    			o = "1";
	    		}
	    		if(servicios[2]==true) {
	    			pp = "1";
	    		}
	    		if(nivel[0]==true) {
	    			q = "1";
	    		}
	    		if(nivel[1]==true) {
	    			r = "1";
	    		}
	    		if(nivel[2]==true) {
	    			s = "1";
	    		}
	    		if(nivel[3]==true) {
	    			t = "1";
	    		}
	    		if(nivel[4]==true) {
	    			u = "1";
	    		}
	    		if(nivel[5]==true) {
	    			v = "1";
	    		}
	    		if(nivel[6]==true) {
	    			w = "1";
	    		}
	    		if(nivel[7]==true) {
	    			x = "1";
	    		}
	    		if(nivel[8]==true) {
	    			y = "1";
	    		}
	    		if(nivel[9]==true) {
	    			z = "1";
	    		}
	    		if(nivel[10]==true) {
	    			aa = "1";
	    		}
	    		if(nivel[11]==true) {
	    			ab = "1";
	    		}
	    		if(nivel[12]==true) {
	    			ac = "1";
	    		}
	    		if(nivel[13]==true) {
	    			ad = "1";
	    		}
	    		if(nivel[14]==true) {
	    			ae = "1";
	    		}
	    		if(nivel[15]==true) {
	    			af = "1";
	    		}
	    		TareaWSBuscadorAvanzado llam_soap = (TareaWSBuscadorAvanzado) new TareaWSBuscadorAvanzado().execute(a,b,c,d,e,f,g,h,i,j,k,ll,m,ff,gg,hh,ii,jj,kk,lll,n,o,pp,q,r,s,t,u,v,w,x,y,z,aa,ab,ac,ad,ae,af);
	        	//llam_soap.doInBackground();
	        	//Log.d("hola", llam_soap.resSoap.toString());
	        	//Toast toast = Toast.makeText(a, llam_soap.resSoap, Toast.LENGTH_LONG);
	            //toast.show();
	        	Boolean ready = llam_soap.getReady();
	        	while(!ready){
	        		Thread.sleep(1000);
	        		ready = llam_soap.getReady();
	        	}
	        	ParserBuscAv p = new ParserBuscAv();
	            //InputStream in = IOUtils.toInputStream(llam_soap.resSoap.toString(), "UTF-8");
	            //InputStream in = new ByteArrayInputStream(llam_soap.resSoap.toString().getBytes(StandardCharsets.UTF_8));
	            InputStream in = new ByteArrayInputStream(llam_soap.resSoap.toString().getBytes("UTF-8"));
	            l = p.parse(in);
	            
	    	} catch(Exception e){
	    		
	    	}
	    	return l;     
	    }
	    
	    

}
