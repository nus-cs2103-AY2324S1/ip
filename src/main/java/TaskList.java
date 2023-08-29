import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void formatPrintMessage(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public void addTask(String taskDescription) {
        Task task;
        String[] taskInformation = taskDescription.split(" /");
        String taskName = taskInformation[0].trim();
        if (taskInformation.length == 1) {
            task = new ToDo(taskName);
        } else if (taskInformation.length == 2) {
            task = new Deadline(taskName, taskInformation[1].replace("by ", ""));
        } else if (taskInformation.length == 3) {
            task = new Event(taskName, taskInformation[1].replace("from",""),
                    taskInformation[2].replace("to ", ""));
        } else {
            formatPrintMessage("Invalid task format");
            return;
        }
        this.tasks.add(task);
        formatPrintMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + this.tasks.size() + " task(s) in the list.");

    }

    public void deleteTask(int taskNumber) {
        if (taskNumber > this.tasks.size()) {
            formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        this.tasks.remove(task);
        formatPrintMessage("Noted. I've removed this task:\n  " + task + "\nNow you have " + this.tasks.size() + " task(s) in the list.");
    }

    public void showAllTasks() {
        if (this.tasks.size() == 0) {
            formatPrintMessage("You have no tasks in your list.");
            return;
        }

        System.out.println();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + "." + this.tasks.get(i));
        }

        System.out.println();
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber > this.tasks.size()) {
            formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        task.markAsDone();
    }

    public void unmarkTaskAsDone(int taskNumber) {
        if (taskNumber > this.tasks.size()) {
            formatPrintMessage("Task number does not exist");
            return;
        }

        Task task = this.tasks.get(taskNumber - 1);
        task.unmarkAsDone();
    }
}
