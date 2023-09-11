package taskmate.main;

import java.io.IOException;

import taskmate.commands.Command;
import taskmate.exceptions.EmptyByException;
import taskmate.exceptions.EmptyFromException;
import taskmate.exceptions.EmptyToException;
import taskmate.exceptions.InvalidByException;
import taskmate.exceptions.InvalidCommandTypeException;
import taskmate.exceptions.InvalidDescriptionException;
import taskmate.exceptions.InvalidFromException;
import taskmate.exceptions.InvalidToException;
import taskmate.exceptions.NoDataException;
import taskmate.exceptions.NotAnIntegerException;
import taskmate.tools.Parser;
import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;


/**
 * TaskMate is the class whose objects represent task-tracking applications
 */
public class TaskMate {

    private static final String CHATBOT_NAME = "TaskMate";
    private static final String DEFAULT_SAVE_TASK_FILE_PATH = "./data/saved_tasks.txt";

    /**
     * An enum that represents the different command types which TaskMate supports
     */
    public enum CommandTypes {
        list, bye, todo, deadline, event, mark, unmark, delete, help, find
    }

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    TaskMate() {
        this.ui = new Ui(CHATBOT_NAME);
        this.storage = new Storage(DEFAULT_SAVE_TASK_FILE_PATH);
        this.tasks = new TaskList();

        // Load existing tasks from disk
        try {
            String fileContents = storage.readFromFile();
            this.tasks = new TaskList(fileContents);
        } catch (IOException e) {
            ui.printFileNotFoundResponse(storage.getSaveFilePath());
        } catch (NoDataException e) {
            ui.printNoDataResponse();
        }
    }

    public static void main(String[] args) {
        new TaskMate().run();
    }

    /**
     * Starts up TaskMate by greeting user and waiting for user command.
     * The method does not terminate until the user command "bye" is entered.
     */
    public void run() {

        // Greets user
        ui.greetUser();
        ui.promptUser();

        // Reading user input
        String userInput;
        Command command;
        while (true) {
            userInput = ui.nextLine();

            // Parse user input
            try {
                command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.getIsExit()) {
                    break;
                }
            } catch (InvalidCommandTypeException e) {
                ui.printInvalidCommandTypeExceptionResponse();
                System.out.println(e); // todo: Remove for production code
            } catch (InvalidDescriptionException e) {
                ui.printInvalidCommandTypeExceptionResponse(); // todo: Add new message for chatbot
                System.out.println(e); // todo: Remove for production code
            } catch (EmptyByException e) {
                ui.printEmptyByExceptionResponse();
                System.out.println(e); // todo: Remove for production code
            } catch (InvalidByException e) {
                ui.printInvalidByExceptionResponse();
                System.out.println(e);
            } catch (InvalidToException e) {
                ui.printInvalidToExceptionResponse();
                System.out.println(e);
            } catch (EmptyToException e) {
                ui.printEmptyToExceptionResponse();
                System.out.println(e);
            } catch (InvalidFromException e) {
                ui.printInvalidFromExceptionResponse();
                System.out.println(e);
            } catch (EmptyFromException e) {
                ui.printEmptyFromExceptionResponse();
                System.out.println(e);
            } catch (NotAnIntegerException e) {
                ui.printNotAnIntegerExceptionResponse();
                System.out.println(e);
            }
        }
    }

    /**
     * Parses and executes the user's input and returns a String object that is TaskMate's reply to that user input.
     * @param userInput A String that represents the user's command
     * @return A String that represents TaskMate's reply to the user's command
     */
    public String getResponse(String userInput) {

        // Parse user input
        try {
            Command command = Parser.parse(userInput);
            command.execute(tasks, ui, storage);
        } catch (InvalidCommandTypeException e) {
            ui.printInvalidCommandTypeExceptionResponse();
        } catch (InvalidDescriptionException e) {
            ui.printInvalidCommandTypeExceptionResponse(); // todo: Add new message for chatbot
        } catch (EmptyByException e) {
            ui.printEmptyByExceptionResponse();
        } catch (InvalidByException e) {
            ui.printInvalidByExceptionResponse();
        } catch (InvalidToException e) {
            ui.printInvalidToExceptionResponse();
        } catch (EmptyToException e) {
            ui.printEmptyToExceptionResponse();
        } catch (InvalidFromException e) {
            ui.printInvalidFromExceptionResponse();
        } catch (EmptyFromException e) {
            ui.printEmptyFromExceptionResponse();
        } catch (NotAnIntegerException e) {
            ui.printNotAnIntegerExceptionResponse();
        }
        return ui.popStoredMessage();
    }

}
