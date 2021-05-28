package src;

import javafx.beans.property.SimpleStringProperty;

public class Mantenimiento {
    private SimpleStringProperty idM;
    private SimpleStringProperty idC;
    private SimpleStringProperty idS;
    private SimpleStringProperty fechaM;
    private SimpleStringProperty obsev;
    private SimpleStringProperty cant;
    private SimpleStringProperty subT;
    private SimpleStringProperty tot;

    public Mantenimiento(String idM, String idC, String idS, String fechaM, String obsev, String cant, String subT, String tot) {
        this.idM = new SimpleStringProperty(idM);
        this.idC = new SimpleStringProperty(idC);
        this.idS = new SimpleStringProperty(idS);
        this.fechaM = new SimpleStringProperty(fechaM);
        this.obsev = new SimpleStringProperty(obsev);
        this.cant = new SimpleStringProperty(cant);
        this.subT = new SimpleStringProperty(subT);
        this.tot = new SimpleStringProperty(tot);
    }

    public String getIdM() {
        return idM.get();
    }
    public String getIdC() {
        return idC.get();
    }
    public String getIdS() {
        return idS.get();
    }
    public String getFechaM() {
        return fechaM.get();
    }
    public String getObsev() {
        return obsev.get();
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

    public void setIdM(String idM) {
        this.idM.set(idM);
    }

    public void setIdC(String idC) {
        this.idC.set(idC);
    }

    public void setIdS(String idS) {
        this.idS.set(idS);
    }

    public void setFechaM(String fechaM) {
        this.fechaM.set(fechaM);
    }

    public void setObsev(String obsev) {
        this.obsev.set(obsev);
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
}
