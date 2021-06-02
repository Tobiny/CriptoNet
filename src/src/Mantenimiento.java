package src;

import javafx.beans.property.SimpleStringProperty;

public class Mantenimiento {
    private SimpleStringProperty idM;
    private SimpleStringProperty idC;
    private SimpleStringProperty fechaM;
    private SimpleStringProperty obsev;
    private SimpleStringProperty tot;

    public Mantenimiento(String idM, String idC, String fechaM, String obsev, String tot) {
        this.idM = new SimpleStringProperty(idM);
        this.idC = new SimpleStringProperty(idC);
        this.fechaM = new SimpleStringProperty(fechaM);
        this.obsev = new SimpleStringProperty(obsev);
        this.tot = new SimpleStringProperty(tot);
    }

    public String getIdM() {
        return idM.get();
    }
    public String getIdC() {
        return idC.get();
    }
    public String getFechaM() {
        return fechaM.get();
    }
    public String getObsev() {
        return obsev.get();
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


    public void setFechaM(String fechaM) {
        this.fechaM.set(fechaM);
    }

    public void setObsev(String obsev) {
        this.obsev.set(obsev);
    }

    public void setTot(String tot) {
        this.tot.set(tot);
    }
}
