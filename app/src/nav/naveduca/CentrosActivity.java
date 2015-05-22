package nav.naveduca;


import java.io.Serializable;
import java.util.ArrayList;

import nav.naveduca.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;

//Actividad para mostrar los listados de centros
//Desarrollado por Mikel San Martin Huarte
	public class CentrosActivity extends ActionBarActivity implements Serializable{
        ListView listView;
        EditText et;
        int textlength = 0;
        private ArrayList<Centro> array_sort = new ArrayList<Centro>();
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_centros);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
            try{
            
            	ProgressDialog dialog = ProgressDialog.show(CentrosActivity.this, "","Loading. Please wait...", true);
            	dialog.show();
            	setTitle(getResources().getString(R.string.actividad));
            	
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
			 
			tabs.setCurrentTab(0);
			

			
            
            
            final Context a = this.getApplicationContext();
            //Log.d("modo", "Halo"+ bundle.getString("modo"));
            
            final CentroAdapter adaptador;
            
    		if (bundle.getString("modo").equalsIgnoreCase("buscadorSimple") ){
    			adaptador = new CentroAdapter(bundle.getString("modo"), null, null,null,null,null);
    		}
    		else if (bundle.getString("modo").equalsIgnoreCase("camino") ){
    			adaptador = new CentroAdapter(bundle.getString("modo"), null, null,null,null,null);
    			setTitle(getResources().getString(R.string.destino));
    		}
    		else {
    			adaptador = new CentroAdapter(bundle.getString("modo"), (boolean[])getIntent().getExtras().getSerializable("ano"), (boolean[])getIntent().getExtras().getSerializable("naturaleza"),(boolean[])getIntent().getExtras().getSerializable("modelo"),(boolean[])getIntent().getExtras().getSerializable("servicios"),(boolean[])getIntent().getExtras().getSerializable("nivel"));
    		}

        	dialog.cancel();
            
             
//            final CentroAdapter adaptador = new CentroAdapter();
            listView = (ListView) findViewById(R.id.listView1);
            et = (EditText) findViewById(R.id.EditText01);
            listView.setAdapter(adaptador);
            
			
            
            //Para el texto introducido en el buscador
            et.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    // Abstract Method of TextWatcher Interface.
                }
     
                public void beforeTextChanged(CharSequence s, int start, int count,
                        int after) {
                    // Abstract Method of TextWatcher Interface.
                }
     
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    textlength = et.getText().length();
                    array_sort.clear();
                     
                    
                    for (int i = 0; i < adaptador.ll.size(); i++) {
                        if (textlength <= adaptador.getItemOriginal(i).getNombre().length()) {
                            if ((adaptador.getItemOriginal(i).getNombre().toLowerCase().contains(et.getText().toString().toLowerCase()))) {
                                array_sort.add(adaptador.getItemOriginal(i));
                                //Log.d("lat",(String.valueOf(adaptador.getItemOriginal(i).getLatitud())));
                            }
                            
                            else {
                            	if (textlength <= adaptador.getItemOriginal(i).getLocalidad().length()){
                            	
                            		if ((adaptador.getItemOriginal(i).getLocalidad().toLowerCase().contains(et.getText().toString().toLowerCase()))) {
                                    array_sort.add(adaptador.getItemOriginal(i));
                                }
                            }}
                        }
                        

                    }
                     adaptador.l = array_sort;
                     listView.setAdapter(adaptador);
                }
            });
//            final ImageView image1 = (ImageView) findViewById(R.id.imageView1);
//			findViewById(R.id.imageView1).setOnClickListener(new OnClickListener(){
//	            public void onClick(View arg0) {
//	            	
//	                //Button boton = (Button)arg0;
//	            	try{
//	            		image1.setColorFilter(0xFF6E6E6E, PorterDuff.Mode.MULTIPLY);
//	            		Intent intent = new Intent(CentrosActivity.this, BusquedaMapaActivity.class);
//	            		//Bundle bundle = new Bundle();
//	            		//bundle.putSerializable("array", (Serializable) adaptador.l);
//	            		intent.putExtra("array", (Serializable) adaptador.l) ;
//	            	    startActivity(intent);
//	            		
//	            	} catch(Exception e){
//	            		Log.d("excepcion",e.toString());
//	            	};
//	            }
//	            });
        
            
            
            // ListView Item Click Listener
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                        long arg3) {

                    Centro centro = adaptador.getItem(arg2);

                    
                    if (bundle.getString("modo").equalsIgnoreCase("camino")){
                    //Toast.makeText(a, centro.getId(),Toast.LENGTH_LONG).show();
            		Intent intent = new Intent(CentrosActivity.this, CaminoActivity.class);
            		intent.putExtra("id", centro.getId());
            		intent.putExtra("nombre", centro.getNombre());
            		intent.putExtra("latitud", centro.getLatitud());
            		intent.putExtra("longitud", centro.getLongitud());
            		startActivity(intent);
                    }
                    else{
                		Intent intent = new Intent(CentrosActivity.this, InfoCentrosActivity.class);
                		intent.putExtra("id", centro.getId());
                		intent.putExtra("nombre", centro.getNombre());
                		intent.putExtra("latitud", centro.getLatitud());
                		intent.putExtra("longitud", centro.getLongitud());
                		startActivity(intent);
                    }

                }
            });

			tabs.setOnTabChangedListener(new OnTabChangeListener() {
			    @Override
			    public void onTabChanged(String tabId) {
			        //Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
			        if (tabId.equals("mitab2")){
		            	try{;
	            		Intent intent = new Intent(CentrosActivity.this, BusquedaMapaActivity.class);
	            		//Bundle bundle = new Bundle();
	            		//bundle.putSerializable("array", (Serializable) adaptador.l);
	            		intent.putExtra("array", (Serializable) adaptador.l) ;
	            		intent.putExtra("modo", bundle.getString("modo"));
	            	    startActivity(intent);
	            	    tabs.setCurrentTab(0);
	            		
	            	} catch(Exception e){
	            		Log.d("excepcion",e.toString());
	            	};
			        }
			    }
			    
			});
        }
            catch(Exception e){
            	Log.d("---------->",e.toString());
            }
        }
        
    	public static class PlaceholderFragment extends Fragment {

    		public PlaceholderFragment() {
    		}

    		@Override
    		public View onCreateView(LayoutInflater inflater, ViewGroup container,
    				Bundle savedInstanceState) {
    			View rootView = inflater.inflate(
    					R.layout.fragment_centros, container, false);
    			return rootView;
    		}
    	}
        
		public void onBackPressed() {
			NavUtils.navigateUpFromSameTask(this);
		}




    }






