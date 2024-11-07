package com.example.admin.movienewsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.movienewsapp.R;
import com.example.admin.movienewsapp.delegates.FilmDelegate;
import com.example.admin.movienewsapp.models.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 9/27/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{
    Context ctxt;
    List<Results> results;

    public MovieAdapter(Context ctxt, List<Results> results) {
        this.ctxt = ctxt;
        this.results = results;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li=LayoutInflater.from(ctxt);
        View v=li.inflate(R.layout.movie_sample,parent,false);
        MovieHolder mh=new MovieHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Results r=results.get(position);
        Picasso.with(ctxt).load("http://image.tmdb.org/t/p/w500/"+r.getPoster_path())
                .placeholder(R.drawable.forest)
                .into(holder.iv);
        holder.tvName.setText(r.getTitle());
        holder.tvIMDB.setText(r.getVote_average());
        holder.tvRelease.setText(r.getRelease_date());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvName,tvIMDB,tvRelease;
        public MovieHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.img_ms);
            tvName=itemView.findViewById(R.id.tv_name_ms);
            tvIMDB=itemView.findViewById(R.id.tv_imdb_ms);
            tvRelease=itemView.findViewById(R.id.tv_release_ms);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delegate.onFilmClick(results.get(getAdapterPosition()));
                }
            });
        }
    }

    FilmDelegate delegate;
    public void setOnFilmClick(FilmDelegate delegate){
        this.delegate=delegate;
    }

}
