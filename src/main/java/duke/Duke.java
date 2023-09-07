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
        this.ui = new Ui();

        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.loadTasksFromFile());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     * It displays a welcome message, reads user commands,
     * and performs corresponding actions until the user exits.
     */
    public void run() {
        this.ui.showWelcomeMessage();

        int taskIndex;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();

            try {
                if (Parser.isBye(command)) {
                    this.ui.showGoodbyeMessage();

                    this.storage.saveTasksToFile(this.tasks.getAllTasks());
                    break;
                } else if (Parser.isList(command)) {
                    this.ui.showTaskList(this.tasks.getAllTasks());
                } else if (Parser.isMarkDone(command)) {
                    taskIndex = Parser.extractTaskIndex(command);

                    this.tasks.markAsDone(taskIndex);

                    this.ui.showTaskMarkedAsDone(this.tasks.getTask(taskIndex));

                    this.storage.saveTasksToFile(this.tasks.getAllTasks());
                } else if (Parser.isMarkNotDone(command)) {
                    taskIndex = Parser.extractTaskIndex(command);

                    this.tasks.markAsNotDone(taskIndex);

                    this.ui.showTaskMarkedAsNotDone(this.tasks.getTask(taskIndex));

                    this.storage.saveTasksToFile(this.tasks.getAllTasks());
                } else if (Parser.isDelete(command)) {
                    taskIndex = Parser.extractTaskIndex(command);

                    Task deletedTask = this.tasks.deleteTask(taskIndex);

                    this.ui.showTaskDeleted(deletedTask, this.tasks.getTotalTasks());

                    this.storage.saveTasksToFile(this.tasks.getAllTasks());
                } else {
                    Task newTask = Parser.parseTask(command);

                    this.tasks.addTask(newTask);

                    this.ui.showTaskAdded(newTask, this.tasks.getTotalTasks());

                    this.storage.saveTasksToFile(this.tasks.getAllTasks());
                }
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
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