package duke;

/**
 * This class encapsulates making sense of the user command.
 */
public class Parser {

    private TaskList taskList;
    private Storage storage;
    private String userInput;
    private boolean bye;


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
        this.bye = false;
    }

    /**
     * Checks the user input and perform the corresponding action based on the input.
     */
    public void parse() {
        String[] input = this.userInput.split(" ", 2);
        Ui.horizontalLine();
        try {
            switch (input[0]) {
            case "list":
                this.taskList.listTasks(this.taskList, false);
                break;
            case "mark":
            case "unmark":
                try {
                    int taskNum = Integer.parseInt(input[1]);
                    this.taskList.markOrUnmarkTask(input[0], taskNum);
                    storage.rewriteFile(this.taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("BEEPBEEP! You forgot to give a task number!");
                } catch (NumberFormatException e) {
                    throw new DukeException("WOIWOI! That is an invalid input!");
                }
                break;
            case "bye":
                Ui.goodbyeMessage();
                this.bye = true;
                break;
            case "todo":
                try {
                    this.taskList.addTodoTask(input[1]);
                    Ui.addedTaskOutput(this.taskList);
                    storage.addLineToFile(this.taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("BEEPBEEP! You forgot to give a description!");
                }
                break;
            case "deadline":
            case "event":
                try {
                    String[] remainLine = input[1].split(" /", 2);
                    this.taskList.addDeadlineOrEventTask(input[0], remainLine);
                    Ui.addedTaskOutput(this.taskList);
                    storage.addLineToFile(this.taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("BEEPBEEP! You forgot to give a description or date/time!");
                }
                break;
            case "delete":
                try {
                    int taskNum = Integer.parseInt(input[1]);
                    this.taskList.deleteTasks(taskNum);
                    storage.rewriteFile(this.taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("BEEPBEEP! You forgot to give a task number!");
                } catch (NumberFormatException e) {
                    throw new DukeException("WOIWOI! That is an invalid input!");
                }
                break;
            case "find":
                try {
                    if (input[1].isEmpty()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    this.taskList.findTasks(input[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("BEEPBEEP! You forgot to give a keyword for me to search!");
                }
                break;
            default:
                throw new DukeException("Can you hear the siren? "
                        + "Because I don't know what that means!");
            }
        } catch (DukeException e) {
            Ui.showExceptionError(e);
        }
        Ui.horizontalLine();
    }

    public boolean isBye() {
        return this.bye;
    }
}
