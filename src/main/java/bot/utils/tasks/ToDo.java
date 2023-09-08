package bot.utils.tasks;

import bot.exceptions.InvalidTaskException;

/**
 * ToDo subclass. Contains a name.
 */
class ToDo extends Task {
    /**
     * Default constructor.
     *
     * @param name Name of task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Alternative constructor. Usually used when reading data from a file.
     *
     * @param name   Name of ToDo.
     * @param isDone Completion status of Todo.
     */
    protected ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Reads a string of standardised data and creates a ToDo object.
     *
     * @param str Data string to read.
     * @return ToDo object.
     * @throws InvalidTaskException If an object cannot be created.
     */
    public static ToDo convertFromDataString(String str) throws InvalidTaskException {
        if (!str.matches("t/[01]/.+")) {
            throw new InvalidTaskException("Could not read Todo.");
        }
        String[] arr = str.split("/");
        return new ToDo(arr[2], arr[1].equals("1"));
    }

    /**
     * Checks for sameness of ToDo. ToDos are the same if they have the same name.
     *
     * @param o Object to compare to.
     * @return True if objects are the same, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ToDo) {
            return this.getName().equals(((ToDo) o).getName());
        }
        return false;
    }

    /**
     * Creates a ToDo object.
     *
     * @param str Raw string to create the ToDo object from.
     * @return ToDo object.
     * @throws InvalidTaskException If a ToDo object cannot be created from the string.
     */
    public static ToDo makeToDo(String str) throws InvalidTaskException {
        String name = str.substring(4).trim();
        if (name.equals("")) {
            throw new InvalidTaskException("Sorry, the todo description can't be empty.");
        }
        return new ToDo(name);
    }

    /**
     * String representation of the todo.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Get data string representation.
     *
     * @return Data string.
     */
    public String convertToDataString() {
        return "t/" + (super.isDone() ? "1" : "0") + "/" + super.getName();
    }
}
