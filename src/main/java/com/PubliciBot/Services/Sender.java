package com.PubliciBot.Services;

import com.PubliciBot.DAO.Interfaces.Task;
import java.util.Stack;

public class Sender extends Thread{
    Tasker tasker;
    private boolean run=false;

    public Sender(Tasker tasker){
        this.tasker=tasker;
    }


    @Override
    public void run() {
        super.run();
        run=true;

        while (run&&tasker.isAlive()){
            Task task=Tasker.getTasker().giveMeaTask();
            if(task!=null)
                task.execute();

        }
    }


    public void stopSender(){
        this.run=false;
    }

}
