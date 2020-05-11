package com.example.gedungnikah;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PengelolaFragment extends Fragment {

    Button btnLogin, btnRegister;

    public PengelolaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengelola, container, false);

        btnLogin = (Button) view.findViewById(R.id.btnLoginPengelola);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah2 = new Intent(getActivity(), Pengelola.class);
                startActivity(pindah2);
            }
        });


        return view;
    }
}
