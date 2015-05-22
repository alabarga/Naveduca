package nav.naveduca;

import java.util.Locale;

import nav.naveduca.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;


//Actividad de la pantalla principal
//Desarrollado por Mikel San Martin Huarte

public class PantallaPrincipalActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
		setContentView(R.layout.activity_pantalla_principal);
		setTitle(getResources().getString(R.string.app_name));
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		
		
		
		if(!isOnline()){
			// Instantiate an AlertDialog.Builder with its constructor
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			

			// Chain together various setter methods to set the dialog characteristics
			builder.setMessage(R.string.dialog_message)
			       .setTitle(R.string.dialog_title);

			// Add the buttons
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User clicked OK button
			        	   finish();
			           }
			       });
			AlertDialog dialog = builder.create();
			dialog.setCancelable(false);
		      dialog.show();

		}
		
		final ImageView image3 = (ImageView) findViewById(R.id.imageView3);
		findViewById(R.id.imageView3).setOnClickListener(new OnClickListener(){
            public void onClick(View arg0) {
            	
                //Button boton = (Button)arg0;
            	try{
            		image3.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
            		Intent intent = new Intent(PantallaPrincipalActivity.this, CentrosActivity.class);
            		intent.putExtra("modo", "camino");
            		startActivity(intent);
            		
            	} catch(Exception e){

            	};
            }
            });
		final ImageView image2 = (ImageView) findViewById(R.id.imageView2);
		findViewById(R.id.imageView2).setOnClickListener(new OnClickListener(){
            public void onClick(View arg0) {
            	
                //Button boton = (Button)arg0;
            	try{
            		image2.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
            		Intent intent = new Intent(PantallaPrincipalActivity.this, BusquedaAvanzadaActivity.class);
            		intent.putExtra("modo", "buscadorAvanzado");
            		startActivity(intent);
            	} catch(Exception e){

            	};
            }
            });
		
	
		final ImageView image1 = (ImageView) findViewById(R.id.imageView1);	
	findViewById(R.id.imageView1).setOnClickListener(new OnClickListener(){
        public void onClick(View arg0) {
        	
            //Button boton = (Button)arg0;
        	try{
        		image1.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
        		Intent intent = new Intent(PantallaPrincipalActivity.this, CentrosActivity.class);
        		intent.putExtra("modo", "buscadorSimple");
        		startActivity(intent);
        	} catch(Exception e){

        	};
        }
        });
	
	final ImageView image4 = (ImageView) findViewById(R.id.imageView0);
	findViewById(R.id.imageView0).setOnClickListener(new OnClickListener(){
        public void onClick(View arg0) {
        	
            //Button boton = (Button)arg0;
        	try{
        		SharedPreferences prefs =
       			     getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
       			 
       			SharedPreferences.Editor editor = prefs.edit();
       			editor.putString("idioma", "es");
       			editor.commit();
        		image4.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
        		Locale locale = new Locale("es"); 
        		Locale.setDefault(locale);
        		Configuration config = new Configuration();
        		config.locale = locale;
        		Context context =getApplicationContext() ;
        		context.getApplicationContext().getResources().updateConfiguration(config, null);          		
        		Intent intent = new Intent(PantallaPrincipalActivity.this, PantallaPrincipalActivity.class);
        		startActivity(intent);      
        		finish();
        	} catch(Exception e){

        	};
        }
        });
	
	final ImageView image5 = (ImageView) findViewById(R.id.imageView00);
	findViewById(R.id.imageView00).setOnClickListener(new OnClickListener(){
        public void onClick(View arg0) {
        	
            //Button boton = (Button)arg0;
        	try{
        		SharedPreferences prefs =
          			     getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
          			 
          			SharedPreferences.Editor editor = prefs.edit();
          			editor.putString("idioma", "eu");
          			editor.commit();
        		image5.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
        		Locale locale = new Locale("eu"); 
        		Locale.setDefault(locale);
        		Configuration config = new Configuration();
        		config.locale = locale;
        		Context context =getApplicationContext() ;
        		context.getApplicationContext().getResources().updateConfiguration(config, null);          		
        		Intent intent = new Intent(PantallaPrincipalActivity.this, PantallaPrincipalActivity.class);
        		startActivity(intent);      
        		finish();
        	} catch(Exception e){

        	};
        }
        });
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pantalla_principal, menu);
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
		return super.onOptionsItemSelected(item);
	}
	
	//Controlar la conexion a internet
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

	    return cm.getActiveNetworkInfo() != null && 
	       cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_pantalla_principal, container, false);
			return rootView;
		}
	}
	
	


}
