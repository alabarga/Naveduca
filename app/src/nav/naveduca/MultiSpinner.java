package nav.naveduca;

import nav.naveduca.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//Spinner con seleccion multiple
//Inspirado por http://stackoverflow.com/a/6022474/1521064
//Desarrollado por Mikel San Martin Huarte
public class MultiSpinner extends Spinner {

    private CharSequence[] entries;
    private boolean[] selected;
    public MultiSpinnerListener listener;
    private AlertDialog dialog;
    

    public MultiSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiSpinner);
        entries = a.getTextArray(R.styleable.MultiSpinner_android_entries);
        if (entries != null) {
            selected = new boolean[entries.length]; // false-filled by default
        }
        a.recycle();
    }

    private OnMultiChoiceClickListener mOnMultiChoiceClickListener = new OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            selected[which] = isChecked;
        }
    };

    private DialogInterface.OnClickListener mOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // build new spinner text & delimiter management
            StringBuffer spinnerBuffer = new StringBuffer();
            for (int i = 0; i < entries.length; i++) {
                if (selected[i]) {
                    spinnerBuffer.append(entries[i+1]);
                    spinnerBuffer.append(", ");
                }
            }

            // Remove trailing comma
            if (spinnerBuffer.length() > 2) {
                spinnerBuffer.setLength(spinnerBuffer.length() - 2);
            }

            // display new text
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_item,
                    new String[] { spinnerBuffer.toString() });
            //spinnerBuffer.
            //adapter.remove(getItem(0));
            setAdapter(adapter);

            if (listener != null) {
                listener.onItemsSelected(selected);
            }
            
            // hide dialog
            dialog.dismiss();
        }
    };

    @Override
    public boolean performClick() {
    	CharSequence[] entries2= new CharSequence[entries.length-1];
    	for (int i = 1; i < entries.length; i++) {
            
            	entries2[i-1]=entries[i];
            
        }
        dialog = new AlertDialog.Builder(getContext())
                .setMultiChoiceItems(entries2, selected, mOnMultiChoiceClickListener)
                .setPositiveButton(android.R.string.ok, mOnClickListener)
                .show();
        return true;
    }

    public AlertDialog getDialog(){
    	return dialog;
    }
    public void setMultiSpinnerListener(MultiSpinnerListener listener) {
        this.listener = listener;
    }

    public interface MultiSpinnerListener {
        public void onItemsSelected(boolean[] selected);
    }
    
    public boolean[] getSelected(){
    	return selected;
    }
    public boolean[] setSelected(int i){
    	selected[i]=true;
    	return selected;
    }
    public void setSelect(boolean[] select){
    	selected = select;
    }
    public boolean getSelectedI(int i){
    	return selected[i];
    }
}
