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
    public Task markTaskCompleted(){
        return new Task(this.name, true);
    }
    public Task markTaskUncompleted(){
        return new Task(this.name, false);
    }
}
