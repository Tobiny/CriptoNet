package src;

import javafx.beans.property.SimpleStringProperty;

public class DetalleMantenimiento {


    private SimpleStringProperty dM;
    private SimpleStringProperty dS;
    private SimpleStringProperty cant;
    private SimpleStringProperty subt;

    public DetalleMantenimiento(String dM, String dS, String cant, String subt){
        this.dM = new SimpleStringProperty(dM);
        this.dS = new SimpleStringProperty(dS);
        this.cant = new SimpleStringProperty(cant);
        this.subt = new SimpleStringProperty(subt);
    }
    public String getdM() {
        return dM.get();
    }

    public SimpleStringProperty dMProperty() {
        return dM;
    }

    public void setdM(String dM) {
        this.dM.set(dM);
    }

    public String getdS() {
        return dS.get();
    }

    public SimpleStringProperty dSProperty() {
        return dS;
    }

    public void setdS(String dS) {
        this.dS.set(dS);
    }

    public String getCant() {
        return cant.get();
    }

    public SimpleStringProperty cantProperty() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant.set(cant);
    }

    public String getSubt() {
        return subt.get();
    }

    public SimpleStringProperty subtProperty() {
        return subt;
    }

    public void setSubt(String subt) {
        this.subt.set(subt);
    }

}
