package duke.util;

import duke.task.*;

import java.time.LocalDate;

public class Parser {
    public static Deadline parseLoadDeadline(String input) {
        int byIndex = input.indexOf('|', 7);

        String description = input.substring(8, byIndex - 1);
        String by = input.substring(byIndex + 2);

        LocalDate byDate = LocalDate.parse(by);
        Deadline deadline = new Deadline(description, byDate);

        if (input.charAt(4) == '1') {
            deadline.markAsDone();
        }

        return deadline;
    }

    public static Event parseLoadEvent(String input) {
        int fromIndex = input.indexOf('|', 7);
        int toIndex = input.indexOf('|', fromIndex + 1);

        String description = input.substring(8, fromIndex - 1);
        String from = input.substring(fromIndex + 2, toIndex - 1);
        String to = input.substring(toIndex + 2);

        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        Event event = new Event(description, fromDate, toDate);

        if (input.charAt(4) == '1') {
            event.markAsDone();
        }

        return event;
    }

    public static ToDo parseLoadToDo(String input) {
        String description = input.substring(8);

        ToDo todo = new ToDo(description);

        if (input.charAt(4) == '1') {
            todo.markAsDone();
        }

        return todo;
    }

    public static Deadline parseUserDeadline(String input) throws DeadlineException {
        int byIndex = input.indexOf("/by");

        if (input.length() <= 9) {
            throw new DeadlineException();
        } else if (byIndex == -1) {
            throw new DeadlineException();
        }

        String description = input.substring(9, byIndex - 1);
        String by = input.substring(byIndex + 4);

        LocalDate byDate = LocalDate.parse(by);

        Deadline deadline = new Deadline(description, byDate);
        return deadline;
    }

    public static Event parseUserEvent(String input) throws EventException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (input.length() <= 6) {
            throw new EventException();
        } else if (fromIndex == -1 || toIndex == -1) {
            throw new EventException();
        } else if (fromIndex > toIndex) {
            throw new EventException();
        }

        String description = input.substring(6, fromIndex - 1);
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);

        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        Event event = new Event(description, fromDate, toDate);
        return event;
    }

    public static ToDo parseUserToDo(String input) throws ToDoException {
        if (input.length() <= 5) {
            throw new ToDoException();
        }

        ToDo todo = new ToDo(input.substring(5));
        return todo;
    }

    public static int parseUserMark(String input, int size) throws TaskException {
        int index = Integer.valueOf(input.substring(5)) - 1;

        if (input.length() <= 5) {
            throw new TaskException();
        } else if (index >= size) {
            throw new TaskException();
        }

        return index;
    }

    public static int parseUserUnmark(String input, int size) throws TaskException {
        int index = Integer.valueOf(input.substring(7)) - 1;

        if (input.length() <= 7) {
            throw new TaskException();
        } else if (index >= size) {
            throw new TaskException();
        }

        return index;
    }

    public static int parseUserDelete(String input, int size) throws TaskException {
        int index = Integer.valueOf(input.substring(7)) - 1;

        if (input.length() <= 7) {
            throw new TaskException();
        } else if (index >= size) {
            throw new TaskException();
        }

        return index;
    }
}
