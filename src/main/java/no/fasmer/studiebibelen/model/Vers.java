package no.fasmer.studiebibelen.model;

import java.io.Serializable;

public class Vers implements Serializable {
    
    private String vers;
    private String tekst;

    public String getVers() {
        return vers;
    }

    public void setVers(String vers) {
        this.vers = vers;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
    
}
