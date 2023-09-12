package duke.commands;

import duke.task.*;
import duke.ui.Ui;
import duke.utilities.MYBotExceptions;
import duke.utilities.Storage;

import java.util.List;

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
     * @param task_List The task list containing existing tasks.
     */
    public Commands(Ui ui, Storage storage, TaskList task_List) {
        this.ui = ui;
        this.taskList = task_List;
        this.storage = storage;

        // Assertions for constructor parameters
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert task_List != null : "TaskList cannot be null";
    }

    /**
     * Adds a Todo task to the task list and saves it to storage.
     *
     * @param input The description of the Todo task.
     */
    public void addTodoTask(String input) {
        assert input != null : "Input cannot be null";

        Task task = new Todo(input);
        generalAddTasks(task);
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

        Task task = new Deadline(input, dueBy);
        generalAddTasks(task);
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

        Task task = new Event(input, from, to);
        generalAddTasks(task);
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
        ui.printAddTask(task, taskList.getTask_Count());
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param task_List The task list containing tasks to be listed.
     */
    public void listTasks(TaskList task_List){
        assert task_List != null : "TaskList cannot be null";

        List<Task> tasks = task_List.getTaskList();
        int taskCount = task_List.getTask_Count();
        ui.printTaskList(tasks, taskCount);
    }

    /**
     * Marks a task as done in the task list and saves it to storage.
     *
     * @param taskNumber The index of the task to be marked.
     */
    public void markTask(int taskNumber) {
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
                throw new MYBotExceptions.NoSuchTaskException();
            }
        } catch (MYBotExceptions e) {
            ui.printException(e);
        }
    }

    /**
     * Unmarks a task as not sone in task list and saves it to storage
     *
     * @param taskNumber The index of the task to be marked.
     */
    public void unmarkTask(int taskNumber){
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
                throw new MYBotExceptions.NoSuchTaskException();
            }
        } catch (MYBotExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes a task from the task list and saves the updated list to storage.
     *
     * @param taskNumber The index of the task to be removed.
     */
    public void removeTask(int taskNumber) {

        try {
            assert taskNumber >= 0 && taskNumber <= taskList.getTask_Count() : "Invalid task number";

            boolean isValidTaskNumber = taskNumber >= 0;
            boolean isValidTask = taskNumber <= taskList.getTask_Count();

            if (isValidTaskNumber && isValidTask) {
                Task taskToBeRemoved = taskList.getTask(taskNumber);
                taskList.removeTask(taskNumber);
                storage.saveTasksToFile(taskList);
                ui.printRemoveTask(taskToBeRemoved, taskList);
            } else {
                throw new MYBotExceptions.InvalidTaskException();
            }
        } catch (MYBotExceptions e) {
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
        ui.printMatchingTasks(matchingTasks);
    }
}
