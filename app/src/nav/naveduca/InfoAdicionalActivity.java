package nav.naveduca;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import nav.naveduca.R;

import Proceso_XML.ParserInfoAdicional;
import SOAP.TareaWSdetalleCentro;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

//Actividad para mostrar la informacion adicional de un centro educativo
//Desarrollado por Mikel San Martin Huarte
public class InfoAdicionalActivity extends ActionBarActivity {

//	private int d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
		try{
			SharedPreferences prefs =
				     getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
			if(prefs.getString("idioma", "es").equalsIgnoreCase("es")){
				Locale locale = new Locale("es"); 
	    		Locale.setDefault(locale);
	    		Configuration config = new Configuration();
	    		config.locale = locale;
	    		Context context =getApplicationContext() ;
	    		context.getApplicationContext().getResources().updateConfiguration(config, null);          		
			}
			else if (prefs.getString("idioma", "es").equalsIgnoreCase("eu")){
				Locale locale = new Locale("eu"); 
	    		Locale.setDefault(locale);
	    		Configuration config = new Configuration();
	    		config.locale = locale;
	    		Context context =getApplicationContext() ;
	    		context.getApplicationContext().getResources().updateConfiguration(config, null);          		
			}
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_info_adicional);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
			
			final Bundle bundle=getIntent().getExtras();
			
			obtenerDetalle(bundle);
			
			final String nombre = bundle.getString("nombre");
//			final String id = bundle.getString("id");
//			final double latitud = bundle.getDouble("latitud");
//			final double longitud = bundle.getDouble("longitud");
			
			setTitle(nombre);
			
			//Tabs
			Resources res = getResources();
			
			TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
			tabs.setup();
			 
			TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
			spec.setContent(R.id.tab1);
			spec.setIndicator(getResources().getString(R.string.info_basica),
			    res.getDrawable(android.R.drawable.ic_dialog_info));
			tabs.addTab(spec);
			 
			spec=tabs.newTabSpec("mitab2");
			spec.setContent(R.id.tab2);
			spec.setIndicator(getResources().getString(R.string.info_adicional),
			    res.getDrawable(android.R.drawable.ic_menu_help));
			tabs.addTab(spec);
			
			spec=tabs.newTabSpec("mitab3");
			spec.setContent(R.id.tab3);
			spec.setIndicator(getResources().getString(R.string.localizacion),
			res.getDrawable(android.R.drawable.ic_dialog_map));
			tabs.addTab(spec);
			 
			tabs.setCurrentTab(1);
			
			tabs.setOnTabChangedListener(new OnTabChangeListener() {
			    @Override
			    public void onTabChanged(String tabId) {
			        //Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
			        if (tabId.equals("mitab1")){
	            		Intent intent = new Intent(InfoAdicionalActivity.this, InfoCentrosActivity.class);
	            		intent.putExtra("id", bundle.getString("id"));
	            		intent.putExtra("nombre", bundle.getString("nombre"));
	            		intent.putExtra("latitud", bundle.getDouble("latitud"));
	            		intent.putExtra("longitud", bundle.getDouble("longitud"));
	            		startActivity(intent);
	            		finish();
			        }
			        if (tabId.equals("mitab3")){
	            		Intent intent = new Intent(InfoAdicionalActivity.this, MapaActivity.class);
	            		intent.putExtra("id", bundle.getString("id"));
	            		intent.putExtra("nombre", bundle.getString("nombre"));
	            		intent.putExtra("latitud", bundle.getDouble("latitud"));
	            		intent.putExtra("longitud", bundle.getDouble("longitud"));
	            		startActivity(intent);
	            		finish();
			        }
			    }
			});
			}catch(Exception e){
				Log.d("Excepcion", e.toString());
			}
		
		}


	public Centro obtenerDetalle(Bundle bundle){
		Centro c = null;
		
		
		TextView text0 = (TextView) findViewById(R.id.textView0);
		TextView text1 = (TextView) findViewById(R.id.textView1);
		TextView text2 = (TextView) findViewById(R.id.textView2);
		TextView text3 = (TextView) findViewById(R.id.textView3);
		TextView text4 = (TextView) findViewById(R.id.textView4);
		TextView text5 = (TextView) findViewById(R.id.textView5);
		TextView text6 = (TextView) findViewById(R.id.textView6);
		TextView text7 = (TextView) findViewById(R.id.textView7);
		TextView text8 = (TextView) findViewById(R.id.textView8);
		TextView text9 = (TextView) findViewById(R.id.textView9);
		TextView text10 = (TextView) findViewById(R.id.textView10);
		TextView text11 = (TextView) findViewById(R.id.textView11);
		TextView text12 = (TextView) findViewById(R.id.textView12);
		TextView text13 = (TextView) findViewById(R.id.textView13);
		TextView text14 = (TextView) findViewById(R.id.textView14);
		TextView text15 = (TextView) findViewById(R.id.textView15);
		TextView text16 = (TextView) findViewById(R.id.textView16);
		
		try{
	    	TareaWSdetalleCentro llam_soap = (TareaWSdetalleCentro) new TareaWSdetalleCentro().execute(bundle.getString("id"));
        	//llam_soap.doInBackground(bundle.getString("id"));
        	Boolean ready = llam_soap.getReady();
        	while(!ready){
        		Thread.sleep(1000);
        		ready = llam_soap.getReady();
        	}
        	
        	ParserInfoAdicional p = new ParserInfoAdicional();
            //InputStream in = IOUtils.toInputStream(llam_soap.resSoap.toString(), "UTF-8");
            //InputStream in = new ByteArrayInputStream(llam_soap.resSoap.toString().getBytes(StandardCharsets.UTF_8));
            InputStream in = new ByteArrayInputStream(llam_soap.resSoap.toString().getBytes("UTF-8"));
            c = p.parse(in);
        	
        	
        	
//        	ready = false;
//            p.execute(llam_soap.resSoap.toString());
//        	while(!ready){
//        		Thread.sleep(2);
//        		ready = p.getReady();
//        	}
//            c = p.getCentro();
            
            
            //c.setId(bundle.getString("id"));
            //Log.d("c.nombre", c.nombre);
            setTitle(c.getNombre());
            //Log.d("c.modelo",c.naturaleza);
            String modelo, tipo, servicios, atencion;
			final String sitna;
			String zona, matriculas, plan , programas, tanos, objetivos, valores, distinciones, jornada, proyectos, reconocimientos;    
			text0.setVisibility(View.INVISIBLE);
            if(c.getModelo()==null){
            	//modelo = getResources().getString(R.string.nodisponible);
            	text1.setVisibility(View.GONE);
            }
            else {
            	modelo = c.getModelo();
            	text1.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.modelo)+"</font> " + modelo));
            }
            if(c.getTipo()==null){
            	//tipo = getResources().getString(R.string.nodisponible);
            	text2.setVisibility(View.GONE);
            }
            else {
            	tipo = c.getTipo();
            	text2.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.tipo)+"</font> " + tipo));
            }
            if(c.getServicios()==null){
            	//servicios = getResources().getString(R.string.nodisponible);
            	text3.setVisibility(View.GONE);
            }
            else {
            	servicios = c.getServicios();
            	text3.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.servicios)+"</font> " + servicios));
            }
            if(c.getAtencion()==null){
            	//atencion = getResources().getString(R.string.nodisponible);
            	text4.setVisibility(View.GONE);
            }
            else {
            	atencion = c.getAtencion();
            	text4.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.atencion)+"</font> " + atencion));
            }
            if(c.getProgramas()==null){
            	//programas = getResources().getString(R.string.nodisponible);
            	text5.setVisibility(View.GONE);
            }
            else {
            	programas = c.getProgramas();
            	text5.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.programas)+"</font> " + programas)); 
            }

            if(c.getTanos()==null){
            	//tanos = getResources().getString(R.string.nodisponible);
            	text6.setVisibility(View.GONE);
            }
            else {
            	tanos = c.getTanos();
            	text6.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.tanos)+"</font> " + tanos));
            }
            if(c.getPlan()==null || c.getPlan() ==""){
            	//plan = getResources().getString(R.string.nodisponible);
            	text7.setVisibility(View.GONE);
            }
            else {
            	Log.d("entra","entra");
            	try{
            	plan = c.getPlan();
            	Log.d("entra1","entra" + plan);
            	text7.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.plan)+"</font> " + plan));
            	}catch(Exception e){
            		Log.d("0", "0"+e.toString());
            	}
            	//text7.setVisibility(View.GONE);

            }
            if(c.getSitna()==null){
            	//sitna = getResources().getString(R.string.nodisponible);
            	text8.setVisibility(View.GONE);
            }
            else {
            	sitna = c.getSitna();
            	text8.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.sitna)+"</font> <u>" + sitna + "</u>"));
    			text8.setOnClickListener(new View.OnClickListener() {
    			    @Override
    			    public void onClick(View v) {
    			    	try{
    		            String enlaceweb = sitna;
    		            if (!sitna.startsWith("http://") && !sitna.startsWith("https://"))
    		            enlaceweb = "http://" + sitna;
    			    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(enlaceweb));
    			    	startActivity(browserIntent);
    			    	}catch(Exception e){
    			    		
    			    	}
    			    }
    			});
            }
            if(c.getMatriculas()==null){
            	//matriculas = getResources().getString(R.string.nodisponible);
            	text9.setVisibility(View.GONE);
            }
            else {
            	matriculas = c.getMatriculas();
            	text9.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.matriculas)+"</font> " + matriculas));
            }
            if(c.getObjetivos()==null || c.getObjetivos()==""){
            	//objetivos = getResources().getString(R.string.nodisponible);
            	text10.setVisibility(View.GONE);
            }
            else {
            	objetivos = c.getObjetivos();
            	text10.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.objetivos)+"</font> " + objetivos));
            }
            if(c.getValores()==null || c.getValores() ==""){
            	//valores = getResources().getString(R.string.nodisponible);
            	text11.setVisibility(View.GONE);
            }
            else {
            	valores = c.getValores();
            	text11.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.valores)+"</font> " + valores));
            }
            if(c.getDistinciones()==null || c.getDistinciones() ==""){
            	//distinciones = getResources().getString(R.string.nodisponible);
            	text12.setVisibility(View.GONE);
            }
            else {
            	distinciones = c.getDistinciones();
            	text12.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.distinciones)+"</font> " + distinciones));
            }
            if(c.getJornada()==null || c.getJornada()==""){
            	//jornada = getResources().getString(R.string.nodisponible);
            	text13.setVisibility(View.GONE);
            }
            else {
            	jornada = c.getJornada();
            	text13.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.jornada)+"</font> " + jornada));
            }
            if(c.getProyectos()==null){
            	//proyectos = getResources().getString(R.string.nodisponible);
            	text14.setVisibility(View.GONE);
            }
            else {
            	proyectos = c.getProyectos();
            	text14.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.proyectos)+"</font> " + proyectos));
            }
            if(c.getReconocimientos()==null){
            	//reconocimientos = getResources().getString(R.string.nodisponible);
            	text15.setVisibility(View.GONE);
            }
            else {
            	reconocimientos = c.getReconocimientos();
            	text15.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.reconocimientos)+"</font> " + reconocimientos));
            }
            if(c.getZona()==null){
            	//zona = getResources().getString(R.string.nodisponible);
            	text16.setVisibility(View.GONE);
            }
            else {
            	zona = c.getZona();
            	text16.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.zona)+"</font> " + zona));
            }

           
        	
		} catch(Exception e){
			Log.d("excepcion", e.toString());
		}
		return c;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		switch (item.getItemId()) {
        case android.R.id.home:
            super.onBackPressed();
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}

}
