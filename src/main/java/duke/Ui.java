package duke;

public class Ui {
    public Ui(){}

    private void printLines(){
        System.out.println("____________________________________________________________");
    }

    /**
     * Tells the user that he has entered in valid query for the specified type
     * @param taskType string, the type of task
     */
    public void showError(String taskType){
        this.printLines();
        System.out.println("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
        this.printLines();
    }

    /**
     * Tells the user the number of tasks left
     * @param i  the number of tasks left
     */
    public void showNumTasks(int i){
        this.printLines();
        System.out.println("Now you have "+ String.valueOf(i) + " tasks in the list.");
        this.printLines();
    }

    /**
     * Sends the user a welcome message
     */
    public void hello() {
        String logo =
                "___________  __________  __________  ||   \n"
                        +"|         | |         | |         |  ||      \n"
                        +"-----------  ---------- ----------   ||  \n"
                        + "    ||         ||          ||        ||       \n"
                        + "    ||         ||          ||        ||           \n"
                        + "    ||         ||          ||        ||           \n"
                        + "    ||         ||          ||        ||       \n"
                        + "    ||      __________  __________   ||      \n"
                        + "    ||      |         | |         |  ______ \n"
                        + "    ||      ----------  ----------   ______    \n";
        this.printLines();
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");
        this.printLines();
    }

    /**
     * says Goodbye to the user
     */
    public void goodbye() { System.out.println("Bye. Hope to see you again soon!");}

    /**
     * Notifies the user the tasks in list and the number of tasks
     * @param tasks the TaskList of tasks
     */
    public void tasksInList(TaskList tasks) {
        this.printLines();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task t: tasks.getList()){
            System.out.println(String.valueOf(i) + "." + t.toString());
            i++;
        }
        this.printLines();
    }

    /**
     * Notifies the user that a task has been marked as done
     * @param item the item marked as done
     */
    public void taskDone(Task item) {
        this.printLines();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(item.toString());
        this.printLines();
    }

    /**
     * Notifies the user that a task has been unmarked
     * @param item the item that has been unmarked
     */
    public void taskUndone(Task item) {
        this.printLines();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(item.toString());
        this.printLines();
    }

    /**
     * Notifies the user that a task has been added
     * @param item the item that has been added
     * @param tasks the TaskList containing the tasks
     */
    public void taskAdd(Task item, TaskList tasks){
        this.printLines();
        System.out.println("Got it. I've added this task:");
        System.out.println(item.toString());
        System.out.println("Now you have "+ String.valueOf(tasks.getList().size()) + " tasks in the list.");
        this.printLines();
    }

    /**
     * Notifies the user that a task has been deleted
     * @param item the item that has been deleted
     * @param tasks the TaskList
     */
    public void taskDelete(Task item, TaskList tasks){
        this.printLines();
        System.out.println("Noted. I've removed this task:");
        System.out.println(item.toString());
        System.out.println("Now you have "+ String.valueOf(tasks.getList().size()) + " tasks in the list.");
        this.printLines();
    }

    /**
     * Prints that a bad date/time was found
     */
    public static void badDateLoaded(){
        System.out.println("Invalid date/time format encountered");
    }

    /**
     * Prints that an unrecognised command was found
     */
    public void unrecognisedCommand(){
        System.out.println("____________________________________________________________");
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the error
     */
    public static void Error(){
        System.out.println("An error occurred");
    }

    /**
     * Prints the error with the exception message
     * @param e the Exception that was thrown
     */
    public static void Error(Exception e){
        System.out.println("An error occurred" + e.getMessage());
    }

}
