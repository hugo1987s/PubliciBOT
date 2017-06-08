package com.PubliciBot.DM;

import com.vaadin.ui.Image;

/**
 * Created by Hugo on 22/05/2017.
 */
public class Mensaje {
    private String textoMensaje;
    private Image imagenMensaje;

    public Mensaje(String textoMensaje)
    {
        this(textoMensaje, null);
    }

    public Mensaje( Image imagenMensaje)
    {
        this(null, imagenMensaje);
    }

    public Mensaje(String textoMensaje, Image imagenMensaje) {
        this.textoMensaje = textoMensaje;
        this.imagenMensaje = imagenMensaje;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public Image getImagenMensaje() {
        return imagenMensaje;
    }

    @Override
    public String toString(){
        return "Texto mensaje "+ this.textoMensaje;
    }
}
