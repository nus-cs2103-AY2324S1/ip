package fluke;

import java.io.IOException;

import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;
import fluke.tasks.Task;

/**
 * Fluke is a chatbot which gets your commands right by fluke. Fluke's main goal is to help manage tasks.
 */
public class Fluke {

    private static final String SAVE_FILE_NAME = "fluke.txt";

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
    public Fluke(String filePath) throws FlukeException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructs a Fluke chatbot.
     * @throws FlukeException when an error is encountered during the creation of a Fluke chatbot.
     */
    public Fluke() throws FlukeException {
        this.ui = new Ui();
        this.storage = new Storage(SAVE_FILE_NAME);
        tasks = new TaskList(storage.load());
    }

    public String getGreeting() {
        return this.ui.getGreeting();
    }

    public String getResponse(String input) {
        try {
            Command commandType = Parser.parseCommand(input);
            switch (commandType) {
            case BYE:
                return this.ui.getBye();
            case LIST:
                return this.ui.getListOfTasks(tasks);
            case FIND:
                return findTask(input);
            case MARK:
            case UNMARK:
            case DELETE:
            case TODO:
            case DEADLINE:
            case EVENT:
                return changeTodoList(commandType, input);
            default:
                throw new InvalidInputException();
            }
        } catch (FlukeException d) {
            return this.ui.getError(d.getMessage());
        }
    }

    /**
     * Tells Fluke to add a Todo to its tasks.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while adding the task, for instance, with invalid inputs.
     */
    public String addTodo(String command) throws FlukeException {
        String parsedDescription = Parser.parseTodoCommand(command);
        Task taskAdded = this.tasks.addTodo(parsedDescription);
        return this.ui.getTaskAdded(taskAdded, tasks);
    }

    /**
     * Tells Fluke to add a Deadline to its tasks.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while adding the task, for instance, with invalid inputs.
     */
    private String addDeadline(String command) throws FlukeException {
        String[] parsedCommand = Parser.parseDeadlineCommand(command);
        String description = parsedCommand[0];
        String byDate = parsedCommand[1];
        Task taskAdded = this.tasks.addDeadline(description, byDate);
        return this.ui.getTaskAdded(taskAdded, tasks);
    }

    /**
     * Tells Fluke to add an Event to its tasks.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while adding the task, for instance, with invalid inputs.
     */
    private String addEvent(String command) throws FlukeException {
        String[] parsedCommand = Parser.parseEventCommand(command);
        String description = parsedCommand[0];
        String fromDate = parsedCommand[1];
        String toDate = parsedCommand[2];
        Task taskAdded = this.tasks.addEvent(description, fromDate, toDate);
        return this.ui.getTaskAdded(taskAdded, tasks);
    }

    /**
     * Tells Fluke to mark a task as done.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while marking a task as done, for instance, with invalid inputs.
     */
    private String markTaskAsDone(String command) throws FlukeException {
        int index = Parser.parseMarkAsDoneCommand(command);
        Task taskMarked = tasks.markTaskAsDone(index);
        return this.ui.getTaskMarkedAsDone(taskMarked);
    }

    /**
     * Tells Fluke to mark a task as not done.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while marking a task as not done, for instance, with invalid inputs.
     */
    private String markTaskAsUndone(String command) throws FlukeException {
        int index = Parser.parseMarkAsUndoneCommand(command);
        Task taskMarked = tasks.markTaskAsUndone(index);
        return this.ui.getTaskMarkedAsUndone(taskMarked);
    }

    /**
     * Tells Fluke to delete a task.
     * @param command Command given to Fluke.
     * @throws FlukeException if an error occurs while deleting a task, for instance, with invalid inputs.
     */
    private String deleteTask(String command) throws FlukeException {
        int index = Parser.parseDeleteCommand(command);
        Task deleted = tasks.deleteTask(index);
        return this.ui.getTaskDeleted(deleted, tasks);
    }

    /**
     * Fluke attempts to find tasks with the corresponding keyword.
     * @param command the command given
     * @throws InvalidInputException if there is no keyword given.
     */
    private String findTask(String command) throws InvalidInputException {
        String keyword = Parser.parseFindCommand(command);
        return this.ui.getTasksWithKeyword(this.tasks.findTask(keyword));
    }

    /**
     * Helper function for additional logic related to changing the list.
     * @param commandType type of command
     * @param command the content in the command
     * @throws FlukeException an exception related to operations with fluke.Fluke
     */
    private String changeTodoList(Command commandType, String command) throws FlukeException {
        // 1. make changes to the list
        String response = "";
        switch (commandType) {
        case MARK:
            response = markTaskAsDone(command);
            break;
        case UNMARK:
            response = markTaskAsUndone(command);
            break;
        case DELETE:
            response = deleteTask(command);
            break;
        case TODO:
            response = addTodo(command);
            break;
        case DEADLINE:
            response = addDeadline(command);
            break;
        case EVENT:
            response = addEvent(command);
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
        // 3. return response
        return response;
    }
}
