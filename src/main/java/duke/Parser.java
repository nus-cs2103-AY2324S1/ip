package duke;

import java.time.format.DateTimeParseException;

/**
 * Provides functionality for parsing user inputs and executing corresponding commands.
 */
public abstract class Parser {

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
            Taskmanager.list();
            break;
        case "delete":
            Taskmanager.removeTask(Integer.parseInt(split[1]));
            break;
        case "mark":
            Taskmanager.mark(Integer.parseInt(split[1]));
            break;
        case "unmark":
            Taskmanager.unmark(Integer.parseInt(split[1]));
            break;
        default:
            try {
                switch (keyword) {
                case "todo": {
                    String description = "";
                    for (int i = 1; i < split.length; ++i) {
                        description = description + split[i] + " ";
                    }
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Taskmanager.addTask(new Todos(description));
                    break;
                }
                case "deadline": {
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
                    String time = "";
                    while (i < split.length) {
                        time = time + split[i] + " ";
                        i += 1;
                    }
                    if (time.isEmpty()) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The time/date of a deadline cannot be empty.");
                    }
                    Taskmanager.addTask(new Deadlines(description, time));
                    break;
                }
                case "event": {
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
                    String end = "";
                    while (i < split.length) {
                        end = end + split[i] + " ";
                        i += 1;
                    }
                    if (end.isEmpty()) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The ending time of an event cannot be empty.");
                    }
                    Taskmanager.addTask(new Events(description, start, end));
                    break;
                }
                default:
                    throw new IllegalArgumentException(
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println(e.getMessage() + "\nTry again: ");
            }
        }
    }
}
