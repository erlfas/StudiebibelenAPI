package no.fasmer.studiebibelen.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class KapittelMap implements Serializable {

    private String bok;
    private Integer antall;
    private Map<String, Integer> kapittel2AntVers;

    public String getBok() {
        return bok;
    }

    public void setBok(String bok) {
        this.bok = bok;
    }

    public Integer getAntall() {
        return antall;
    }

    public void setAntall(Integer antall) {
        this.antall = antall;
    }

    public Map<String, Integer> getKapittel2AntVers() {
        if (kapittel2AntVers == null) {
            kapittel2AntVers = new HashMap<>();
        }
        
        return kapittel2AntVers;
    }

    public void setKapittel2AntVers(Map<String, Integer> kapittel2AntVers) {
        this.kapittel2AntVers = new HashMap<>(kapittel2AntVers);
    }
    
}
