import errors.TaskErrors;
import errors.IncorrectMarkParametersException;
import tasks.*;
import ui.Ui;

import java.util.Scanner;

public class Dot {
    private static final TaskList dotTaskList = TaskList.newTaskList(100);

    public static void main(String[] args) {
        Ui.welcome();
        Scanner sc = new Scanner(System.in);

        boolean isOngoing = true;
        while (isOngoing) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    isOngoing = false;
                    break;
                case "list":
                    dotTaskList.list();
                    break;
                default:
                    // TODO: abstract out validation
                    /* TODO: In a later increment, there will be a requirement to abstract out the Parser
                             However, it is unclear whether we should keep constructing Tasks in TaskList.
                             We allow for Dot to access the 4 classes that are <: Task
                     */
                    // Note: To support more than 3 kinds of tasks, we can code a robust function and follow
                    // a standardised format.
                    if (input.startsWith("mark")) {
                        String[] substrings = input.split(" ");
                        if (substrings.length == 2) {
                            try {
                                int position = Integer.parseInt(substrings[1]);
                                dotTaskList.markTask(position - 1, true);
                            } catch (NumberFormatException e) {
                                TaskErrors.ERR_USING_MARK.printErrorMessage(e);
                            }
                        } else if (substrings.length == 1) {
                            TaskErrors.ERR_USING_MARK.printErrorMessage(
                                    new IncorrectMarkParametersException("No description of your todo"));
                        } else {
                            TaskErrors.ERR_USING_MARK.printErrorMessage(
                                    new IncorrectMarkParametersException("Too many parameters"));
                        }
                        break;
                    } else if (input.startsWith("unmark")) {
                        String[] substrings = input.split(" ");
                        if (substrings.length == 2) {
                            try {
                                int position = Integer.parseInt(substrings[1]);
                                dotTaskList.markTask(position - 1, false);
                            } catch (NumberFormatException e) {
                                TaskErrors.ERR_USING_UNMARK.printErrorMessage(e);
                            }
                        } else if (substrings.length == 1) {
                            TaskErrors.ERR_USING_UNMARK.printErrorMessage(
                                    new IncorrectMarkParametersException("No description of your todo"));
                        } else {
                            TaskErrors.ERR_USING_UNMARK.printErrorMessage(
                                    new IncorrectMarkParametersException("Too many parameters"));
                        }
                        break;
                    } else if (input.startsWith("todo")) {
                        String restOfString = input.substring(5);
                        // TODO: Due to increments, we delay error handling from Level-4 onwards
                        // Hence, we assume that input is correct, for now.
                        Task newTodoTask = new Todo(restOfString);
                        dotTaskList.addItem(newTodoTask);
                        break;
                    } else if (input.startsWith("deadline")) {
                        int indexOfSlash = input.indexOf("/");
                        String description = input.substring(9, indexOfSlash - 1);
                        String deadline = input.substring(indexOfSlash + 4);
                        Task newDeadlineTask = new Deadline(description, deadline);
                        dotTaskList.addItem(newDeadlineTask);
                        break;
                    } else if (input.startsWith("event")) {
                        int indexOfFirstSlash = input.indexOf("/");
                        int indexOfFSecondSlash = input.indexOf("/", indexOfFirstSlash + 1);
                        String description = input.substring(6, indexOfFirstSlash - 1);
                        // Note: if there is a space between start and secondSlash, we include it for compatability
                        String start = input.substring(indexOfFirstSlash + 5, indexOfFSecondSlash).strip();
                        String end = input.substring(indexOfFSecondSlash + 4);
                        Task newEventTask = new Event(description, start, end);
                        dotTaskList.addItem(newEventTask);
                        break;
                    }
                    break;
            }
        }
        Ui.goodbye();
    }
}
