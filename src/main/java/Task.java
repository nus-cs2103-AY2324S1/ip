public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public void markDone() {
        if (isDone) {
            return;
        } else {
            printHorizontalLine();
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println( "[" + this.getStatusIcon() + "] " + this.getDescription() );
            printHorizontalLine();
        }
    }

    public void markUndone() {
        printHorizontalLine();
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println( "[" + this.getStatusIcon() + "] " + this.getDescription() );
        printHorizontalLine();
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}