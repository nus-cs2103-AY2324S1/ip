package duke;

import java.time.format.DateTimeParseException;

public abstract class Parser {

    public static void parseInput(String[] spelt, String keyword)
            throws IllegalArgumentException {
        switch (keyword) {
        case "list":
            Taskmanager.list();
            break;
        case "delete":
            Taskmanager.removeTask(Integer.parseInt(spelt[1]));
            break;
        case "mark":
            Taskmanager.mark(Integer.parseInt(spelt[1]));
            break;
        case "unmark":
            Taskmanager.unmark(Integer.parseInt(spelt[1]));
            break;
        default:
            try {
                switch (keyword) {
                case "todo": {
                    String description = "";
                    for (int i = 1; i < spelt.length; ++i) {
                        description = description + spelt[i] + " ";
                    }
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Taskmanager.addTask(new ToDos(description));
                    break;
                }
                case "deadline": {
                    int i = 1;
                    if (i == spelt.length) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String temp = spelt[i];
                    String description = "";
                    while (!temp.equals("/by")) {
                        description = description + temp + " ";
                        i += 1;
                        temp = spelt[i];
                    }
                    i += 1;
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String time = "";
                    while (i < spelt.length) {
                        time = time + spelt[i] + " ";
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
                    if (i == spelt.length) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String temp = spelt[i];
                    String description = "";
                    while (!temp.equals("/from")) {
                        description = description + temp + " ";
                        i += 1;
                        temp = spelt[i];
                    }
                    i += 1;
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String start = "";
                    if (i == spelt.length) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The starting time of an event cannot be empty.");
                    }
                    temp = spelt[i];
                    while (!temp.equals("/to")) {
                        start = start + temp + " ";
                        i += 1;
                        temp = spelt[i];
                    }
                    i += 1;
                    if (start.isEmpty()) {
                        throw new IllegalArgumentException(
                                "☹ OOPS!!! The starting time of an event cannot be empty.");
                    }
                    String end = "";
                    while (i < spelt.length) {
                        end = end + spelt[i] + " ";
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
