package jarvis.commands;

import java.time.LocalDateTime;

import jarvis.exceptions.InvalidDateTimeFormatException;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.ui.Ui;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.tasks.Event;
import jarvis.tasks.TaskList;

/**
 * Represents a command to add an "Event" task in the Jarvis app.
 */
public class EventCommand implements Command {

    private String userInput;

    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the event command by adding a new "Event" task to the task list.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui for user interface interactions.
     * @param storage  The Storage for saving tasks.
     * @throws InvalidIndexException      If an invalid index is provided.
     * @throws InvalidTaskFormatException If the task format is invalid.
     */
    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidIndexException, InvalidTaskFormatException {
        if (userInput.equalsIgnoreCase("event")) {
            throw new InvalidTaskFormatException(null);
        }
        assert taskList != null && ui != null && storage != null;

        int indexOfFrom = userInput.indexOf("from");
        int indexOfTo = userInput.indexOf("to");
        if (indexOfFrom == 1 || indexOfTo == 1 && indexOfFrom >= indexOfTo) {
            throw new InvalidIndexException(null);
        }
        try {
            return setEvent(taskList, ui, storage, indexOfFrom, indexOfTo);
        } catch (InvalidDateTimeFormatException e) {
            return e.getMessage();
        }
    }

    private String setEvent(TaskList taskList, Ui ui, Storage storage, int indexOfFrom, int indexOfTo)
            throws InvalidDateTimeFormatException {
        String taskTitle = userInput.substring(6, indexOfFrom).trim();
        String fromDateTime = userInput.substring(indexOfFrom + 4, indexOfTo).trim();
        String toDateTime = userInput.substring(indexOfTo + 2).trim();
        LocalDateTime formattedFromTime = Parser.parseStringToDateTime(fromDateTime);
        LocalDateTime formattedToTime = Parser.parseStringToDateTime(toDateTime);
        Event event = new Event(taskTitle, formattedFromTime, formattedToTime, false);
        taskList.addTask(event);
        storage.saveTasks(taskList.getTaskList());
        return ui.printResponse("Yes Master! I've added this task: \n" + "\t" + event.toString() + "\n"
                + "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
    }
}

