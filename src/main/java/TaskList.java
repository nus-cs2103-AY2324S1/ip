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
            return task.toString();
        } else {
            System.out.println("Invalid Index of task!");
            return null;
        }
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public void addTask(Task task) {
        if (taskCount >= MAX_TASKS) {
            System.out.println("No more space to add Task :((");
        } else {
            this.tasks[taskCount] = task;
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
            System.out.println("____________________________________________________________");
            System.out.println("Horray!! No tasks in the task list!");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + this.getTaskDetails(i));
            }
            System.out.println("____________________________________________________________");
        }

    }
}
