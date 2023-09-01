package duke;

public class ToDo extends Task {


    /**
     * Constructs a new {@code ToDo} object, with {@code isCompleted} set to false.
     *
     * @param details Details of the {@code ToDo}.
     */
    public ToDo(String details) {
        super(details);
    }

    /**
     * Loads a {@code ToDo} object that was previously stored in the hard disk.
     * The {@code isCompleted} parameter corresponds to the completion status in the last
     * instance of {@code Duke}.
     *
     * @param details Details of the {@code ToDo}.
     * @param isCompleted Completion status of the {@code ToDo}.
     */
    public ToDo(String details, boolean isCompleted) {
        super(details, isCompleted);
    }

    /**
     * Returns the string representation of the {@code ToDo}, to be
     * printed in the {@code list} method.
     *
     * @return String representation of the {@code ToDo}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
