package arona;

import arona.commands.*;
import arona.parser.Parser;
import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

import java.util.Scanner;

/**
 * Arona is a simple command-line task management application that allows users to manage their tasks.
 * Users can add tasks, mark tasks as done, delete tasks, and list all tasks.
 */
public class Arona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a new instance of the Arona application with the specified file path for data storage.
     *
     * @param filePath The file path where task data is stored.
     */
    public Arona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    /**
     * Runs the Arona application, allowing users to interact with the task management system via the command-line interface.
     */
    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().toLowerCase().trim();

            if (userInput.isEmpty()) {
                continue;
            }

            String[] inputTokens = Parser.parseUserInput(userInput);
            String command = Parser.getCommand(inputTokens);

            if (command.equals("bye")) {
                if (inputTokens.length == 1) {
                    break;
                }
            }
            try {
                switch (command) {
                case "list":
                    ListCommand listCommand = new ListCommand(tasks, ui);
                    listCommand.execute();
                    break;
                case "unmark":
                    UnMarkCommand unmarkCommand = new UnMarkCommand(tasks, ui, storage, Parser.getTaskIndex(inputTokens));
                    unmarkCommand.execute();
                    break;
                case "mark":
                    MarkCommand markCommand = new MarkCommand(tasks, ui, storage, Parser.getTaskIndex(inputTokens));
                    markCommand.execute();
                    break;
                case "todo":
                    ToDoCommand toDoCommand = new ToDoCommand(tasks, ui, storage, Parser.getToDoDescription(inputTokens));
                    toDoCommand.execute();
                    break;
                case "deadline":
                    DeadlineCommand deadlineCommand = new DeadlineCommand(tasks, ui, storage, Parser.getDeadlineDescription(inputTokens));
                    deadlineCommand.execute();
                    break;
                case "event":
                    EventCommand eventCommand = new EventCommand(tasks, ui, storage, Parser.getEventDescription(inputTokens));
                    eventCommand.execute();
                    break;
                case "delete":
                    DeleteCommand deleteCommand = new DeleteCommand(tasks, ui, storage, Parser.getTaskIndex(inputTokens));
                    deleteCommand.execute();
                    break;
                default:
                    ui.showInvalidArgumentMessage();
                    break;
                }
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
        ui.showGoodbyeMessage();
    }

    /**
     * The main entry point for the Arona application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Arona("src/main/java/arona/data/arona.txt").run(); // if jar file use "../../src/main/java/arona/data/arona.txt"
    }
}

