package duke.tasks;


/***
 * Task class, superclass to Todo, Deadline and Event subclasses
 */
public class Task {
    protected String description;
    protected boolean isDone;

    //total number of tasks
    protected static int size = 0;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        size = size + 1;
    }

    public String getDescription(){
        return this.description;
    }

    public static int getSize(){
        return size;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /***
     * Sets isDone to true or false depending on user input
     * @param action string mark or unmark
     */
    public String setAction(String action){
        String result = "";
        if(action.equals("mark")){
            this.isDone = true;
            result = "Nice! I've marked this task as done:\n";
        } else if(action.equals("unmark")){
            this.isDone = false;
            result = "OK, I've marked this task as not done yet:\n";
        }
        result += "  " + this;
        return result;
    }

    /***
     * reduce size of tasks and print remaining number
     */
    public void delete(){
        size = size -1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + size + " tasks in the list.");

    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon()+ "] " + this.description;
    }

}