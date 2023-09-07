package helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.EmptyTasksException;
import exceptions.ErrorStorageException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import exceptions.InvalidIndexException;
import exceptions.InvalidTaskDescriptionException;
import exceptions.InvalidTimeFormatException;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.Todo;


/**
 * Represents Parser class that deals with processing user commands
 */
public class Parser {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected final String cmd;
    protected final TaskList taskList;
    protected String input;
    protected Storage storage;
    protected Ui ui;

    //TODO 1. Handle GUI exception | 2. Handle exit GUI when bye command

    /**
     * Constructor for Helpers.Commands to initalise the relevant parameters
     *
     * @param cmd      The command entered
     * @param taskList The list of tasks
     * @param storage  The storage class to perform read/write
     */
    public Parser(String cmd, TaskList taskList, Storage storage) throws ErrorStorageException {
        this.cmd = cmd;
        this.taskList = taskList;
        this.ui = new Ui();
        this.storage = storage;
    }

    /**
     * Creates a task relating to ToDo.
     *
     * @param input String of user input
     * @throws InvalidTaskDescriptionException if task description inside input is empty
     */
    public String createToDo(String input) throws InvalidTaskDescriptionException {
        String taskDesc = input.split(" ", 2)[1];
        if (input.split(" ").length < 2) {
            throw new InvalidTaskDescriptionException("To-Do task");
        } else {
            Todo td = new Todo(taskDesc, false);
            this.taskList.addTask(td);
            this.storage.write(this.taskList.getTaskList());
            return ui.showAddTaskMessage(this.taskList, td);
        }
    }

    /**
     * Creates a task relating to Deadline.
     *
     * @param input String of user input
     * @throws InvalidArgumentException if input does not contain the correct /by argument
     */
    public String createDeadline(String input) throws InvalidArgumentException, InvalidTimeFormatException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length == 1 || parts[1].isEmpty() || parts[1].isBlank()) {
            ui.showArgumentErrorMessage(input.substring(9), "/by");
        } else {
            try {
                String taskDesc = parts[0].split(" ", 2)[1];
                LocalDateTime by = LocalDateTime.parse(parts[1], dateTimeFormat);
                Deadline dl = new Deadline(taskDesc, false, by);
                this.taskList.addTask(dl);
                this.storage.write(this.taskList.getTaskList());
                return ui.showAddTaskMessage(this.taskList, dl);
            } catch (Exception e) {
                ui.showInvalidTimeFormatErrorMessage(parts[1]);
            }

        }
        return "Error";
    }

    /**
     * Creates a task relating to Events.
     *
     * @param input String of user input
     * @throws InvalidArgumentException if input does not contain the correct /from and /to argument
     */
    public String createEvent(String input) throws InvalidArgumentException, InvalidTimeFormatException {
        String[] parts = input.split("\\s+/from\\s+|\\s+/to\\s+");
        if (parts.length < 3) {
            ui.showArgumentErrorMessage(input.substring(6), "/from and /to");
        } else {
            try {
                String taskDesc = parts[0].split(" ", 2)[1];
                LocalDateTime afterFrom = LocalDateTime.parse(parts[1], dateTimeFormat);
                LocalDateTime afterTo = LocalDateTime.parse(parts[2], dateTimeFormat);
                Events ev = new Events(taskDesc, false, afterFrom, afterTo);
                this.taskList.addTask(ev);
                this.storage.write(this.taskList.getTaskList());
                return ui.showAddTaskMessage(this.taskList, ev);

            } catch (Exception e) {
                ui.showInvalidTimeFormatErrorMessage(parts[1] + " " + parts[2]);
            }
        }
        return "Error";
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param input String of user input
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public String unmark(String input) throws EmptyTasksException, InvalidIndexException, InvalidCommandException {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1]);
            if (index < 0 || this.taskList.getListLength() == 0) {
                throw new EmptyTasksException(input);
            } else {
                try {
                    Task task = this.taskList.markTaskAsUnDone(index - 1);
                    this.storage.write(this.taskList.getTaskList());
                    return ui.showUnmarkDoneMessage(task);

                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidIndexErrorMessage(input);
                }

            }
        } catch (Exception e) {
            ui.showInvalidCommandErrorMessage(e.getMessage());

        }
        return "Error";
    }

    /**
     * Marks a task as completed.
     *
     * @param input String of user input
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public String mark(String input) throws EmptyTasksException, InvalidIndexException, InvalidCommandException {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1]);
            if (index < 0 || this.taskList.getListLength() == 0) {
                ui.showEmptyTasksError(input);

            } else {
                try {
                    Task task = this.taskList.markTaskAsDone(index - 1);
                    this.storage.write(this.taskList.getTaskList());
                    return ui.showMarkDoneMessage(task);

                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidIndexErrorMessage(input);
                }
            }
        } catch (Exception e) {
            ui.showInvalidCommandErrorMessage(e.getMessage());
        }
        return "Error";
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input String of user input
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public String deleteTask(String input) throws InvalidIndexException, EmptyTasksException {
        int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        if (index < 0 || this.taskList.getListLength() == 0) {
            throw new EmptyTasksException(input);
        } else {
            try {
                Task task = this.taskList.removeTask(index);
                this.storage.write(this.taskList.getTaskList());
                return ui.showDeletedTaskMessage(this.taskList, task);

            } catch (IndexOutOfBoundsException e) {
                ui.showInvalidIndexErrorMessage(input);
            }
        }
        return "Error";
    }

    /**
     * Method to filter tasks based on user input
     * @param input Regex input by user
     */
    public String findTasks(String input) throws InvalidArgumentException {
        String regex = input.split(" ", 2)[1];
        if (input.split(" ").length < 2) {
            ui.showInvalidArgumentErrorMessage("find", regex);
        } else {
            return ui.showFilteredTaskList(this.taskList, regex);
        }
        return "Error";
    }

    /**
     * Execute the controllers.Duke's functions to process user's input.
     *
     * @throws InvalidCommandException if user enters an invalid command
     */
    public String execute(String input) throws InvalidCommandException {
        this.input = input;
        CommandsList command = null;
        try {
            try {
                command = CommandsList.valueOf(this.cmd.toUpperCase());
            } catch (Exception e) {
                ui.showInvalidCommandErrorMessage(this.cmd);
            }

            switch (command) {
            case LIST:
                return ui.showTaskList(this.taskList);

            case TODO:
                return createToDo(this.input);

            case DEADLINE:
                return createDeadline(this.input);

            case EVENT:
                return createEvent(this.input);

            case UNMARK:
                return unmark(this.input);

            case MARK:
                return mark(this.input);

            case DELETE:
                return deleteTask(this.input);

            case FIND:
                return findTasks(this.input);

            case BYE:
                return "\nBye! Hope to see you again soon, macho!";

            default:
                return "Invalid command macho! Please try again!";
            }

        } catch (InvalidArgumentException | EmptyTasksException | InvalidCommandException
                 | InvalidTaskDescriptionException | InvalidIndexException | InvalidTimeFormatException
                 | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return "Error";
    }

    private enum CommandsList {
        LIST,
        TODO,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        BYE
    }


}
