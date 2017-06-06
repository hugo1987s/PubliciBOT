package com.PubliciBot.DM;

/**
 * Created by Hugo on 19/05/2017.
 */

public class Usuario {
    private String mail;
    private String contrasena;
    private Rol rol;

    public Usuario()
    {
        new Usuario("", "", new Rol());

    }

    public Usuario(String mail, String contrasena, Rol rol) {
        this.mail = mail;
        this.contrasena = contrasena;
        this.rol = rol;
    }



    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object user){
        if(this == user) return true;
        if(user == null) return false;

        if(user instanceof Usuario) {
            Usuario other = (Usuario) user;
            if (this.mail == null || other.mail == null)
                return false;
            if (this.contrasena == null || other.contrasena == null)
                return false;
            if (this.rol == null || other.rol == null)
                return false;
            return  this.mail.equals(other.mail) &&
                    this.contrasena.equals(other.contrasena);
        }
        return false;
    }

    @Override
    public String toString(){
        return "Usuario:"     +
                "\nMail: "    +this.mail+
                "\nRol: "     +this.rol.toString();
    }
}
