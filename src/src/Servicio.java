package src;

import javafx.beans.property.SimpleStringProperty;

public class Servicio {
    public SimpleStringProperty idS;
    public SimpleStringProperty nomS;
    public SimpleStringProperty preS;

    public Servicio(String idS, String nomS, String preS){
        this.idS = new SimpleStringProperty(idS);
        this.nomS = new SimpleStringProperty(nomS);
        this.preS = new SimpleStringProperty(preS);
    }
    public String getIdS() {
        return idS.get();
    }
    public void setIdS(String idS) {
        this.idS.set(idS);
    }
    public String getNomS() {
        return nomS.get();
    }
    public void setNomS(String nomS) {
        this.nomS.set(nomS);
    }
    public String getPreS() {
        return preS.get();
    }
    public void setPreS(String preS) {
        this.preS.set(preS);
    }
}
