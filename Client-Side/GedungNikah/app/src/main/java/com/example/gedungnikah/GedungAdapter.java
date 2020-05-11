package com.example.gedungnikah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class GedungAdapter extends ArrayAdapter<Gedung> {
    private List<Gedung> gedungs;
    private Context context;
    customButtonListener buttonListener;

    public interface customButtonListener {
        public void onButtonClickListener(int position, int id);
    }

    public void setCustomButtonListener(customButtonListener listener){
        this.buttonListener = listener;
    }

    public GedungAdapter(Context context, int resource, List<Gedung> gedungs){
        super(context,resource,gedungs);
        this.context = context;
        this.gedungs = gedungs;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listViewItem = inflater.inflate(R.layout.activity_list_gedung,null,true);
        TextView textViewNama = (TextView) listViewItem.findViewById(R.id.Nama);
        TextView textViewAlamat = (TextView) listViewItem.findViewById(R.id.Alamat);
        Button buttonDetail = (Button) listViewItem.findViewById(R.id.btnDetail);

        final Gedung gedung = gedungs.get(position);

        textViewNama.setText(gedung.getNama_gedung());
        textViewAlamat.setText(gedung.getAlamat_gedung());

        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonListener != null){
                    buttonListener.onButtonClickListener(position,gedung.getId_gedung());
                }
            }
        });

        return listViewItem;
    }

}
