package com.techinc.common.fileupload.Dialogflow.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;


import com.fasterxml.jackson.annotation.*;

public class Parameters {

    private List<String> any;
    private String clases;
    private String gmail;
    private String lenguajes;
    private String eliminar;


    @JsonProperty("any")
    public List<String> getAny() { return any; }
    @JsonProperty("any")
    public void setAny(List<String> value) { this.any = value; }

    @JsonProperty("Clases")
    public String getClases() { return clases; }
    @JsonProperty("Clases")
    public void setClases(String value) { this.clases = value; }

    @JsonProperty("Gmail")
    public String getGmail() { return gmail; }
    @JsonProperty("Gmail")
    public void setGmail(String value) { this.gmail = value; }

    @JsonProperty("Lenguajes")
    public String getLenguajes() { return lenguajes; }
    @JsonProperty("Lenguajes")
    public void setLenguajes(String value) { this.lenguajes = value; }

    @JsonProperty("Eliminar")
    public String getEliminar() { return eliminar; }
    @JsonProperty("Eliminar")
    public void setEliminar(String value) { this.eliminar = value; }


}

