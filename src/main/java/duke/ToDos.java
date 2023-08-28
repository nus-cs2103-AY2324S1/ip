package duke;

/**
 * ToDos encapsulates a task without any date or timeline attached to it
 */
public class ToDos extends Task {
    /**
     * Constructor for creating a To-Do Task
     *
     * @param taskName name of task.
     *
     */

    private String taskName;

    public ToDos(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    /**
     * Constructor for creating a To-Do Task
     * @param taskName name of task.
     * @param isDone  whether the task is done or not
     */
    public ToDos(String taskName, boolean isDone) {
        super(taskName);
        this.taskName = taskName;
        if (isDone) {
            super.quietlyCompleteTask();
        }
    }

    /**
     * taskValidator does nothing if there are no wrong inputs but throws a WrongInputException
     * if inputs are invalid
     * @param input the user's string input
     * @throws WrongInputException which informs the user of the error and actions to take
     */
    public static void taskValidator(String input) throws WrongInputException {
        String[] splitString = input.split(" ");
        if (splitString.length < 2) {
            throw new WrongInputException("Cannot be blank", "Enter a non-blank To-Do task");
        }
    }

    /**
     * Converts a To-Do task into a string that can be saved
     * @return  a string that can be saved
     */
    @Override
    public String convertToSaveFormat() {
        return "T" + Storage.FILESEPERATORCHARACTER + this.isDone() + Storage.FILESEPERATORCHARACTER + this.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
