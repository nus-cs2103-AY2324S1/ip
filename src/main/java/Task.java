public class Task {
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

    public String toString(){
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public String getName(){
        return this.name;
    }
}
