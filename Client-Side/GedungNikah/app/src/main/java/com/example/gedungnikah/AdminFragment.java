package com.example.gedungnikah;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AdminFragment extends Fragment {

    Button btnlogin;

    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        btnlogin = (Button) view.findViewById(R.id.btnLoginAdmin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah2 = new Intent(getActivity(), AdminListGedung.class);
                startActivity(pindah2);
            }
        });

        return view;
    }
}
