import Exceptions.EmptyDescriptionException;

abstract public class Task{
    private boolean isDone;
    private String description;

    Task(String s) throws EmptyDescriptionException {
        if(s.isBlank()){
            throw new EmptyDescriptionException();
        }
        this.description = s;
        this.isDone = false;
    }
    public void markDone(){
        this.isDone = true;
    }
    public void markUndone(){
        this.isDone = false;
    }
    public String getDesc(){
        return description;
    }
    public boolean getStatus(){
        return isDone;
    }
    public String toString(){
        return "[" + (isDone?"X":" ") + "] " + description;
    }
}