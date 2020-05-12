package com.example.gedungnikah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PengelolaFragment extends Fragment {

    Button btnLogin, btnRegister;
    int status;
    EditText username, password;

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

        username = (EditText) view.findViewById(R.id.EditTextUsername);
        password = (EditText) view.findViewById(R.id.EditTextPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLoginPengelola);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username.getText().toString(), password.getText().toString());
            }
        });

        btnRegister = (Button) view.findViewById(R.id.btnRegisterPengelola);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(username.getText().toString(), password.getText().toString());
                Toast.makeText(getActivity(), "Register Berhasil", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private void login(String username, String password){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nama_pengelola", username);
            jsonObject.put("password_pengelola", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("http://192.168.43.18/project-uas-venues/Server-Side/API/pengelola/login.php")
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("result");
                            for (int i=0; i<=jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                status = jsonObject.optInt("id_pengelola");

                                if (status != 0) {
                                    Context context = getActivity();
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("cekLogin", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("status", status);
                                    editor.apply();
                                    Intent pindah2 = new Intent(getActivity(), Pengelola.class);
                                    startActivity(pindah2);
                                } else {
                                    Toast.makeText(getActivity(),"Username dan Password salah",Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(getActivity(),""+error.getErrorDetail(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void register(String username, String password){
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nama_pengelola", username);
            jsonObject.put("password_pengelola", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("http://192.168.43.18/project-uas-venues/Server-Side/API/pengelola/create_pengelola.php")
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
