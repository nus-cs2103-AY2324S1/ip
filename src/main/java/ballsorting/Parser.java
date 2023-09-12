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

        } else if (input.equals("help")) {

            StringBuilder output = new StringBuilder();
            output.append("Here are the list of possible commands:\n");
            output.append("1. todo {description}\n");
            output.append("2. deadline {description} /by{yyyy-MM-dd HH:mm}\n");
            output.append("3. event {description} /from{yyyy-MM-dd HH:mm} /to{yyyy-MM-dd HH:mm}\n");
            output.append("4. mark {task number} - mark as done\n");
            output.append("5. unmark {task number} - mark as not done\n");
            output.append("6. delete {task number} - delete a task\n");
            output.append("7. find {key word} - search for a task\n");
            output.append("8. bye - quits the chatbot\n");
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
                return CustomErrorHandling.emptySearchTerm();
            } else {
                String searchString = input.substring(4).trim();
                if (searchString.equals("")) {
                    return CustomErrorHandling.emptySearchTerm();
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
                    return CustomErrorHandling.emptyTodoDescription();
                } else {
                    assert !des.equals("");
                    curr = new Todo(des);
                }

            } else if (input.startsWith("deadline")) {

                int i = "deadline ".length();
                while (i < input.length() && input.charAt(i) != '/') {
                    description.append(input.charAt(i));
                    i++;
                }
                i += "/by ".length();
                if (description.toString().equals("")) {
                    return CustomErrorHandling.emptyDeadlineDescription();
                } else if (i >= input.length() || input.substring(i).equals("")) {
                    return CustomErrorHandling.emptyDeadlineDuedate();
                } else {
                    LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                    assert !description.toString().equals("");
                    curr = new Deadline(description.toString(), endDateTime);
                }


            } else if (input.startsWith("event")) {

                int i = "event ".length();
                while (i < input.length() && input.charAt(i) != '/') {
                    description.append(input.charAt(i));
                    i++;
                }
                i += "/from ".length(); //6
                while (i < input.length() && input.charAt(i) != '/') {
                    start.append(input.charAt(i));
                    i++;
                }
                i += "/to ".length(); //4
                if (description.toString().equals("")) {
                    return CustomErrorHandling.emptyEventDescription();
                } else if (start.toString().equals("")) {
                    return CustomErrorHandling.emptyEventStartDate();
                } else if (i >= input.length() || input.substring(i).equals("")) {
                    return CustomErrorHandling.emptyEventEndDate();
                } else {
                    LocalDateTime startDateTime = LocalDateTime.parse(start.toString(), Ballsorter.inputFormatter);
                    LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                    if (endDateTime.isBefore(startDateTime)) {
                        return "☹ OOPS!!! The end time of an event cannot be before the start";
                    }
                    assert !description.toString().equals("");
                    curr = new Event(description.toString(), startDateTime, endDateTime);
                }

            } else {
                return CustomErrorHandling.commandNotFound();
            }

            assert curr != null;
            return taskList.addTask(curr);
        }
    }
}
