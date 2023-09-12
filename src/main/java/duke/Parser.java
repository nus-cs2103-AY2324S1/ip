package duke;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Parser {
    TaskList taskList;
    private static final Pattern DATE_PATTERN_DASH = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern DATE_PATTERN_SLASH = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4} .*\\d{4}$");
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Writes task into Local Storage.
     * @param promptText    Task from user's input.
     * @throws DukeException    Exception to be thrown when the input cannot be read.
     */
    public String createTask(String promptText) throws DukeException {
        String returnString;
        if (promptText.startsWith("todo")) {
            try {
                Task todo = new Todo(promptText.substring(5));
                String returnStatement = taskList.add(todo, true);
                taskList.writeToFile();
                returnString = returnStatement;
            } catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of a todo cannot be empty.");
            }
        }
        else if (promptText.startsWith("deadline")) {
            try {
                String[] parts = promptText.split("/", 2);
                String date = parts[1].substring(3);
                if (DateParser.isEitherDate(date)) {
                    Task deadline = new Deadline(parts[0].substring(9),
                            DateParser.formatDate(date));
                    returnString = taskList.add(deadline, true);
                } else {
                    Task deadline = new Deadline(parts[0].substring(9), parts[1].substring(2));
                    returnString = taskList.add(deadline, true);
                }
                taskList.writeToFile();
            } catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
            }
        } else {
            try {
                String[] taskDetails = promptText.split("/", 2);
                String dateParts = taskDetails[1].substring(5);
                int splitIndex = dateParts.indexOf("/to");
                String startDate = dateParts.substring(0, splitIndex - 1);
                String endDate = dateParts.substring(splitIndex + 4);

                if (DateParser.isEitherDate(startDate) && DateParser.isEitherDate(endDate)) {
                    Task event = new Event(taskDetails[0].substring(6),
                            DateParser.formatDate(startDate), DateParser.formatDate(endDate));
                    returnString = taskList.add(event, true);
                } else {
                    Task event = new Event(taskDetails[0].substring(6), startDate, endDate);
                    returnString = taskList.add(event, true);
                }
                taskList.writeToFile();
            } catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of an event cannot be empty.");
            }
        }
        return returnString;
    }

    /**
     * Marks task as done or undone.
     *
     * @param promptText User's input to mark or unmark a task.
     * @return
     * @throws DukeException Exception that is thrown when the task does not exist.
     */
    public String markTask(String promptText) throws DukeException {
        String returnString;
        try {
            int i = Integer.parseInt(promptText.substring(promptText.length() - 1));
            Task t = taskList.get(i-1);
            if (promptText.startsWith("unmark")) {
                returnString = t.unmark();
                taskList.writeToFile();
            } else {
                returnString = t.mark(true);
                taskList.writeToFile();
            }
            return returnString;
        } catch (NumberFormatException n) {
            throw new DukeException("OOPS!! You must mark/unmark an index.");
        } catch (IndexOutOfBoundsException i) {
            throw new DukeException("OOPS!! This index doesn't exist.");
        }
    }

    public String findTask(String promptText) {
        String findTask = promptText.substring(5);
        String returnString = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).description.contains(findTask)) {
                returnString += i+1 + ". " + taskList.get(i).toString() + "\n";
            }
        }
        System.out.println("Here are the matching tasks in your list: ");
        System.out.println(returnString);
        return "Here are the matching tasks in your list: \n" + returnString;
    }
}
