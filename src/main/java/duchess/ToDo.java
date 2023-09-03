package duchess;

class ToDo extends Task {

    /**
     * Creates a new ToDo instance with the provided name and status.
     *
     * @param name   - the name of the ToDo.
     * @param status - the current task status of the ToDo.
     */
    public ToDo(String name, TaskStatus status) {
        super(name, status);
    }

    /**
     * Creates a new ToDo instance with the provided name. The status will be the default status in Task.
     *
     * @param name   - the name of the ToDo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the String representation of this ToDo.
     *
     * @return the String representation of this ToDo.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    /**
     * Returns the String representation of this ToDo, for the purposes of saving.
     *
     * @return the String representation of this ToDo.
     */
    @Override
    public String toSaveString() {
        return String.format("T|%s", super.toSaveString());

    }

    /**
     * Returns a new ToDo from a Save String.
     *
     * @return the ToDo that this String is represented by.
     */
    public static ToDo fromSaveString(String s) {
        String[] splitString = s.split(Task.SAVE_STRING_DELIMITER);
        // Not enough arguments; minmally, it needs the Type, the Marked status, and the Name.
        if (splitString.length < 3) {
            return null;
        }

        TaskStatus taskStatus = TaskStatus.UNMARKED;
        String name = "";

        if (Integer.parseInt(splitString[1]) == 1) {
            taskStatus = TaskStatus.MARKED;
        }

        name = splitString[2];

        return new ToDo(name, taskStatus);
    }
}
