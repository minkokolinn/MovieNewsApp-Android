package com.example.admin.movienewsapp.models;

import java.util.List;

public class MyVideo
{
    private String id;

    private List<Trailer> results;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", results = "+results+"]";
    }
}

		