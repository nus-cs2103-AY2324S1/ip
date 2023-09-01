package duke;

import duke.Deadlines;
import duke.Events;

import java.time.format.DateTimeParseException;

public abstract class Parser {

    public static void parseInput(String[] splt, String keyword) {
        switch (keyword) {
        case "list":
            Taskmanager.list();
            break;
        case "delete":
            Taskmanager.removeTask(Integer.parseInt(splt[1]));
            break;
        case "mark":
            Taskmanager.mark(Integer.parseInt(splt[1]));
            break;
        case "unmark":
            Taskmanager.unmark(Integer.parseInt(splt[1]));
            break;
        default:
            try {
                switch (keyword) {
                case "todo": {
                    String description = "";
                    for (int i = 1; i < splt.length; ++i) {
                        description = description + splt[i] + " ";
                    }
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Taskmanager.addTask(new ToDos(description));
                    break;
                }
                case "deadline": {
                    int i = 1;
                    if (i == splt.length) {
                        throw new IllegalArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String temp = splt[i];
                    String description = "";
                    while (!temp.equals("/by")) {
                        description = description + temp + " ";
                        i += 1;
                        temp = splt[i];
                    }
                    i += 1;
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String time = "";
                    while (i < splt.length) {
                        time = time + splt[i] + " ";
                        i += 1;
                    }
                    if (time.isEmpty()) {
                        throw new IllegalArgumentException("☹ OOPS!!! The time/date of a deadline cannot be empty.");
                    }
                    Taskmanager.addTask(new Deadlines(description, time));
                    break;
                }
                case "event": {
                    int i = 1;
                    if (i == splt.length) {
                        throw new IllegalArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String temp = splt[i];
                    String description = "";
                    while (!temp.equals("/from")) {
                        description = description + temp + " ";
                        i += 1;
                        temp = splt[i];
                    }
                    i += 1;
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String start = "";
                    if (i == splt.length) {
                        throw new IllegalArgumentException("☹ OOPS!!! The starting time of an event cannot be empty.");
                    }
                    temp = splt[i];
                    while (!temp.equals("/to")) {
                        start = start + temp + " ";
                        i += 1;
                        temp = splt[i];
                    }
                    i += 1;
                    if (start.isEmpty()) {
                        throw new IllegalArgumentException("☹ OOPS!!! The starting time of an event cannot be empty.");
                    }
                    String end = "";
                    while (i < splt.length) {
                        end = end + splt[i] + " ";
                        i += 1;
                    }
                    if (end.isEmpty()) {
                        throw new IllegalArgumentException("☹ OOPS!!! The ending time of an event cannot be empty.");
                    }
                    Taskmanager.addTask(new Events(description, start, end));
                    break;
                }
                default:
                    throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println(e.getMessage() + "\nTry again: ");
            }
        }
    }
}
