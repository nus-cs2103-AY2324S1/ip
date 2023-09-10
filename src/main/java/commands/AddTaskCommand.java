package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;
import ui.Ui;

/**
 * Represents a command to add a task to the list of tasks.
 */
public class AddTaskCommand extends Command {

    /**
     * Constructor for AddCommand
     * @param command The command to be executed
     */
    public AddTaskCommand(String command) {
        super(command);
    }

    /**
     * Enum for the different types of tasks
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Executes the AddCommand, adding the task to the list of tasks.
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     * @throws DukeException If the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String input = getCommand();
        String[] inputArray = input.split(" ");
        String taskType = inputArray[0];
        String[] inputSplit;
        Task newTask;
        switch (TaskType.valueOf(taskType.toUpperCase())) {
        case TODO:
            try {
                newTask = new TodoTask(inputArray[1]);
                taskList.add(newTask);
                ui.showTaskAdded(newTask, taskList.size());
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Description of todo cannot be empty.");
            }
        case DEADLINE:
            try {
                inputSplit = input.split(" /by ");
                newTask = new DeadlineTask(inputSplit[0].substring(9), inputSplit[1]);
                taskList.add(newTask);
                ui.showTaskAdded(newTask, taskList.size());
                break;
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new DukeException("Deadline should follow the format deadline <description> /by <date and time>");
            }
        case EVENT:
            try {
                inputSplit = input.split(" /");
                newTask = new EventTask(inputSplit[0].substring(6),
                        inputSplit[1].substring(5),
                        inputSplit[2].substring(3));
                taskList.add(newTask);
                ui.showTaskAdded(newTask, taskList.size());
                break;
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Event should follow the format event <description> "
                        + "/from <start date and time> /to <end date and time>");
            }
        default:
            throw new DukeException("Task type is not recognised. Please use todo, deadline or event.");
        }
    }

}
