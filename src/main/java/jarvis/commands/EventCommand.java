package jarvis.commands;

import java.time.LocalDateTime;

import jarvis.Event;
import jarvis.Parser;
import jarvis.Storage;
import jarvis.TaskList;
import jarvis.Ui;
import jarvis.exceptions.InvalidDateTimeFormatException;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;

public class EventCommand implements Command {

    private String userInput;

    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

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
