package duke;

/**
 * This class encapsulates making sense of the user command.
 */
public class Parser {

    private TaskList taskList;
    private Storage storage;
    private String userInput;
    private boolean isGoodbye;


    /**
     * Constructor for Parser
     *
     * @param userInput the user input that will be going through the validation check
     * @param taskList the corresponding TaskList object that contains the tasks
     * @param storage the corresponding Storage object that contains the file
     */
    public Parser(String userInput, TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.userInput = userInput;
        this.storage = storage;
        isGoodbye = false;
    }

    /**
     * Checks the user input and perform the corresponding action based on the input.
     */
    public String parse() {
        String[] input = userInput.trim().split(" ", 2);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            switch (input[0]) {
            case "list":
                listCommand(stringBuilder);
                break;
            case "mark":
            case "unmark":
                markUnmarkCommand(input, stringBuilder);
                break;
            case "bye":
                byeCommand(stringBuilder);
                break;
            case "todo":
                toDoCommand(input, stringBuilder);
                break;
            case "deadline":
            case "event":
                deadlineEventCommand(input, stringBuilder);
                break;
            case "delete":
                deleteCommand(input, stringBuilder);
                break;
            case "find":
                findCommand(input, stringBuilder);
                break;
            case "remind":
                remindCommand(stringBuilder);
                break;
            default:
                throw new DukeException("Can you hear the siren? "
                        + "Because I don't know what that means!");
            }
        } catch (DukeException e) {
            return Ui.showExceptionError(e);
        }

        assert !stringBuilder.toString().isEmpty();
        return stringBuilder.toString();
    }

    public boolean isBye() {
        return isGoodbye;
    }

    private void listCommand(StringBuilder stringBuilder) {
        stringBuilder.append(taskList.listTasks(taskList, false));
    }

    private void markUnmarkCommand(String[] input, StringBuilder stringBuilder) throws DukeException {
        try {
            int taskNum = Integer.parseInt(input[1]);
            storage.rewriteFile(taskList);
            taskList.markOrUnmarkTask(input[0], taskNum, stringBuilder);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("BEEPBEEP! You forgot to give a task number!");
        } catch (NumberFormatException e) {
            throw new DukeException("WOIWOI! That is an invalid input!");
        }
    }

    private void byeCommand(StringBuilder stringBuilder) {
        stringBuilder.append(Ui.getGoodbyeMessage());
        isGoodbye = true;
    }

    private void toDoCommand(String[] input, StringBuilder stringBuilder) throws DukeException {
        try {
            taskList.addTodoTask(input[1]);
            stringBuilder.append(Ui.addTaskOutput(taskList));
            storage.addLineToFile(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("BEEPBEEP! You forgot to give a description!");
        }
    }

    private void deadlineEventCommand(String[] input, StringBuilder stringBuilder) throws DukeException {
        try {
            String[] remainLine = input[1].split(" /", 2);
            taskList.addDeadlineOrEventTask(input[0], remainLine);
            stringBuilder.append(Ui.addTaskOutput(taskList));
            storage.addLineToFile(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("BEEPBEEP! You forgot to give a description or date/time!");
        }
    }

    private void deleteCommand(String[] input, StringBuilder stringBuilder) throws DukeException {
        try {
            int taskNum = Integer.parseInt(input[1]);
            taskList.deleteTasks(taskNum, stringBuilder);
            storage.rewriteFile(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("BEEPBEEP! You forgot to give a task number!");
        } catch (NumberFormatException e) {
            throw new DukeException("WOIWOI! That is an invalid input!");
        }
    }

    private void findCommand(String[] input, StringBuilder stringBuilder) throws DukeException {
        try {
            if (input[1].isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            taskList.findTasks(input[1].trim().toUpperCase(), stringBuilder);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("BEEPBEEP! You forgot to give a keyword for me to search!");
        }
    }

    private void remindCommand(StringBuilder stringBuilder) {
        taskList.doReminder(stringBuilder);
    }
}
