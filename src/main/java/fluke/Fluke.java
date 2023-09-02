package fluke;

import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;
import fluke.tasks.Task;

import java.io.IOException;
import java.util.Scanner;

/**
 * Fluke is a chatbot which gets your commands right by fluke. Fluke's main goal is to help manage tasks.
 */
public class Fluke {
    private final static String SAVE_FILE_NAME = "fluke.txt";

    /**
     * Enumerates all possible commands.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a Fluke chatbot.
     * @param filePath file path in which Fluke stores its tasks.
     */
    public Fluke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FlukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main entry point into Fluke's program.
     * @param args Arguments given for Fluke's program.
     */
    public static void main(String[] args) {
        // initialise Fluke
        Fluke fluke = new Fluke(SAVE_FILE_NAME);
        fluke.run();
    }

    /**
     * Runs Fluke's program, accepting input from the user and outputting appropriately.
     */
    public void run() {
        // greet the user
        this.ui.greet();
        // initialise scanner to check for user input
        Scanner scanner = new Scanner(System.in);
        boolean waitingForInput = true;
        while (waitingForInput) {
            // check for user commands
            try {
                String nextCommand = scanner.nextLine();
                Command commandType = Parser.parseCommand(nextCommand);
                switch (commandType) {
                case BYE:
                    waitingForInput = false;
                    this.ui.sayBye();
                    break;
                case LIST:
                    this.ui.showListOfTasks(tasks);
                    break;
                case MARK:
                case UNMARK:
                case DELETE:
                case TODO:
                case DEADLINE:
                case EVENT:
                    changeTodoList(commandType, nextCommand);
                    break;
                default:
                    throw new InvalidInputException();
                }
            } catch (FlukeException d) {
                this.ui.showError(d.getMessage());
            }
        }
    }

    /**
     * Tells Fluke to add a Todo to its tasks.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while adding the task, for instance, with invalid inputs.
     */
    public void addTodo(String command) throws FlukeException {
        String parsedDescription = Parser.parseTodoCommand(command);
        Task taskAdded = this.tasks.addTodo(parsedDescription);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    /**
     * Tells Fluke to add a Deadline to its tasks.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while adding the task, for instance, with invalid inputs.
     */
    private void addDeadline(String command) throws FlukeException {
        String[] parsedCommand = Parser.parseDeadlineCommand(command);
        String description = parsedCommand[0];
        String by = parsedCommand[1];
        Task taskAdded = this.tasks.addDeadline(description, by);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    /**
     * Tells Fluke to add an Event to its tasks.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while adding the task, for instance, with invalid inputs.
     */
    private void addEvent(String command) throws FlukeException {
        String[] parsedCommand = Parser.parseEventCommand(command);
        String description = parsedCommand[0];
        String from = parsedCommand[1];
        String to = parsedCommand[2];
        Task taskAdded = this.tasks.addEvent(description, from, to);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    /**
     * Tells Fluke to mark a task as done.
     * @param nextCommand Command given to Fluke.
     * @throws FlukeException if an error occurs while marking a task as done, for instance, with invalid inputs.
     */
    private void markTaskAsDone(String command) throws FlukeException {
        int index = Parser.parseMarkAsDoneCommand(command);
        Task taskMarked = tasks.markTaskAsDone(index);
        this.ui.showTaskMarkedAsDone(taskMarked);
    }

    /**
     * Tells Fluke to mark a task as not done.
     * @param nextCommand Command given to Fluke.
     * @throws FlukeException if an error occurs while marking a task as not done, for instance, with invalid inputs.
     */
    private void markTaskAsUndone(String command) throws FlukeException {
        int index = Parser.parseMarkAsUndoneCommand(command);
        Task taskMarked = tasks.markTaskAsUndone(index);
        this.ui.showTaskMarkedAsUndone(taskMarked);
    }

    /**
     * Tells Fluke to delete a task.
     * @param nextCommand Command given to Fluke.
     * @throws FlukeException if an error occurs while deleting a task, for instance, with invalid inputs.
     */
    private void deleteTask(String command) throws FlukeException {
        int index = Parser.parseDeleteCommand(command);
        Task deleted = tasks.deleteTask(index);
        this.ui.showTaskDeleted(deleted, tasks);
    }

    /**
     * Helper function for additional logic related to changing the list.
     * @param commandType type of command
     * @param nextCommand the content in the command
     * @throws FlukeException if there is an error relating to Fluke's operations, or if there is an error saving.
     */
    private void changeTodoList(Command commandType, String nextCommand) throws FlukeException {
        // 1. make changes to the list
        switch (commandType) {
        case MARK:
            markTaskAsDone(nextCommand);
            break;
        case UNMARK:
            markTaskAsUndone(nextCommand);
            break;
        case DELETE:
            deleteTask(nextCommand);
            break;
        case TODO:
            addTodo(nextCommand);
            break;
        case DEADLINE:
            addDeadline(nextCommand);
            break;
        case EVENT:
            addEvent(nextCommand);
            break;
        default:
            // should not occur
            throw new FlukeException("An unknown error has occurred.");
        }
        // 2. save to file
        try {
            this.storage.save(tasks.getListOfTasks());
        } catch (IOException i) {
            throw new FlukeException(i.getMessage());
        }
    }
}
