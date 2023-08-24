public class Task{
    private boolean isDone;
    private String description;

    Task(String s){
        this.description = s;
        this.isDone = false;
    }
    public void markDone(){
        this.isDone = true;
    }
    public void markUndone(){
        this.isDone = false;
    }
    public String toString(){
        return "[" + (isDone?"X":" ") + "] " + description;
    }
}