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
            TaskMaster.list();
            break;
        case "delete":
            TaskMaster.removeTask(Integer.parseInt(split[1]));
            break;
        case "mark":
            TaskMaster.mark(Integer.parseInt(split[1]));
            break;
        case "unmark":
            TaskMaster.unmark(Integer.parseInt(split[1]));
            break;
        case "find":
            try {
                String key = "";
                for (int i = 1; i < split.length; ++i) {
                    key = key + split[i] + " ";
                }
                if (key.isEmpty()) {
                    throw new IllegalArgumentException(
                            "☹ OOPS!!! You must enter a keyword to search for tasks.");
                }
                key = key.substring(0, key.length() - 1);
                TaskMaster.findTask(key);
                break;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage() + "\nTry again: ");
            }
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
                    description = description.substring(0, description.length() - 1);
                    TaskMaster.addTask(new Todos(description));
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
                    TaskMaster.addTask(new Deadlines(description, time));
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
                    TaskMaster.addTask(new Events(description, start, end));
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
