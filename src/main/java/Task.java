import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }



    // public static Task createTask(String input) throws DukeException {


    // }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatusIcon(String statusIcon) {
        this.isDone = statusIcon.equals("X");
    }
    
    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        if (isDone) {
            System.out.println("Task is already done.");
        } else {
            isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println(this.toString());
    }

    public void markAsUndone() {
        if (!isDone) {
            System.out.println("Task is already not done.");
        } else {
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toFileString();

    public abstract void fromFileString(String fileString) throws DukeException;
}
