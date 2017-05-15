package com.PubliciBot.DM;

/**
 * Created by Hugo on 14/05/2017.
 * key: un nombre para ese recurso
 * valor: un valor para la key, como puede ser una URL, un objeto de menu, etc
 */
public class Privilegios {
    private String key;
    private String valor;

    public Privilegios()
    {
        new Privilegios("","");
    }

    public Privilegios(String key, String valor) {
        this.key = key;
        this.valor = valor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
