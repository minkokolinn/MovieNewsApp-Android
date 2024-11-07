package com.example.admin.movienewsapp.models;

import java.util.List;

public class MyCast
{
    private String id;

    private List<Cast> cast;



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", cast = "+cast+"]";
    }
}

			