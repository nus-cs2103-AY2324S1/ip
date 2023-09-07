package chatterchicken;

import chatterchicken.command.Command;
import chatterchicken.data.exception.CCException;
import chatterchicken.parser.Parser;
import chatterchicken.storage.Storage;
import chatterchicken.tasklist.TaskList;
import chatterchicken.ui.Ui;

import java.util.Scanner;


public class ChatterChicken {

    public static final String PATH = "src/main/data/task-list.txt";

    private TaskList tasks;

    private Parser parser;
    private Storage storage;

    private Ui ui;

    public ChatterChicken() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(parser);
    }

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