package nav.naveduca;

import java.io.Serializable;
import java.util.List;

import nav.naveduca.R;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//Actividad que es el adaptador de CentrosActivity
//Desarrollado por Mikel San Martin Huarte
public class CentroAdapter extends BaseAdapter implements Serializable{
//	public Centro c;
	public List<Centro>l, ll ;
//	private String modo;
	
	public CentroAdapter(String modo, boolean[] ano, boolean[] naturaleza,boolean[] modelo,boolean[] servicios,boolean[] nivel ){
		//this.modo=modo;
		Centro c= new Centro("","","","",0,0);
		if (modo.equalsIgnoreCase("buscadorSimple") || modo.equalsIgnoreCase("camino")){
			l = c.getInfoCentros();
		}
		else if (modo.equalsIgnoreCase("buscadorAvanzado")){
			l = c.getBuscadorAvanzadoCentros(ano, naturaleza, modelo, servicios, nivel);
		}
		ll = l;
	}

	
//  Centro c= new Centro("","","","",0,0);
//  
//  List<Centro> l = c.getInfoCentros();
//  
//  List<Centro> ll = l;
  


	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.d("pruebasize", Integer.toString(l.size()));
		return l.size();
	}

	public Centro getItemOriginal(int position) {
		return ll.get(position);
	}
	
	@Override
	public Centro getItem(int position) {
		// TODO Auto-generated method stub
		return l.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//LayoutInflater inflater = (LayoutInflater) ListViewWithBaseAdapter.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext());
//		View rowView = inflater.inflate(R.layout.elem_lista, parent,false);
		
		if(convertView==null)
	    {
	        //LayoutInflater inflater = (LayoutInflater) ListViewWithBaseAdapter.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext());
	        convertView = inflater.inflate(R.layout.elem_lista, parent,false);
	    }
		
		TextView nombre = (TextView)convertView.findViewById(R.id.textView1);
        TextView localidad = (TextView)convertView.findViewById(R.id.textView2);

        Centro centro = l.get(position);

        nombre.setText(centro.getNombre());
        localidad.setText(centro.getLocalidad());

        return convertView;
		
	}

	
}
