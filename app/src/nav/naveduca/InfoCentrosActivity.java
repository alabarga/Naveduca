package nav.naveduca;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import nav.naveduca.R;

import Proceso_XML.ParserDetalleCentro;
import SOAP.TareaWSdetalleCentro;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

//Actividad para mostrar la informacion basica de un centro educativo
//Desarrollado por Mikel San Martin Huarte
public class InfoCentrosActivity extends ActionBarActivity {

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
			setContentView(R.layout.activity_info_centros);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
			
			final Bundle bundle=getIntent().getExtras();
			//text.setText(bundle.getString("id")) ;
			
			
			//setTitle(bundle.getString("nombre"));
	
	//		ListView drawer = (ListView) findViewById(R.id.drawer);
	//		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	//		
	//		String[] opciones = {"Información básica", "Información sobre modelo", "Localización"};
	//		drawer.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, opciones));
	
			final Centro c = obtenerDetalle(bundle);
			
			
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
			 
			tabs.setCurrentTab(0);
				
			
				
				
				
				tabs.setOnTabChangedListener(new OnTabChangeListener() {
				    @Override
				    public void onTabChanged(String tabId) {
				        //Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
				        if (tabId.equals("mitab2")){
		            		Intent intent = new Intent(InfoCentrosActivity.this, InfoAdicionalActivity.class);
		            		intent.putExtra("id", c.getId());
		            		intent.putExtra("nombre", c.getNombre());
		            		intent.putExtra("latitud", c.getLatitud());
		            		intent.putExtra("longitud", c.getLongitud());
		            		startActivity(intent);
		            		finish();
				        }
				        if (tabId.equals("mitab3")){
		            		Intent intent = new Intent(InfoCentrosActivity.this, MapaActivity.class);
		            		intent.putExtra("id", c.getId());
		            		intent.putExtra("nombre", c.getNombre());
		            		intent.putExtra("latitud", c.getLatitud());
		            		intent.putExtra("longitud", c.getLongitud());
		            		startActivity(intent);
		            		finish();
				        }
				    }
				});
				
//				final TextView tv = (TextView) tabs.getChildAt(1).findViewById(android.R.id.title);
//				tv.setLines(2);


			
	}catch(Exception e){
		Log.d("Excepcion", e.toString());
	}
			
			
		}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_centros, menu);
		return true;
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
	
	public Centro obtenerDetalle(Bundle bundle){
		Centro c = null;
		
		
		
		TextView text1 = (TextView) findViewById(R.id.textView1);
		TextView text2 = (TextView) findViewById(R.id.textView2);
		TextView text3 = (TextView) findViewById(R.id.textView3);
		TextView text4 = (TextView) findViewById(R.id.textView4);
		TextView text5 = (TextView) findViewById(R.id.textView5);
		TextView text6 = (TextView) findViewById(R.id.textView6);
		TextView text7 = (TextView) findViewById(R.id.textView7);
		TextView text8 = (TextView) findViewById(R.id.textView8);
		TextView text9 = (TextView) findViewById(R.id.textView9);
		
		try{
	    	TareaWSdetalleCentro llam_soap = (TareaWSdetalleCentro) new TareaWSdetalleCentro().execute(bundle.getString("id"));
        	//llam_soap.doInBackground(bundle.getString("id"));
        	//Log.d("hola", llam_soap.resSoap.toString());
        	
//        	TareaWSBuscadorAvanzado llam_soap2 = new TareaWSBuscadorAvanzado();
//        	llam_soap2.doInBackground(bundle.getString("id"));
//        	Log.d("nuevooooooo", llam_soap2.resSoap.toString());
        	Boolean ready = llam_soap.getReady();
        	while(!ready){
        		Thread.sleep(1000);
        		ready = llam_soap.getReady();
        	}
        	
        	ParserDetalleCentro p = new ParserDetalleCentro();
            //InputStream in = IOUtils.toInputStream(llam_soap.resSoap.toString(), "UTF-8");
            //InputStream in = new ByteArrayInputStream(llam_soap.resSoap.toString().getBytes(StandardCharsets.UTF_8));
        	Log.d("ehhhhh",llam_soap.resSoap.toString());
            InputStream in = new ByteArrayInputStream(llam_soap.resSoap.toString().getBytes("UTF-8"));
            c = p.parse(in);
            c.setId(bundle.getString("id"));
            //Log.d("c.nombre", c.nombre);
            setTitle(c.getNombre());
            //Log.d("c.modelo",c.naturaleza);
            String direccion, nombre, cp, localidad;
			final String telefono;
			String fax;
			final String email;
			final String web;
			String naturaleza;
            if(c.getNombre()==null){
            	nombre = getResources().getString(R.string.nodisponible);
            }
            else {
            	nombre = c.getNombre();
            	text1.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.nombre)+"</font> " + nombre));
            }
            if(c.getDireccion()==null){
            	//direccion = getResources().getString(R.string.nodisponible);
            	text2.setVisibility(View.GONE);
            }
            else {
            	direccion = c.getDireccion();
            	text2.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.direccion)+"</font> " + direccion));
            }
            if(c.getCodigoPostal()==null){
            	//cp = getResources().getString(R.string.nodisponible);
            	text3.setVisibility(View.GONE);
            }
            else {
            	cp = c.getCodigoPostal();
            	text3.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.cp)+"</font> " + cp));
            }
            if(c.getLocalidad()==null){
            	//localidad = getResources().getString(R.string.nodisponible);
            	text4.setVisibility(View.GONE);
            }
            else {
            	localidad = c.getLocalidad();
            	text4.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.localidad)+"</font> " + localidad));
            	
            }
            if(c.getTelefono()==null){
            	//telefono = getResources().getString(R.string.nodisponible);
            	//text5.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.telefono)+"</font> " + telefono));
            	text5.setVisibility(View.GONE);
            }
            else {
            	telefono = c.getTelefono();
            	text5.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.telefono)+"</font> <u> " + telefono + "</u>"));
    			text5.setOnClickListener(new View.OnClickListener() {
    			    @Override
    			    public void onClick(View v) {
    			    	try{
    						// Instantiate an AlertDialog.Builder with its constructor
    						AlertDialog.Builder builder = new AlertDialog.Builder(InfoCentrosActivity.this);

    						// Chain together various setter methods to set the dialog characteristics
    						builder.setMessage(R.string.dialog_title2)
    						       .setTitle(R.string.dialog_message2);

    						// Add the buttons
    						builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
    						           public void onClick(DialogInterface dialog, int id) {
    						               // User clicked OK button
    			    		            	Intent callIntent = new Intent(Intent.ACTION_CALL);
    			    		            	callIntent.setData(Uri.parse("tel:"+ telefono));
    			    		            	startActivity(callIntent);
    						           }
    						       });
    						builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						        	   dialog.cancel();
						           }
						       });
    						AlertDialog dialog = builder.create();
    					    dialog.show();

    			    	}catch(Exception e){
    			    		Log.d("halo",e.toString());
    			    	}
    			    }
    			});

            }
            if(c.getFax()==null){
            	//fax = getResources().getString(R.string.nodisponible);
            	text6.setVisibility(View.GONE);
            }
            else {
            	fax = c.getFax();
            	text6.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.fax)+"</font> " + fax));
            }
            if(c.getEmail()==null){
            	//email = getResources().getString(R.string.nodisponible);
            	//text7.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.email)+"</font> " + email ));
            	text7.setVisibility(View.GONE);
            }
            else {
            	email = c.getEmail();
            	text7.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.email)+"</font> <u> " + email + "</u>"));
    			text7.setOnClickListener(new View.OnClickListener() {
    			    @Override
    			    public void onClick(View v) {
    			    	try{
    		            	Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
    		                        "mailto",email, null));
    		            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
    		            startActivity(Intent.createChooser(emailIntent, "Send email..."));
    			    	}catch(Exception e){
    			    		
    			    	}
    			    }
    			});

            }
            if(c.getWeb()==null){
            	//web = getResources().getString(R.string.nodisponible);
            	//text8.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.web)+"</font>" + web ));
            	text8.setVisibility(View.GONE);
            }
            else {
            	web = c.getWeb();
            	text8.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.web)+"</font> <u>" + web + "</u>"));
    			text8.setOnClickListener(new View.OnClickListener() {
    			    @Override
    			    public void onClick(View v) {
    			    	try{
    		            String enlaceweb = web;
    		            if (!web.startsWith("http://") && !web.startsWith("https://"))
    		            enlaceweb = "http://" + web;
    			    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(enlaceweb));
    			    	startActivity(browserIntent);
    			    	}catch(Exception e){
    			    		
    			    	}
    			    }
    			});
            }
            if(c.getNaturaleza()==null){
            	//naturaleza = getResources().getString(R.string.nodisponible);
            	text9.setVisibility(View.GONE);
            }
            else {
            	naturaleza = c.getNaturaleza();
            	text9.setText(Html.fromHtml("<font COLOR=#B41111>"+ getResources().getString(R.string.naturaleza)+"</font> " + naturaleza));
            }

        	
		} catch(Exception e){
			Log.d("excepcion", e.toString());
		}
		return c;
	}



}
