package com.PubliciBot.DM;

/**
 * Created by Hugo on 11/06/2017.
 */
public class Medio {

    private TipoMedio tipoMedio;
    private String emailDestino;
    private String usuarioPerfilOrigen;
    private String contrasenaPerfilOrigen;
    private String perfilDestino;

    public Medio()
    {
        tipoMedio = TipoMedio.EMAIL;
        emailDestino = "";
        usuarioPerfilOrigen = "";
        contrasenaPerfilOrigen = "";
        perfilDestino = "";
    }

    public TipoMedio getTipoMedio() {
        return tipoMedio;
    }

    public void setTipoMedio(TipoMedio tipoMedio) {
        this.tipoMedio = tipoMedio;
    }

    public String getEmailDestino() {
        return emailDestino;
    }

    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    public String getUsuarioPerfilOrigen() {
        return usuarioPerfilOrigen;
    }

    public void setUsuarioPerfilOrigen(String usuarioPerfilOrigen) {
        this.usuarioPerfilOrigen = usuarioPerfilOrigen;
    }

    public String getContrasenaPerfilOrigen() {
        return contrasenaPerfilOrigen;
    }

    public void setContrasenaPerfilOrigen(String contrasenaPerfilOrigen) {
        this.contrasenaPerfilOrigen = contrasenaPerfilOrigen;
    }

    public String getPerfilDestino() {
        return perfilDestino;
    }

    public void setPerfilDestino(String perfilDestino) {
        this.perfilDestino = perfilDestino;
    }
}
