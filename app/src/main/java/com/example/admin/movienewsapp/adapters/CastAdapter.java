package com.example.admin.movienewsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.movienewsapp.R;
import com.example.admin.movienewsapp.models.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 9/28/2018.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder>{
    Context ctxt;
    List<Cast> casts;

    public CastAdapter(Context ctxt, List<Cast> casts) {
        this.ctxt = ctxt;
        this.casts = casts;
    }

    @NonNull
    @Override
    public CastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li=LayoutInflater.from(ctxt);
        View v=li.inflate(R.layout.cast_sample,parent,false);
        CastHolder ch=new CastHolder(v);
        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull CastHolder holder, int position) {
        Cast c=casts.get(position);
        holder.tv.setText(c.getName());
        Picasso.with(ctxt).load("http://image.tmdb.org/t/p/w500/"+c.getProfile_path()).into(holder.civ);
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class CastHolder extends RecyclerView.ViewHolder{
        CircleImageView civ;
        TextView tv;
        public CastHolder(View itemView) {
            super(itemView);
            civ=itemView.findViewById(R.id.civ_cs);
            tv=itemView.findViewById(R.id.tv_name_cs);
        }
    }
}
