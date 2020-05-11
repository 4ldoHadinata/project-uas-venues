package com.example.gedungnikah;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiConnect extends AsyncTask<String,String,String> {
    private ProgressDialog pd;
    private Context context;
    private Gedung gedung;
    private int action;

    public static final int INSERT_ACTION = 1;
    public static final int UPDATE_ACTION = 2;
    public static final int DELETE_ACTION = 3;
    public static final int SYNC_ACTION = 4;

    protected void onPreExecute(){
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        this.action = Integer.parseInt(params[0]);
        switch (action){
            case ApiConnect.UPDATE_ACTION :
                return updateAPI();
            case ApiConnect.DELETE_ACTION :
                return deleteAPI();
            case ApiConnect.INSERT_ACTION:
                return insertAPI();
            case ApiConnect.SYNC_ACTION:
                return syncAPI();
            default:
                return "something is went wrong";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (pd.isShowing()){
            pd.dismiss();
        }
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }

    private String insertAPI(){
        HttpURLConnection connection = null;
        if (gedung == null){
            return "gedung is null";
        }
        try{
            URL url = new URL("http://10.39.8.93/project-uas-venues/Server-Side/API/gedung/create.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = gedung.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String updateAPI(){
        HttpURLConnection connection = null;
        if (gedung == null){
            return "gedung is null";
        }
        try{
            URL url = new URL("http://10.39.8.93/project-uas-venues/Server-Side/API/gedung/update.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = gedung.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String deleteAPI(){
        HttpURLConnection connection = null;
        if (gedung == null){
            return "gedung is null";
        }
        try{
            URL url = new URL("http://10.39.8.93/project-uas-venues/Server-Side/API/gedung/delete.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = gedung.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String JSONDecoderSync(String in){
        try {
            JSONObject reader = new JSONObject(in);
            JSONArray records = reader.getJSONArray("records");
            String result = "";
            UserListGedung.db.emptyData();
            for(int i=0;i<records.length();i++){
                JSONObject items = records.getJSONObject(i);
                int id_gedung = items.getInt("id_gedung");
                String nama_gedung = items.getString("nama_gedung");
                String alamat_gedung = items.getString("alamat_gedung");
                int harga_sewa_gedung = items.getInt("harga_sewa_gedung");
                int luas_gedung = items.getInt("luas_gedung");
                int daya_tampung = items.getInt("daya_tampung");
                String kontak = items.getString("kontak");

                boolean isSynced = UserListGedung.db.insertDataDetail(id_gedung,nama_gedung,alamat_gedung,harga_sewa_gedung,luas_gedung,daya_tampung,kontak);
                if(isSynced == true)
                    result = "Data Synced";
                else
                    result = "Data Not Synced";
            }
            Log.d("result",result);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("result","NULL");
            return null;
        }
    }

    private String syncAPI(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://10.39.8.93/project-uas-venues/Server-Side/API/gedung/read.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }
            return JSONDecoderSync(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ApiConnect(Context context){
        this.context = context;
    }

    public ApiConnect(Context context,Gedung gedung){
        this.context = context;
        this.gedung = gedung;
    }

}
