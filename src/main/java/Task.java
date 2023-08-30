public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a Task object.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Displays a horizontal line. */
    private static void printHorizontalLine() {
        String horizontalLine = "    ____________________________________________________________";
        System.out.println(horizontalLine);
    }

    /** Method to mark task as done.
     *
     * @param shouldPrint True to print, false to not print
     */
    public void markAsDone(boolean shouldPrint) {
        this.isDone = true;

        if (shouldPrint) {
            printHorizontalLine();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + this);
            printHorizontalLine();
        }
    }

    /** Method to mark task as not done. */
    public void markAsNotDone() {
        this.isDone = false;

        printHorizontalLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this);
        printHorizontalLine();
    }

    /**
     * Method to get the status icon of the task based on whether the task is done.
     *
     * @return The status icon of the task.
     *         Returns X if task is done,
     *         else returns an empty space.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to get the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%1s] %2s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns task information in format for saving.
     * Format is [1 if completed, 0 if not completed] | [task description]
     *
     * @return Task information in format for saving
     */
    public String getInformationForSaving() {
        return String.format("%1s | %2s", this.isDone ? "1" : "0", this.description);
    }
}
