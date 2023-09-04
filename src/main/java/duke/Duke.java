package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeBadInputException;
import javafx.application.Application;

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

        // try to establish a connection to the file
        // set this.storage to null if not possible
        try {
            this.storage = new Storage(filePath);
        } catch (IOException e) {
            this.ui.getErrorMessage(
                    "has some internal problem and is unable to help you today, please contact quacks mum");
            this.storage = null;
        } catch (DukeBadInputException e) {
            this.ui.getErrorMessage(filePath + " is not a text file, please provide a file!");
            this.storage = null;
        }

        // read from storage, throws an error when unable to rewrite to the storage file
        if (this.storage != null) {
            this.taskList = new TaskList();
            try {
                // check for corrupted files
                if (this.taskList.loadTasks(this.storage.readFile())) {
                    this.ui.getUnexpectedErrorMessage("Some task are corrupted,"
                            + " attempting to recover uncorrupted tasks");
                    if (!this.storage.rewriteAll(this.taskList.getAllTask())) {
                        this.ui.getUnexpectedErrorMessage(
                                "not all tasks were successfully written, please contact my mother :( ");
                    }
                }
            } catch (IOException e) {
                this.ui.getUnexpectedErrorMessage("error when rewriting to storage: " + e.getMessage());
            }
        }
    }

    /**
     * Entry point of the program
     *
     * @param args System arguments, should be empty
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
        // new Duke("data/data.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (DukeBadInputException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return e.getMessage()
                    + ", quack only understand numbers, please input a numeric value!";
        } catch (DateTimeParseException e) {
            return e.getMessage()
                    + "Quack only understands date in this format: "
                    + "YYYY-MM-DD HH:MM, do give the hours in 24hours format";
        }
    }

    /**
     * Ensure that the files are written
     */
    public void close() {
        this.storage.close();
    }
}
