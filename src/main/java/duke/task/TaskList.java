package duke.task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import duke.Storage;
import duke.Ui;

public class TaskList {
    private int taskNum = 0;
    Storage store = new Storage();
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        try {
            this.tasks = store.loadTasks();
        } catch (FileNotFoundException e) {
            Ui.noTaskList();
        }
        this.taskNum = this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskNum += 1;
        store.saveTasks(this.tasks);
    }

    public int taskCount() {
        return this.taskNum;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void markTask(int index) {
        store.saveTasks(this.tasks);
        this.tasks.get(index).markAsDone();
    }

    public void unMarkTask(int index) {
        store.saveTasks(this.tasks);
        this.tasks.get(index).markAsNotDone();
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
        this.taskNum -= 1;
        store.saveTasks(this.tasks);
    }
}
