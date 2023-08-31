package Helpers;


import Exceptions.*;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents Parser class that deals with processing user commands
 */
public class Parser {

    protected final String cmd;
    protected final TaskList taskList;
    protected final String input;
    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected Storage storage;
    protected Ui ui;

    /**
     * Constructor for Helpers.Commands to initalise the relevant parameters
     *
     * @param cmd      The command entered
     * @param input    The whole user input
     * @param taskList The list of tasks
     * @param ui       The ui to perform operations
     * @param storage  The storage class to perform read/write
     */
    public Parser(String cmd, String input, TaskList taskList, Ui ui, Storage storage) throws ErrorStorageException {
        this.input = input;
        this.cmd = cmd;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Creates a task relating to ToDo.
     *
     * @param input String of user input
     * @throws InvalidTaskDescriptionException if task description inside input is empty
     */
    public void createToDo(String input) throws InvalidTaskDescriptionException {
        String taskDesc = input.split(" ", 2)[1];
        if (input.split(" ").length < 2) {
            throw new InvalidTaskDescriptionException("To-Do task");
        } else {
            Todo td = new Todo(taskDesc, false);
            this.taskList.addTask(td);
            this.storage.write(this.taskList.getTaskList());
            ui.showAddTaskMessage(this.taskList, td);
        }
    }

    /**
     * Creates a task relating to Deadline.
     *
     * @param input String of user input
     * @throws InvalidArgumentException if input does not contain the correct /by argument
     */
    public void createDeadline(String input) throws InvalidArgumentException, InvalidTimeFormatException {
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
                ui.showAddTaskMessage(this.taskList, dl);
            } catch (Exception e) {
                ui.showInvalidTimeFormatErrorMessage(parts[1]);
            }

        }
    }

    /**
     * Creates a task relating to Events.
     *
     * @param input String of user input
     * @throws InvalidArgumentException if input does not contain the correct /from and /to argument
     */
    public void createEvent(String input) throws InvalidArgumentException, InvalidTimeFormatException {
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
            ui.showAddTaskMessage(this.taskList, ev);

            } catch (Exception e) {
                ui.showInvalidTimeFormatErrorMessage(parts[1] + " " + parts[2]);
            }
        }
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param input String of user input
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public void unmark(String input) throws EmptyTasksException, InvalidIndexException, InvalidCommandException {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1]);
            if (index < 0 || this.taskList.getListLength() == 0) {
                throw new EmptyTasksException(input);
            } else {
                try {
                    Task task = this.taskList.markTaskAsUnDone(index - 1);
                    this.storage.write(this.taskList.getTaskList());
                    ui.showUnmarkDoneMessage(task);

                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidIndexErrorMessage(input);
                }

            }
        } catch (Exception e) {
            ui.showInvalidCommandErrorMessage(e.getMessage());

        }

    }

    /**
     * Marks a task as completed.
     *
     * @param input String of user input
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public void mark(String input) throws EmptyTasksException, InvalidIndexException, InvalidCommandException {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1]);
            if (index < 0 || this.taskList.getListLength() == 0) {
                ui.showEmptyTasksError(input);

            } else {
                try {
                    Task task = this.taskList.markTaskAsDone(index - 1);
                    this.storage.write(this.taskList.getTaskList());
                    ui.showMarkDoneMessage(task);

                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidIndexErrorMessage(input);
                }
            }
        } catch (Exception e) {
            ui.showInvalidCommandErrorMessage(e.getMessage());
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input String of user input
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public void deleteTask(String input) throws InvalidIndexException, EmptyTasksException {
        int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        if (index < 0 || this.taskList.getListLength() == 0) {
            throw new EmptyTasksException(input);
        } else {
            try {
                Task task = this.taskList.removeTask(index);
                this.storage.write(this.taskList.getTaskList());
                ui.showDeletedTaskMessage(this.taskList, task);

            } catch (IndexOutOfBoundsException e) {
                ui.showInvalidIndexErrorMessage(input);
            }
        }
    }

    /**
     * Execute the Duke's functions to process user's input.
     *
     * @throws InvalidCommandException if user enters an invalid command
     */
    public void execute() throws InvalidCommandException {
        CommandsList command = null;
        try {
            try {
                command = CommandsList.valueOf(this.cmd.toUpperCase());
            } catch (Exception e) {
                ui.showInvalidCommandErrorMessage(this.cmd);
            }

            switch (command) {
                case LIST:
                    ui.showTaskList(this.taskList);
                    break;

                case TODO:
                    createToDo(this.input);
                    break;

                case DEADLINE:
                    createDeadline(this.input);
                    break;

                case EVENT:
                    createEvent(this.input);
                    break;

                case UNMARK:
                    unmark(this.input);
                    break;

                case MARK:
                    mark(this.input);
                    break;

                case DELETE:
                    deleteTask(this.input);
                    break;

                case BYE:
                    ui.quit();
                    System.out.println("\nBye! Hope to see you again soon, macho!");
                    break;

                default:
                    System.out.println("Invalid command macho! Please try again!");
            }

        } catch (InvalidArgumentException | EmptyTasksException | InvalidCommandException |
                 InvalidTaskDescriptionException | InvalidIndexException | InvalidTimeFormatException |
                NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    private enum CommandsList {
        LIST,
        TODO,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        DELETE,
        BYE
    }


}
