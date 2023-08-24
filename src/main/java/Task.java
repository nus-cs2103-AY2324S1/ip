public class Task {
    int taskNumber;
    String taskName;
    boolean isDone = false;

    public Task(int taskNumber,String taskName) {
        this.taskNumber = taskNumber;
        this.taskName = taskName;
    }
    public void printTask() {
        if (isDone) {
            System.out.println(taskNumber + ".[X] " + taskName);
        } else {
            System.out.println(taskNumber + ".[ ] " + taskName);
        }
    }

    // mark task as done and print out the line
    public void markDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + taskName);
    }

    // mark task as undone and print out the line
    public void unmarkDone() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + taskName);
    }
}
