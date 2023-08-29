package task;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private boolean isDone;
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    @Override
    public String toString(){
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public String getName(){
        return this.name;
    }
}
