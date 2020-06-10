package com.google.sps.data;

public class Comment{
    private final long id;
    private final String text;
    private final long timestamp;
    private final String email;

    public Comment(long id, String text, long timestamp, String email){
        this.id=id;
        this.text=text;
        this.timestamp=timestamp;
        this.email=email;

    }

    public String toString(){
        return email+": "+text;
    }
}