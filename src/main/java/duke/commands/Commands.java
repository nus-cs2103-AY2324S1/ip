package duke.commands;


import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.exceptions.MyBotExceptions;
import duke.exceptions.NoSuchTaskException;
import duke.utilities.Storage;

/**
 * The Commands class provides methods for executing various commands tasks.
 * It handles adding tasks, listing tasks, marking tasks as done, umarking
 * and removing tasks.
 * It interacts with the UI, task list and storage component.
 */
public class Commands {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Commands instance.
     *
     * @param ui The UI component for displaying messages to the user.
     * @param storage The storage component for loading and saving task data.
     * @param taskList The task list containing existing tasks.
     */
    public Commands(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;

        // Assertions for constructor parameters
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "TaskList cannot be null";
    }

    /**
     * Adds a task to the task list, saves it to storage and prints tasks details.
     *
     * @param task The task to be added.
     */
    public void generalAddTasks(Task task) {
        assert task != null : "Task cannot be null";

        taskList.addTask(task);
        storage.saveTasksToFile(taskList);
        ui.printAddTask(task, taskList.getTaskCount());
    }

    /**
     * Puts repeated tasks into a List of Tasks
     * Prints the command telling users input entered exists
     *
     * @param input The description of the newly added task
     */
    public void taskRepeated(String input) {
        List<Task> repeatedTasks = taskList.findMatchingTasks(input);
        ui.printRepeatedTasks(repeatedTasks);
    }

    /**
     * Adds a Todo task to the task list and saves it to storage.
     *
     * @param input The description of the Todo task.
     */
    public void addTodoTask(String input) {
        assert input != null : "Input cannot be null";

        boolean isExistingTask = taskList.findMatchingTasks(input).isEmpty();
        boolean isSupposedtoProceed = input.startsWith("!");

        if (!isExistingTask) {
            taskRepeated(input);
            taskList.tempStoreTask("todo " + "!" + input);
        } else if (isSupposedtoProceed) {
            input = input.substring(1);
            Task task = new Todo(input);
            generalAddTasks(task);
        } else {
            Task task = new Todo(input);
            generalAddTasks(task);
        }

    }

    /**
     * Adds a Deadline task to the task list and saves it to storage.
     *
     * @param input The description of the Deadline task.
     * @param dueBy The deadline of the task.
     */
    public void addDeadlineTask(String input, String dueBy) {
        assert input != null : "Input cannot be null";
        assert dueBy != null : "Deadline cannot be null";

        boolean isExistingTask = taskList.findMatchingTasks(input).isEmpty();
        boolean isSupposedtoProceed = input.startsWith("!");

        if (!isExistingTask) {
            taskRepeated(input);
            taskList.tempStoreTask("deadline " + "!" + input + " /by " + dueBy);
        } else if (isSupposedtoProceed) {
            input = input.substring(1);
            Task task = new Deadline(input, dueBy);
            generalAddTasks(task);
        } else {
            Task task = new Deadline(input, dueBy);
            generalAddTasks(task);
        }
    }

    /**
     * Adds an Event task to the task list and saves it to storage.
     *
     * @param input The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    public void addEventTask(String input, String from, String to) {
        assert input != null : "Input cannot be null";
        assert from != null : "Start time cannot be null";
        assert to != null : "End time cannot be null";

        boolean isExistingTask = taskList.findMatchingTasks(input).isEmpty();
        boolean isSupposedtoProceed = input.startsWith("!");

        if (!isExistingTask) {
            taskRepeated(input);
            taskList.tempStoreTask("event " + "!"
                    + input + " /from " + from + " /to " + to);
        } else if (isSupposedtoProceed) {
            input = input.substring(1);
            Task task = new Event(input, from, to);
            generalAddTasks(task);
        } else {
            Task task = new Event(input, from, to);
            generalAddTasks(task);
        }
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param taskList The task list containing tasks to be listed.
     */
    public void listTasks(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";

        List<Task> tasks = taskList.getTaskList();
        int taskCount = taskList.getTaskCount();
        ui.printTaskList(tasks, taskCount);
    }

    /**
     * Marks a task as done in the task list and saves it to storage.
     *
     * @param taskNumber The index of the task to be marked.
     * @throws MyBotExceptions If a specified task number does not exist,
     *     throws the NoSuchTaskException
     */
    public void markTask(int taskNumber) throws MyBotExceptions {
        try {
            assert taskNumber > 0 : "Task number must be greater than 0";
            assert taskList.getTask(taskNumber) != null : "Task does not exist";

            boolean isValidTaskNumber = taskNumber > 0;
            boolean isValidTask = taskList.getTask(taskNumber) != null;

            if (isValidTaskNumber && isValidTask) {
                Task taskTobeMarked = taskList.getTask(taskNumber);
                taskTobeMarked.setTaskDone();
                storage.saveTasksToFile(taskList);
                ui.printMarkTask(taskTobeMarked);
            } else {
                throw new NoSuchTaskException();
            }
        } catch (MyBotExceptions e) {
            ui.printException(e);
        }
    }

    /**
     * Unmarks a task as not sone in task list and saves it to storage
     *
     * @param taskNumber The index of the task to be marked.
     * @throws MyBotExceptions If a specified task number does not exist,
     *      *     throws the NoSuchTaskException
     */
    public void unmarkTask(int taskNumber) throws MyBotExceptions {
        try {
            assert taskNumber > 0 : "Task number must be greater than 0";
            assert taskList.getTask(taskNumber) != null : "Task does not exist";

            boolean isValidTaskNumber = taskNumber > 0;
            boolean isValidTask = taskList.getTask(taskNumber) != null;

            if (isValidTaskNumber && isValidTask) {
                Task taskTobeMarked = taskList.getTask(taskNumber);
                taskTobeMarked.undoTask();
                storage.saveTasksToFile(taskList);
                ui.printUnmarkTask(taskTobeMarked);
            } else {
                throw new NoSuchTaskException();
            }
        } catch (MyBotExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes a task from the task list and saves the updated list to storage.
     *
     * @param taskNumber The index of the task to be removed.
     * @throws MyBotExceptions If a specified task number does not exist,
     *      throws the NoSuchTaskException
     */
    public void removeTask(int taskNumber) {

        try {
            assert taskNumber >= 0 && taskNumber <= taskList.getTaskCount() : "Invalid task number";

            boolean isValidTaskNumber = taskNumber >= 0;
            boolean isValidTask = taskNumber <= taskList.getTaskCount();

            if (isValidTaskNumber && isValidTask) {
                Task taskToBeRemoved = taskList.getTask(taskNumber);
                taskList.removeTask(taskNumber);
                storage.saveTasksToFile(taskList);
                ui.printRemoveTask(taskToBeRemoved, taskList);
            } else {
                throw new NoSuchTaskException();
            }
        } catch (MyBotExceptions e) {
            ui.printException(e);
        }
    }

    /**
     * Find and prints tasks that match the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public void findTasks(String keyword) {
        assert keyword != null : "Keyword cannot be null";

        List<Task> matchingTasks = taskList.findMatchingTasks(keyword);
        if (matchingTasks.isEmpty()) {
            ui.printNoMatchingTasks();
        } else {
            ui.printMatchingTasks(matchingTasks);
        }
    }
}
