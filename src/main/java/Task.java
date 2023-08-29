import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class contains a name and its status (if it is done or not done), and the functions to set it as
 * done/undone, and give the string representations of it, its status and it in a list
 */
public class Task {
    private String name;
    private boolean isDone;


    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }



    /**
     * This method set this task as done
     */
    public void setDone() {
        this.isDone = true;
    }
    /**
     * This method set this task as undone
     *
     */
    public void setUndone() {
        this.isDone = false;
    }
    /**
     * This method gives the name of the task
     *
     * @return the name of the task
     */
    public String showTask() {
        return this.name;
    }

    /**
     * This method gives the string representation of the status of the task (done or not done)
     *
     * @return the string representation of the done status of a task.
     */
    private String statusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * This gives the string representation of the task in a list
     *
     * @return the string representation of the task in a list
     */
    public String showTaskinList() {
        return this.statusIcon() + " " + this.showTask();
    }
    public String printList() {
        if (this.isDone) {
            return "1 | " + this.showTask();
        } else {
            return "0 | " + this.showTask();
        }
    }

    public static String printDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }


}
