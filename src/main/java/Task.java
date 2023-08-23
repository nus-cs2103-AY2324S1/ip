public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public boolean markDone() {
        if(this.isDone){
            return false;
        }
        this.isDone = true;
        return true;
    }

    public boolean unmarkDone() {
        if(!this.isDone){
            return false;
        }
        this.isDone = false;
        return true;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

