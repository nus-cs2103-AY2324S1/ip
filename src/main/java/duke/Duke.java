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
     */
    public Duke() {
        String filePath = "data/duke.txt";
        this.ui = new Ui();
        System.out.println(this.ui.getWelcomeMessage());
        this.storage = new Storage(filePath);
        // load data file
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            System.out.println(this.ui.showError(e.getMessage()));
            System.out.println(this.ui.getLine());
            this.tasks = new TaskList();
        }
    }

    /**
     * Main method.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
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
                System.out.println(message.send());
                this.storage.writeToFile(this.tasks);
            } catch (InvalidInputException e) {
                System.out.println(this.ui.showError(e.getMessage()));
            } catch (InvalidCommandException e) {
                System.out.println(this.ui.showMenu());
            } catch (InvalidIndexException e) {
                System.out.println(this.ui.showInvalidIndexError());
            } catch (IOException e) {
                System.out.println(this.ui.showSaveDataError());
            } finally {
                System.out.println(this.ui.getLine());
            }
        }
    }

    /**
     * Returns the response to the user input.
     * @param userInput User input.
     * @return Response to the user input.
     */
    public String getResponse(String userInput) {
        try {
            Message message = UserInputParser.parse(userInput, this.tasks);
            this.storage.writeToFile(this.tasks);
            return message.send();
        } catch (InvalidInputException e) {
            return this.ui.showError(e.getMessage());
        } catch (InvalidCommandException e) {
            return this.ui.showMenu();
        } catch (InvalidIndexException e) {
            return this.ui.showInvalidIndexError();
        } catch (IOException e) {
            return this.ui.showSaveDataError();
        }
    }
}
