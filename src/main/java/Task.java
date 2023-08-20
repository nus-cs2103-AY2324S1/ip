public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public void markTask(){
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }
}
