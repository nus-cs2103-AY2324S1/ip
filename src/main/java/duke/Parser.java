package duke;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.OutOfRangeException;
import exceptions.UnknownCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts user command into a Task
 */
public class Parser {

    public static String parseDeadline(String input, TaskList list) {
        try {
            if (input.substring(9).isBlank()) {
                throw new EmptyDescriptionException();
            }
            String[] split = input.substring(9).split(" /by ");
            String description = split[0];
            LocalDate date = LocalDate.parse(split[1]);
            String by = date.format(DateTimeFormatter.ofPattern("MMM d yyy"));
            return list.addTask(new Deadline(description, by));
        } catch(DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "oOps invalid time input";
        }
    }

    public static String parseToDo(String input, TaskList list) {
        try {
            String description = input.substring(5);
            if (description.isBlank()) {
                throw new EmptyDescriptionException();
            }
            return list.addTask(new Todo(description));
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static String parseEvent(String input, TaskList list) { //add date time formatter
        try {
            if (input.substring(6).isBlank()) {
                throw new EmptyDescriptionException();
            }
            String[] split = input.substring(6).split(" /from ");
            String description = split[0];
            String[] fromto = split[1].split(" /to ");
            String from = fromto[0];
            String to = fromto[1];
            return list.addTask(new Event(description, from, to));
        } catch (DukeException e) {
            return e.getMessage();
        }

    }
    public static String parseDelete(String input, TaskList list) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= 0 && index <= list.count) {
                return list.deleteTask(index);
            } else {
                throw new OutOfRangeException();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
    public static String parseMark(String input, TaskList list) {
        try {
            int num = input.charAt(5) - '0' - 1;
            if (num >= 0 && num < list.count) {
                list.getTask(num).markAsDone();
                return "Nice! I've marked this task done:\n" + list.getTask(num);
            } else {
                throw new OutOfRangeException();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }

    }
    public static String parseUnmark(String input, TaskList list) {
        try {
            int num = input.charAt(7) - '0' - 1;
            if (num >= 0 && num < list.count) {
                list.getTask(num).markAsUndone();
                return "OK, I've marked this task as not done yet:\n" + list.getTask(num);
            } else {
                throw new OutOfRangeException();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static String parseFind(String input, TaskList list) {
        String description = input.substring(5);
        return list.findTask(description, list);
    }

    public static String parseEdit(String input, TaskList list) {
        int num = input.charAt(5) - '0' - 1;
        String edit = input.substring(7);
        list.getTask(num).description = edit;
        return "Ok! I've edited this task :\n" + (num + 1) + ". " + list.getTask(num);
    }

}
