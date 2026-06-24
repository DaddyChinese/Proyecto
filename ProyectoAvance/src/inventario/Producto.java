package inventario;

import java.io.Serializable;

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String categoria;
    private int stock;
    private double precio;

    public Producto(String nombre, String categoria, int stock, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format(
                "%-15s %-15s Stock:%d Precio:S/. %.2f",
                nombre,
                categoria,
                stock,
                precio
        );
    }
}