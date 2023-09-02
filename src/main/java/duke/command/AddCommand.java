package duke.command;

import java.time.LocalDateTime;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Command;
import duke.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84
/**
 * Represents a command to add tasks to the task list.
 * This command can handle adding different types of tasks such as Todo, Deadline, and Event.
 */
public class AddCommand extends Command {
    /**
     * Enumerates the types of tasks that can be added.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private String taskDescription;
    private TaskType taskType;
    private LocalDateTime parsedDateTime;
    private LocalDateTime parsedFromDate;
    private LocalDateTime parsedToDate;

    // Use Method Overloading for different Commands to be added

    /**
     * Constructs an AddCommand for a Todo task.
     *
     * @param taskType       The type of the task to be added.
     * @param taskDescription The description of the task.
     */
    public AddCommand(TaskType taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    /**
     * Constructs an AddCommand for a Deadline task.
     *
     * @param taskType       The type of the task to be added.
     * @param taskDescription The description of the task.
     * @param parsedDateTime  The parsed date and time for the deadline.
     */
    public AddCommand(TaskType taskType, String taskDescription, LocalDateTime parsedDateTime) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.parsedDateTime = parsedDateTime;
    }

    /**
     * Constructs an AddCommand for an Event task.
     *
     * @param taskType       The type of the task to be added.
     * @param taskDescription The description of the task.
     * @param parsedFromDate The parsed start date and time for the event.
     * @param parsedToDate   The parsed end date and time for the event.
     */
    public AddCommand(TaskType taskType, String taskDescription, LocalDateTime parsedFromDate, LocalDateTime parsedToDate) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.parsedFromDate = parsedFromDate;
        this.parsedToDate = parsedToDate;
    }

    /**
     * Executes the AddCommand to add a task to the task list.
     *
     * @param taskList The TaskList containing the list of tasks.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage handler for saving tasks to a file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task newTask = null;
            switch (taskType) {
                case TODO:
                    newTask = new Todo(taskDescription);
                    break;
                case DEADLINE:
                    newTask = new Deadline(taskDescription, parsedDateTime);
                    break;
                case EVENT:
                    newTask = new Event(taskDescription, parsedFromDate, parsedToDate);
                    break;
            }
            taskList.addTask(newTask);
            ui.displayAddedTask(newTask, taskList.numTasks());
            storage.saveTasks(taskList.getAllTasks()); // Save the updated task list
        } catch (Exception e) {
            ui.showErrorMessage("Error adding task.");
        }
    }
}