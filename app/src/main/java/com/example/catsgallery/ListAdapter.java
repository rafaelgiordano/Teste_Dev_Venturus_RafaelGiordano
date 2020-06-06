package com.example.catsgallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//Classe para os Adapter com o Viewholder
class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<CreateList> galleryList;
    private Context context;

    public ListAdapter(List<CreateList> galleryList, Context context) {
        this.galleryList = galleryList;
        this.context = context;
    }
    /*public ListAdapter(List<CreateList> galleryList, Context context) {
        this.galleryList = galleryList;
        this.context = context;
    }*/

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Toast.makeText(context, "entrou onCreateViewHolder", Toast.LENGTH_SHORT).show();
        //inflate da view para o layout criado.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        //Toast.makeText(context, "entrou onBindViewHolder", Toast.LENGTH_SHORT).show();
        //holder.title.setText(galleryList.get(position).getImage_title());
        //holder.img.setImageResource((galleryList.get(position).getImage_id()));
        //Picasso.with(context).load(galleryList.get(position).getImage_id()).resize(240, 120).into(holder.img);
        //Picasso.with(context).load(galleryList.get(position).getImage_id()).resize(240, 120).into(holder.img);


        /*
        * Criacao das view pelo adapter com Picasso
        *
        * Nao consegui fazer o adapter funcionar e criar as view para as fotos.
        * */
        //Picasso.with(context).load(galleryList.get(position).getLink()).resize(300, 200).into(holder.img);
        //holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);


        //zoom on click
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Image",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        //private TextView title;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //title = (TextView)itemView.findViewById(R.id.title);
            img = (ImageView)itemView.findViewById(R.id.img);
        }
    }
}
