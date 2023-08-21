public class TaskList {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(task);
            taskCount++;
            System.out.println("added: " + task);
        } else {
            System.out.println("Sorry, the task list is full.");
        }
    }

    public void listTasks() {
        if (taskCount == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            }
        }
    }

    public void markTaskAsDone(int index) {
        if (index >= 1 && index <= taskCount) {
            tasks[index - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].getDescription());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskAsNotDone(int index) {
        if (index >= 1 && index <= taskCount) {
            tasks[index - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].getDescription());
        } else {
            System.out.println("Invalid task index.");
        }
    }
}
