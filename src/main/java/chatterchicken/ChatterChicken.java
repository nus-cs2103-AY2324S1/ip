package chatterchicken;

import chatterchicken.command.Command;
import chatterchicken.data.exception.CCException;
import chatterchicken.parser.Parser;
import chatterchicken.storage.Storage;
import chatterchicken.tasklist.TaskList;
import chatterchicken.ui.Ui;

import java.util.Scanner;

/**
 * The {@code ChatterChicken} class is the main class for the ChatterChicken task manager application.
 * It initializes and coordinates the application's components, such as the UI, parser, storage, and task list.
 * The class also defines the main method for launching the application and the main loop for user interaction.
 */
public class ChatterChicken {

    public static final String PATH = "src/main/data/task-list.txt";
    private TaskList tasks;
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;

    /**
     * Initializes the ChatterChicken application by creating instances of the UI, parser, and storage.
     */
    public ChatterChicken() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(parser);
    }

    /**
     * The main entry point of the ChatterChicken application.
     * Initializes the application, runs the main loop, and catches and displays exceptions.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ChatterChicken chatterChicken = new ChatterChicken();
        chatterChicken.run();
    }

    /**
     * Initiates the main loop of the ChatterChicken application.
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
    private void executeCommand(Command command) throws CCException {
        String action = command.getAction();
        String taskDescription = command.getTaskDescription();
        switch (action) {
        case "list":
            tasks.printList();
            break;
        case "mark":
            tasks.markTask(taskDescription);
            break;
        case "unmark":
            tasks.unmarkTask(taskDescription);
            break;
        case "delete":
            tasks.deleteTask(taskDescription);
            break;
        case "find":
            tasks.find(taskDescription);
            break;
        case "todo":
        case "deadline":
        case "event":
            tasks.addTask(parser.parseTask(action, taskDescription));
            break;
        default:
            throw new CCException("OOPS!!! I'm sorry, but I don't know what that means :<");
        }
    }
}