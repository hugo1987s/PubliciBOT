package com.PubliciBot.DM;

/**
 * Created by Hugo on 11/06/2017.
 */
public class Medio {

    private TipoPost tipoPost;
    private String emailDestino;
    private String usuarioPerfilOrigen;
    private String contrasenaPerfilOrigen;
    private String perfilDestino;

    public Medio()
    {
        tipoPost = TipoPost.EMAIL;
        emailDestino = "";
        usuarioPerfilOrigen = "";
        contrasenaPerfilOrigen = "";
        perfilDestino = "";
    }

    public TipoPost getTipoPost() {
        return tipoPost;
    }

    public void setTipoPost(TipoPost tipoPost) {
        this.tipoPost = tipoPost;
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
