import java.util.ArrayList;
public class TaskList {
    private int taskCount;
    private final String[] tasks;
    private final int MAX_TASKS = 100;

    public TaskList() {
        tasks = new String[MAX_TASKS];
        taskCount = 0;
    }

    public void addTask(String task) {
        if (taskCount >= MAX_TASKS) {
            System.out.println("No more space to add Task :((");
        } else {
            this.tasks[taskCount] = task;
            this.taskCount++;
        }
    }

    public void displayTasks() {
        if (taskCount == 0) {
            System.out.println("Horray!! No tasks in the task list!");
        } else {
            System.out.println("____________________________________________________________");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + this.tasks[i]);
            }
            System.out.println("____________________________________________________________");
        }

    }
}
