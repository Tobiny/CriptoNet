package src;

import javafx.beans.property.SimpleStringProperty;

public class DetalleVenta {
    private SimpleStringProperty IdV;
    private SimpleStringProperty IdP;
    private SimpleStringProperty cant;
    private SimpleStringProperty subt;

    public DetalleVenta(String IdV, String IdP, String cant, String subt){
        this.IdV = new SimpleStringProperty(IdV);
        this.IdP = new SimpleStringProperty(IdP);
        this.cant = new SimpleStringProperty(cant);
        this.subt = new SimpleStringProperty(subt);
    }

    public String getIdV() {return IdV.get();}
    public String getIdP() {return IdP.get();}
    public String getCant() {return cant.get();}
    public String getSubt() {return subt.get();}

    public void setIdV(String idV) {this.IdV.set(idV);}
    public void setIdP(String idP) {this.IdP.set(idP);}
    public void setCant(String cant) {this.cant.set(cant);}
    public void setSubt(String subt) {this.subt.set(subt);}

}
