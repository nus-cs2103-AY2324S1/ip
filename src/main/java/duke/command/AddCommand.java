package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;


/**
 * Represents a command to add tasks to the task list in the Duke application.
 */
public class AddCommand extends Command {
    private String fullCommand;
    private char type;

    /**
     * Constructs an AddCommand object with full command and task type.
     *
     * @param fullCommand The full command input provided by the user.
     * @param type The type of task being added ('T' for Todo, 'D' for Deadline, 'E' for Event).
     */
    public AddCommand(String fullCommand, char type) {
        this.fullCommand = fullCommand;
        this.type = type;
    }

    /**
     * Executes the add task command, adding the specified task to the task list and updating the storage.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     * @throws DukeException If there's an error while parsing the user input or updating the storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert type == 'T' || type == 'D' || type == 'E' : "Invalid task type";
        switch (type) {
        case 'T':
            AddToDo(taskList, ui, storage);
            break;
        case 'D':
            AddDeadLine(taskList, ui, storage);
            break;
        case 'E':
            AddEvent(taskList, ui, storage);
            break;
        default:
            throw new DukeException("Invalid type!");
        }
    }

    private void AddEvent(TaskList taskList, Ui ui, Storage storage) {
        Event eventTask = getEventTask();
        taskList.addTask(eventTask);
        displayAddedTaskMessage(taskList, ui, eventTask);
        storage.updateFileContents(taskList);
    }

    private static void displayAddedTaskMessage(TaskList taskList, Ui ui, Task task) {
        ui.sendMessage("Got it. I've added this task:\n\t\t"
                + task
                + "\n\tNow you have " + taskList.size() + " tasks in the list.");
    }

    private Event getEventTask() {
        String eventDescription = fullCommand.substring(6);
        int indexFrom = eventDescription.indexOf("/from");
        int indexTo = eventDescription.indexOf("/to");

        String eventString = eventDescription.substring(0, indexFrom).trim();
        String startTime = eventDescription.substring(indexFrom + "/from".length(), indexTo).trim();
        String endTime = eventDescription.substring(indexTo + "/to".length()).trim();

        Event eventTask = new Event(eventString,
                LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return eventTask;
    }

    private void AddDeadLine(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = getDeadlineTask();
        taskList.addTask(deadline);
        displayAddedTaskMessage(taskList, ui, deadline);
        storage.updateFileContents(taskList);
    }

    private Deadline getDeadlineTask() throws DukeException {
        String deadlineDescription = fullCommand.substring(9);
        String descriptionText = deadlineDescription.substring(0, deadlineDescription.indexOf("/by"));
        String dateTime = deadlineDescription.substring(deadlineDescription.indexOf("/by") + 4).trim();
        Deadline deadline;
        try {
            DateTimeFormatter altInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime byDateTime = LocalDateTime.parse(dateTime, altInputFormatter);
            deadline = new Deadline(descriptionText, byDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date Time: " + e.getMessage());
        }
        return deadline;
    }

    private void AddToDo(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = getTodoTask();
        taskList.addTask(todo);
        displayAddedTaskMessage(taskList, ui, todo);
        storage.updateFileContents(taskList);
    }

    private Todo getTodoTask() {
        String description = fullCommand.substring(5);
        Todo todo = new Todo(description);
        return todo;
    }

    /**
     * Indicates whether this command is an exit command.
     * AddCommand is not an exit command, so this method returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
