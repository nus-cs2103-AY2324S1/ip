package taskmate.main;

import java.io.IOException;

import taskmate.commands.Command;
import taskmate.exceptions.*;
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
        } catch (FileCorruptedException e) {
            ui.printFileCorruptedResponse(e);
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
        while (true) {
            String userInput = ui.nextLine();

            // Parse user input
            try {
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.getIsExit()) {
                    break;
                }
            } catch (Exception e) {
                handleException(e);
            }
        }
    }

    private void handleException(Exception e) {
        if (e instanceof InvalidCommandTypeException
                | e instanceof InvalidDescriptionException) {
            ui.printInvalidCommandTypeExceptionResponse();
        } else if (e instanceof EmptyByException) {
            ui.printEmptyByExceptionResponse();
        } else if (e instanceof InvalidByException) {
            ui.printInvalidByExceptionResponse();
        } else if (e instanceof InvalidToException) {
            ui.printInvalidToExceptionResponse();
        } else if (e instanceof EmptyToException) {
            ui.printEmptyToExceptionResponse();
        } else if (e instanceof InvalidFromException) {
            ui.printInvalidFromExceptionResponse();
        } else if (e instanceof EmptyFromException) {
            ui.printEmptyFromExceptionResponse();
        } else if (e instanceof NotAnIntegerException) {
            ui.printNotAnIntegerExceptionResponse();
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
        } catch (Exception e) {
            handleException(e);
        }
        return ui.popStoredMessage();
    }

}
