package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import taskutil.Deadline;
import taskutil.Event;
import taskutil.Task;
import taskutil.TaskList;
import taskutil.Todo;

/**
 * Contains methods to read commands and dates from user input.
 */
public class Parser {

    // Format of date to be received as user input, saved to or read from data file.
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");

    /**
     * Parses Date from string to LocalDateTime object.
     * @param dateString String in the DATE_FORMAT form.
     * @return LocalDateTime object.
     * @throws DateTimeParseException To be handled in parseUserInput().
     */
    public static LocalDateTime parseDate(String dateString) throws DateTimeParseException {
        return LocalDateTime.parse(dateString, DATE_FORMAT);
    }

    /**
     * Converts LocalDateTime object to String for storing in data file.
     * @param dateTime LocalDateTime object to be parsed.
     * @return String in the DATE_FORMAT form.
     */
    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMAT);
    }

    /**
     * Parses and acts on user input for chatbot.
     * @param userInput String to be parsed.
     * @param taskList TaskList to be modified based on command used.
     * @return boolean to determine if chatbot should wait for next command.
     */
    public static boolean parseCommand(String userInput, TaskList taskList) {
        String[] splitInput = userInput.split(" ", 2); // Limit 2 to only separate out command word.
        switch (splitInput[0]) {
        case "mark":
            try {
                int index = Integer.parseInt(splitInput[1]) - 1;
                taskList.changeStatusIndex(index, true);
            } catch (NumberFormatException e) { // If argument of "mark" is not a number.
                Ui.output("You need to provide a valid number");
            }
            break;
        case "unmark":
            try {
                int index = Integer.parseInt(splitInput[1]) - 1;
                taskList.changeStatusIndex(index, false);
            } catch (NumberFormatException e) { // If argument of "unmark" is not a number.
                Ui.output("You need to provide a valid number");
            }
            break;
        case "delete":
            try {
                int index = Integer.parseInt(splitInput[1]) - 1;
                taskList.deleteTask(index);
            } catch (NumberFormatException e) { // If argument of "delete" is not a number.
                Ui.output("You need to provide a valid number:\n" + "       eg. delete 1");
            }
            break;
        case "todo":
            if (splitInput.length != 2) { // Checks for description before creating task.
                Ui.output("Wrong format, make sure your command is in the format:\n"
                        + "       todo [DESCRIPTION]");
            } else {
                Task todo = new Todo(splitInput[1]);
                taskList.addTask(todo, true);
            }
            break;
        case "deadline":
            try {
                String[] deadVar = splitInput[1].split(" /by ", 2);
                Task deadline = new Deadline(deadVar[0], parseDate(deadVar[1]));
                taskList.addTask(deadline, true);
            } catch (ArrayIndexOutOfBoundsException e) { // String not split due to improper format
                Ui.output("Wrong format, make sure your command is in the format:\n"
                        + "      deadline [DESCRIPTION] /by [dd.mm.yyyy]");
            } catch (DateTimeParseException e) { // Date not formatted properly
                Ui.output("Try the date format [dd.mm.yyyy tttt]:"
                        + "\n       eg. [05.08.2020 1500] for 5 Aug 2020, 3PM");
            }
            break;
        case "event":
            // Using 1 split statement that splits by /(from|to) allows wrong combos like "/to x /from x".
            try {
                String[] eventVar = splitInput[1].split(" /from ", 2);
                String[] times = eventVar[1].split(" /to ", 2);
                LocalDateTime start = parseDate(times[0]);
                LocalDateTime end = parseDate(times[1]);
                if (start.isAfter(end)) { // Checks that event start <= end
                    Ui.output("An event cannot end before it starts... might wanna check your dates");
                } else {
                    Task event = new Event(eventVar[0], start, end);
                    taskList.addTask(event, true);
                }
            } catch (ArrayIndexOutOfBoundsException e) { // String not split due to improper format
                Ui.output("Wrong format, make sure your command is in the format:\n"
                        + "      event [DESCRIPTION] /from [dd.mm.yyyy] /to [dd.mm.yyyy]");
            } catch (DateTimeParseException e) { // Date not formatted properly
                Ui.output("Try the date format [dd.mm.yyyy tttt]:"
                        + "\n       eg. [05.08.2020 1500] for 5 Aug 2020, 3PM");
            }
            break;
        case "find":
            Ui.output(taskList.queryList(splitInput[1]));
            break;
        case "list":
            Ui.output(taskList.listToString());
            break;
        case "end":
            Ui.output("Come back if you need anything else!");
            return false;
        default:
            Ui.output("Sorry, I don't recognise this comment :(");
            break;
        }
        return true;
    }
}
