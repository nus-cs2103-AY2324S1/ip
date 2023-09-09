import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    public void listTasks() {
        Ui.listTasks(this.tasks);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        Ui.addTask(t, this.tasks);
        this.storage.addOneLineToFile(this.tasks);
    }

    public void deleteTask(int num) {
        Task re = tasks.remove(num - 1);
        this.storage.rewriteFile(tasks);
        Ui.deleteTask(re, tasks);
    }

    public void markTask(int num) {
        Task t = tasks.get(num);
        t.markAsDone();
        Ui.markTask(t);
        this.storage.rewriteFile(tasks);
    }
}
