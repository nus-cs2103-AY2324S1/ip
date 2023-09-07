package duke.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class is an abstract base class for representing tasks in the Duke application.
 */
public abstract class Task {
    enum Type {
        TODO("[T]", 3),
        DEADLINE("[D]", 4),
        EVENT("[E]", 5);

        private final String name;
        private final int numParams;

        private Type(String name, int numParams) {
            this.name = name;
            this.numParams = numParams;
        }

        /**
         * Retrieves the Type enum based on its name.
         *
         * @param name The name of the Type enum.
         * @return The Type enum corresponding to the given name.
         * @throws DukeException if no matching Type enum is found.
         */
        protected static Type of(String name) throws DukeException {
            for (Type type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            throw new InvalidFileDataException();
        }

        /**
         * Gets the number of parameters expected for this task type.
         *
         * @return The number of parameters.
         */
        protected int param() {
            return this.numParams;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private static final String MARKED_CHECKBOX = "[X]";
    private static final String UNMARKED_CHECKBOX = "[ ]";
    private static final DateTimeFormatter DATETIME_FORMAT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String title;
    private Type type;
    private boolean checked;

    /**
     * Constructs a new Task object with a title and a task type.
     *
     * @param title The title of the task.
     * @param type  The type of the task.
     */
    protected Task(String title, Type type) {
        this.title = title;
        this.type = type;
        this.checked = false;
    }

    /**
     * Gets the title of the task.
     *
     * @return The title of the task.
     */
    protected String name() {
        return this.title;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    protected Type type() {
        return this.type;
    }

    /**
     * Checks if the task is marked (completed).
     *
     * @return true if the task is marked; false otherwise.
     */
    protected boolean marked() {
        return this.checked;
    }

    /**
     * Marks the task as completed.
     */
    protected void mark() {
        this.checked = true;
    }

    /**
     * Unmarks the task as completed.
     */
    protected void unmark() {
        this.checked = false;
    }

    /**
     * Converts the task to a CSV (Comma-Separated Values) string.
     *
     * @return A CSV string representation of the task.
     */
    protected abstract String toCsv();

    /**
     * Converts a LocalDateTime object to a formatted string.
     *
     * @param datetime The LocalDateTime object to be converted.
     * @return A formatted string representation of the date and time.
     */
    protected static String dateToString(LocalDateTime datetime) {
        return datetime.format(Task.DATETIME_FORMAT);
    }

    @Override
    public String toString() {
        return String.format("%s %s",
            this.checked ? Task.MARKED_CHECKBOX : Task.UNMARKED_CHECKBOX,
            this.name()
          );
    }
}
