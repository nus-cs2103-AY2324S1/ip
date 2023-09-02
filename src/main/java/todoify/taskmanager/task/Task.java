package todoify.taskmanager.task;

import java.lang.reflect.Field;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * A task that the task manager can keep in its task list.
 *
 * <p>
 * This is an abstract class which should be inherited by custom task types. It contains basic properties for a task,
 * which can be extended as needed. All subclasses must also ensure they conform to compatibility with GSON and do not
 * allow any null fields.
 * </p>
 */
public abstract class Task {

    /**
     * Title for the task in question.
     */
    private final String title;

    /**
     * Whether the task is completed.
     *
     * <p>
     * This is intentionally called "completed" instead of "isComplete" as Task and its subclasses utilise Gson to
     * construct the JSON file, which uses these variable names as JSON keys.
     * </p>
     */
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
     *
     * @return The title of the task as a string.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Whether the task is marked as completed.
     *
     * @return true if completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Marks the task as completed or incomplete.
     *
     * @param isCompleted Whether to mark it as complete (true) or incomplete (false).
     */
    public void setCompleted(boolean isCompleted) {
        this.completed = isCompleted;
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
        for (Field field : fields) {
            try {
                // Forcibly allow access. This is required to verify integrity.
                field.setAccessible(true);

                // Check if the field is null. If so, it's an error.
                if (field.get(this) == null) {
                    throw new IllegalArgumentException(String.format(
                            "Tasks may not have null values, but %s is null.", field.getName()
                    ));
                }

            } catch (IllegalAccessException e) {
                // Well, we tried.
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

    /**
     * Returns a JSON representation of the task.
     *
     * @return A Gson {@link JsonElement} representing this task.
     */
    public JsonElement toJsonRepresentation() {
        Gson gson = new Gson();
        return gson.toJsonTree(this);
    }

    /**
     * Initializes a task using the given JSON data.
     *
     * @param jsonElement The Gson {@link JsonElement} representing this task.
     * @throws IllegalArgumentException if the data causes the class to have missing values.
     */
    public static <T extends Task> T fromJsonRepresentation(JsonElement jsonElement, Class<T> classType) {
        Gson gson = new Gson();
        T task = gson.fromJson(jsonElement, classType);
        task.assertValidState();
        return task;
    }
}
