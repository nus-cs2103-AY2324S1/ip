package duke;

import duke.command.Command;
import duke.data.exception.CCException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Gui;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * The {@code Duke} class is the main class for the Duke task manager application.
 * It initializes and coordinates the application's components, such as the UI, parser, storage, and task list.
 * The class also defines the main method for launching the application and the main loop for user interaction.
 */
public class Duke {

    public static final String PATH = "src/main/data/task-list.txt";
    private TaskList tasks;
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;

    /**
     * Initializes the Duke application by creating instances of the UI, parser, and storage.
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(parser);
        this.tasks = new TaskList(storage.loadTasksFromFile(), ui);
    }

    /**
     * The main entry point of the Duke application.
     * Initializes the application, runs the main loop, and catches and displays exceptions.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public String getResponse(String input) throws CCException {
        if (input.equals("bye")) {
            return ui.displayFarewell();
        } else {
            Command command = parser.parseInput(input);
            String response = executeCommand(command);
            storage.saveTasksToFile(tasks);
            return response;
        }
    }

    /**
     * Initiates the main loop of the Duke application.
     * Reads user input, processes commands, and provides responses until the user chooses to exit.
     * Catches and displays exceptions.
     */
    private void run() {
        try (Scanner sc = new Scanner(System.in)) {
            tasks = new TaskList(storage.loadTasksFromFile(), ui);
            ui.displayGreeting();
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                Command command = parser.parseInput(input);
                executeCommand(command);
                storage.saveTasksToFile(tasks);
                input = sc.nextLine();
            }
        } catch (CCException e) {
            System.err.println(e.getMessage());
        }
        ui.displayFarewell();
    }

    /**
     * Executes the specified command by invoking corresponding methods on the tasks.
     * 
     * @param command The parsed user command.
     * @throws CCException If an error occurs during command execution.
     */
    private String executeCommand(Command command) throws CCException {
        String action = command.getAction();
        String taskDescription = command.getTaskDescription();
        String response;
        switch (action) {
        case "list":
            response = tasks.printList();
            break;
        case "mark":
            response = tasks.markTask(taskDescription);
            break;
        case "unmark":
            response = tasks.unmarkTask(taskDescription);
            break;
        case "delete":
            response = tasks.deleteTask(taskDescription);
            break;
        case "find":
            response = tasks.find(taskDescription);
            break;
        case "todo":
        case "deadline":
        case "event":
            response = tasks.addTask(parser.parseTask(action, taskDescription));
            break;
        default:
            throw new CCException("OOPS!!! I'm sorry, but I don't know what that means :<");
        }
        return response;
    }
}