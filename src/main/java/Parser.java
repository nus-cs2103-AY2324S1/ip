import exceptions.EmptyTaskDescriptionException;
import exceptions.InvalidCommandException;
import exceptions.InvalidDateFormatException;
import exceptions.InvalidTaskNumberException;
import exceptions.TaskAlreadyDoneException;
import exceptions.TaskNotDoneException;

public class Parser {

    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input and triggers the appropriate action on TaskList.
     * @param input The entire string of the user's input.
     * @throws InvalidTaskNumberException
     * @throws TaskAlreadyDoneException
     * @throws TaskNotDoneException
     * @throws InvalidDateFormatException
     * @throws DukeException When the command is invalid.
     */
    public void parse(String input) throws InvalidCommandException, EmptyTaskDescriptionException, InvalidTaskNumberException, TaskAlreadyDoneException, TaskNotDoneException, InvalidDateFormatException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                taskList.bye();
                break;
            case "list":
                taskList.printTasks();
                break;
            case "delete":
                taskList.deleteTask(input);
                break;
            case "mark":
                taskList.markTask(input);
                break;
            case "unmark":
                taskList.unmarkTask(input);
                break;
            case "todo":
                taskList.addTodo(input);
                break;
            case "deadline":
                taskList.addDeadline(input);
                break;
            case "event":
                taskList.addEvent(input);
                break;
            case "date":
                taskList.eventsOndate(input);
                break;
            default:
                throw new InvalidCommandException("Unknown command: " + input);
        }
    }
}
