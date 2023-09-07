package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;



/**
 * Class that handles userInput and return appropriate action.
 *
 * @author syamfarh
 */
public class Parser {

    /**
     * parse UserInput and passed the result to ui to output the result.
     *
     * @param input User input from main.
     * @param tasks current Tasklist from chatBot.
     * @param ui ui that control output result to user.
     */
    public static String replyUser(String input, TaskList tasks, Ui ui) throws DukeException {
        String result;

        switch (input) {
        case "bye":
            result = ui.exitGreeting();
            break;
        case "barbie":
            result = ui.customReply();
            break;
        case "list":
            result = ui.outputList(tasks);
            break;
        default:
            if (input.startsWith("mark ")) {
                try {
                    int i = Integer.parseInt(input.substring(5));
                    tasks.markTaskDone(i - 1);
                    result = ui.markSuccess(tasks.getTasks(i - 1));
                } catch (NumberFormatException err) {
                    throw new DukeException("☹ OOPS!!! The number input does not exist.", err);
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int i = Integer.parseInt(input.substring(7));
                    tasks.markTaskUndone(i - 1);
                    result = ui.unMarkSuccess(tasks.getTasks(i - 1));
                } catch (NumberFormatException err) {
                    throw new DukeException("☹ OOPS!!! The number input does not exist.", err);
                }
            } else if(input.startsWith("find ")) {
                String desc = input.substring(5);
                if (desc.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of find cannot be empty.",
                            new RuntimeException());
                } else {
                    result = ui.findSuccess(tasks.findTasks(desc));
                }
            } else {
                if (input.startsWith("todo ")) {
                    String desc = input.substring(5);
                    if (desc.length() == 0) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.",
                                new RuntimeException());
                    } else {
                        tasks.addTask(new ToDo(desc));
                        result = ui.toDoSuccess(tasks.getTasks(tasks.getSize() - 1), tasks.getSize());
                    }
                } else if (input.startsWith("deadline ")) {
                    try {
                        int index = input.indexOf("/by");
                        String date;
                        if (isValidDate(input.substring(index + 4))) {
                            date = parseDate(input.substring(index + 4));
                        } else {
                            date = input.substring(index + 4);
                        }
                        tasks.addTask(new Deadline(input.substring(9, index - 1), date));
                        result = ui.deadLineSuccess(tasks.getTasks(tasks.getSize() - 1), tasks.getSize());
                    } catch (StringIndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! The deadline format is incorrect! \n"
                                + "follow the format: deadline description /by end date", err);
                    }

                } else if (input.startsWith("event ")) {
                    try {
                        int indexFrom = input.indexOf("/from");
                        int indexTo = input.indexOf("/to");
                        String dateFrom;
                        if (isValidDate(input.substring(indexFrom + 6, indexTo - 1))) {
                            dateFrom = parseDate(input.substring(indexFrom + 6, indexTo - 1));
                        } else {
                            dateFrom = input.substring(indexFrom + 6, indexTo - 1);
                        }
                        String dateTo;
                        if (isValidDate(input.substring(indexTo + 4))) {
                            dateTo = parseDate(input.substring(indexTo + 4));
                        } else {
                            dateTo = input.substring(indexTo + 4);
                        }
                        tasks.addTask(new Event(input.substring(6, indexFrom - 1),
                                dateFrom,
                                dateTo));
                        result = ui.eventSuccess(tasks.getTasks(tasks.getSize() - 1), tasks.getSize());
                    } catch (StringIndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! The event format is incorrect! \n"
                                + "follow the format: event description /from start date /to end date", err);
                    }
                } else if (input.startsWith("delete ")) {
                    try {
                        int i = Integer.parseInt(input.substring(7));
                        Task removedTask = tasks.removeTask(i - 1);
                        result = ui.deleteSuccess(removedTask, tasks.getSize());
                    } catch (NumberFormatException err) {
                        throw new DukeException("☹ OOPS!!! The number input does not exist.", err);
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                            new RuntimeException());
                }
            }
            break;
        }

        return result;

    }
    /**
     * Dheck if the input is a valid Date.
     * valid date format: dd/MM/yyyy HHmm
     *
     * @param input of String type
     * @return true if it is a valid date format
     */
    public static boolean isValidDate(String input) {
        String[] splitInput = input.split("/");

        if (splitInput.length != 3) {
            return false;
        }

        if (!isNumeric(splitInput[0]) || !isNumeric(splitInput[1])) {
            return false;
        }

        String[] yearAndTime = splitInput[2].split(" ");

        if (yearAndTime.length != 2) {
            return false;
        }

        if (!isNumeric(yearAndTime[0]) || !isNumeric(yearAndTime[1])) {
            return false;
        }

        return true;
    }

    /**
     * Convert a valid String date to a different format.
     *
     * @param input of String type.
     * @return new String date format: MMM dd yyyy hh:mm a
     */
    private static String parseDate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime d1 = LocalDateTime.parse(input, formatter);

            DateTimeFormatter returnFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            return d1.format(returnFormatter);

        } catch (Exception e) {
            return input;
        }
    }

    /**
     * Check if the string input is a numeric characters.
     *
     * @param str of String type.
     * @return true if the str only contain numeric characters.
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); //match a number with optional '-' and decimal.
    }

}
