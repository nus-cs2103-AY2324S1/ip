package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static int parseInput(String input, TaskList taskList) throws DukeException {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return 0;
        } else if (input.equals("list")) {
            taskList.printTasks();
        } else if (input.startsWith("mark")) {
            parseMark(input, taskList);
        } else if (input.startsWith("unmark")) {
            parseUnmark(input, taskList);
        } else if (input.startsWith("todo")) {
            parseToDo(input, taskList);
        } else if (input.startsWith("deadline")) {
            parseDeadline(input, taskList);
        } else if (input.startsWith("event")) {
            parseEvent(input, taskList);
        } else if (input.startsWith("delete")) {
            parseDelete(input, taskList);
        } else {
            throw new DukeException("I do not understand :(((");
        }

        return 1;
    }

    private static void parseToDo(String s, TaskList taskList) throws DukeException {
        String name = s.substring(4).trim();
        if (name.length() == 0) {
            throw new DukeException("Description of todo cannot be empty!");
        }

        taskList.addTask(new ToDo(name), true);
    }

    private static void parseDeadline(String s, TaskList taskList) throws DukeException {
        // Extract name and by
        int byIndex = s.indexOf("/by");
        if (byIndex == -1) {
            // "/by" not found
            throw new DukeException("Please include when the deadline is! (`deadline name /by date`)");
        }

        String name = s.substring(8, byIndex).trim();
        String by = s.substring(byIndex + 3).trim();

        if (name.equals("") || by.equals("")) {
            // No name or deadline
            throw new DukeException("Please include name and deadline!"
                    + "(`deadline name /by date (in yyyy-mm-dd)`)");
        }

        taskList.addTask(new Deadline(name, parseDate(by)), true);
    }

    private static void parseEvent(String s, TaskList taskList) throws DukeException {
        // Extract name, from and to
        int fromIndex = s.indexOf("/from");
        int toIndex = s.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            // "/from" or "/to" not found
            throw new DukeException("Please include when the event is from and to!"
                    + " (`event name /from date /to date`)");
        }

        String name = s.substring(5, fromIndex).trim();
        String from = s.substring(fromIndex + 5, toIndex).trim();
        String to = s.substring(toIndex + 3).trim();
        if (name.equals("") || from.equals("") || to.equals("")) {
            // No name, from or to
            throw new DukeException("Please include the name of the event"
                    + " and when the event is from and to! (`event name /from date /to date`)");
        }

        taskList.addTask(new Event(name, from, to), true);
    }

    private static void parseMark(String s, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(s.substring(4).trim()) - 1;

            taskList.markTask(index, true);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid number!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list index!");
        }
    }

    private static void parseUnmark(String s, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(s.substring(6).trim()) - 1;

            taskList.markTask(index, false);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid number!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list index!");
        }
    }

    private static void parseDelete(String s, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(s.substring(6).trim()) - 1;

            taskList.deleteTask(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid number!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list index!");
        }
    }

    public static Task parseTaskListEntry(String entry) {
        Task task;
        String[] splitEntry = entry.split("\\|"); // Split by "|"
        String type = splitEntry[0].trim();

        // Create task according to type
        if (type.equals("T")) {
            task = new ToDo(splitEntry[2].trim());
        } else if (type.equals("D")) {
            task = new Deadline(splitEntry[2].trim(), splitEntry[3].trim());
        } else if (type.equals("E")) {
            task = new Event(splitEntry[2].trim(), splitEntry[3].trim(), splitEntry[4].trim());
        } else {
            return null;
        }

        // Check if task is done
        task.setIsDone(splitEntry[1].trim().equals("1"));

        return task;
    }

    private static String parseDate(String date) {
        LocalDate date1 = LocalDate.parse(date);
        return date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
