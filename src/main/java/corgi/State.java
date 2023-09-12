package corgi;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.tasks.TaskListIndexOutOfBoundsException;
import corgi.tasks.TaskStatusException;
import corgi.ui.TextRenderer;

/**
 * An immutable State class to store the state of the chatbot.
 */
public final class State {
    private final TaskList tasks;
    private final Storage<Task> storage;
    private final TextRenderer renderer;

    /**
     * Construct new state object with given task list, storage and text renderer.
     *
     * @param tasks The given task list.
     * @param storage The given storage.
     * @param renderer The given text renderer.
     */
    public State(
            TaskList tasks, Storage<Task> storage, TextRenderer renderer) {
        this.tasks = tasks;
        this.storage = storage;
        this.renderer = renderer;
    }

    /**
     * Getter for task list.
     *
     * @return The task list.
     */
    public TaskList getTaskList() {
        return this.tasks;
    }

    /**
     * Getter for storage.
     *
     * @return The storage.
     */
    public Storage<Task> getStorage() {
        return this.storage;
    }

    /**
     * Getter for text renderer.
     *
     * @return The text renderer.
     */
    public TextRenderer getTextRenderer() {
        return this.renderer;
    }

    /**
     * Add target task to the task list.
     *
     * @param task The target task.
     * @return New state with the updated task list.
     */
    public State addTask(Task task) {
        TaskList newTaskList = this.tasks.add(task);

        this.storage.save(newTaskList);

        return new State(newTaskList, storage, renderer);
    }

    /**
     * Remove task at the target index in the task list.
     *
     * @param index The target index.
     * @return New state with the updated task list.
     * @throws TaskListIndexOutOfBoundsException
     */
    public State removeTask(int index) throws TaskListIndexOutOfBoundsException {
        TaskList newTaskList = this.tasks.remove(index);

        this.storage.save(newTaskList);

        return new State(newTaskList, storage, renderer);
    }

    /**
     * Mark task at the target index to a given status.
     *
     * @param index The target index.
     * @param status The expected status.
     * @return New state with the updated task list.
     * @throws TaskListIndexOutOfBoundsException
     * @throws TaskStatusException
     */
    public State markTask(int index, boolean status)
            throws TaskListIndexOutOfBoundsException, TaskStatusException {
        TaskList newTaskList = this.tasks.mark(index, status);

        this.storage.save(newTaskList);

        return new State(newTaskList, storage, renderer);
    }

    /**
     * Store the current task list to local.
     */
    public void save() {
        this.storage.save(this.tasks);
    }
}
