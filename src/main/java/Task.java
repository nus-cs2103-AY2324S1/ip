public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Displays a horizontal line. */
    private static void printHorizontalLine() {
        String horizontalLine = "    ____________________________________________________________";
        System.out.println(horizontalLine);
    }

    public void markAsDone() {
        this.isDone = true;

        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this.getStatusIconAndDescription());
        printHorizontalLine();
    }

    public void markAsNotDone() {
        this.isDone = false;

        printHorizontalLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this.getStatusIconAndDescription());
        printHorizontalLine();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusIconAndDescription() {
        return String.format("[%1s] %2s", this.getStatusIcon(), this.description);
    }
}
