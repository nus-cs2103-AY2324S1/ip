package zean;

import java.util.ArrayList;
import java.util.regex.Pattern;

import zean.exception.DukeException;
import zean.task.Deadline;
import zean.task.Event;
import zean.task.Task;
import zean.task.Todo;

/**
 * The class that provides the parser of the input.
 */
public class Parser {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");

    /**
     * Parses the input and prints the corresponding output.
     *
     * @param input The input from the user.
     * @param tasks The taskList object to store the tasks.
     * @throws DukeException An exception related to the chatbot.
     */
    public static void parse(String input, TaskList tasks) throws DukeException {
        if (input.isBlank()) {
            return;
        }
        String[] inputArr = input.split(" ");
        String description;
        switch(inputArr[0]) {
        case "mark":
            if (inputArr.length < 2) {
                throw new DukeException("\tPlease provide the task number.");
            }
            try {
                tasks.markTaskDone(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease provide a valid task number.");
            }
            break;
        case "unmark":
            if (inputArr.length < 2) {
                throw new DukeException("\tPlease provide the task number.");
            }
            try {
                tasks.markTaskNotDone(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease provide a valid task number.");
            }
            break;
        case "delete":
            if (inputArr.length < 2) {
                throw new DukeException("\tPlease provide the task number.");
            }
            try {
                tasks.deleteTask(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease provide a valid task number.");
            }
            break;
        case "list":
            tasks.list();
            break;
        case "todo":
            description = input.substring(4);
            if (description.isBlank()) {
                throw new DukeException("\tHmm, the description of a todo cannot be empty :(");
            }
            tasks.add(description);
            break;
        case "deadline":
            int indexOfBy = input.indexOf("/by");

            if (indexOfBy == -1) {
                throw new DukeException("\tOOPS!!! You forgot to specify the deadline.\n\tUse \"/by\" to do so.");
            }

            description = input.substring(8, indexOfBy);
            if (description.isBlank()) {
                throw new DukeException("\tHmm, the description of a deadline cannot be empty :(");
            }

            String deadline = input.substring(indexOfBy + 3).strip();
            if (deadline.isBlank()) {
                throw new DukeException("\tOOPS!!! You forgot to specify the deadline.");
            }
            if (!DATE_PATTERN.matcher(deadline).matches()) {
                throw new DukeException("\tHmm, I don't understand the date. Use this format: YYYY-MM-DD");
            }

            tasks.add(description, deadline);
            break;
        case "event":
            int indexOfFrom = input.indexOf("/from");
            int indexOfTo = input.indexOf("/to");
            if (indexOfFrom == -1) {
                throw new DukeException("\tOOPS!!! You forgot to specify the starting date."
                        + "\n\tUse \"/from\" to do so.");
            }
            if (indexOfTo == -1) {
                throw new DukeException("\tOOPS!!! You forgot to specify the ending date.\n\tUse \"/to\" to do so.");
            }

            description = input.substring(5, indexOfFrom);
            if (description.isBlank()) {
                throw new DukeException("\tHmm, the description of an event cannot be empty :(");
            }
            String from = input.substring(indexOfFrom + 5, indexOfTo).strip();

            if (from.isBlank()) {
                throw new DukeException("\tOOPS!!! You forgot to specify the starting date.");
            }

            String to = input.substring(indexOfTo + 3).strip();
            if (to.isBlank()) {
                throw new DukeException("\tOOPS!!! You forgot to specify the ending date.");
            }

            if (!DATE_PATTERN.matcher(from).matches() || !DATE_PATTERN.matcher(to).matches()) {
                throw new DukeException("\tHmm, I don't understand the date. Use this format: YYYY-MM-DD");
            }
            tasks.add(description, from, to);
            break;
        default:
            throw new DukeException("\tOOPS!!! I'm sorry, but I don't understand what that means :-(");
        }
    }

    public static int parseToTask(ArrayList<Task> arrList, String text) {
        String[] textArr = text.split(" \\| ");
        int count = 0;
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
        return count;
    }
}
