package parser;

import exceptions.BocchiException;
import exceptions.EmptyTaskException;
import exceptions.EventConflictException;
import exceptions.InvalidInputException;
import exceptions.InvalidSyntaxException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Class used to parse all inputs from the user
 */
public class Parser {
    private static final int EXPECTED_TOKENS_IN_EVENT = 3;
    private static final String LINE_BREAK = "___________________________________________________";
    private final Ui ui;

    /**
     * Creates a new Parser instance.
     *
     * @param ui The user interface component for displaying messages.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Adds the task to the current task list.
     *
     * @param input Remaining input string excluding action
     * @param taskList Current list of tasks
     * @return Updated task list
     */
    private TaskList addTask(String input, String action, TaskList taskList)
            throws BocchiException {
        Task task;
        switch (action) {
        case "deadline":
            if (!input.contains("/by")) {
                throw new InvalidSyntaxException("deadline");
            }
            // Further tokenize into action and deadline
            String[] deadlineTokens = input.split("\\s*/by\\s*");
            task = new Deadline(deadlineTokens[0], deadlineTokens[1]);
            break;
        case "event":
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new InvalidSyntaxException("event");
            }
            // Further tokenize into action, start time and end time
            // Regex identified by /to OR /from
            String[] eventTokens = input.split(
                    "\\s*/to\\s*|\\s*/from\\s*",
                    EXPECTED_TOKENS_IN_EVENT
            );
            task = new Event(eventTokens[0], eventTokens[1], eventTokens[2]);
            break;
        case "todo":
            task = new Todo(input);
            break;
        default:
            throw new InvalidInputException();
        }
        if (taskList.hasConflict(task)) {
            throw new EventConflictException();
        }

        TaskList newTaskList = taskList.addTask(task);
        boolean isAdded = newTaskList.isTaskPresent(task);
        assert isAdded;
        ui.addTaskSuccessful(task, taskList);
        return newTaskList;
    }

    /**
     * Removes the task from the current task list.
     *
     * @param taskNumber Remaining input string excluding action
     * @param taskList Current list of tasks
     * @return Updated task list
     */
    private TaskList deleteTask(int taskNumber, TaskList taskList) {
        Task toDelete = taskList.getTask(taskNumber);
        TaskList newTaskList = taskList.deleteTask(toDelete);
        boolean isDeleted = !newTaskList.isTaskPresent(toDelete);
        assert isDeleted;
        ui.deleteTaskSuccessful(toDelete, newTaskList);
        return newTaskList;
    }

    /**
     * Marks the indicated task.
     *
     * @param taskNumber Task.Task number
     * @param taskList Current list of tasks
     * @return Updated task list
     */
    private TaskList markTask(int taskNumber, TaskList taskList) {
        TaskList newTaskList = taskList.markTask(taskNumber);
        ui.markTaskSuccessful(taskNumber, taskList);
        return newTaskList;
    }

    /**
     * Unmarks the indicated task.
     *
     * @param taskNumber Task.Task number
     * @param taskList Current list of tasks
     * @return Updated task list
     */
    private TaskList unmarkTask(int taskNumber, TaskList taskList) {
        TaskList newTaskList = taskList.unmarkTask(taskNumber);
        ui.unmarkTaskSuccessful(taskNumber, taskList);
        return newTaskList;
    }

    /**
     * Outputs the current list of tasks to be done.
     *
     * @param taskList Current list of tasks
     */
    private void displayTasks(TaskList taskList) {
        ui.displayTasks(taskList);
    }

    private void findSimilarTasks(String query, TaskList taskList) {
        TaskList similarTasks = taskList.getSimilarTasks(query);
        ui.displayTasks(similarTasks);
    }

    /**
     * Processes the user's input command and updates the task list accordingly.
     *
     * @param command  The user's input command.
     * @param taskList The current list of tasks.
     * @return The updated task list.
     * @throws BocchiException An exception thrown for invalid inputs.
     */
    public TaskList process(String command, TaskList taskList) throws BocchiException {
        // This splits the input string into two to isolate the first token as an action
        String[] tokens = command.split(" ", 2);
        String action = tokens[0];

        switch (action) {
        case "list":
            displayTasks(taskList);
            break;
        case "find":
            findSimilarTasks(tokens[1], taskList);
            break;
        case "mark":
            taskList = markTask(Integer.parseInt(tokens[1]), taskList);
            break;
        case "unmark":
            taskList = unmarkTask(Integer.parseInt(tokens[1]), taskList);
            break;
        case "delete":
            taskList = deleteTask(Integer.parseInt(tokens[1]), taskList);
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            // tokens[1] which is the remaining input is parsed based on the action
            if (tokens.length == 1) {
                throw new EmptyTaskException(action);
            }
            taskList = addTask(tokens[1], action, taskList);
            break;
        default:
            throw new InvalidInputException();
        }
        return taskList;
    }

    /**
     * Checks if the user's input command indicates termination of the program.
     *
     * @param command The user's input command.
     * @return True if the program should terminate, false otherwise.
     */
    public boolean isTerminated(String command) {
        return command.equals("bye");
    }
}
