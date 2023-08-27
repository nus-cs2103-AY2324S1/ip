package duke;

import java.util.ArrayList;

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

    public void save(Storage storage) {
        storage.save(this.tasks);
        this.ui.exitMessage();
    }

    /**
     * Prints out the list of tasks tracked by the chatbot
     */
    public void listTasks() {
        this.ui.listMessage(tasks);
    }

    public void markTaskAsDone(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.markAsDone();
        this.ui.markTaskAsDoneMessage(task);
    }

    public void unmarkTask(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.unmark();
        this.ui.unmarkTaskMessage(task);
    }

    public void deleteTask(int taskNumber) {
        Task task = this.tasks.remove(taskNumber);
        this.ui.deleteTaskMessage(task, this.tasks.size());
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.ui.addTaskMessage(task, this.tasks.size());
    }
}
