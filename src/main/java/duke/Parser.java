package duke;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.OutOfRangeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts user command into a Task
 */
public class Parser {

    /**
     * Takes in a deadline task detail as input to add a deadline task to the list.
     *
     * @param input User command.
     * @param list List for task to be added into.
     * @return Message to user when task is successfully added.
     */
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

    /**
     * Takes in to-do details as input to add a to-do task to the list.
     *
     * @param input User command.
     * @param list List for task to be added into.
     * @return Message to user when task is successfully added.
     */
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

    /**
     * Takes in event details as input to add an event task to the list.
     *
     * @param input User command.
     * @param list List for task to be added into.
     * @return Message to user when task is successfully added.
     */
    public static String parseEvent(String input, TaskList list) {
        try {
            if (input.substring(6).isBlank()) {
                throw new EmptyDescriptionException();
            }
            String[] split = input.substring(6).split(" /from ");
            String description = split[0];
            String[] fromTo = split[1].split(" /to ");
            LocalDate startDate = LocalDate.parse(fromTo[0]);
            LocalDate endDate = LocalDate.parse(fromTo[1]);
            String from = startDate.format(DateTimeFormatter.ofPattern("MMM d yyy"));
            String to = endDate.format(DateTimeFormatter.ofPattern("MMM d yyy"));
            return list.addTask(new Event(description, from, to));
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "oOps invalid time input";
        }
    }

    /**
     * Takes in an index as input to delete the corresponding task from the list.
     *
     * @param input User command.
     * @param list List for task to be deleted from.
     * @return Message to user when task is successfully deleted.
     */
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

    /**
     * Takes in an index as input to mark the corresponding task from the list
     * as completed.
     *
     * @param input User command.
     * @param list List for task to be marked.
     * @return Message to user when task is successfully marked
     */
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

    /**
     * Takes in an index as input to unmark the corresponding task from the list
     * as uncompleted.
     *
     * @param input User command.
     * @param list List for task to be unmarked.
     * @return Message to user when task is successfully unmarked
     */
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

    /**
     * Takes in a keyword as input to find the tasks with matching keywords.
     *
     * @param input User command.
     * @param list List for tasks to be found.
     * @return List of tasks with the same keyword
     */
    public static String parseFind(String input, TaskList list) {
        String description = input.substring(5);
        return list.findTask(description, list);
    }

    /**
     * Takes in an index as input to edit the corresponding task from the list.
     *
     * @param input User command.
     * @param list List for task to be edited.
     * @return Message to user when task is successfully edited
     */
    public static String parseEdit(String input, TaskList list) {
        try {
            int num = input.charAt(5) - '0' - 1;
            if (num >= 0 && num < list.count) {
                String edit = input.substring(7);
                list.getTask(num).description = edit;
                return "Ok! I've edited this task :\n" + (num + 1) + ". " + list.getTask(num);
            } else {
                throw new OutOfRangeException();
            }

        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
