package duke.task;

public class Task {
    protected String name ;
    protected boolean status ;

    //Constructors
    public Task(String name){
        this.name = name ;
        this.status = false;
    }

    private Task(String name, boolean status){
        this.name = name ;
        this.status = status ;
    }

    //Getters
    public String getTaskName(){
        return this.name;
    }
    public boolean isTaskCompleted(){
        return this.status;
    }

    // Setters
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
