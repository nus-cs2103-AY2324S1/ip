public class Task {
    public static int count = 1;
    String description;
    boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    public void markAsDone(){
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

}
