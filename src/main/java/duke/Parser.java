package duke;

public class Parser {

    private final TaskList taskList;

    private final Storage storage;
    private final String userInput;

    private boolean bye;

    public Parser(String userInput, TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.userInput = userInput;
        this.storage = storage;
        this.bye = false;
    }

    public void parse() {
        String[] input = this.userInput.split(" ", 2);
        Ui.horizontalLine();
        try {
            switch (input[0]) {
            case "list":
                this.taskList.listTasks();
                break;
            case "mark":
            case "unmark":
                try {
                    int taskNum = Integer.parseInt(input[1]);
                    this.taskList.markTasks(input, taskNum);
                    storage.rewriteFile(this.taskList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("BEEPBEEP! You forgot to give a task number!");
                } catch (NumberFormatException e) {
                    throw new DukeException("WOIWOI! That is an invalid input!");
                }
                break;
            case "bye":
                Ui.print("WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!");
                this.bye = true;
                break;
            case "todo":
                try {
                    this.taskList.addTask(input[1]);
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
                    this.taskList.deadlineOrEventTask(input[0], remainLine);
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
            default:
                throw new DukeException("Can you hear the siren? " +
                        "Because I don't know what that means!");
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
