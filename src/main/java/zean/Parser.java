package zean;

import java.util.ArrayList;
import java.util.regex.Pattern;

import zean.exception.ZeanException;
import zean.task.Deadline;
import zean.task.Event;
import zean.task.Task;
import zean.task.Todo;

/**
 * The class that provides the parser of the input.
 *
 * @author Zhong Han
 */
public class Parser {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");

    /**
     * Parses the input and calls the corresponding methods.
     *
     * @param input The input from the user.
     * @param tasks The taskList object to store the tasks.
     * @return The output to be printed on the console.
     * @throws ZeanException An exception related to the chatbot.
     */
    public static String parse(String input, TaskList tasks) throws ZeanException {
        if (input.isBlank()) {
            throw new ZeanException("OOPS!!! You forgot to type in anything!");
        }
        String[] inputArr = input.split(" ");
        String output;
        switch(inputArr[0]) {
        case "mark":
            output = parseMarkTask(input, tasks);
            break;
        case "unmark":
            output = parseUnmarkTask(input, tasks);
            break;
        case "delete":
            output = parseDeleteTask(input, tasks);
            break;
        case "list":
            output = tasks.list();
            break;
        case "todo":
            output = parseTodoTask(input, tasks);
            break;
        case "deadline":
            output = parseDeadlineTask(input, tasks);
            break;
        case "event":
            output = parseEventTask(input, tasks);
            break;
        case "find":
            output = parseFindTask(input, tasks);
            break;
        default:
            throw new ZeanException("OOPS!!! I'm sorry, but I don't understand what that means :-(");
        }
        return output;
    }

    private static String parseMarkTask(String input, TaskList tasks) {
        String[] inputArr = input.split(" ");
        String output;
        if (inputArr.length < 2) {
            throw new ZeanException("Please provide the task number.");
        }
        try {
            output = tasks.markTaskDone(Integer.parseInt(inputArr[1]));
        } catch (NumberFormatException e) {
            throw new ZeanException("Please provide a valid task number.");
        }
        return output;
    }

    private static String parseUnmarkTask(String input, TaskList tasks) {
        String[] inputArr = input.split(" ");
        String output;
        if (inputArr.length < 2) {
            throw new ZeanException("Please provide the task number.");
        }
        try {
            output = tasks.markTaskNotDone(Integer.parseInt(inputArr[1]));
        } catch (NumberFormatException e) {
            throw new ZeanException("Please provide a valid task number.");
        }
        return output;
    }

    private static String parseDeleteTask(String input, TaskList tasks) {
        String[] inputArr = input.split(" ");
        String output;
        if (inputArr.length < 2) {
            throw new ZeanException("Please provide the task number.");
        }
        try {
            output = tasks.deleteTask(Integer.parseInt(inputArr[1]));
        } catch (NumberFormatException e) {
            throw new ZeanException("Please provide a valid task number.");
        }
        return output;
    }

    private static String parseTodoTask(String input, TaskList tasks) {
        String description = input.substring(4);
        if (description.isBlank()) {
            throw new ZeanException("Hmm, the description of a todo cannot be empty :(");
        }
        String output = tasks.add(description);
        return output;
    }

    private static String parseDeadlineTask(String input, TaskList tasks) {
        int indexOfBy = input.indexOf("/by");

        if (indexOfBy == -1) {
            throw new ZeanException("OOPS!!! You forgot to specify the deadline."
                    + "\nUse \"/by\" to do so.");
        }

        String description = input.substring(8, indexOfBy);
        if (description.isBlank()) {
            throw new ZeanException("Hmm, the description of a deadline cannot be empty :(");
        }

        String deadline = input.substring(indexOfBy + 3).strip();
        if (deadline.isBlank()) {
            throw new ZeanException("OOPS!!! You forgot to specify the deadline.");
        }
        if (!DATE_PATTERN.matcher(deadline).matches()) {
            throw new ZeanException("Hmm, I don't understand the date. "
                    + "Use this format: YYYY-MM-DD");
        }

        String output = tasks.add(description, deadline);
        return output;
    }

    private static String parseEventTask(String input, TaskList tasks) {
        int indexOfFrom = input.indexOf("/from");
        int indexOfTo = input.indexOf("/to");
        if (indexOfFrom == -1) {
            throw new ZeanException("OOPS!!! You forgot to specify the starting date."
                    + "\nUse \"/from\" to do so.");
        }
        if (indexOfTo == -1) {
            throw new ZeanException("OOPS!!! You forgot to specify the ending date."
                    + "\nUse \"/to\" to do so.");
        }
        if (indexOfTo < indexOfFrom) {
            throw new ZeanException("OOPS!!! The start date should come before end date.");
        }

        String description = input.substring(5, indexOfFrom);
        if (description.isBlank()) {
            throw new ZeanException("Hmm, the description of an event cannot be empty :(");
        }
        String from = input.substring(indexOfFrom + 5, indexOfTo).strip();

        if (from.isBlank()) {
            throw new ZeanException("OOPS!!! You forgot to specify the starting date.");
        }

        String to = input.substring(indexOfTo + 3).strip();
        if (to.isBlank()) {
            throw new ZeanException("OOPS!!! You forgot to specify the ending date.");
        }

        if (!DATE_PATTERN.matcher(from).matches() || !DATE_PATTERN.matcher(to).matches()) {
            throw new ZeanException("Hmm, I don't understand the date. "
                    + "Use this format: YYYY-MM-DD");
        }
        String output = tasks.add(description, from, to);
        return output;
    }


    private static String parseFindTask(String input, TaskList tasks) {
        String description = input.substring(4);
        if (description.isBlank()) {
            throw new ZeanException("Please provide the keyword for me to search.");
        }
        String output = tasks.find(description);
        return output;
    }

    /**
     * Parses the formatted text read from the file and adds the created task to the ArrayList.
     *
     * @param arrList The ArrayList to contain the tasks.
     * @param text The formatted text read from the file.
     * @return The number of tasks added successfully to the ArrayList.
     */
    public static int parseToTask(ArrayList<Task> arrList, String text) {
        String[] textArr = text.split(" \\| ");
        int count = 0;
        try {
            switch (textArr[0]) {
            case "T":
                arrList.add(new Todo(textArr[1], textArr[2]));
                count++;
                break;
            case "D":
                arrList.add(new Deadline(textArr[1], textArr[2], textArr[3]));
                count++;
                break;
            case "E":
                arrList.add(new Event(textArr[1], textArr[2], textArr[3], textArr[4]));
                count++;
                break;
            default:
                break;
            }
        } catch (ZeanException e) {
            throw new ZeanException("Invalid format of data found!\nEdit the data in the file and restart the app.");
        }
        return count;
    }
}
