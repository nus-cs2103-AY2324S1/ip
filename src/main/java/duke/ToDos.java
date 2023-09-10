package duke;

/**
 * ToDos encapsulates a task without any date or timeline attached to it
 */
public class ToDos extends Task {
    public static final int NAME_OFFSET = 5;
    private String taskName;


    /**
     * Constructor for creating a To-Do Task
     * @param taskName name of task.
     */
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
     * Creates a To-Do task from the user's input
     * @param input the user's input
     * @return  a To-Do task
     * @throws WrongInputException  if the input is invalid
     */
    public static Task createTaskFromInput(String input) throws WrongInputException {
        String taskName = input.substring(NAME_OFFSET);
        Task toDo = new ToDos(taskName);
        return toDo;
    }

    /**
     * Converts a To-Do task into a string that can be saved
     * @return  a string that can be saved
     */
    @Override
    public String convertToSaveFormat() {
        return "T" + Storage.FILESEPERATORCHARACTER + this.isDone() + Storage.FILESEPERATORCHARACTER + this.taskName
                + Storage.FILESEPERATORCHARACTER + this.saveTagFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
