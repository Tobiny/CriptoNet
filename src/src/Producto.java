package src;

import javafx.beans.property.SimpleStringProperty;

public class Producto {
    private SimpleStringProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty costo;
    private SimpleStringProperty unidad;

    public Producto(String id, String nom, String costo, String unidad){
        this.id = new SimpleStringProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.costo = new SimpleStringProperty(costo);
        this.unidad = new SimpleStringProperty(unidad);
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public void setCosto(String costo) {
        this.costo.set(costo);
    }

    public void setUnidad(String unidad) {
        this.unidad.set(unidad);
    }

    public String getId() {
        return id.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getCosto() {
        return costo.get();
    }

    public String getUnidad() {
        return unidad.get();
    }
}
