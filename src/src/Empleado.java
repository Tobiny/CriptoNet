package src;

import javafx.beans.property.SimpleStringProperty;

public class Empleado {
    private SimpleStringProperty idE;
    private SimpleStringProperty nom;

    public Empleado(String idE, String nom) {
        this.idE = new SimpleStringProperty(idE);
        this.nom = new SimpleStringProperty(nom);
    }

    public String getIdE() {
        return idE.get();
    }
    public String getNom() {
        return nom.get();
    }
    public void setIdE(String idE) {
        this.idE.set(idE);
    }
    public void setNom(String nom) {
        this.nom.set(nom);
    }
}
