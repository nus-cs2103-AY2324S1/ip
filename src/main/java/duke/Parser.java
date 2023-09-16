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

    // Format of datetime to be received as user input, saved to or read from data file.
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");

    /**
     * Parses Date from string to LocalDateTime object.
     *
     * @param dateString String in the DATE_FORMAT form.
     * @return LocalDateTime object.
     * @throws DateTimeParseException To be handled in parseUserInput().
     */
    public static LocalDateTime parseDate(String dateString) throws DateTimeParseException {
        assert dateString != null : "String to be parsed into date cannot be null";
        return LocalDateTime.parse(dateString, DATE_FORMAT);
    }

    /**
     * Converts LocalDateTime object to String for storing in data file.
     *
     * @param dateTime LocalDateTime object to be parsed.
     * @return String in the DATE_FORMAT form.
     */
    public static String formatDate(LocalDateTime dateTime) {
        assert dateTime != null : "LocalDateTime object to be formatted cannot be null";
        return dateTime.format(DATE_FORMAT);
    }

    /**
     * Parses help command.
     *
     * @return Chatbot message with details on all supported commands.
     */
    private static String parseHelp() {
        return "Wanna know what I can do? Here you go:\n"
                + "  [help] - The command you just did\n"
                + "  [mark] - Marks a task as done\n"
                + "  [unmark] - The opposite of mark\n"
                + "  [delete] - Makes me forget your task\n"
                + "  [todo] - Add a task with no date attached\n"
                + "  [deadline] - Add a task with a deadline (duh)\n"
                + "  [event] - Add a task with a start/end date\n"
                + "  [schedule] - I'll find all tasks that have to be done before this date\n"
                + "  [find] - Google but worse\n"
                + "  [list] - I'll literally list out every task you have, done or not\n"
                + "  [end] - Bye";
    }

    /**
     * Parses both the mark and unmark commands.
     *
     * @param splitInput User input containing index of task to be modified.
     * @param taskList TaskList containing all tasks.
     * @param isCompleted New status of task.
     * @return Chatbot output for mark/unmark command.
     */
    private static String parseSetMark(String[] splitInput, TaskList taskList, boolean isCompleted) {
        try {
            int index = Integer.parseInt(splitInput[1]) - 1;
            return taskList.setStatusByIndex(index, isCompleted);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // If argument of mark/unmark is not a number.
            return "You need to provide a valid number:\n" + "       eg. mark/unmark 1";
        }
    }

    /**
     * Parses delete command.
     *
     * @param splitInput User input containing index of task to be deleted.
     * @param taskList TaskList containing all tasks.
     */
    private static void parseDelete(String[] splitInput, TaskList taskList) {
        try {
            int index = Integer.parseInt(splitInput[1]) - 1;
            taskList.deleteTask(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // If argument of "delete" is not a number.
            Ui.setOutMessage("You need to provide a valid number:\n" + "       eg. delete 2");
        }
    }

    /**
     * Parses new To-do task command.
     *
     * @param splitInput User input containing arguments for new To-do task.
     * @param taskList TaskList for newly generated task to be added to.
     */
    private static void parseTodo(String[] splitInput, TaskList taskList) {
        if (splitInput.length != 2) { // Checks for presence of description before creating task.
            Ui.setOutMessage("Wrong format, make sure your command is in the format:\n"
                    + "       todo [DESCRIPTION]");
        } else {
            Task todo = new Todo(splitInput[1].trim());
            taskList.addTask(todo, true);
        }
    }

    /**
     * Parses new Deadline task command.
     *
     * @param splitInput User input containing arguments for new Deadline task.
     * @param taskList TaskList for newly generated task to be added to.
     */
    private static void parseDeadline(String[] splitInput, TaskList taskList) {
        try {
            String[] deadVar = splitInput[1].split(" /by ", 2);
            Task deadline = new Deadline(deadVar[0].trim(), parseDate(deadVar[1]));
            taskList.addTask(deadline, true);
        } catch (ArrayIndexOutOfBoundsException e) { // String not split due to improper format
            Ui.setOutMessage("Wrong format, make sure your command is in the format:\n"
                    + "      deadline [DESCRIPTION] /by [dd.mm.yyyy tttt]");
        } catch (DateTimeParseException e) { // Date not formatted properly
            Ui.setOutMessage("Try the date format [dd.mm.yyyy tttt]:"
                    + "\n       eg. [05.08.2020 1500] for 5 Aug 2020, 3PM");
        }
    }

    /**
     * Parses new Event task command.
     *
     * @param splitInput User input containing arguments for new Event task.
     * @param taskList TaskList for newly generated task to be added to.
     */
    private static void parseEvent(String[] splitInput, TaskList taskList) {
        // Using 1 split statement that splits by /(from|to) allows wrong combos like "/to x /from x".
        try {
            String[] eventVar = splitInput[1].split(" /from ", 2);
            String[] times = eventVar[1].split(" /to ", 2);
            LocalDateTime start = parseDate(times[0]);
            LocalDateTime end = parseDate(times[1]);
            if (start.isAfter(end)) { // Checks that event start <= end
                Ui.setOutMessage("An event cannot end before it starts... might wanna check your dates");
            } else {
                Task event = new Event(eventVar[0].trim(), start, end);
                taskList.addTask(event, true);
            }
        } catch (ArrayIndexOutOfBoundsException e) { // String not split due to improper format
            Ui.setOutMessage("Wrong format, make sure your command is in the format:\n"
                    + "      event [DESCRIPTION] /from [dd.mm.yyyy tttt] /to [dd.mm.yyyy tttt]");
        } catch (DateTimeParseException e) { // Date not formatted properly
            Ui.setOutMessage("Try the date format [dd.mm.yyyy tttt]:"
                    + "\n       eg. [05.08.2020 1500] for 5 Aug 2020, 3PM");
        }
    }

    /**
     * Parses Schedule command.
     *
     * @param splitInput User input containing arguments for Schedule command.
     * @param taskList TaskList to be queried.
     */
    private static String parseSchedule(String[] splitInput, TaskList taskList) {
        try {
            LocalDateTime queryDate = Parser.parseDate(splitInput[1]);
            return taskList.filterByTime(queryDate);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Wrong format, make sure your command is in the format:\n"
                    + "      schedule [dd.mm.yyyy tttt]";
        } catch (DateTimeParseException e) { // Date not formatted properly
            return "Try the date format [dd.mm.yyyy tttt]:"
                    + "\n       eg. [05.08.2020 1500] for 5 Aug 2020, 3PM";
        }
    }

    /**
     * Parses and acts on user input for chatbot.
     *
     * @param userInput String to be parsed.
     * @param taskList TaskList to be modified based on command used.
     * @return Chatbot reply to user command.
     */
    public static String parseCommand(String userInput, TaskList taskList) {
        String[] splitInput = userInput.trim().split(" ", 2); // Limit 2 to only separate out command word.
        String command = splitInput[0].toLowerCase();
        switch (command) { // break statements are redundant due to return statements.
        case "help":
            return parseHelp();
        case "mark":
            return parseSetMark(splitInput, taskList, true);
        case "unmark":
            return parseSetMark(splitInput, taskList, false);
        case "delete":
            parseDelete(splitInput, taskList);
            return Ui.getOutMessage();
        case "todo":
            parseTodo(splitInput, taskList);
            return Ui.getOutMessage();
        case "deadline":
            parseDeadline(splitInput, taskList);
            return Ui.getOutMessage();
        case "event":
            parseEvent(splitInput, taskList);
            return Ui.getOutMessage();
        case "schedule":
            return parseSchedule(splitInput, taskList);
        case "find":
            return taskList.queryList(splitInput[1]);
        case "list":
            return taskList.listToString();
        case "end":
            return "Come back if you need anything else!";
        default:
            return "You should try asking for [help]... no really do it";
        }
    }
}
