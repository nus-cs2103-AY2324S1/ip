import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
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
     * All subclasses must also ensure they conform to compatibility with GSON
     * and do not allow any null fields.
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
         * Asserts that this task's parameters are in a valid state, having correctly configured with no null values.
         *
         * @throws IllegalArgumentException if the object is incorrectly constructed with invalid, null parameters.
         */
        public void assertValidState() {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field: fields) {
                try {
                    if (field.get(this) == null) {
                        throw new IllegalArgumentException(String.format(
                                "Tasks may not have null values, but %s is null.", field.getName()
                        ));
                    }
                } catch (IllegalAccessException e) {
                    // Do nothing.
                }
            }
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
        private String deadline; // TODO: dates should not be a string

        public Deadline(String title, String deadline) {
            super(title);
            this.deadline = deadline;
        }

        /**
         * Obtains the deadline of this task.
         * @return The deadline of this task as a string.
         */
        public String getDeadline() {
            return this.deadline;
        }

        @Override
        public String toString() {
            return String.format(
                    "<D> %s %s (by: %s)",
                    this.getCompletedIndicatorString(),
                    this.getTitle(),
                    this.getDeadline()
            );
        }
    }

    /**
     * An event task. It tracks a title and the range associated with the
     * event (from date-time to date-time), and can be marked as completed.
     */
    public static class Event extends Task {

        private String startTimestamp; // TODO: Start times should not be a string.
        private String endTimestamp; // TODO: End times should not be a string.

        public Event(String title, String startTimestamp, String endTimestamp) {
            super(title);
            this.startTimestamp = startTimestamp;
            this.endTimestamp = endTimestamp;
        }

        /**
         * Obtains the starting timestamp of this event task.
         *
         * @return The starting timestamp as a string.
         */
        public String getStartTimestamp() {
            return this.startTimestamp;
        }

        /**
         * Obtains the ending timestamp of this event task.
         *
         * @return The ending timestamp as a string.
         */
        public String getEndTimestamp() {
            return this.endTimestamp;
        }

        @Override
        public String toString() {
            return String.format(
                    "<E> %s %s (from: %s, to: %s)",
                    this.getCompletedIndicatorString(),
                    this.getTitle(),
                    this.getStartTimestamp(),
                    this.getEndTimestamp()
            );
        }
    }



    private List<Task> taskList;
    private InternalStorage.Path storageLocation;
    private static final String DEFAULT_FILENAME = "tasks.json";

    /**
     * Constructor for a task manager, managing a list of items representing "tasks",
     * with a custom storage location.
     *
     * @param storageLocation Path
     */
    public TaskManager(InternalStorage.Path storageLocation) {
        this.taskList = new ArrayList<>();
        this.storageLocation = storageLocation;
    }

    /**
     * Constructor for a task manager, managing a list of items representing "tasks",
     * with the default storage location.
     */
    public TaskManager() {
        this(new InternalStorage.Path(DEFAULT_FILENAME));
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

    /**
     * Loads and replaces the task list in memory with the one currently in storage.
     *
     * <p>
     *     This method will load the data from storage and replace all in-memory contents.
     *     Any unrecognized, incompatible tasks may be omitted entirely.
     * </p>
     *
     * @throws IOException if there were any issues retrieving the data.
     */
    public void loadFromStorage() throws IOException, JsonSyntaxException {
        Gson gson = new Gson();
        try {
            String data = InternalStorage.loadFrom(this.storageLocation);
            JsonArray array = JsonParser.parseString(data).getAsJsonArray();

            // Prepare a new list of tasks.
            List<Task> tasks = new ArrayList<>();

            // Prepare a new set of classes, from most specific to least specific.
            // This ordering is required to match the provided JSON to a class that's as specific as possible.
            @SuppressWarnings("unchecked")
            Class<Task>[] availClasses = new Class[]{ Event.class, Deadline.class, Todo.class };

            // Iterate through the items in the JSON array.
            for (JsonElement item: array) {
                Task task = null;

                // Iterate through possible classes and attempt to get them.
                for (Class<Task> cls: availClasses) {
                    try {
                        task = gson.fromJson(item, cls);
                        task.assertValidState();
                    } catch (JsonSyntaxException | IllegalArgumentException e) {
                        // This particular task is broken. Make it null.
                        task = null;
                    }

                    if (task != null) {
                        break;
                    }
                }

                // Skip if we cannot parse.
                if (task == null) {
                    continue;
                }

                // Add it if we can.
                tasks.add(task);
            }

            // Replace the task list with a new one
            this.taskList = tasks;

        } catch (JsonSyntaxException | FileNotFoundException e) {

            // Silence these errors and replace the task list with a new one.
            this.taskList = new ArrayList<>();

        }
    }

    /**
     * Saves and replaces the task list in storage with the one currently in memory.
     *
     * @throws IOException if there were any issues saving the data.
     */
    public void saveToStorage() throws IOException {
        Gson gson = new Gson();
        String data = gson.toJson(this.taskList);
        InternalStorage.saveTo(this.storageLocation, data);
    }
}
