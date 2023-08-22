import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public void formatPrintMessage(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public void addTask(String taskName) {
        Task task = new Task(taskName);
        this.tasks.add(task);
        formatPrintMessage("added: " + taskName);
    }

    public void showAllTasks() {
        if (this.tasks.size() == 0) {
            formatPrintMessage("You have no tasks in your list.");
            return;
        }

        System.out.println();

        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + this.tasks.get(i));
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
