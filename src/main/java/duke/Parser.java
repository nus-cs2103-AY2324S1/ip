package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates all methods that processes the user's input to perform actions accordingly.
 */
public class Parser {
    /**
     * Parses the input gotten from the main menu.
     * @param input user input
     * @param taskList task list managed by the chatbot
     * @return process code, where 0 means the program should end and 1 otherwise
     * @throws DukeException if there are malformed inputs
     */
    public static String parseInput(String input, TaskList taskList) throws DukeException {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            return taskList.printTasks();
        } else if (input.startsWith("mark")) {
            return parseMark(input, taskList);
        } else if (input.startsWith("unmark")) {
            return parseUnmark(input, taskList);
        } else if (input.startsWith("todo")) {
            return parseToDo(input, taskList);
        } else if (input.startsWith("deadline")) {
            return parseDeadline(input, taskList);
        } else if (input.startsWith("event")) {
            return parseEvent(input, taskList);
        } else if (input.startsWith("delete")) {
            return parseDelete(input, taskList);
        } else if (input.startsWith("find")) {
            return parseFind(input, taskList);
        } else if (input.startsWith("tag")) {
            return parseTag(input, taskList);
        } else {
            throw new DukeException("I do not understand :(((");
        }
    }

    private static String parseToDo(String s, TaskList taskList) throws DukeException {
        String name = s.substring(4).trim();
        if (name.length() == 0) {
            throw new DukeException("Description of todo cannot be empty!");
        }

        return taskList.addTask(new ToDo(name), true);
    }

    private static String parseDeadline(String s, TaskList taskList) throws DukeException {
        // Extract name and by
        int byIndex = s.indexOf("/by");
        if (byIndex == -1) {
            // "/by" not found
            throw new DukeException("Please include when the deadline is! (`deadline name /by date`)");
        }

        assert s.contains("/by") : "input should contain /by";
        String name = s.substring(8, byIndex).trim();
        String by = s.substring(byIndex + 3).trim();

        if (name.equals("") || by.equals("")) {
            // No name or deadline
            throw new DukeException("Please include name and deadline!"
                    + "(`deadline name /by date (in yyyy-mm-dd)`)");
        }

        String parsedDate = parseDate(by);
        return taskList.addTask(new Deadline(name, parsedDate), true);
    }

    private static String parseEvent(String s, TaskList taskList) throws DukeException {
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

        return taskList.addTask(new Event(name, from, to), true);
    }

    private static String parseMark(String s, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(s.substring(4).trim()) - 1;
            return taskList.markTask(index, true);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid number!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list index!");
        }
    }

    private static String parseUnmark(String s, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(s.substring(6).trim()) - 1;
            return taskList.markTask(index, false);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid number!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list index!");
        }
    }

    private static String parseDelete(String s, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(s.substring(6).trim()) - 1;
            return taskList.deleteTask(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid number!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list index!");
        }
    }

    /**
     * Parse user command to find tasks containing given string.
     * @param s user input
     * @param taskList task list to find tasks from
     * @throws DukeException if input is missing the string to be found
     */
    private static String parseFind(String s, TaskList taskList) throws DukeException {
        String toFind = s.substring(4).trim();
        if (toFind.length() == 0) {
            throw new DukeException("Please enter a string to find!");
        }

        return taskList.findTasks(toFind);
    }

    private static String parseTag(String s, TaskList taskList) throws DukeException {
        try {
            String[] splitInput = s.split(" ");
            if (splitInput.length != 3) {
                throw new DukeException("Invalid input! Please use this format `tag index oneWordTag`");
            }

            int index = Integer.parseInt(splitInput[1].trim()) - 1;
            String tag = splitInput[2];
            return taskList.tagTask(index, tag);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid number!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number within the list index!");
        }
    }

    /**
     * Parses text from the pre-existing task list file to the corresponding Task object.
     * @param entry line from the task list file
     * @return corresponding Task object to the entry
     */
    public static Task parseTaskListEntry(String entry) {
        String[] splitEntry = entry.split("\\|"); // Split by "|"
        assert splitEntry.length >= 3;

        Task task = createTaskFromEntry(splitEntry);
        assert task != null : "task should be set";
        setTaskIsDoneFromEntry(task, splitEntry);
        tagTaskFromEntry(task, splitEntry);

        return task;
    }

    private static Task createTaskFromEntry(String[] splitEntry) {
        String type = splitEntry[0].trim();

        if (type.equals("T")) {
            return new ToDo(splitEntry[2].trim());
        } else if (type.equals("D")) {
            return new Deadline(splitEntry[2].trim(), splitEntry[3].trim());
        } else if (type.equals("E")) {
            return new Event(splitEntry[2].trim(), splitEntry[3].trim(), splitEntry[4].trim());
        } else {
            return null;
        }
    }

    private static void setTaskIsDoneFromEntry(Task task, String[] splitEntry) {
        boolean isDone = splitEntry[1].trim().equals("1");
        task.setIsDone(isDone);
    }

    private static void tagTaskFromEntry(Task task, String[] splitEntry) {
        String tags = splitEntry[splitEntry.length - 1].trim();
        if (tags.length() == 0) {
            return;
        }

        String[] tagsList = tags.split(" ");
        for (String tag : tagsList) {
            // Remove "#" at the start
            task.addTag(tag.substring(1));
        }
    }

    private static String parseDate(String date) throws DukeException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            format.parse(date);
            LocalDate date1 = LocalDate.parse(date);
            return date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (ParseException e) {
            throw new DukeException("Invalid date format! Please enter the date in format yyyy-MM-dd");
        }
    }
}
