package com.gustavo.mobdoc_v0;


public class ProntuarioUpload {
    public  String name;
    public String desc;

    public String getName() {
        return name;
    }

    public String getDes()
    {
        return desc;
    }

    public ProntuarioUpload(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public ProntuarioUpload(){

    }
}
