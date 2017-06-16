package com.PubliciBot.Services;

import com.PubliciBot.DAO.Interfaces.Task;
import com.PubliciBot.DAO.Neodatis.DAONeodatis;
import com.PubliciBot.DM.Campana;
import com.PubliciBot.DM.Post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Stack;

public class Tasker extends Thread{

//SINGLETON
    private static Tasker tasker;
    private static Stack<Task> tasks;
    private static boolean run=false;
    private static ArrayList<Sender> senders;


    private Tasker (){
        tasks=new Stack<>();
        senders =new ArrayList<>();
        int cantsenders=1;
        for(int i=0; i<1;i++) {
            Sender sender = new Sender(this);
            senders.add(sender);
        }
    }


    @Override
    public void run() {

        boolean run=true;

        while (run) {
            System.out.print("Tasker Buscando Tareas...");
            buscaTareas();
            for(Sender sender : senders){
                sender.run();
            }


            try {
                Thread.sleep(60000); //se duerme y vuelve a mandar cada 1 minuto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public  void buscaTareas(){
        DAONeodatis daoNeodatis= new DAONeodatis();
        Instant NOW=Instant.now();
        long nowinSeconds=NOW.getEpochSecond();
        ArrayList<Post> possibleTasks=(ArrayList<Post>)daoNeodatis.obtenerTodos(Post.class);
        System.out.print(possibleTasks);
        for (Post p:possibleTasks
             ) {
            if(p.getFechaInicio().before(Date.from(NOW))){ //Si el Post Todavia Esta vigente (supero la fecha de inicio)

               if(p.getFechaCaducidad().after((Date.from(NOW)))){  //Si no supero la fecha de caducidad


                   if(p.getFechaUltimaEjecucion()!=null) {
                       long ultimaejSeconds = p.getFechaUltimaEjecucion().toInstant().getEpochSecond();   //Convertir ultima ejecucion a segundos

                       if(Math.abs(nowinSeconds-ultimaejSeconds)>p.getAccion().getPeriodicidadSegundos()){  //SI LE TOCA EJECUTARSE (PASO EL COOLDOWN )
                           tasks.add(p);
                       }
                   }

                   else{

                       tasks.add(p);
                   }



                }


            }

        }



    }

    public Task giveMeaTask(){
        if(tasks.size()!=0) {
            return tasks.pop();
        }
        return null;
    }

    public void stopTasker(){
        run=false;
    }

    public void addTask(Task task){
        tasks.push(task);
    }

    public static Tasker getTasker(){
        if(tasker==null){
            tasker=new Tasker();
        }
        return tasker;
    }



}
