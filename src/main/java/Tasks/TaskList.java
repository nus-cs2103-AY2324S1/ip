package Tasks;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(Task[] tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>(1);
        } else {
            this.tasks = new ArrayList<>(Arrays.asList(tasks));
        }
    }


    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.printf("     %s\n", task);
        System.out.printf("Now you have %d tasks in the task list.\n", this.tasks.size());
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

    public void deleteTask(String text) {
        int taskIndex = Integer.parseInt(text) - 1;
        Task task = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        System.out.println("Ok! I've deleted this task:");
        System.out.printf("     %s\n", task);
    }

    public boolean validateTaskIndex(String text) {
        int index;
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.printf("Task index: '%s' is invalid, task index has to be an integer.\n", text);
            return false;
        }

        index = Integer.parseInt(text);

        if (index < 1 || index > this.tasks.size()) {
            System.out.printf("Task index: '%s' is invalid, task index has to be in list.\n", text);
            return false;
        }

        return true;
    }

    public Task[] getTasks() {
        Task[] taskArray = new Task[this.tasks.size()];
        return this.tasks.toArray(taskArray);
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