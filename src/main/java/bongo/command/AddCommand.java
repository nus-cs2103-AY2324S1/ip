package bongo.command;

import java.time.LocalDateTime;

import bongo.helper.BongoException;
import bongo.helper.DateHelper;
import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.Deadline;
import bongo.task.Event;
import bongo.task.Task;
import bongo.task.TaskList;
import bongo.task.Todo;

/**
 * A class for a AddCommand.
 */
public class AddCommand extends Command {
    private final String[] command;
    private Task newTask;

    /**
     * A constructor for a AddCommand.
     *
     * @param command Array of strings from user command.
     * @throws BongoException If there is a problem when initializing task.
     */
    public AddCommand(String[] command) throws BongoException {
        this.command = command;
        this.initialiseTask();
    }

    /**
     * Processes user input and initializes the respective task (Todo, Event or Deadline).
     * Checks if user input to create task is valid.
     *
     * @throws BongoException If task is not initialized properly.
     */
    private void initialiseTask() throws BongoException {
        String taskInput = "";
        if (command.length > 1) {
            taskInput = String.join(" ", command).substring(command[0].length() + 1);
        }
        switch (this.command[0]) {
        case "todo":
            if (command.length <= 1) {
                throw new BongoException("Please include the description of your todo.");
            }
            this.newTask = new Todo(taskInput);
            break;
        case "deadline":
            if (command.length <= 1) {
                throw new BongoException("Please include the description and datetime of your deadline.");
            }
            if (command.length <= 3) {
                throw new BongoException("Please include the datetime of your deadline.");
            }
            int index = taskInput.indexOf("/by");
            if (index == -1) {
                throw new BongoException("Please include the datetime of your deadline.");
            }
            String deadlineDesc = taskInput.substring(0, index - 1);
            String deadlineStr = taskInput.substring(index + 4);
            LocalDateTime deadline = DateHelper.convertStringToDateTime(deadlineStr);
            this.newTask = new Deadline(deadlineDesc, deadline);
            break;
        case "event":
            if (command.length <= 1) {
                throw new BongoException("Please include the description and datetime of your event.");
            }
            if (command.length <= 4) {
                throw new BongoException("Please include the datetime of your event.");
            }
            int fromIndex = taskInput.indexOf("/from");
            int toIndex = taskInput.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw new BongoException("Please include the to and from datetime of the event.");
            }
            String eventDesc = taskInput.substring(0, fromIndex - 1);
            String eventFrom = taskInput.substring(fromIndex + 6, toIndex - 1);
            String eventTo = taskInput.substring(toIndex + 4);
            LocalDateTime from = DateHelper.convertStringToDateTime(eventFrom);
            LocalDateTime to = DateHelper.convertStringToDateTime(eventTo);
            this.newTask = new Event(eventDesc, from, to);
            break;
        default:
            throw new BongoException();
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        storage.add(newTask);
        return ui.showAddedTask(newTask, tasks.getTotalTasks());
    }
}
