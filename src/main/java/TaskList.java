public class TaskList {
    private Task[] tasks;
    private int numTasks;

    public TaskList() {
        this.tasks = new Task[100];
        this.numTasks = 0;
    }

    public void addTask(Task task) {
        this.tasks[numTasks++] = task;
        // assume that the string representation of add is not changed
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    public void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public void markTaskAsDone(int taskNumber) {
        this.tasks[taskNumber - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks[taskNumber - 1].toString());
    }

    public void markTaskAsUndone(int taskNumber) {
        this.tasks[taskNumber - 1].markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks[taskNumber - 1].toString());
    }
}
