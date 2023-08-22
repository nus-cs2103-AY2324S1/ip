public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return this.description;
    }

    public void setAction(String action){
        if(action.equals("mark")){
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done: ");
        }
        else if(action.equals("unmark")){
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet: ");
        }
    }
}