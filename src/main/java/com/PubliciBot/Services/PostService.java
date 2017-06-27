package com.PubliciBot.Services;

import com.PubliciBot.DAO.Interfaces.Task;
import com.PubliciBot.DAO.Neodatis.DAONeodatis;
import com.PubliciBot.DM.AccionPublicitaria;
import com.PubliciBot.DM.Campana;
import com.PubliciBot.DM.Post;

import java.util.ArrayList;

public class PostService {



    public void agregarPost(Campana campana, AccionPublicitaria accion){

        System.out.println(" Caducidad: "+ campana.calcularCaducidad());
        Post post=new Post(campana.getFechaInicio(),campana.calcularCaducidad(),accion,campana.getMensaje());
        campana.getPosts().add(post);
        // System.out.println("Campana: "+this.nombre+" Duracion: "+duracion+" "+unidadMedida+" lo que equivale a :"+ duracion*unidadMedida.unidadASegundos()+" Segundos");
        Tasker.addTask(post);
    }

    public void generarPosts(Campana campana){
        eliminarPosts(campana);

        campana.setPosts(new ArrayList<Post>());

        for(AccionPublicitaria a :campana.getAcciones())
            agregarPost(campana,a);
    }

    public void eliminarPosts(Campana campana){
        for(int i=0 ;i<campana.getPosts().size();i++) {
            Post post = campana.getPosts().get(i);
            campana.borrarPost(post);
            Tasker.getTasker().removeTask(post);
        }


    }

    public void eliminarPosts(Campana campana,AccionPublicitaria accionPublicitaria){
        for(int i=0 ;i<campana.getPosts().size();i++) {
            Post post = campana.getPosts().get(i);
            if (post.getAccion().equals(accionPublicitaria)) {
                campana.borrarPost(post);
                Tasker.getTasker().removeTask(post);
            }
        }

    }

}
