package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public String filterTasks(String description) {
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(description)) {
                filteredTasks.add(task);
            }
        }
        return this.ui.listFoundTasks(filteredTasks);
    }

    public void save(Storage storage) {
        storage.save(this.tasks);
    }

    public String listTasks() {
        return this.ui.listMessage(tasks);
    }

    public String markTaskAsDone(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.markAsDone();
        return this.ui.markTaskAsDoneMessage(task);
    }

    public String unmarkTask(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.unmark();
        return this.ui.unmarkTaskMessage(task);
    }

    public String deleteTask(int taskNumber) {
        Task task = this.tasks.remove(taskNumber);
        return this.ui.deleteTaskMessage(task, this.tasks.size());
    }

    public String addTask(Task task) {
        this.tasks.add(task);
        return this.ui.addTaskMessage(task, this.tasks.size());
    }
}
