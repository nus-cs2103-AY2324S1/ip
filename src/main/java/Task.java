public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private String fileGetStatusIcon(){
        return (isDone ? "Y" : "N");
    }

    private String getDescription(){
        return description;
    }

    public void taskDone(){
        isDone = true;
    }

    public void taskStatusFromFile(boolean status){
        isDone = status;
    }

    public void undoTask() {
        isDone = false;
    }

    @Override
    public String toString(){
        return " [" + getStatusIcon() + "] " + getDescription();
    }

    public String toFileString(){
        return "|" + fileGetStatusIcon() + "|" + getDescription();
    }
}
