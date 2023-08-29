package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The Duke class represents a task management application. It provides functionality
 * to interact with the user, manage tasks, and store task information.
 *
 * @author selwyn
 */
public class Duke {
    /** Name of chatbot */
    private static final String NAME = "duke.Duke Prince";

    /** Task list associated with this duke.Duke object */
    private static TaskList taskList;

    /** Storage associated with this Duke object */
    private Storage storage;

    /** UI associated with this Duke object */
    private Ui ui;

    /** Parser associated with this Duke object */
    private Parser parser;

    /**
     * Constructs a Duke object with the specified file path to initialize the application.
     *
     * @param filePath The file path where task data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui(NAME);
        parser = new Parser();

        String[] dirAndFilePathArr = filePath.split("/");
        String dirPath = "";
        for (int i = 0; i < dirAndFilePathArr.length - 1; i++) {
            dirPath += dirAndFilePathArr[i];
            dirPath += "/";
        }

        System.out.println(dirPath);
        storage = new Storage(dirPath, dirAndFilePathArr[dirAndFilePathArr.length - 1]);
        try {
            taskList = new TaskList(storage.retrieveTasks());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Starts the Duke application by displaying a greeting and processing user commands.
     */
    public void run() {
        ui.greet();
        boolean exitProgram = false;
        String userInput;

        while (!exitProgram) {
            try {
                userInput = ui.readCommand();
                ui.printHorizontalLine();
                Command c = this.parser.parseCommand(userInput);
                c.execute(taskList, ui, storage);
                exitProgram = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    /**
     * The main method to launch the Duke application.
     *
     * @param args Command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}