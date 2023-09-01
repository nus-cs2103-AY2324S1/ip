package parser;

import exceptions.BocchiException;
import exceptions.EmptyTaskException;
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
            throws InvalidSyntaxException {
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
        // Task.Todo is the default case
        default:
            task = new Todo(input);
        }
        TaskList newTaskList = taskList.addTask(task);
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

    /**
     * Returns an updated task list based on user's input
     *
     * @param command User input
     * @param taskList Current task list
     * @return Task list
     * @throws BocchiException
     */
    public TaskList process(String command, TaskList taskList) throws BocchiException {
        // This splits the input string into two to isolate the first token as an action
        String[] tokens = command.split(" ", 2);
        String action = tokens[0];
        switch (action) {
        case "list":
            displayTasks(taskList);
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
        case "deadline":
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

    public boolean isTerminated(String command) {
        return command.equals("bye");
    }
}
