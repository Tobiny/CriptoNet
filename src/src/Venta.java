package src;

import javafx.beans.property.SimpleStringProperty;

public class Venta {
    private SimpleStringProperty idV;
    private SimpleStringProperty idC;
    private SimpleStringProperty tot;
    private SimpleStringProperty date;

    public Venta(String idV, String idC, String tot, String date){
        this.idV = new SimpleStringProperty(idV);
        this.idC = new SimpleStringProperty(idC);
        this.tot = new SimpleStringProperty(tot);
        this.date = new SimpleStringProperty(date);
    }

    public String getIdV() {
        return idV.get();
    }
    public String getIdC() {
        return idC.get();
    }
    public String getTot() {
        return tot.get();
    }
    public String getDate() {
        return date.get();
    }

    public void setIdV(String idV) {this.idV.set(idV);}
    public void setIdC(String idC){this.idC.set(idC);}
    public void setTot(String tot) {this.tot.set(tot);}
    public void setDate(String date) {this.date.set(date);}
}
