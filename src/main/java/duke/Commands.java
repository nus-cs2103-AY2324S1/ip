package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.util.List;

/**
 * The Commands class provides methods for executing various commands tasks.
 * It handles adding tasks, listing tasks, marking tasks as done, umarking
 * and removing tasks.
 * It interacts with the UI, task list and storage component.
 */
public class Commands {
    private Ui ui;
    private TaskList task_List;
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
        this.task_List = task_List;
        this.storage = storage;
    }

    /**
     * Adds a Todo task to the task list and saves it to storage.
     *
     * @param input The description of the Todo task.
     */
    public void addTodoTask(String input) {
        Task task = new Todo(input);
        generalAddTasks(task);
    }

    /**
     * Adds a Deadline task to the task list and saves it to storage.
     *
     * @param input The description of the Deadline task.
     * @param by The deadline of the task.
     */
    public void addDeadlineTask(String input, String by) {
        Task task = new Deadline(input, by);
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
        Task task = new Event(input, from, to);
        generalAddTasks(task);
    }

    /**
     * Adds a task to the task list, saves it to storage and prints tasks details.
     *
     * @param task The task to be added.
     */
    public void generalAddTasks(Task task) {
        task_List.addTask(task);
        storage.saveTasksToFile(task_List);
        ui.printAddTask(task, task_List.getTask_Count());
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param task_List The task list containing tasks to be listed.
     */
    public void listTasks(TaskList task_List){
        List<Task> tasks = task_List.getTask_List();
        int taskCount = task_List.getTask_Count();
        ui.printTaskList(tasks, taskCount);
    }

    /**
     * Marks a task as done in the task list and saves it to storage.
     *
     * @param task_number The index of the task to be marked.
     */
    public void markTasks(int task_number) {
        try {
            if (task_number > 0 && task_List.getTask(task_number) != null) {
                Task taskTobeMarked = task_List.getTask(task_number);
                taskTobeMarked.taskDone();
                storage.saveTasksToFile(task_List);
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
     * @param task_number The index of the task to be marked.
     */
    public void unmarkTasks(int task_number){
        try {
            if (task_number > 0 && task_List.getTask(task_number) != null) {
                Task taskTobeMarked = task_List.getTask(task_number);
                taskTobeMarked.undoTask();
                storage.saveTasksToFile(task_List);
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
     * @param task_number The index of the task to be removed.
     */
    public void removeTasks(int task_number) {

        try {
            if (task_number >= 0 && task_number <= task_List.getTask_Count()) {
                Task taskToBeRemoved = task_List.getTask(task_number);
                task_List.removeTask(task_number);
                storage.saveTasksToFile(task_List);
                ui.printRemoveTasks(taskToBeRemoved, task_List);
            } else {
                throw new MYBotExceptions.InvalidTaskException();
            }
        } catch (MYBotExceptions e) {
            ui.printException(e);
        }
    }
}
