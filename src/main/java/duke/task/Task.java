package duke.task;

/**
 * Represents a task to be added by the user.
 */

public class Task {
    protected String name ;
    protected boolean status ;

    //Constructors
    public Task(String name){
        this.name = name ;
        this.status = false;
    }
    public boolean isTaskCompleted(){
        return this.status;
    }

    public void markTaskCompleted(){
        this.status = true;
    }
    public void markTaskUncompleted(){
        this.status = false;
    }

    @Override
    public String toString(){
        if (this.status){
            return "[X] " + this.name ;
        } else {
            return "[ ] " + this.name ;
        }
    }
}
