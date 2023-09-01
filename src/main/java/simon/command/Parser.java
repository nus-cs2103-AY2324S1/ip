package simon.command;

import simon.SimonException;
import simon.task.Task;
import simon.task.ToDo;
import simon.task.Deadline;
import simon.task.Event;

public class Parser {

    public enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

    public static Command parseCommand(String input) {
        try {
            return Parser.Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Parser.Command.UNKNOWN;
        }
    }

    public static Task parseAddTask(String inData, Command commandType) throws SimonException {
        Task task;
        switch (commandType) {
            case TODO:
                String description = inData.split("todo ").length > 1 ? inData.split("todo ")[1] : "";
                if (description.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new ToDo(description);
                break;

            case DEADLINE:
                String[] deadlineParts = inData.split("deadline ");
                if (deadlineParts.length <= 1 || !inData.contains(" /by ")) {
                    throw new SimonException("☹ OOPS!!! The format for deadline is incorrect. Expected format: 'deadline [task description] /by [dd/mm/yyyy HHmm]'. Time(HHmm) is optional.");
                }
                String nameDeadline = deadlineParts.length > 1 ? deadlineParts[1].split(" /by ")[0] : "";
                if (nameDeadline.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String endDate = deadlineParts[1].split(" /by ").length > 1 ? deadlineParts[1].split(" /by ")[1] : "";
                if (endDate.trim().isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The deadline date cannot be empty.");
                }
                task = new Deadline(nameDeadline, endDate);
                break;

            case EVENT:
                String[] eventParts = inData.split("event ");
                if (eventParts.length <= 1 || !inData.contains("/from ") || !inData.contains("/to ")) {
                    throw new SimonException("☹ OOPS!!! The format for event is incorrect. Expected format: 'event [event description] /from [dd/mm/yyyy HHmm] /to [dd/mm/yyyy HHmm]'. The time(HHmm) is optional.");
                }

                String[] fromToParts = eventParts[1].split("/from ");
                if (fromToParts.length < 2) {
                    throw new SimonException("☹ OOPS!!! The format for event is missing 'from' information.");
                }

                String eventName = fromToParts[0].trim();
                if (eventName.isEmpty()) {
                    throw new SimonException("☹ OOPS!!! The description of an event cannot be empty.");
                }

                String[] toParts = fromToParts[1].split("/to ");
                if (toParts.length < 2) {
                    throw new SimonException("☹ OOPS!!! The format for event is missing 'to' information.");
                }

                String startDate = toParts[0].trim();
                String endDateEvent = toParts[1].trim();

                task = new Event(eventName, startDate, endDateEvent);
                break;

            default:
                throw new SimonException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return task;
    }

}
