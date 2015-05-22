package nav.naveduca;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.w3c.dom.Document;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import nav.naveduca.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;


//Actividad para mostrar una ruta a pie y en coche al centro educativo
//Desarrollado por Mikel San Martin Huarte
public class CaminoActivity extends ActionBarActivity {
	GoogleMap googleMap;
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
		setContentView(R.layout.activity_camino);
		setTitle(getResources().getString(R.string.activitycamino));
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		try{
			initilizeMap();
			googleMap.setMyLocationEnabled(true);
			final Bundle bundle=getIntent().getExtras();
			final double latitud = bundle.getDouble("latitud");
			final double longitud = bundle.getDouble("longitud");
			final double latitudini, longitudini;
			if (googleMap.getMyLocation() != null) {
				latitudini = googleMap.getMyLocation().getLatitude();
				longitudini =googleMap.getMyLocation().getLongitude();
		    }
			else {
//				latitudini = last.getLatitude();
//				longitudini = last.getLongitude();

				LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
		        List<String> providers = lm.getProviders(true);

		        /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
		        Location l = null;
		        
		        for (int i=providers.size()-1; i>=0; i--) {
		                l = lm.getLastKnownLocation(providers.get(i));
		                if (l != null) break;
		        }
		        
		        if (l != null) {
		                latitudini = l.getLatitude();
		                longitudini = l.getLongitude();
		        }
		        else {
					//Si no se puede recuperar latitud y longitud
					//sacamos el camino desde la estacion de autobuses de Pamplona
					latitudini=42.811277200;
					longitudini=-1.644983200;
		        }
				
			}

			LatLng fromPosition = new LatLng(latitudini,longitudini);
			LatLng toPosition = new LatLng(latitud, longitud);

			HacerDireccion md = (HacerDireccion) new HacerDireccion().execute(Double.toString(fromPosition.latitude), Double.toString(fromPosition.longitude), Double.toString(toPosition.latitude), Double.toString(toPosition.longitude), HacerDireccion.MODE_DRIVING);
			
			
        	Boolean ready = md.getReady();
        	while(!ready){
        		Log.d("halo", ready.toString());
        		Thread.sleep(1000);
        		ready = md.getReady();
        	}
			
			Document doc = md.getDoc();
			ArrayList<LatLng> directionPoint = md.getDirection(doc);
			PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);

			for(int i = 0 ; i < directionPoint.size() ; i++) {          
			rectLine.add(directionPoint.get(i));
			
			}
			
			HacerDireccion md2 = (HacerDireccion) new HacerDireccion().execute(Double.toString(fromPosition.latitude), Double.toString(fromPosition.longitude), Double.toString(toPosition.latitude), Double.toString(toPosition.longitude), HacerDireccion.MODE_WALKING);
			
			
        	ready = md2.getReady();
        	while(!ready){
        		Log.d("halo", ready.toString());
        		Thread.sleep(1000);
        		ready = md2.getReady();
        	}
        	Log.d("llega", "llega");
			
			Document doc2 = md2.getDoc();
			ArrayList<LatLng> directionPoint2 = md2.getDirection(doc2);
			PolylineOptions rectLine2 = new PolylineOptions().width(3).color(Color.GREEN);

			for(int i = 0 ; i < directionPoint2.size() ; i++) {          
			rectLine2.add(directionPoint2.get(i));
			
			}

			CameraPosition cameraPosition = new CameraPosition.Builder().target(
	                new LatLng(latitud, longitud)).zoom(10).build();
			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			googleMap.addPolyline(rectLine);
			googleMap.addPolyline(rectLine2);

			 
			//Como llegar
			final ImageView image1 = (ImageView) findViewById(R.id.imageView1);
			findViewById(R.id.imageView1).setOnClickListener(new OnClickListener(){
	            public void onClick(View arg0) {
	            	
	                //Button boton = (Button)arg0;
	            	try{
	            		image1.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
	            		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
	            				Uri.parse("http://maps.google.com/maps?saddr="+latitudini+","+longitudini+"&daddr="+latitud+","+longitud));
	            				startActivity(intent);
	            		
	            	} catch(Exception e){

	            	};
	            }
	            });
			
			
		}catch(Exception e){
			Log.d("mapa", e.toString());
		}
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

	
	private void initilizeMap() {
	    if (googleMap == null) {
	        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
	                R.id.map)).getMap();

	        // check if map is created successfully or not
	        if (googleMap == null) {
	            Toast.makeText(getApplicationContext(),
	                    "Sorry! unable to create maps", Toast.LENGTH_SHORT)
	                    .show();
	        }
	    }
	}

}
