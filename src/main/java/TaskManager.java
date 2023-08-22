import java.util.ArrayList;
import java.util.List;

/**
 * A class that manages a list of items aka "tasks".
 */
public class TaskManager {

    /**
     * A task that the task manager can keep in its task list.
     *
     * <p>
     * This is an abstract class which should be inherited by custom task types.
     * It contains basic properties for a task, which can be extended as needed.
     * </p>
     */
    public abstract static class Task {
        private String title;
        private boolean completed = false;

        /**
         * Initializes a task.
         *
         * @param title The title for the task.
         */
        public Task(String title) {
            this.title = title;
        }

        /**
         * Obtains the title for the task.
         * @return The title of the task as a string.
         */
        public String getTitle() {
            return this.title;
        }

        /**
         * Whether the task is marked as completed.
         * @return true if completed, false otherwise.
         */
        public boolean isCompleted() {
            return this.completed;
        }

        /**
         * Marks the task as completed or incomplete.
         * @param completed Whether to mark it as complete (true) or incomplete (false).
         */
        public void markCompleted(boolean completed) {
            this.completed = completed;
        }

        /**
         * Internal method for a formatted mark-complete symbol for this task.
         */
        protected String getCompletedIndicatorString() {
            return String.format("[%s]", this.isCompleted() ? "X" : " ");
        }

        /**
         * Returns a string representation of the task, to be implemented by inherited classes.
         *
         * @return A string representing the task.
         */
        @Override
        public abstract String toString();
    }

    /**
     * A trivial task that represents to-dos.
     * It has a title and can be marked as completed.
     */
    public static class Todo extends Task {
        public Todo(String title) {
            super(title);
        }

        @Override
        public String toString() {
            return String.format("%s %s", this.getCompletedIndicatorString(), this.getTitle());
        }
    }

    private List<Task> taskList;

    /**
     * Constructor for a task manager, managing a list of items representing "tasks".
     */
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Obtains the items as an iterable.
     * @return The iterable of list of items.
     */
    public Iterable<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Queries the number of items stored.
     * @return The number of items currently stored as an integer.
     */
    public int getTaskCount() {
        return this.taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @return `true`, by definition of {@link List#add}
     */
    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    /**
     * Gets an task from the task list with the corresponding index.
     *
     * @param index The index to obtain.
     * @return The task in question at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Removes the given task from the task list.
     *
     * @param task The task to remove.
     * @return `true` if the task exists in the list and is successfully removed, `false` otherwise.
     */
    public boolean removeTask(Task task) {
        return this.taskList.remove(task);
    }

    /**
     * Removes the task at the corresponding index.
     *
     * @param index The index to remove.
     * @return The task in question that was removed.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }

}
