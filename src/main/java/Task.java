public class Task {
    protected String description;
    protected boolean isDone;

    protected static int size = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        size = size + 1;


    }

    public static int getSize(){
        return size;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setAction(String action){
        String s = "";
        if(action.equals("mark")){
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        else if(action.equals("unmark")){
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + this);

    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon()+ "] " + this.description;
    }

}