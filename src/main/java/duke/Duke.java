package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.message.Message;
import duke.parser.UserInputParser;
import duke.task.TaskList;

/**
 * Represents the Duke program.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath Path to file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        // load data file
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            this.ui.showError(e.getMessage());
            this.ui.showLine();
            this.tasks = new TaskList();
        }
    }

    /**
     * Main method.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs the program.
     */
    private void run() {
        Scanner sc = new Scanner(System.in);
        while (UserInputParser.getIsActive()) {
            String userInput = sc.nextLine();
            try {
                Message message = UserInputParser.parse(userInput, this.tasks);
                message.send();
                this.storage.writeToFile(this.tasks);
            } catch (InvalidInputException e) {
                this.ui.showError(e.getMessage());
            } catch (InvalidCommandException e) {
                this.ui.showMenu();
            } catch (InvalidIndexException e) {
                this.ui.showInvalidIndexError();
            } catch (IOException e) {
                this.ui.showSaveDataError();
            } finally {
                this.ui.showLine();
            }
        }
    }
}
