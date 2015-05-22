package nav.naveduca;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

import nav.naveduca.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//Actividad para mostrar la localizacion de un centro educativo
//Desarrollado por Mikel San Martin Huarte
public class MapaActivity extends ActionBarActivity {
	
	// Google Map
    private GoogleMap googleMap;

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
		setContentView(R.layout.activity_mapa);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
	    
		

		
		
		final Bundle bundle=getIntent().getExtras();
				
				setTitle(bundle.getString("nombre"));
				
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
				 
				tabs.setCurrentTab(2);
				
				tabs.setOnTabChangedListener(new OnTabChangeListener() {
				    @Override
				    public void onTabChanged(String tabId) {
				        //Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
				        if (tabId.equals("mitab1")){
		            		Intent intent = new Intent(MapaActivity.this, InfoCentrosActivity.class);
		            		intent.putExtra("id", bundle.getString("id"));
		            		intent.putExtra("nombre", bundle.getString("nombre"));
		            		intent.putExtra("latitud", bundle.getDouble("latitud"));
		            		intent.putExtra("longitud", bundle.getDouble("longitud"));
		            		startActivity(intent);
		            		finish();
				        }
				        if (tabId.equals("mitab2")){
		            		Intent intent = new Intent(MapaActivity.this, InfoAdicionalActivity.class);
		            		intent.putExtra("id", bundle.getString("id"));
		            		intent.putExtra("nombre", bundle.getString("nombre"));
		            		intent.putExtra("latitud", bundle.getDouble("latitud"));
		            		intent.putExtra("longitud", bundle.getDouble("longitud"));
		            		startActivity(intent);
		            		finish();
				        }
				    }
				});

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		
		try {
            // Loading map
            initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("mapa",e.toString());
        }
		

				

 

		
		
		// latitude and longitude
		final double latitude =bundle.getDouble("latitud") ;
		final double longitude =bundle.getDouble("longitud") ;
		 
		// create marker
		MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(bundle.getString("nombre"));
		 
		// adding marker
		googleMap.addMarker(marker);
		CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(12).build();
		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
		//Latitud y longitud iniciales
		
		googleMap.setMyLocationEnabled(true);
		final double latitudini, longitudini;
		if (googleMap.getMyLocation() != null) {
			latitudini = googleMap.getMyLocation().getLatitude();
			longitudini =googleMap.getMyLocation().getLongitude();
	    }
		else {
//			latitudini = last.getLatitude();
//			longitudini = last.getLongitude();

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

		//Como llegar
		final ImageView image1 = (ImageView) findViewById(R.id.imageView1);
		findViewById(R.id.imageView1).setOnClickListener(new OnClickListener(){
            public void onClick(View arg0) {
            	
                //Button boton = (Button)arg0;
            	try{
            		image1.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
            		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
            				Uri.parse("http://maps.google.com/maps?saddr="+latitudini+","+longitudini+"&daddr="+latitude+","+longitude));
            				startActivity(intent);
            		
            	} catch(Exception e){

            	};
            }
            });
		
	}
	


	
/**
 * function to load map. If map is not created it will create it for you
 * */
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

@Override
protected void onResume() {
    super.onResume();
    initilizeMap();
}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapa, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_mapa, container,
					false);
			return rootView;
		}
	}

}
