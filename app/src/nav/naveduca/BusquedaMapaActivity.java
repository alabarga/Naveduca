package nav.naveduca;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import nav.naveduca.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//Actividad para mostrar la localizacion un conjunto de centros educativos
//Desarrollado por Mikel San Martin Huarte
public class BusquedaMapaActivity extends ActionBarActivity {
	
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
		setContentView(R.layout.activity_busqueda_mapa);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
	    
		

		
		
		final Bundle bundle=getIntent().getExtras();
				
				//setTitle(bundle.getString("nombre"));
				
				//Tabs
				Resources res = getResources();
				
				final TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
				tabs.setup();
				 
				TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
				spec.setContent(R.id.tab1);
				spec.setIndicator(getResources().getString(R.string.modo_lista),
				    res.getDrawable(android.R.drawable.ic_dialog_dialer));
				tabs.addTab(spec);
				 
				spec=tabs.newTabSpec("mitab2");
				spec.setContent(R.id.tab2);
				spec.setIndicator(getResources().getString(R.string.modo_mapa),
				    res.getDrawable(android.R.drawable.ic_dialog_map));
				tabs.addTab(spec);
				 
				tabs.setCurrentTab(1);
				
				tabs.setOnTabChangedListener(new OnTabChangeListener() {
				    @Override
				    public void onTabChanged(String tabId) {
				        //Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
				        if (tabId.equals("mitab1")){
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
		
		try{
		
		final List<Centro> l = extracted(bundle);
		

		for (int i = 0; i < l.size(); i++) {
		// create marker
			final MarkerOptions marker;
			marker = new MarkerOptions().position(new LatLng(l.get(i).getLatitud(), l.get(i).getLongitud())).title(l.get(i).getNombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
		marker.snippet(String.valueOf(i));
		// adding marker
		googleMap.addMarker(marker);
		}
		
		 googleMap.setInfoWindowAdapter(new InfoWindowAdapter(){
			 
			 public View getInfoWindow(Marker arg0){
				 return null;
			 }
			 
			 public View getInfoContents(Marker arg0){
				 View v = getLayoutInflater().inflate(R.layout.marker, null);
				 TextView centro = (TextView)v.findViewById(R.id.loc);
				 centro.setText(arg0.getTitle());
				 TextView localidad = (TextView)v.findViewById(R.id.loc1);
				 localidad.setText(l.get(Integer.parseInt(arg0.getSnippet())).getLocalidad());
				 return v;
			 }
			 
			 
		 });
		
		
		CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(l.get(0).getLatitud(), l.get(0).getLongitud())).zoom(9).build();
		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener(){
			public void onInfoWindowClick(Marker marker){
		        if (bundle.getString("modo").equalsIgnoreCase("camino")){
		        //Toast.makeText(a, centro.getId(),Toast.LENGTH_LONG).show();
				Intent intent = new Intent(BusquedaMapaActivity.this, CaminoActivity.class);
				intent.putExtra("id", l.get(Integer.parseInt(marker.getSnippet())).getId());
				intent.putExtra("nombre", l.get(Integer.parseInt(marker.getSnippet())).getNombre());
				intent.putExtra("latitud", l.get(Integer.parseInt(marker.getSnippet())).getLatitud());
				intent.putExtra("longitud", l.get(Integer.parseInt(marker.getSnippet())).getLongitud());
				startActivity(intent);
		        }
		        else{
		    		Intent intent = new Intent(BusquedaMapaActivity.this, InfoCentrosActivity.class);
		    		intent.putExtra("id", l.get(Integer.parseInt(marker.getSnippet())).getId());
		    		intent.putExtra("nombre", l.get(Integer.parseInt(marker.getSnippet())).getNombre());
		    		intent.putExtra("latitud", l.get(Integer.parseInt(marker.getSnippet())).getLatitud());
		    		intent.putExtra("longitud", l.get(Integer.parseInt(marker.getSnippet())).getLongitud());
		    		startActivity(intent);
		        }
				//Log.d("nombre", l.get(Integer.parseInt(marker.getSnippet())).getNombre());
			}
		});

		
		}catch(Exception e){
			Log.d("exmap", e.toString());
		}
		
	}




	private List<Centro> extracted(final Bundle bundle) {
		return (List<Centro>) bundle.getSerializable("array");
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