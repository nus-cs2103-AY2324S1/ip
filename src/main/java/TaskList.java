import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(int size) {
        this.tasks = new ArrayList<>(size);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.printf("     %s\n", task);
        System.out.printf("Now you have %d tasks in the task list. \n", this.tasks.size());
    }

    public void markTaskDone(String text) {
        Task task = this.tasks.get(Integer.parseInt(text) - 1);
        task.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("     %s\n", task);
    }

    public void markTaskUndone(String text) {
        Task task = this.tasks.get(Integer.parseInt(text) - 1);
        task.markUndone();
        System.out.println("Ok! I've marked this task as undone:");
        System.out.printf("     %s\n", task);
    }

    public void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= this.tasks.size(); ++i) {
            String taskNumber = String.format("%3d.", i);
            System.out.printf("%s %s\n", taskNumber, this.tasks.get(i - 1).toString());
        }
        System.out.printf("You have %d tasks in the task list.\n", this.tasks.size());
    }
}