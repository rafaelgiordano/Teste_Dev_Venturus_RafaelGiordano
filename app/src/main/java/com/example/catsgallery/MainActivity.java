package com.example.catsgallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    //variaveis controle interface
    ListAdapter listAdapter;
    ImageView imageView;
    RecyclerView recyclerView;

    //variavel guarda links que sao realmente .jpg
    List<String > lista_jpg = new ArrayList<>();

    //variavel e funcao chamada da API
    private OkHttpClient httpClient;
    private void fetchData() {
        httpClient = new OkHttpClient.Builder().build();
    }

    public static final String TAG = "ERROR SAIDA";
    //Lista populada para enviar ao Adapter
    List<CreateList> createLists = new ArrayList<CreateList>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Mapeamento interface e variaveis mainActivity
        * */
        recyclerView = (RecyclerView)findViewById(R.id.images_gallery);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),4);
        recyclerView.setLayoutManager(layoutManager);
        imageView = findViewById(R.id.imageView);


        //fetch and populate https://api.imgur.com/3/gallery/search/?q=cats
        fetchData();
        Request request = new Request.Builder()
                .url("https://api.imgur.com/3/gallery/search/?q=cats")
                .header("Authorization","Client-ID 1ceddedc03a5d71")
                //.header("User-Agent","Cat Gallery")
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "An error has occurred " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //aqui volta a resposta do request com os dados da API

                JSONObject data = null;
                try {
                    data = new JSONObject(response.body().string());
                    JSONArray items = data.getJSONArray("data");
                    for(int i=0; i< 60;i++) {
                        JSONObject item = items.getJSONObject(i);
                        CreateList createList = new CreateList();
                        //createList.setImage_title(image_titles[i]);
                        //createList.setImage_id(Integer.parseInt(item.getString("id")));

                        //guarda o id da foto
                        createList.setImage_id(item.getString("id"));

                        //guarda o link da foto
                        createList.setLink(item.get("link").toString());

                        /*testa se o link for do tipo .jpg entao armazena na variavel lista_jpg
                          pois a varios links que nao sao fotos na API, como gifs e mp4
                        */

                        if(item.get("link").toString().contains(".jpg") ) {

                            lista_jpg.add(createList.getLink());

                        }
                        //guarda lista de valores para mandar ao adapter
                        createLists.add(createList);
                    }
                    //chamada da funcao que printa a foto com o Picasso
                    Draw(lista_jpg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //acrescenta e notifica mudancas nos dados do adapter
                listAdapter = new ListAdapter( createLists, getApplicationContext());
                listAdapter.notifyDataSetChanged();


            }
        });
        listAdapter = new ListAdapter( createLists, getApplicationContext());
        recyclerView.setAdapter(listAdapter);

    }
    //funcao de chamada para imprimir as fotos
    public void Draw(final List<String> links){
        //Picasso.with(this).load(link_try).into(imageView);
        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i < links.size(); i++) {
                    Picasso.with(MainActivity.this)
                            .load(links.get(i)).resize(400, 300).centerCrop()
                            .into(imageView);
                }
            }
        });
    }

}
