package org.example;

public class Simbolo {
    private String nombre;
    private String tipo;
    private String valor;

    public Simbolo(String nombre, String tipo, String valor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }
}
