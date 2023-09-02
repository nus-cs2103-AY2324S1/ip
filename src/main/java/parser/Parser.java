package parser;

import exceptions.*;
import tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class serves as an interface between the user and the chatbot by
 * converting user inputs into commands and arguments that the chatbot understands,
 * before finally calling the chatbot's TaskList to execute these commands.
 */
public class Parser {
    /**
     * Splits the user's input into command and arguments.
     */
    public String[] parseInput(String userInput) {
        int firstSpace = userInput.indexOf(' ');
        String userCommand = firstSpace == -1 ? userInput : userInput.substring(0, firstSpace);
        String userArgs = firstSpace == -1 ? "" : userInput.substring(firstSpace + 1);
        return new String[]{userCommand, userArgs};
    }

    /**
     * Core function for parsing user arguments based on the command before the command is even executed.
     * @param userCommand Input command by the user.
     * @param userArgs Args for the command supplied by the user.
     * @throws EkudException Either invalid commands or illegal arguments for the commands.
     */
    public void parseAndExecute(String userCommand, String userArgs, TaskList taskList) throws EkudException {
        Command command = Command.getCommand(userCommand); // Command enum
        if (command == null) {
            throw new EkudInvalidCommandException("Command not found :(");
        }
        switch (command) {
        case SHOWTASKS:
            taskList.showTasks();
            break;
        case MARKTASKASDONE:
            try {
                int index = Integer.valueOf(userArgs) - 1;
                taskList.markTaskAsDone(index);
                break;
            } catch(NumberFormatException e) {
                throw new EkudIllegalArgException("Please input a valid index number :o");
            }
        case MARKTASKASNOTDONE:
            try {
                int index = Integer.valueOf(userArgs) - 1;
                taskList.markTaskAsNotDone(index);
                break;
            } catch(NumberFormatException e) {
                throw new EkudIllegalArgException("Please input a valid index number :o");
            }
        case ADDTODO:
            taskList.addToDo(userArgs);
            break;
        case ADDDEADLINE:
            try {
                String[] deadlineArgs = userArgs.split(" /by ");
                String description = deadlineArgs[0];
                LocalDateTime dateTime = this.parseDateTime(deadlineArgs[1]);
                taskList.addDeadline(description, dateTime);
                break;
            } catch(IndexOutOfBoundsException | DateTimeParseException e) {
                throw new EkudIllegalArgException("Deadline formatted wrongly\n" +
                        "-> Ensure 'deadline <description> /by <dd-mm-yyyy> OR <dd-MM-yyyy hhmm>' is followed\n"
                        + "-> For example: deadline finish quiz /by 03-10-2023 1830");
            }
        case ADDEVENT:
            try {
                String[] eventArgs = userArgs.split(" /from ");
                String[] timings = eventArgs[1].split(" /to ");
                String description = eventArgs[0];
                if (description.isBlank() || timings[0].isBlank() || timings[1].isBlank()) {
                    throw new EkudIllegalArgException("Description/start/end shouldn't be empty :(");
                }
                LocalDateTime fromDateTime = this.parseDateTime(timings[0]);
                LocalDateTime toDateTime = this.parseDateTime(timings[1]);
                taskList.addEvent(description, fromDateTime, toDateTime);
                break;
            } catch(IndexOutOfBoundsException | DateTimeParseException e) {
                throw new EkudIllegalArgException("Event formatted wrongly\n" +
                        "-> Ensure 'event <description> /from <dd-MM-yyyy hhmm> /to <dd-MM-yyyy hhmm>' is followed\n"
                        + "-> For example: event company dinner /from 03-10-2023 1730 /to 03-10-2023 2215");
            }
        case DELETETASK:
            try {
                int index = Integer.valueOf(userArgs) - 1;
                taskList.deleteTask(index);
            } catch(NumberFormatException e) {
                throw new EkudIllegalArgException("Please input a valid index number :o");
            }
        default:
            throw new EkudInvalidCommandException("Command not found :(");
        }
    }

    /**
     * Parses the user's input date and time into a LocalDateTime object.
     * @param inputDateTime User's input dateTime in the format dd-MM-yyyy HHmm.
     * @return LocalDateTime
     */
    public LocalDateTime parseDateTime(String inputDateTime) {
        String[] splitDateTime = inputDateTime.split(" ");
        String time = splitDateTime.length == 2 ? splitDateTime[1] : "2359";
        String date = splitDateTime[0];
        return LocalDateTime.parse(
                date + " " + time, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));

    }

    /**
     * Parses a date and time from the saved tasks file into a LocalDateTime object.
     * @param savedDateTime Saved dateTime in the format dd MMM yyyy h:mm a.
     * @return LocalDateTime
     */
    public LocalDateTime parseSavedDateTime(String savedDateTime) {
        return LocalDateTime.parse(savedDateTime, DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
}
