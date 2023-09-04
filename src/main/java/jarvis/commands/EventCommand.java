package jarvis.commands;

import java.time.LocalDateTime;

import jarvis.Parser;
import jarvis.Storage;
import jarvis.Ui;
import jarvis.exceptions.InvalidDateTimeFormatException;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;
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
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidIndexException, InvalidTaskFormatException {
        if (userInput.equalsIgnoreCase("event")) {
            throw new InvalidTaskFormatException(null);
        }
        int indexOfFrom = userInput.indexOf("from");
        int indexOfTo = userInput.indexOf("to");

        if (indexOfFrom != 1 && indexOfFrom <= userInput.length() ||
                indexOfTo != 1 && indexOfTo <= userInput.length()) {
            try {
                String taskTitle = userInput.substring(6, indexOfFrom).trim();
                String fromDateTime = userInput.substring(indexOfFrom + 4, indexOfTo).trim();
                String toDateTime = userInput.substring(indexOfTo + 2).trim();
                LocalDateTime formattedFromTime = Parser.parseDateTime(fromDateTime);
                LocalDateTime formattedToTime = Parser.parseDateTime(toDateTime);
                Event event = new Event(taskTitle, formattedFromTime, formattedToTime, false);
                taskList.addTask(event);
                storage.saveTasks(taskList.getTaskList());
                ui.printResponse("Yes Master! I've added this task: \n" + "\t" + event.toString() + "\n" +
                        "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
            } catch (InvalidDateTimeFormatException e) {
                System.err.println(e.getMessage());
            }
        } else {
            throw new InvalidIndexException(null);
        }
    }

}
