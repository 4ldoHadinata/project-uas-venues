package com.example.gedungnikah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminFragment extends Fragment {
    int status;
    Button btnlogin;
    EditText username, password;

    public AdminFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        btnlogin = (Button) view.findViewById(R.id.btnLoginAdmin);
        username = (EditText) view.findViewById(R.id.EditTextUserAdmin);
        password = (EditText) view.findViewById(R.id.EditTextPassAdmin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username.getText().toString(), password.getText().toString());
            }
        });

        return view;
    }

    private void login(String username, String password){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username_admin", username);
            jsonObject.put("password_admin", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post("http://192.168.43.18/project-uas-venues/Server-Side/API/admin/login.php")
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
                                status = jsonObject.optInt("id_admin");

                                if (status != 0) {
                                    Context context = getActivity();
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("cekLogin", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("status", status);
                                    editor.apply();
                                    Intent pindah2 = new Intent(getActivity(), AdminListGedung.class);
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

}
