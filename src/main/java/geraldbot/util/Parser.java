package geraldbot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import geraldbot.exception.DukeEmptyParametersException;
import geraldbot.exception.DukeException;
import geraldbot.exception.DukeInvalidCommandException;
import geraldbot.exception.DukeInvalidDateException;
import geraldbot.exception.DukeInvalidIndexException;
import geraldbot.task.Deadline;
import geraldbot.task.Event;
import geraldbot.task.Task;
import geraldbot.task.Todo;

/**
 * The Parser class handles the parsing of user input and the execution of corresponding actions
 * based on the parsed commands. It interacts with the Storage and TaskList classes to manage tasks.
 */
public class Parser {
    private final Storage storage;

    private final TaskList lst;

    private final Ui ui;

    private enum Command {
        LIST,
        BYE,
        FIND,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        INVALID
    }

    /**
     * Initializes a new Parser instance with the provided storage and task list.
     *
     * @param storage   The storage instance used for reading and writing tasks.
     * @param taskList  The task list containing the tasks to be processed.
     */
    public Parser(Storage storage, ArrayList<Task> taskList) {
        this.storage = storage;
        this.lst = new TaskList(taskList);
        this.ui = new Ui();
    }

    /**
     * Parses the user input and executes the corresponding actions.
     *
     * @param input The user input to be parsed and processed.
     * @throws DukeException If an error occurs during parsing or execution.
     */
    public String parse(String input) throws DukeException {
        Command command = getCommand(input);

        switch (command) {
        case LIST:
            return this.printList();
        case BYE:
            return this.ui.bye();
        case FIND:
            return findTasks(input.substring(5).trim());
        case MARK:
            return handleMarkCommand(input);
        case UNMARK:
            return handleUnmarkCommand(input);
        case DELETE:
            return handleDeleteCommand(input);
        case TODO:
            return handleTodoCommand(input);
        case DEADLINE:
            return handleDeadlineCommand(input);
        case EVENT:
            return handleEventCommand(input);
        case INVALID:
        default:
            throw new DukeInvalidCommandException();
        }
    }

    private Command getCommand(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.startsWith("find")) {
            return Command.FIND;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else {
            return Command.INVALID;
        }
    }

    private String handleMarkCommand(String input) throws DukeException {
        String[] parsedString = input.split(" ");

        if (parsedString.length != 2) {
            throw new DukeInvalidCommandException("mark");
        }

        try {
            int num = Integer.parseInt(parsedString[1]);
            if (num > lst.size() || num <= 0) {
                throw new DukeInvalidIndexException(lst.size());
            }
            Task selectedTask = lst.get(num - 1);
            return this.markCompletion(selectedTask, num);
        } catch (NumberFormatException e) {
            throw new DukeInvalidIndexException(lst.size());
        }
    }

    // Handle the "unmark" command
    private String handleUnmarkCommand(String input) throws DukeException {
        String[] parsedString = input.split(" ");

        if (parsedString.length != 2) {
            throw new DukeInvalidCommandException("unmark");
        }

        try {
            int num = Integer.parseInt(parsedString[1]);
            if (num > lst.size() || num <= 0) {
                throw new DukeInvalidIndexException(lst.size());
            }
            Task selectedTask = lst.get(num - 1);
            return this.unmarkCompletion(selectedTask, num);
        } catch (NumberFormatException e) {
            throw new DukeInvalidIndexException(lst.size());
        }
    }

    // Handle the "delete" command
    private String handleDeleteCommand(String input) throws DukeException {
        String[] parsedString = input.split(" ");

        if (parsedString.length != 2) {
            throw new DukeInvalidCommandException("delete");
        }

        try {
            int num = Integer.parseInt(parsedString[1]);
            if (num > lst.size() || num <= 0) {
                throw new DukeInvalidIndexException(lst.size());
            }
            return this.deleteTask(num);
        } catch (NumberFormatException e) {
            throw new DukeInvalidIndexException(lst.size());
        }
    }

    // Handle the "todo" command
    private String handleTodoCommand(String input) throws DukeException {
        if (input.replaceAll("\\s", "").equals(input)) {
            throw new DukeInvalidCommandException("todo");
        }

        String command = "todo";
        String description = input.substring(input.indexOf(' ') + 1).trim();

        if (description.equals("")) {
            throw new DukeInvalidCommandException(command);
        }

        return this.addTodo(description, false);
    }

    // Handle the "deadline" command
    private String handleDeadlineCommand(String input) throws DukeException {
        if (input.replaceAll("\\s", "").equals(input)) {
            throw new DukeInvalidCommandException("deadline");
        }

        String command = "deadline";
        String task = input.substring(input.indexOf(' ') + 1);
        String[] parsedTask = task.split("/", 2);
        String description = parsedTask[0].trim();

        if (parsedTask.length < 2) {
            throw new DukeEmptyParametersException();
        }

        String by = parsedTask[1].trim();
        LocalDateTime deadlineDate = parseDate(by);

        if (description.equals("")) {
            throw new DukeInvalidCommandException(command);
        } else if (deadlineDate == null) {
            throw new DukeInvalidDateException();
        } else {
            return this.addDeadline(description, false, deadlineDate);
        }
    }

    // Handle the "event" command
    private String handleEventCommand(String input) throws DukeException {
        if (input.replaceAll("\\s", "").equals(input)) {
            throw new DukeInvalidCommandException("event");
        }

        String task = input.substring(input.indexOf(' ') + 1);
        String[] parsedTask = task.split("/");

        if (parsedTask.length < 3) {
            throw new DukeEmptyParametersException();
        }

        String description = parsedTask[0].trim();
        String start = parsedTask[1].substring(parsedTask[1].indexOf(' ') + 1).trim();
        String by = parsedTask[2].substring(parsedTask[2].indexOf(' ') + 1).trim();

        if (description.equals("") || start.equals("") || by.equals("")) {
            throw new DukeEmptyParametersException();
        } else {
            return this.addEvent(description, false, start, by);
        }
    }

    /**
     * Parses a date string and returns a LocalDateTime object.
     *
     * @param dateStr The date string to be parsed.
     * @return A LocalDateTime object representing the parsed date.
     */
    private LocalDateTime parseDate(String dateStr) {
        try {
            String[] parts = dateStr.split("\\s+", 2);
            String dateString = parts.length > 1 ? parts[1] : parts[0]; // Use the second part if available

            return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Adds a new todo task to the task list and storage.
     *
     * @param input   The description of the todo task.
     * @param isDone  The completion status of the task.
     */
    public String addTodo(String input, boolean isDone) {
        Todo newTask = new Todo(input, isDone);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        lst.add(newTask);
        storage.addTask(newTaskString);

        message += "\nNow you have " + lst.size() + " tasks in the list.";
        return message;
    }

    /**
     * Adds a new deadline task to the task list and storage.
     *
     * @param input   The description of the deadline task.
     * @param isDone  The completion status of the task.
     * @param by The deadline in LocalDateTime format of that task.
     */
    public String addDeadline(String input, boolean isDone, LocalDateTime by) {
        Deadline newTask = new Deadline(input, isDone, by);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        lst.add(newTask);
        storage.addTask(newTaskString);

        message += "\nNow you have " + lst.size() + " tasks in the list.";
        return message;
    }

    /**
     * Adds a new event task to the task list and storage.
     *
     * @param input   The description of the event task.
     * @param isDone  The completion status of the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public String addEvent(String input, boolean isDone, String start, String end) {
        Event newTask = new Event(input, isDone, start, end);
        String newTaskString = newTask.fileFormat();

        String message = "Got it. I've added this task:\n";
        message += "\t" + newTask;

        lst.add(newTask);
        storage.addTask(newTaskString);

        message += "\nNow you have " + lst.size() + " tasks in the list.";
        return message;
    }

    /**
     * Prints the list of tasks.
     */
    public String printList() {
        String taskList = "Here are the tasks in your list:\n";
        for (int i = 0; i < lst.size(); i++) {
            taskList += (i + 1) + ". " + lst.get(i).toString() + "\n";
        }
        return taskList;
    }

    /**
     * Marks a task as done and updates the storage.
     *
     * @param task The task to be marked as done.
     * @param num  The index of the task in the list.
     */
    public String markCompletion(Task task, int num) {
        if (task.getStatusIcon().equals("X")) {
            String message = "Nice! I've marked this task as done:\n";
            message += "\t" + task;
            return message;
        } else {
            String message = "Nice! I've marked this task as done:\n";

            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            message += "\t" + task;
            return message;
        }
    }

    /**
     * Removes the completion status of a task and updates the storage.
     *
     * @param task The task to be marked as not done.
     * @param num  The index of the task in the list.
     */
    public String unmarkCompletion(Task task, int num) {
        if (task.getStatusIcon().equals(" ")) {
            String message = "OK, I've marked this task as not done yet:\n";
            message += "\t" + task;
            return message;
        } else {
            String message = "OK, I've marked this task as not done yet:\n";
            task.toggleCompletion();
            String updatedTaskString = task.fileFormat();
            this.storage.updateTask(num - 1, updatedTaskString);

            message += "\t" + task;
            return message;
        }
    }

    /**
     * Deletes a task from the task list and updates the storage.
     *
     * @param num The index of the task in the task list to be deleted.
     */
    public String deleteTask(Integer num) {
        String message = "Noted. I've removed this task:\n";
        Task selectedTask = lst.remove(num - 1);
        this.storage.updateTask(num - 1, null);

        message += "\t" + selectedTask;
        message += "\nNow you have " + lst.size() + " tasks in the list.";
        return message;
    }

    /**
     * Finds tasks that match a specified keyword and displays them.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    private String findTasks(String keyword) {
        List<Task> matchingTasks = lst.findTasksByKeyword(keyword);

        String taskList = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            taskList += (i + 1) + ". " + matchingTasks.get(i).toString() + "\n";
        }
        return taskList;
    }
}
