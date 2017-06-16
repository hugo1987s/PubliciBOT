package com.PubliciBot.Services;

import com.PubliciBot.DAO.Interfaces.Task;
import java.util.Stack;

public class Tasker extends Thread{

//SINGLETON
    private static Tasker tasker;
    private static Stack<Task> tasks;
    private static boolean run=false;

    private Tasker (){
        tasks=new Stack<>();

    }


    @Override
    public void run() {
        super.run();
        boolean run=true;

        while (run) {

            buscaCampanas();


            try {
                Thread.sleep(60000); //se duerme y vuelve a mandar cada 1 minuto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void buscaCampanas(){
        //ACA DEBERIA LEVANTAR LAS CAMPAÑAS Y REVISAR CUALES SON LAS QUE ESTAN EN RANGO
        //LUEGO UNA VEZ QUE ENCUENTRA LAS QUE ESTAN VIGENTES REVISA SUS ACCIONES
        // LUEGO POR CADA ACCION REVISA CUANDO FUE SU ULTIMA FECHA DE EJECUCION
        //SI LA FECHA ACTUAL --- FECCHA DE EJECUCION DE LA ACCION > PERIODICIDAD DE LA ACCION

        //convierte la accion en un TASK

        //lo agrega a la pila



    }

    public  Task giveMeaTask(){
        return tasks.pop();
    }

    public  void stopTasker(){
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
