package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
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
        switch (type) {
        case 'T':
            String description = fullCommand.substring(5);
            Todo todo = new Todo(description);
            taskList.addTask(todo);
            ui.sendMessage("Got it. I've added this task:\n\t\t"
                    + todo
                    + "\n\tNow you have " + taskList.size() + " tasks in the list.");
            storage.updateFileContents(taskList);
            break;
        case 'D':
            String deadlineDescription = fullCommand.substring(9);
            String descriptionText = deadlineDescription.substring(0, deadlineDescription.indexOf("/by"));
            String dateTime = deadlineDescription.substring(deadlineDescription.indexOf("/by") + 4).trim();
            Deadline deadline = null;
            try {
                DateTimeFormatter altInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime byDateTime = LocalDateTime.parse(dateTime, altInputFormatter);
                deadline = new Deadline(descriptionText, byDateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date Time: " + e.getMessage());
            }
            taskList.addTask(deadline);
            ui.sendMessage("Got it. I've added this task:\n\t\t"
                    + deadline
                    + "\n\tNow you have " + taskList.size() + " tasks in the list.");
            storage.updateFileContents(taskList);
            break;
        case 'E':
            String eventDescription = fullCommand.substring(6);
            int indexFrom = eventDescription.indexOf("/from");
            int indexTo = eventDescription.indexOf("/to");

            String eventString = eventDescription.substring(0, indexFrom).trim();
            String startTime = eventDescription.substring(indexFrom + "/from".length(), indexTo).trim();
            String endTime = eventDescription.substring(indexTo + "/to".length()).trim();

            Event eventTask = new Event(eventString,
                    LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            taskList.addTask(eventTask);
            ui.sendMessage("Got it. I've added this task:\n\t\t"
                    + eventTask
                    + "\n\tNow you have " + taskList.size() + " tasks in the list.");
            storage.updateFileContents(taskList);
            break;
        default:
        }
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
