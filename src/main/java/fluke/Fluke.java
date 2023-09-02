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
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND
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
        boolean isWaitingForInput = true;
        while (isWaitingForInput) {
            // check for user commands
            try {
                String nextCommand = scanner.nextLine();
                Command commandType = Parser.parseCommand(nextCommand);
                switch (commandType) {
                case BYE:
                    isWaitingForInput = false;
                    this.ui.sayBye();
                    break;
                case LIST:
                    this.ui.showListOfTasks(tasks);
                    break;
                case FIND:
                    findTask(nextCommand);
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
        String byDate = parsedCommand[1];
        Task taskAdded = this.tasks.addDeadline(description, byDate);
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
        String fromDate = parsedCommand[1];
        String toDate = parsedCommand[2];
        Task taskAdded = this.tasks.addEvent(description, fromDate, toDate);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    /**
     * Tells Fluke to mark a task as done.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while marking a task as done, for instance, with invalid inputs.
     */
    private void markTaskAsDone(String command) throws FlukeException {
        int index = Parser.parseMarkAsDoneCommand(command);
        Task taskMarked = tasks.markTaskAsDone(index);
        this.ui.showTaskMarkedAsDone(taskMarked);
    }

    /**
     * Tells Fluke to mark a task as not done.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while marking a task as not done, for instance, with invalid inputs.
     */
    private void markTaskAsUndone(String command) throws FlukeException {
        int index = Parser.parseMarkAsUndoneCommand(command);
        Task taskMarked = tasks.markTaskAsUndone(index);
        this.ui.showTaskMarkedAsUndone(taskMarked);
    }

    /**
     * Tells Fluke to delete a task.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while deleting a task, for instance, with invalid inputs.
     */
    private void deleteTask(String command) throws FlukeException {
        int index = Parser.parseDeleteCommand(command);
        Task deleted = tasks.deleteTask(index);
        this.ui.showTaskDeleted(deleted, tasks);
    }

    /**
     * Fluke attempts to find tasks with the corresponding keyword.
     * @param command the command given
     * @throws InvalidInputException if there is no keyword given.
     */
    private void findTask(String command) throws InvalidInputException {
        String keyword = Parser.parseFindCommand(command);
        this.ui.showTasksWithKeyword(this.tasks.findTask(keyword));
    }

    /**
     * Helper function for additional logic related to changing the list.
     * @param commandType type of command
     * @param command the content in the command
     * @throws FlukeException an exception related to operations with fluke.Fluke
     */
    private void changeTodoList(Command commandType, String command) throws FlukeException {
        // 1. make changes to the list
        switch (commandType) {
        case MARK:
            markTaskAsDone(command);
            break;
        case UNMARK:
            markTaskAsUndone(command);
            break;
        case DELETE:
            deleteTask(command);
            break;
        case TODO:
            addTodo(command);
            break;
        case DEADLINE:
            addDeadline(command);
            break;
        case EVENT:
            addEvent(command);
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
