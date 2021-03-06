package no.fasmer.studiebibelen.model;

import java.io.Serializable;
import java.util.List;

public class Kapittel implements Serializable {
    
    private String bok;
    private String kapittel;
    private List<Vers> vers;

    public String getBok() {
        return bok;
    }

    public void setBok(String bok) {
        this.bok = bok;
    }

    public String getKapittel() {
        return kapittel;
    }

    public void setKapittel(String kapittel) {
        this.kapittel = kapittel;
    }

    public List<Vers> getVers() {
        return vers;
    }

    public void setVers(List<Vers> vers) {
        this.vers = vers;
    }
    
}
