import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    /**
     * Return specific String output based on user Input.
     *
     * @param input User input from main.
     * @return output reply.
     */
    public static String replyUser(String input, TaskList tasks) {
        String output;
        switch (input) {
        case "bye":
            output = "Bye. Hope to see you again soon!";
            break;
        case "barbie":
            output = "Hi barbie!";
            break;
        case "list":
            output = outputList(tasks);
            break;
        default:
            if (input.startsWith("mark ")) {
                try {
                    Integer i = Integer.valueOf(input.substring(5));
                    tasks.markTaskDone(i - 1);
                    output = "Nice! I've marked this task as done: \n" + tasks.getTasks(i - 1).toString();
                } catch (NumberFormatException err) {
                    output = "☹ OOPS!!! The number input does not exist.";
                } catch (DukeException e) {
                    output = e.toString();
                }
            } else if(input.startsWith("unmark ")) {
                try {
                    Integer i = Integer.valueOf(input.substring(7));
                    tasks.markTaskUndone(i - 1);
                    output = "OK, I've marked this task as not done yet: \n" + tasks.getTasks(i - 1).toString();
                } catch (NumberFormatException err){
                    output = "☹ OOPS!!! The number input does not exist.";
                } catch (DukeException e) {
                    output = e.toString();
                }
            } else {
                if (input.startsWith("todo ")) {
                    String desc = input.substring(5);
                    if (desc.length() == 0) {
                        output = "☹ OOPS!!! The description of a todo cannot be empty.";
                    } else {
                        tasks.addTask(new ToDo(desc));
                        output = "Got it. I've added this task: \n" + tasks.getTasks(tasks.getSize() - 1) + "\n"
                                + "Now you have " + tasks.getSize() + " tasks in the list.";
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
                        tasks.addTask(new Deadline(input.substring(9,index - 1), date));
                        output = "Got it. I've added this task: \n" + tasks.getTasks(tasks.getSize() - 1) + "\n"
                                + "Now you have " + tasks.getSize() + " tasks in the list.";
                    } catch (StringIndexOutOfBoundsException err) {
                        output= "☹ OOPS!!! The deadline format is incorrect! \n" +
                                "follow the format: deadline description /by end date";
                    }

                } else if (input.startsWith("event ")) {
                    try {
                        int indexFrom = input.indexOf("/from");
                        int indexTo = input.indexOf("/to");
                        String dateFrom;
                        if (isValidDate(input.substring(indexFrom + 6,indexTo - 1))) {
                            dateFrom = parseDate(input.substring(indexFrom + 6,indexTo - 1));
                        } else {
                            dateFrom = input.substring(indexFrom + 6,indexTo - 1);
                        }
                        String dateTo;
                        if (isValidDate(input.substring(indexTo + 4))) {
                            dateTo = parseDate(input.substring(indexTo + 4));
                        } else {
                            dateTo = input.substring(indexTo + 4);
                        }
                        tasks.addTask(new Event(input.substring(6,indexFrom - 1),
                                dateFrom,
                                dateTo));
                        output = "Got it. I've added this task: \n" + tasks.getTasks(tasks.getSize() - 1) + "\n"
                                + "Now you have " + tasks.getSize() + " tasks in the list.";
                    } catch (StringIndexOutOfBoundsException err){
                        output= "☹ OOPS!!! The event format is incorrect! \n" +
                                "follow the format: event description /from start date /to end date";
                    }
                } else if (input.startsWith("delete ")) {
                    try {
                        Integer i = Integer.valueOf(input.substring(7));
                        Task removedTask = tasks.removeTask(i - 1);
                        output = "Noted. I've removed this task: \n" + removedTask.toString() +
                                "\nNow you have " + tasks.getSize() + " tasks in the list.";
                    } catch (NumberFormatException err){
                        output = "☹ OOPS!!! The number input does not exist.";
                    } catch (DukeException e) {
                        output = e.toString();
                    }
                } else {
                    output = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                }
            }
            break;
        }

        return output;
    }
    /**
     * check if the input is a valid Date.
     * valid date format: dd/MM/yyyy HHmm
     * @param input of String type
     * @return true if it is a valid date format
     */
    private static boolean isValidDate(String input) {
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
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }


    /**
     * Output all the user input.
     *
     * @return this.lists
     */
    private static String outputList(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: ");
        int i = 1;
        for (Task val : tasks.getTasks()) {
            output.append("\n").append(i).append(". ").append(val);
            i++;
        }

        return output.toString();
    }
}
