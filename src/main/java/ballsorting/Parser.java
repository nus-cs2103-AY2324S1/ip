package ballsorting;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Encapsulates the reading of user input.
 */
public class Parser {
    private Scanner sc;
    public Parser() {

    }

    /**
     * Creates a new instance of the Parser.
     * @param sc Scans the user input.
     */
    public Parser(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Parses the user input.
     * @param input Command entered by user.
     * @param taskList Tasks in the chatbot.
     */
    public String parseUserInput(String input, TaskList taskList) {

        if (input.equals("list")) {

            StringBuilder output = new StringBuilder();
            output.append("Here are the tasks in your list:\n");
            output.append(taskList.getStringList());
            return output.toString();

        } else if (input.startsWith("mark")) {

            int target = Integer.parseInt(input.substring(5)) - 1;
            return taskList.markTask(target);

        } else if (input.startsWith("unmark")) {

            int target = Integer.parseInt(input.substring(7)) - 1;
            return taskList.unmarkTask(target);

        } else if (input.startsWith("delete")) {

            int target = Integer.parseInt(input.substring(7)) - 1;
            return taskList.deleteTask(target);

        } else if (input.startsWith("find")) {

            if (input.length() == 4) {
                return "☹ OOPS!!! Please enter a search term";
            } else {
                String searchString = input.substring(4).trim();
                if (searchString.equals("")) {
                    return "☹ OOPS!!! Please enter a search term";
                } else {
                    return taskList.getSearchList(input.substring(5));
                }
            }

        } else {

            Task curr = null;
            StringBuilder description = new StringBuilder();
            StringBuilder start = new StringBuilder();

            if (input.startsWith("todo")) {

                String des = input.substring(4).trim();
                if (des.equals("")) {
                    return "☹ OOPS!!! The description of a todo cannot be empty.";
                } else {
                    curr = new Todo(des);
                }

            } else if (input.startsWith("deadline")) {

                int i = 9;
                while (i < input.length() && input.charAt(i) != '/') {
                    description.append(input.charAt(i));
                    i++;
                }
                i += 4;
                if (description.toString().equals("")) {
                    return "☹ OOPS!!! The description of a deadline cannot be empty.";
                } else if (i >= input.length() || input.substring(i).equals("")) {
                    return "☹ OOPS!!! The deadline of a deadline cannot be empty.";
                } else {
                    LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                    curr = new Deadline(description.toString(), endDateTime);
                }


            } else if (input.startsWith("event")) {

                int i = 6;
                while (i < input.length() && input.charAt(i) != '/') {
                    description.append(input.charAt(i));
                    i++;
                }
                i += 6;
                while (i < input.length() && input.charAt(i) != '/') {
                    start.append(input.charAt(i));
                    i++;
                }
                i += 4;
                if (description.toString().equals("")) {
                    return "☹ OOPS!!! The description of an event cannot be empty.";
                } else if (start.toString().equals("")) {
                    return "☹ OOPS!!! The start time of an event cannot be empty.";
                } else if (i >= input.length() || input.substring(i).equals("")) {
                    return "☹ OOPS!!! The end time of an event cannot be empty.";
                } else {
                    LocalDateTime startDateTime = LocalDateTime.parse(start.toString(), Ballsorter.inputFormatter);
                    LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                    curr = new Event(description.toString(), startDateTime, endDateTime);
                }

            } else {
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }

            return taskList.addTask(curr);
//            if (curr != null) {
//                taskList.addTask(curr);
//            }
        }
    }
}
