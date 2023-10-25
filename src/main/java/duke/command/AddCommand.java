package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
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
        executeAddTask(taskList, ui, storage);
    }

    private void executeAddTask(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        switch (type) {
        case 'T':
            addToDo(taskList, ui, storage);
            break;
        case 'D':
            addDeadLine(taskList, ui, storage);
            break;
        case 'E':
            addEvent(taskList, ui, storage);
            break;
        default:
            throw new DukeException("Invalid type!");
        }
    }

    private void addEvent(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event eventTask = getEventTask();
        boolean isDuplicate = taskList.contains(eventTask.toString());
        if (isDuplicate) {
            throw new DukeException("Error! You cannot have duplicate event task.");
        }
        taskList.addTask(eventTask);
        displayAddedTaskMessage(taskList, ui, eventTask);
        storage.updateFileContents(taskList);
    }

    private static void displayAddedTaskMessage(TaskList taskList, Ui ui, Task task) {
        ui.sendMessage("Got it. I've added this task:\n\t"
                + task
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private Event getEventTask() throws DukeException {
        String eventDescription = fullCommand.substring(6);
        int indexFrom = eventDescription.indexOf("/from");
        int indexTo = eventDescription.indexOf("/to");

        String eventString = eventDescription.substring(0, indexFrom).trim();
        String startTime = eventDescription.substring(indexFrom + "/from".length(), indexTo).trim();
        String endTime = eventDescription.substring(indexTo + "/to".length()).trim();

        Event eventTask;
        try {
            eventTask = new Event(eventString,
                    LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date Time: Date has to be in \"yyyy-MM-dd HH:mm\" format\n" + e.getMessage());
        }
        return eventTask;
    }

    private void addDeadLine(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = getDeadlineTask();
        boolean isDuplicate = taskList.contains(deadline.toString());
        if (isDuplicate) {
            throw new DukeException("Error! You cannot have duplicate deadline task.");
        }
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
            throw new DukeException("Invalid Date Time: Date has to be in \"yyyy-MM-dd HH:mm\" format\n" + e.getMessage());
        }
        return deadline;
    }

    private void addToDo(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = getTodoTask();
        boolean isDuplicate = taskList.contains(todo.toString());
        if (isDuplicate) {
            throw new DukeException("Error! You cannot have duplicate todo task.");
        }
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
