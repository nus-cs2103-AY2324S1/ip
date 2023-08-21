public class Task {
    private String taskName;
    private boolean done;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    //default constructor
    public Task() {
        this.taskName = "Untitled task";
        this.done = false;
    }

    public void mark() {
        printLine();
        this.done = true;
        System.out.println("SIUUU! I've marked this task as done: \n [X] " + taskName);
        printLine();
    }

    public void unmark() {
        printLine();
        this.done = false;
        System.out.println("OK, I've marked this task as not done yet: \n [ ] " + taskName);
        printLine();
    }

    @Override
    public String toString() {
        String status = this.done ? "[X]" : "[ ]";
        return status + " " + taskName;
    }

}
