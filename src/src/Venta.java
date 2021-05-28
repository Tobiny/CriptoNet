package src;

import javafx.beans.property.SimpleStringProperty;

public class Venta {
    private SimpleStringProperty idV;
    private SimpleStringProperty idC;
    private SimpleStringProperty idP;
    private SimpleStringProperty cant;
    private SimpleStringProperty subT;
    private SimpleStringProperty tot;
    private SimpleStringProperty date;

    public Venta(String idV, String idC, String date, String idP, String cant, String subT, String tot){
        this.idV = new SimpleStringProperty(idV);
        this.idC = new SimpleStringProperty(idC);
        this.idP = new SimpleStringProperty(idP);
        this.cant = new SimpleStringProperty(cant);
        this.subT = new SimpleStringProperty(subT);
        this.tot = new SimpleStringProperty(tot);
        this.date = new SimpleStringProperty(date);
    }

    public String getIdV() {
        return idV.get();
    }
    public String getIdC() {
        return idC.get();
    }
    public String getIdP() {
        return idP.get();
    }
    public String getCant() {
        return cant.get();
    }
    public String getSubT() {
        return subT.get();
    }
    public String getTot() {
        return tot.get();
    }
    public String getDate() {
        return date.get();
    }

    public void setIdV(String idV) {
        this.idV.set(idV);
    }
    public void setIdC(String idC) {
        this.idC.set(idC);
    }
    public void setIdP(String idP) {
        this.idP.set(idP);
    }
    public void setCant(String cant) {
        this.cant.set(cant);
    }
    public void setSubT(String subT) {
        this.subT.set(subT);
    }
    public void setTot(String tot) {
        this.tot.set(tot);
    }
    public void setDate(String date) {
        this.date.set(date);
    }
}
