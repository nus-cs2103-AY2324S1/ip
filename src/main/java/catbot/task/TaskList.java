package catbot.task;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import catbot.internal.Bounds;
import catbot.internal.NamedParameterMap;

/**
 * Object to manage a list of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private final TaskArrayListStorage storage;

    /**
     * Constructs a TaskList with a path to read and write from, for storage.
     *
     * @param path relative directory to read from and write to.
     */
    public TaskList(String path) {
        if (path != null) {
            this.storage = new TaskArrayListStorage(path);
            this.storage.setDefault(() -> new ArrayList<>());
            this.tasks = storage.readOrDefault();
        } else {
            this.storage = null;
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        update();
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index index of the task to remove.
     * @return the removed task.
     */
    public Task removeTask(int index) {
        Task removed = tasks.remove(index);
        update();
        return removed;
    }

    /**
     * Checks if the provided integer is a valid index starting from 1.
     * If so, converts to index starting from 0, and passes to the first consumer.
     * Otherwise, passes the original integer to the second consumer.
     *
     * @param integer   integer to check if is valid index based on {@link Bounds Bounds}
     * @param ifValid   consumer to accept (integer - 1) if it is a valid index
     * @param otherwise consumer to accept (integer) if it is not a valid index
     * @see java.util.Optional#ifPresentOrElse for inspiration.
     */
    public void ifValidIndexElse(int integer, Consumer<Integer> ifValid, Consumer<Integer> otherwise) {
        Bounds bounds = getIndexBounds();
        if (bounds.contains(integer)) {
            ifValid.accept(integer - 1);
        } else {
            otherwise.accept(integer);
        }
    }

    /**
     * Retrieves a {@link Bounds bounds} object that represents valid indexes.
     * @return Bounds object for relevant indexes.
     */
    public Bounds getIndexBounds() {
        return new Bounds(1, tasks.size());
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index index of the task to mark as done.
     */
    public void markTask(int index) {
        tasks.get(index).setDone();
        update();
    }

    /**
     * Marks the task at the given index as undone.
     *
     * @param index index of the task to mark as undone.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public void unmarkTask(int index) {
        tasks.get(index).setUndone();
        update();
    }

    /**
     * Edits the task at the given index based on the parameters and arguments provided through a NamedParameterMap.
     *
     * @param index the index of the task to edit.
     * @param map   map that contains new values for parameters.
     *              valid parameters result in their arguments replacing previous values in the task.
     */
    public void editTask(int index, NamedParameterMap map) {
        tasks.get(index).edit(map);
        update();
    }

    /**
     * Retrieves the length of the list.
     *
     * @return number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task at a specified index.
     *
     * @param index index of the task to retrieve.
     * @return task at the provided index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns an ArrayList of the string representations of all tasks, in sequence.
     *
     * @return ArrayList of toStringed tasks.
     */
    public ArrayList<String> getTaskStrings() {
        return tasks.stream().map(task -> task.toString()).collect(Collectors.toCollection(ArrayList::new));
    }

    private void update() {
        this.storage.write(this.tasks);
    }

    /**
     * Returns a list of all tasks whose descriptions contain the search string.
     *
     * @param string text to search in descriptions.
     * @return TaskList containing tasks whose descriptions contain the search text.
     */
    public TaskList findInDescriptions(String string) {
        TaskList taskList = new TaskList(null);
        // credit to IntelliJ for this line of code that I won't bother to understand
        taskList.tasks = tasks.stream().filter(task -> task.getDescription().contains(string))
                .collect(Collectors.toCollection(ArrayList::new));
        return taskList;
    }

}
