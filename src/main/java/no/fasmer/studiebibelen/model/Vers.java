package no.fasmer.studiebibelen.model;

import java.io.Serializable;

public class Vers implements Serializable {
    
    private int vers;
    private String tekst;

    public int getVers() {
        return vers;
    }

    public void setVers(int vers) {
        this.vers = vers;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
}
