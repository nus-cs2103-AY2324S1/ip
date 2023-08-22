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
    public ToDos(String taskName) {
        super(taskName);
    }

    /**
     * taskValidator does nothing if there are no wrong inputs but throws a WrongInputException
     * if inputs are invalid
     * @param input the user's string input
     * @throws WrongInputTask which informs the user of the error and actions to take
     */
    public static void taskValidator(String input) throws WrongInputTask {
        String[] splitString = input.split(" ");
        if (splitString.length < 2) {
            throw new WrongInputTask("Cannot be blank", "Enter a non-blank To-Do task");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
