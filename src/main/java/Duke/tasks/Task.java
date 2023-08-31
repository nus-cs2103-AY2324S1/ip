package Duke.tasks;


/***
 * Task class, superclass to Todo, Deadline and Event subclasses
 */
public class Task {
    protected String description;
    protected boolean isDone;

    //total number of Duke.tasks
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

    /***
     * reduce size of Duke.tasks and print remaining number
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