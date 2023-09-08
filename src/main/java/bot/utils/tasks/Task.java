package bot.utils.tasks;

import bot.exceptions.InvalidTaskException;


/**
 * Abstract task class for tasks that can be created for the task list in the chatbot.
 */
public abstract class Task {
    /**
     * Name of the task.
     */
    private String name;
    /**
     * Indicates if the task is completed.
     */
    private boolean isDone = false;

    /**
     * Default constructor.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Alternative constructor. Usually used when reading data from a file.
     *
     * @param name   Name of the task.
     * @param isDone Completion status of task.
     */
    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Creates a task based on the string input. Throws an InvalidTaskException
     * if no task can be created from the string.
     *
     * @param str Raw string to create task from.
     * @return Task object containing information from the string.
     * @throws InvalidTaskException If no task can be created.
     */
    public static Task makeTask(String str) throws InvalidTaskException {
        if (str.startsWith("todo")) {
            return ToDo.makeToDo(str);
        }
        if (str.startsWith("deadline")) {
            return Deadline.makeDeadline(str);
        }
        if (str.startsWith("event")) {
            return Event.makeEvent(str);
        }
        throw new InvalidTaskException("No such task exists with that code.");
    }

    /**
     * Reads a string of standardised data and constructs a Task object based on the information.
     *
     * @param str Data string.
     * @return Bot.Task object.
     * @throws InvalidTaskException If the task object cannot be created from the string.
     */
    public static Task convertFromDataString(String str) throws InvalidTaskException {
        if (str.startsWith("t")) {
            return ToDo.convertFromDataString(str);
        }
        if (str.startsWith("d")) {
            return Deadline.convertFromDataString(str);
        }
        if (str.startsWith("e")) {
            return Event.convertFromDataString(str);
        }
        throw new InvalidTaskException("No such task exists with that code.");
    }

    /**
     * Checks if the command word contains a task command.
     *
     * @param str Command word to check.
     * @return True if a task command is found, false otherwise.
     */
    public static boolean isTaskCommand(String str) {
        return str.equals("todo") || str.equals("event") || str.equals("deadline");
    }

    /**
     * Gets completion status of the task.
     *
     * @return True if the task is complete, false otherwise.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    protected boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the string representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") + "] " + this.getName();
    }

    /**
     * Changes the task's completion status to complete.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes the task's completion status to incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Creates data string of task.
     *
     * @return Data string.
     */
    public abstract String convertToDataString();

}
