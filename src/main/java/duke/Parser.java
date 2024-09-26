package duke;

import java.time.format.DateTimeParseException;

/**
 * Provides functionality for parsing user inputs and executing corresponding commands.
 */
public abstract class Parser {
    private static String response;

    /**
     * Parses the user input and dispatches corresponding commands.
     *
     * @param split The split of user input as an array of strings.
     * @param keyword The keyword/command from the user input.
     */
    public static void parseInput(String[] split, String keyword)
            throws IllegalArgumentException {
        switch (keyword) {
        case "list":
            Parser.response = TaskMaster.list();
            break;
        case "delete":
            Parser.response = TaskMaster.removeTask(Integer.parseInt(split[1]));
            break;
        case "mark":
            Parser.response = TaskMaster.mark(Integer.parseInt(split[1]));
            break;
        case "unmark":
            Parser.response = TaskMaster.unmark(Integer.parseInt(split[1]));
            break;
        case "peekN":
            try {
                Parser.peekNotes(split);
                break;
            } catch (IllegalArgumentException e) {
                Parser.response = e.getMessage() + "\nTry again: ";
                break;
            }
        case "editN":
            try {
                Parser.editNotes(split);
                break;
            } catch (IllegalArgumentException e) {
                Parser.response = e.getMessage() + "\nTry again: ";
                break;
            }
        case "find":
            try {
                Parser.find(split);
                break;
            } catch (IllegalArgumentException e) {
                Parser.response = e.getMessage() + "\nTry again: ";
                break;
            }
        default:
            try {
                Parser.parseTask(keyword, split);
            } catch (IllegalArgumentException | DateTimeParseException e) {
                Parser.response = e.getMessage() + "\nTry again: ";
            }
        }
    }

    private static void parseTask(String keyword, String[] split) throws IllegalArgumentException {
        switch (keyword) {
            case "todo": {
                parseTodo(split);
                break;
            }
            case "deadline": {
                parseDeadline(split);
                break;
            }
            case "event": {
                parseEvent(split);
                break;
            }
            default:
                throw new IllegalArgumentException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void parseTodo(String[] split) throws IllegalArgumentException {
        String description = "";
        for (int i = 1; i < split.length; ++i) {
            description = description + split[i] + " ";
        }
        if (description.isEmpty()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The description of a todo cannot be empty.");
        }
        description = description.substring(0, description.length() - 1);
        Parser.response = TaskMaster.addTask(new Todos(description));
    }

    private static void parseDeadline(String[] split) throws IllegalArgumentException {
        int i = 1;
        if (i == split.length) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String temp = split[i];
        String description = "";
        while (!temp.equals("/by")) {
            description = description + temp + " ";
            i += 1;
            temp = split[i];
        }
        i += 1;
        if (description.isEmpty()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        description = description.substring(0, description.length() - 1);
        String time = "";
        while (i < split.length) {
            time = time + split[i] + " ";
            i += 1;
        }
        if (time.isEmpty()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The time/date of a deadline cannot be empty.");
        }
        time = time.substring(0, time.length() - 1);
        Parser.response = TaskMaster.addTask(new Deadlines(description, time));
    }

    private static void parseEvent(String[] split) throws IllegalArgumentException {
        int i = 1;
        if (i == split.length) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The description of an event cannot be empty.");
        }
        String temp = split[i];
        String description = "";
        while (!temp.equals("/from")) {
            description = description + temp + " ";
            i += 1;
            temp = split[i];
        }
        i += 1;
        if (description.isEmpty()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The description of an event cannot be empty.");
        }
        description = description.substring(0, description.length() - 1);
        String start = "";
        if (i == split.length) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The starting time of an event cannot be empty.");
        }
        temp = split[i];
        while (!temp.equals("/to")) {
            start = start + temp + " ";
            i += 1;
            temp = split[i];
        }
        i += 1;
        if (start.isEmpty()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The starting time of an event cannot be empty.");
        }
        start = start.substring(0, start.length() - 1);
        String end = "";
        while (i < split.length) {
            end = end + split[i] + " ";
            i += 1;
        }
        if (end.isEmpty()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! The ending time of an event cannot be empty.");
        }
        end = end.substring(0, end.length() - 1);
        Parser.response = TaskMaster.addTask(new Events(description, start, end));
    }

    private static void find(String[] split) throws IllegalArgumentException {
        String key = "";
        for (int i = 1; i < split.length; ++i) {
            key = key + split[i] + " ";
        }
        if (key.isEmpty()) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! You must enter a keyword to search for tasks.");
        }
        key = key.substring(0, key.length() - 1);
        Parser.response = TaskMaster.findTask(key);
    }

    private static void peekNotes(String[] split) throws IllegalArgumentException {
        if (split.length == 1) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! You must enter an index to see its notes.");
        }
        // need to handle the case where user did not put integer for the parameter
        Parser.response = TaskMaster.peekNotes(Integer.parseInt(split[1]));
    }

    private static void editNotes(String[] split) throws IllegalArgumentException {
        if (split.length == 1) {
            throw new IllegalArgumentException(
                    "☹ OOPS!!! You must enter an index to see its notes.");
        }
        // need to handle the case where user did not put integer for the parameter
        String notes = "";
        for (int i = 2; i < split.length; ++i) {
            notes = notes + split[i] + " ";
        }
        if (!notes.isEmpty()) {
            notes = notes.substring(0, notes.length() - 1);
        }
        Parser.response = TaskMaster.editNotes(Integer.parseInt(split[1]), notes);
    }

    /**
     * Retrieves the returned response from method called.
     *
     * @return a returned response from method called.
     */
    public static String getResponse() {
        return response;
    }
}
