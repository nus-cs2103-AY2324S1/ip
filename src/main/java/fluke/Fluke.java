package fluke;

import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;
import fluke.tasks.Task;

import java.io.IOException;
import java.util.Scanner;

public class Fluke {
    private final static String SAVE_FILE_NAME = "fluke.txt";
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;

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

    public static void main(String[] args) {
        // initialise fluke.Fluke
        Fluke fluke = new Fluke(SAVE_FILE_NAME);
        fluke.run();
    }

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

    public void addTodo(String command) throws FlukeException {
        String parsedDescription = Parser.parseTodoCommand(command);
        Task taskAdded = this.tasks.addTodo(parsedDescription);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    private void addDeadline(String command) throws FlukeException {
        String[] parsedCommand = Parser.parseDeadlineCommand(command);
        String description = parsedCommand[0];
        String byDate = parsedCommand[1];
        Task taskAdded = this.tasks.addDeadline(description, byDate);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    private void addEvent(String command) throws FlukeException {
        String[] parsedCommand = Parser.parseEventCommand(command);
        String description = parsedCommand[0];
        String fromDate = parsedCommand[1];
        String toDate = parsedCommand[2];
        Task taskAdded = this.tasks.addEvent(description, fromDate, toDate);
        this.ui.showTaskAdded(taskAdded, tasks);
    }

    private void markTaskAsDone(String command) throws FlukeException {
        int index = Parser.parseMarkAsDoneCommand(command);
        Task taskMarked = tasks.markTaskAsDone(index);
        this.ui.showTaskMarkedAsDone(taskMarked);
    }

    private void markTaskAsUndone(String command) throws FlukeException {
        int index = Parser.parseMarkAsUndoneCommand(command);
        Task taskMarked = tasks.markTaskAsUndone(index);
        this.ui.showTaskMarkedAsUndone(taskMarked);
    }

    private void deleteTask(String command) throws FlukeException {
        int index = Parser.parseDeleteCommand(command);
        Task deleted = tasks.deleteTask(index);
        this.ui.showTaskDeleted(deleted, tasks);
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
