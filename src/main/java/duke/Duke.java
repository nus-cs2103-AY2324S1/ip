package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.ListCommand;
import duke.exception.DukeBadInputException;

/**
 * A task manager that initialise a quack instance
 */
public class Duke {

    /**
     * Instance handling all the user interface
     */
    private Ui ui;
    /**
     * Instance handling the tasks state
     */
    private TaskList taskList;
    /**
     * Path to the storage, default is ./data/data.txt
     */
    private Storage storage;

    /**
     * Construct a new Duke object which uses filePath as the storage
     *
     * @param filePath - path to the storage file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.ui.welcomeMessage();

        // try to establish a connection to the file
        // set this.storage to null if not possible
        try {
            this.storage = new Storage(filePath);
        } catch (IOException e) {
            this.ui.errorMessage(
                    "has some internal problem and is unable to help you today, please contact quacks mum");
            this.ui.println(e.getMessage());
            this.storage = null;
        } catch (DukeBadInputException e) {
            this.ui.errorMessage(filePath + " is not a text file, please provide a file!");
            this.storage = null;
        }

        // read from storage, throws an error when unable to rewrite to the storage file
        if (this.storage != null) {
            this.taskList = new TaskList();
            try {
                // check for corrupted files
                if (this.taskList.loadTasks(this.storage.readFile())) {
                    this.ui.unexpectedError("Some task are corrupted, attempting to recover uncorrupted tasks");
                    if (!this.storage.rewriteAll(this.taskList.getAllTask())) {
                        this.ui.unexpectedError(
                                "not all tasks were successfully written, please contact my mother :( ");
                    }
                }
            } catch (IOException e) {
                this.ui.unexpectedError("error when rewriting to storage: " + e.getMessage());
            }
        }
    }

    /**
     * Entry point of the program
     *
     * @param args  System arguments, should be empty
     */
    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }

    /**
     * Start the running the quack program, will not run if storage is null
     */
    private void run() {
        if (this.storage == null) {
            this.ui.goodbyeMessage();
            return;
        }

        // Prints out the current items in the list
        try {
            new ListCommand().execute(this.taskList, this.ui, this.storage);
        } catch (DukeBadInputException e) {
            this.ui.unexpectedError(e.getMessage());
        }

        // start to collect commands
        this.ui.lineBreak();
        this.ui.println("");
        this.collectCommand();
        this.storage.close();

        // Goodbye Message
        this.ui.goodbyeMessage();
    }

    /**
     * Handles the collection and execution of the command until the exit command is given
     */
    private void collectCommand() {
        while (true) {
            try {
                String input = this.ui.readCommand().trim();
                if (input.isBlank()) {
                    continue;
                }
                Command command = Parser.parse(input);
                if (command.isExit()) {
                    break;
                }
                this.ui.lineBreak();
                command.execute(this.taskList, this.ui, this.storage);
            } catch (DukeBadInputException e) {
                this.ui.errorMessage(e.getMessage());
            } catch (NumberFormatException e) {
                this.ui.errorMessage(e.getMessage()
                        + ", quack only understand numbers, please input a numeric value!");
            } catch (DateTimeParseException e) {
                this.ui.errorMessage(e.getMessage());
                this.ui.println(
                        "Quack only understands date in this format: "
                                + "YYYY-MM-DD HH:MM, do give the hours in 24hours format");
            }
            this.ui.lineBreak();
            this.ui.println("");
        }
        this.ui.close();
    }

}
