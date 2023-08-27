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
            return String.format("<T> %s %s", this.getCompletedIndicatorString(), this.getTitle());
        }
    }

    /**
     * A deadline task. It tracks a title and a deadline associated
     * with it, and can be marked as completed.
     */
    public static class Deadline extends Task {

        private long deadline;

        public Deadline(String title, long deadline) {
            super(title);
            this.deadline = deadline;
        }

        /**
         * Obtains the deadline of this task.
         * @return The deadline of this task as seconds since Unix epoch (1970-01-01 00:00:00 UTC).
         */
        public long getDeadline() {
            return this.deadline;
        }

        @Override
        public String toString() {
            return String.format(
                    "<D> %s %s (by: %s)",
                    this.getCompletedIndicatorString(),
                    this.getTitle(),
                    EpochConverter.getUserReadableStringFromEpoch(this.getDeadline())
            );
        }
    }

    /**
     * An event task. It tracks a title and the range associated with the
     * event (from date-time to date-time), and can be marked as completed.
     */
    public static class Event extends Task {

        private long startTimestamp; // TODO: Start times should not be a string.
        private long endTimestamp; // TODO: End times should not be a string.

        public Event(String title, long startTimestamp, long endTimestamp) {
            super(title);
            this.startTimestamp = startTimestamp;
            this.endTimestamp = endTimestamp;
        }

        /**
         * Obtains the starting timestamp of this event task.
         *
         * @return The starting timestamp as seconds since Unix epoch (1970-01-01 00:00:00 UTC).
         */
        public long getStartTimestamp() {
            return this.startTimestamp;
        }

        /**
         * Obtains the ending timestamp of this event task.
         *
         * @return The ending timestamp as seconds since Unix epoch (1970-01-01 00:00:00 UTC).
         */
        public long getEndTimestamp() {
            return this.endTimestamp;
        }

        @Override
        public String toString() {
            return String.format(
                    "<E> %s %s (from: %s, to: %s)",
                    this.getCompletedIndicatorString(),
                    this.getTitle(),
                    EpochConverter.getUserReadableStringFromEpoch(this.getStartTimestamp()),
                    EpochConverter.getUserReadableStringFromEpoch(this.getEndTimestamp())
            );
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
