package nav.naveduca;

import java.util.Locale;

import nav.naveduca.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

//Actividad para elegir el idioma de la aplicacion
//Desarrollado por Mikel San Martin Huarte
//No se utiliza en la ultima version
public class IdiomaActivity extends ActionBarActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
			setContentView(R.layout.activity_idioma);
			
			setTitle(getResources().getString(R.string.idioma));
			
			final ImageView image1 = (ImageView) findViewById(R.id.imageView1);
			findViewById(R.id.imageView1).setOnClickListener(new OnClickListener(){
	            public void onClick(View arg0) {
	            	
	                //Button boton = (Button)arg0;
	            	try{
	            		image1.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
	            		Locale locale = new Locale("es"); 
	            		Locale.setDefault(locale);
	            		Configuration config = new Configuration();
	            		config.locale = locale;
	            		Context context =getApplicationContext() ;
	            		context.getApplicationContext().getResources().updateConfiguration(config, null);          		
	            		Intent intent = new Intent(IdiomaActivity.this, PantallaPrincipalActivity.class);
	            		startActivity(intent);      
	            		finish();
	            	} catch(Exception e){
	            		//System.out.print(e.toString());
	            		//Log.d("halo", e.toString());
	            		
	            		Log.d("halo", e.getMessage());
	            	};
	            }
	            });
			
			final ImageView image2 = (ImageView) findViewById(R.id.imageView2);
			findViewById(R.id.imageView2).setOnClickListener(new OnClickListener(){
	            public void onClick(View arg0) {
	            	
	                //Button boton = (Button)arg0;
	            	try{
	            		image2.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
	            		Locale locale = new Locale("eu"); 
	            		Locale.setDefault(locale);
	            		Configuration config = new Configuration();
	            		config.locale = locale;
	            		Context context =getApplicationContext() ;
	            		context.getApplicationContext().getResources().updateConfiguration(config, null);
	            		Intent intent = new Intent(IdiomaActivity.this, PantallaPrincipalActivity.class);
	            		startActivity(intent);    
	            		finish();
	            	} catch(Exception e){
	            		//System.out.print(e.toString());
	            		//Log.d("halo", e.toString());
	            		
	            		Log.d("halo", e.getMessage());
	            	};
	            }
	            });

		}
		public void onBackPressed() {
			Toast.makeText(this, getResources().getString(R.string.select), Toast.LENGTH_SHORT).show();
		}
		
		public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case android.R.id.home:

	            Toast.makeText(this, getResources().getString(R.string.select), Toast.LENGTH_SHORT).show();
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	}



