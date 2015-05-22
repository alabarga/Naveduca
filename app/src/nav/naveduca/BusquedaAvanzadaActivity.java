package nav.naveduca;

import java.util.Locale;

import nav.naveduca.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

//Actividad para hacer la busqueda avanzada
//Desarrollado por Mikel San Martin Huarte
public class BusquedaAvanzadaActivity extends ActionBarActivity {

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
		WindowManager.LayoutParams params = getWindow().getAttributes(); 
		params.flags |= LayoutParams.FLAG_KEEP_SCREEN_ON; 
		params.screenBrightness = 0; 
		getWindow().setAttributes(params);


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_busqueda_avanzada);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		final Bundle bundle=getIntent().getExtras();
		
		setTitle(getResources().getString(R.string.title_activity_busqueda_avanzada));
		
		final MultiSpinner multiSpinner1 = (MultiSpinner) findViewById(R.id.multispinner1);
		final MultiSpinner multiSpinner2 = (MultiSpinner) findViewById(R.id.multispinner2);
		final MultiSpinner multiSpinner3 = (MultiSpinner) findViewById(R.id.multispinner3);
		final MultiSpinner multiSpinner4 = (MultiSpinner) findViewById(R.id.multispinner4);
		final MultiSpinner multiSpinner5 = (MultiSpinner) findViewById(R.id.multispinner5);

		prefs =
			     getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
			 
		if (prefs.getBoolean("ano1", true)){
			multiSpinner1.setSelected(0);
		}
		if (prefs.getBoolean("ano2", false)){
			multiSpinner1.setSelected(1);
		}
		if (prefs.getBoolean("natu1", false)){
			multiSpinner2.setSelected(0);
		}
		if (prefs.getBoolean("natu2", false)){
			multiSpinner2.setSelected(1);
		}
		if (prefs.getBoolean("natu3", false)){
			multiSpinner2.setSelected(2);
		}
		if (prefs.getBoolean("mode1", false)){
			multiSpinner3.setSelected(0);
		}

		if (prefs.getBoolean("mode2", false)){
			multiSpinner3.setSelected(1);
		}
		if (prefs.getBoolean("mode3", false)){
			multiSpinner3.setSelected(2);
		}
		if (prefs.getBoolean("mode4", false)){
			multiSpinner3.setSelected(3);
		}
		if (prefs.getBoolean("mode5", false)){
			multiSpinner3.setSelected(4);
		}
		if (prefs.getBoolean("mode6", false)){
			multiSpinner3.setSelected(5);
		}
		if (prefs.getBoolean("mode7", false)){
			multiSpinner3.setSelected(6);
		}
		if (prefs.getBoolean("mode8", false)){
			multiSpinner3.setSelected(7);
		}
		if (prefs.getBoolean("mode9", false)){
			multiSpinner3.setSelected(8);
		}
		if (prefs.getBoolean("mode10", false)){
			multiSpinner3.setSelected(9);
		}
		if (prefs.getBoolean("mode11", false)){
			multiSpinner3.setSelected(10);
		}
		if (prefs.getBoolean("mode12", false)){
			multiSpinner3.setSelected(11);
		}
		if (prefs.getBoolean("mode13", false)){
			multiSpinner3.setSelected(12);
		}
		if (prefs.getBoolean("mode14", false)){
			multiSpinner3.setSelected(13);
		}
		if (prefs.getBoolean("mode15", false)){
			multiSpinner3.setSelected(14);
		}
		if (prefs.getBoolean("ser1", false)){
			multiSpinner4.setSelected(0);
		}
		if (prefs.getBoolean("ser2", false)){
			multiSpinner4.setSelected(1);
		}
		if (prefs.getBoolean("ser3", false)){
			multiSpinner4.setSelected(2);
		}
		if (prefs.getBoolean("niv1", false)){
			multiSpinner5.setSelected(0);
		}
		if (prefs.getBoolean("niv2", false)){
			multiSpinner5.setSelected(1);
		}
		if (prefs.getBoolean("niv3", false)){
			multiSpinner5.setSelected(2);
		}
		if (prefs.getBoolean("niv4", false)){
			multiSpinner5.setSelected(3);
		}
		if (prefs.getBoolean("niv5", false)){
			multiSpinner5.setSelected(4);
		}
		if (prefs.getBoolean("niv6", false)){
			multiSpinner5.setSelected(5);
		}
		if (prefs.getBoolean("niv7", false)){
			multiSpinner5.setSelected(6);
		}
		if (prefs.getBoolean("niv8", false)){
			multiSpinner5.setSelected(7);
		}
		if (prefs.getBoolean("niv9", false)){
			multiSpinner5.setSelected(8);
		}
		if (prefs.getBoolean("niv10", false)){
			multiSpinner5.setSelected(9);
		}
		if (prefs.getBoolean("niv11", false)){
			multiSpinner5.setSelected(10);
		}
		if (prefs.getBoolean("niv12", false)){
			multiSpinner5.setSelected(11);
		}
		if (prefs.getBoolean("niv13", false)){
			multiSpinner5.setSelected(12);
		}
		if (prefs.getBoolean("niv14", false)){
			multiSpinner5.setSelected(13);
		}
		if (prefs.getBoolean("niv15", false)){
			multiSpinner5.setSelected(14);
		}
		if (prefs.getBoolean("niv16", false)){
			multiSpinner5.setSelected(15);
		}
		multiSpinner1.performClick();
		multiSpinner1.getDialog().getButton(Dialog.BUTTON_POSITIVE).performClick();
		multiSpinner2.performClick();
		multiSpinner2.getDialog().getButton(Dialog.BUTTON_POSITIVE).performClick();
		multiSpinner3.performClick();
		multiSpinner3.getDialog().getButton(Dialog.BUTTON_POSITIVE).performClick();
		multiSpinner4.performClick();
		multiSpinner4.getDialog().getButton(Dialog.BUTTON_POSITIVE).performClick();
		multiSpinner5.performClick();
		multiSpinner5.getDialog().getButton(Dialog.BUTTON_POSITIVE).performClick();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		params.screenBrightness=1;

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener(){
            public void onClick(View arg0) {
            	
                //Button boton = (Button)arg0;
            	try{
            		boolean[] prueba1 = multiSpinner1.getSelected();
            		boolean[] prueba2 = multiSpinner2.getSelected();
            		boolean[] prueba3 = multiSpinner3.getSelected();
            		boolean[] prueba4 = multiSpinner4.getSelected();
            		boolean[] prueba5 = multiSpinner5.getSelected();
//            		Log.d("valores", "hola"+ prueba1[0]);
            		SharedPreferences prefs =
            			     getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
            			 
            			SharedPreferences.Editor editor = prefs.edit();
            			editor.putBoolean("ano1", multiSpinner1.getSelectedI(0));
            			editor.putBoolean("ano2", multiSpinner1.getSelectedI(1));
            			editor.putBoolean("natu1", multiSpinner2.getSelectedI(0));
            			editor.putBoolean("natu2", multiSpinner2.getSelectedI(1));
            			editor.putBoolean("natu3", multiSpinner2.getSelectedI(2));
            			editor.putBoolean("mode1", multiSpinner3.getSelectedI(0));
            			editor.putBoolean("mode2", multiSpinner3.getSelectedI(1));
            			editor.putBoolean("mode3", multiSpinner3.getSelectedI(2));
            			editor.putBoolean("mode4", multiSpinner3.getSelectedI(3));
            			editor.putBoolean("mode5", multiSpinner3.getSelectedI(4));
            			editor.putBoolean("mode6", multiSpinner3.getSelectedI(5));
            			editor.putBoolean("mode7", multiSpinner3.getSelectedI(6));
            			editor.putBoolean("mode8", multiSpinner3.getSelectedI(7));
            			editor.putBoolean("mode9", multiSpinner3.getSelectedI(8));
            			editor.putBoolean("mode10", multiSpinner3.getSelectedI(9));
            			editor.putBoolean("mode11", multiSpinner3.getSelectedI(10));
            			editor.putBoolean("mode12", multiSpinner3.getSelectedI(11));
            			editor.putBoolean("mode13", multiSpinner3.getSelectedI(12));
            			editor.putBoolean("mode14", multiSpinner3.getSelectedI(13));
            			editor.putBoolean("mode15", multiSpinner3.getSelectedI(14));
            			editor.putBoolean("ser1", multiSpinner4.getSelectedI(0));
            			editor.putBoolean("ser2", multiSpinner4.getSelectedI(1));
            			editor.putBoolean("ser3", multiSpinner4.getSelectedI(2));
            			editor.putBoolean("niv1", multiSpinner5.getSelectedI(0));
            			editor.putBoolean("niv2", multiSpinner5.getSelectedI(1));
            			editor.putBoolean("niv3", multiSpinner5.getSelectedI(2));
            			editor.putBoolean("niv4", multiSpinner5.getSelectedI(3));
            			editor.putBoolean("niv5", multiSpinner5.getSelectedI(4));
            			editor.putBoolean("niv6", multiSpinner5.getSelectedI(5));
            			editor.putBoolean("niv7", multiSpinner5.getSelectedI(6));
            			editor.putBoolean("niv8", multiSpinner5.getSelectedI(7));
            			editor.putBoolean("niv9", multiSpinner5.getSelectedI(8));
            			editor.putBoolean("niv10", multiSpinner5.getSelectedI(9));
            			editor.putBoolean("niv11", multiSpinner5.getSelectedI(10));
            			editor.putBoolean("niv12", multiSpinner5.getSelectedI(11));
            			editor.putBoolean("niv13", multiSpinner5.getSelectedI(12));
            			editor.putBoolean("niv14", multiSpinner5.getSelectedI(13));
            			editor.putBoolean("niv15", multiSpinner5.getSelectedI(14));
            			editor.putBoolean("niv16", multiSpinner5.getSelectedI(15));
            			editor.commit();
            		Intent intent = new Intent(BusquedaAvanzadaActivity.this, CentrosActivity.class); 
            		intent.putExtra("modo", bundle.getString("modo"));
            		intent.putExtra("ano", prueba1);
            		intent.putExtra("naturaleza", prueba2);
            		intent.putExtra("modelo", prueba3);
            		intent.putExtra("servicios", prueba4);
            		intent.putExtra("nivel", prueba5);
            		startActivity(intent);
            	} catch(Exception e){

            	};
            }
            });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.busqueda_avanzada, menu);
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
					R.layout.fragment_busqueda_avanzada, container, false);
			return rootView;
		}
	}
	
	public void onBackPressed() {
		NavUtils.navigateUpFromSameTask(this);
	}

}
