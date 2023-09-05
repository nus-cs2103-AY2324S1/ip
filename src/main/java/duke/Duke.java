package duke;

import duke.task.TaskList;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Duke is a task management application that allows users to manage tasks
 * by adding, marking as done, deleting, and listing tasks.
 */
public class Duke {
    private final String name;
    private final TaskList taskList;

    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param name     The name of the Duke application.
     * @param filePath The path to the file used for storing tasks.
     */
    public Duke(String name, String filePath) {
        this.name = name;
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        storage.loadTasks(this.taskList);
    }

    /**
     * The main method that initializes and runs the Duke application.
     *
     * @param args Command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        Duke duke = new Duke("Duke", "src/main/java/resource/duke.txt");
        duke.run();
    }

    /**
     * Handles the user input and performs corresponding actions.
     *
     * @param input The user's input command.
     */
    public void handleInput(String input) {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];

        try {
            switch (command) {
            case "list":
                this.taskList.showAllTasks(this.ui);
                break;
            case "mark":
                int markTaskNumber = Parser.parseInt(inputArr[1]);
                this.taskList.markTaskAsDone(markTaskNumber, this.storage, this.ui);
                break;
            case "unmark":
                int unmarkTaskNumber = Parser.parseInt(inputArr[1]);
                this.taskList.unmarkTaskAsDone(unmarkTaskNumber, this.storage, this.ui);
                break;
            case "todo":
                String todoDescription = Parser.validateToDoCommand(input);
                this.taskList.addTask(todoDescription, this.storage, this.ui);
                break;
            case "deadline":
                String deadlineDescription = Parser.validateDeadlineCommand(input);
                this.taskList.addTask(deadlineDescription, this.storage, this.ui);
                break;
            case "event":
                String eventDescription = Parser.validateEventCommand(input);
                this.taskList.addTask(eventDescription, this.storage, this.ui);
                break;
            case "delete":
                int deleteTaskNumber = Parser.parseInt(inputArr[1]);
                this.taskList.deleteTask(deleteTaskNumber, this.storage, this.ui);
                break;
            case "find":
                String keyword = input.replace("find", "").trim();
                this.taskList.findTasks(keyword, this.ui);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            ui.formatPrintMessage(e.getMessage());
        }
    }

    /**
     * Runs the Duke application, handling user input and commands.
     */
    public void run() {
        ui.greet(this.name);
        String command = ui.readCommand();
        while (!command.equals("bye")) {
            handleInput(command);
            command = ui.readCommand();
        }
        ui.exit();
    }
}
