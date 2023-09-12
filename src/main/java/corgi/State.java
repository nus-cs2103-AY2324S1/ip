package corgi;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.tasks.TaskListIndexOutOfBoundsException;
import corgi.tasks.TaskStatusException;
import corgi.ui.TextRenderer;

public final class State {
    private final TaskList tasks;
    private final Storage<Task> storage;
    private final TextRenderer renderer;

    public State(
            TaskList tasks, Storage<Task> storage, TextRenderer renderer) {
        this.tasks = tasks;
        this.storage = storage;
        this.renderer = renderer;
    }

    public TaskList getTaskList() {
        return this.tasks;
    }

    public Storage<Task> getStorage() {
        return this.storage;
    }

    public TextRenderer getTextRenderer() {
        return this.renderer;
    }

    public State addTask(Task task) {
        TaskList newTaskList = this.tasks.add(task);

        this.storage.save(newTaskList);

        return new State(newTaskList, storage, renderer);
    }

    public State removeTask(int index) throws TaskListIndexOutOfBoundsException {
        TaskList newTaskList = this.tasks.remove(index);

        this.storage.save(newTaskList);

        return new State(newTaskList, storage, renderer);
    }

    public State markTask(int index, boolean status) 
            throws TaskListIndexOutOfBoundsException, TaskStatusException {
        TaskList newTaskList = this.tasks.mark(index, status);

        this.storage.save(newTaskList);

        return new State(newTaskList, storage, renderer);
    }

    public void save() {
        this.storage.save(this.tasks);
    }
}
