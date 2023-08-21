public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name){
        this.name = name;
        this.isDone = false;
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

    public String toString() {
        return this.getStatusIcon() + " " + this.name;
    }
}
