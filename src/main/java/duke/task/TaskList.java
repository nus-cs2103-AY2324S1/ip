package duke.task;

import duke.Storage;
import duke.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
        loadTasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    public void removeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index.");
        }
        tasks.remove(index);
        storage.saveTasks(tasks);
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index.");
        }
        tasks.get(index).mark();
        storage.saveTasks(tasks);
    }

    public void unmarkTaskAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index.");
        }
        tasks.get(index).unmark();
        storage.saveTasks(tasks);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void loadTasks() {
        tasks = storage.loadTasks(); // Load tasks from storage when initializing
    }
}

