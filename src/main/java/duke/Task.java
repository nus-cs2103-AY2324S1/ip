package duke;
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
    public void updateAsDone() {
        this.isDone = true;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("     Nice! I've marked this task as done:\n"
                           + "       " + this.printDesc());
    }
    public void markAsUndone() {
        this.isDone = false;
        System.out.println("     OK, I've marked this task as not done yet:\n"
                           + "       " + this.printDesc());
    }
    public String printDesc() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public void printMessage(int numTask) {
        System.out.println(Duke.horizontalLine + "\n"
                + "     Got it. I've added this task:\n"
                + "       " + this.printDesc() + "\n"
                + "     Now you have " + (numTask + 1) + " tasks in the list.\n"
                + Duke.horizontalLine);
    }

    public String getDescription() {
        if (this.isDone) {
            return "done~" + this.description;
        } else {
            return "notDone~" + this.description;
        }
    }
}
