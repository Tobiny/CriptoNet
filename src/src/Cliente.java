package src;

import javafx.beans.property.SimpleStringProperty;

public class Cliente {
    private SimpleStringProperty idC;
    private SimpleStringProperty idE;
    private SimpleStringProperty nom;
    private SimpleStringProperty dom;
    private SimpleStringProperty rfc;
    private SimpleStringProperty tipoM;

    public Cliente(String idC, String idE, String nom, String dom, String rfc, String tipoM){
        this.idC = new SimpleStringProperty(idC);
        this.idE = new SimpleStringProperty(idE);
        this.nom = new SimpleStringProperty(nom);
        this.dom = new SimpleStringProperty(dom);
        this.rfc = new SimpleStringProperty(rfc);
        this.tipoM = new SimpleStringProperty(tipoM);
    }

    public String getIdC() {
        return idC.get();
    }
    public void setIdC(String idC) {
        this.idC.set(idC);
    }
    public String getIdE() {
        return idE.get();
    }
    public void setIdE(String idE) {
        this.idE.set(idE);
    }
    public String getNom() {
        return nom.get();
    }
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    public String getDom() {
        return dom.get();
    }
    public void setDom(String dom) {
        this.dom.set(dom);
    }
    public String getRfc() { return rfc.get(); }
    public void setRfc(String rfc) {
        this.rfc.set(rfc);
    }
    public String getTipoM() {
        return tipoM.get();
    }
    public void setTipoM(String tipoM) {
        this.tipoM.set(tipoM);
    }
}
