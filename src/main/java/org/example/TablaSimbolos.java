package org.example;

import java.util.HashMap;
import java.util.Map;

public class TablaSimbolos {
    private Map<String, Simbolo> simbolos;

    public TablaSimbolos() {
        this.simbolos = new HashMap<>();
    }

    public void agregarSimbolo(String nombre, String tipo, String valor) {
        simbolos.put(nombre, new Simbolo(nombre, tipo, valor));
    }

    public Map<String, Simbolo> getSimbolos() {
        return simbolos;
    }
}
