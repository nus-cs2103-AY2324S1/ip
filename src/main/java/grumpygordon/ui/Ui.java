package grumpygordon.ui;

import java.util.Scanner;

import grumpygordon.commands.Command;
import grumpygordon.commands.ListCommand;
import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.parser.Parser;
import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;



/**
 * Represents the user interface of the chatbot.
 */
public class Ui {

    /**
     * Separator for responses.
     */
    public static final String SEPARATOR = "    ____________________________________________________________\n";

    /**
     * Indentation for responses.
     */
    private static final String INDENTATION = "     ";

    /**
     * Intro string when chatbot starts.
     */
    private static final String INTRO = INDENTATION + "Oi! I'm Grumpy Gordon.\n"
            + INDENTATION + "Why are you bothering me?\n";

    /**
     * Outro string when chatbot ends.
     */
    private static final String OUTRO = INDENTATION + "Bye. Hope to never see you again.\n";

    /**
     * Scanner to read user input.
     */
    private final Scanner scanner;

    /**
     * List of tasks by the user.
     */
    private final TaskList tasks;

    /**
     * Storage of tasks.
     */
    private final Storage storage;

    /**
     * Constructor of Ui.
     *
     * @param tasks List of tasks by user.
     * @param storage Storage of tasks.
     */
    public Ui(TaskList tasks, Storage storage) {
        this.scanner = new Scanner(System.in);
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Main loop for GrumpyGordon.
     */
    public void run() {
        Command command = new ListCommand();
        String userInput;
        this.showIntroMessage();

        while (!command.isExit()) {
            try {
                userInput = this.scanner.nextLine();
                command = Parser.parseCommand(userInput, this.tasks);
                command.execute(tasks, this, storage);
            } catch (GrumpyGordonException e) {
                this.showErrorMessage(e);
            }
        }
        scanner.close();
    }

    /**
     * Shows the intro message when GrumpyGordon starts.
     */
    public void showIntroMessage() {
        System.out.println(SEPARATOR + INTRO + SEPARATOR);
    }

    /**
     * Shows the outro message when GrumpyGordon ends.
     */
    public void showOutroMessage() {
        System.out.println(SEPARATOR + OUTRO + SEPARATOR);
    }

    /**
     * Shows the error message when GrumpyGordon encounters an error.
     *
     * @param e GrumpyGordonException that was thrown
     */
    public void showErrorMessage(GrumpyGordonException e) {
        System.out.println(SEPARATOR + INDENTATION + e.getMessage() + SEPARATOR);
    }

    /**
     * Shows the command message when GrumpyGordon executes a command.
     *
     * @param message Message to be shown
     */
    public void showCommandMessage(String message) {
        System.out.println(SEPARATOR + message + SEPARATOR);
    }
}
