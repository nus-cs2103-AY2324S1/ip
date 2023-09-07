package duke;

import duke.command.DukeException;
import duke.command.Parser;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * The Duke class represents a chatbot application.
 * It allows users to add, mark as done, mark as not done, delete, and list 3 different type of tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     * It displays a welcome message, reads user commands,
     * and performs corresponding actions until the user exits.
     */
    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            try {
                if (Parser.isBye(command)) {
                    ui.showGoodbyeMessage();
                    storage.saveTasksToFile(tasks.getAllTasks());
                    break;
                } else if (Parser.isList(command)) {
                    ui.showTaskList(tasks.getAllTasks());
                } else if (Parser.isMarkDone(command)) {
                    int taskIndex = Parser.extractTaskIndex(command);
                    tasks.markAsDone(taskIndex);
                    ui.showTaskMarkedAsDone(tasks.getTask(taskIndex));
                    storage.saveTasksToFile(tasks.getAllTasks());
                } else if (Parser.isMarkNotDone(command)) {
                    int taskIndex = Parser.extractTaskIndex(command);
                    tasks.markAsNotDone(taskIndex);
                    ui.showTaskMarkedAsNotDone(tasks.getTask(taskIndex));
                    storage.saveTasksToFile(tasks.getAllTasks());
                } else if (Parser.isDelete(command)) {
                    int taskIndex = Parser.extractTaskIndex(command);
                    Task deletedTask = tasks.deleteTask(taskIndex);
                    ui.showTaskDeleted(deletedTask, tasks.getTotalTasks());
                    storage.saveTasksToFile(tasks.getAllTasks());
                } else {
                    Task newTask = Parser.parseTask(command);
                    tasks.addTask(newTask);
                    ui.showTaskAdded(newTask, tasks.getTotalTasks());
                    storage.saveTasksToFile(tasks.getAllTasks());
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method to start the Duke application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/duke/duke.txt").run();
    }
}