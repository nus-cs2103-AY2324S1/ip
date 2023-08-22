import java.util.ArrayList;
public class TaskList {
    private int taskCount;
    private final Task[] tasks;
    private final int MAX_TASKS = 100;

    public boolean isValidListIndex(int taskIndex) {
        return (taskIndex >= 0 && taskIndex < taskCount);
    }

    public TaskList() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public String getTaskDetails(int taskListIndex) {
        if(isValidListIndex(taskListIndex)) {
            Task task = tasks[taskListIndex];
            return String.format("[" + task.getStatusIcon() + "] " +
            task.getDescription());
        } else {
            System.out.println("Invalid Index of task!");
            return null;
        }
    }

    public void addTask(String task) {
        if (taskCount >= MAX_TASKS) {
            System.out.println("No more space to add Task :((");
        } else {
            this.tasks[taskCount] = new Task(task);
            this.taskCount++;
        }
    }

    public void markTaskAsDone(int taskListIndex) {
        if (isValidListIndex(taskListIndex)) {
            this.tasks[taskListIndex].markAsDone();
        }
    }

    public void markTaskAsNotDone(int taskListIndex) {
        if (isValidListIndex(taskListIndex)) {
            this.tasks[taskListIndex].markAsNotDone();
        }
    }

    public void displayTasks() {
        if (taskCount == 0) {
            System.out.println("Horray!! No tasks in the task list!");
        } else {
            System.out.println("____________________________________________________________");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + this.getTaskDetails(i));
            }
            System.out.println("____________________________________________________________");
        }

    }
}
