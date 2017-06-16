package com.PubliciBot.Services;

import com.PubliciBot.DAO.Interfaces.Task;
import java.util.Stack;

public class Tasker extends Thread{

//SINGLETON
    private static Tasker tasker;
    private static Stack<Task> tasks;

    @Override
    public void run() {
        super.run();
        boolean run=true;
        while(run){
            if(tasks.size()>0){
               Task task=tasks.pop();
               task.execute();

            }


        }


    }

    public static void addTask(Task task){
        tasks.push(task);
    }

    public static Tasker getTasker(){
        if(tasker==null){
            tasker=new Tasker();
        }
        return tasker;
    }



}
