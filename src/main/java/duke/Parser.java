package duke;

import exceptions.InvalidInputException;
import exceptions.EmptyTaskException;
import exceptions.EmptyDateException;
import exceptions.OutOfRangeException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * The Parser class handles the parsing of user input and corresponding actions.
 */
public class Parser {
    private static String response;

    /**
     * Parses the user input and performs corresponding actions on the task list.
     *
     * @param userInput The input provided by the user.
     * @param taskList  The TaskList instance used to manage tasks.
     */
    public static String parseInput(String userInput, TaskList taskList) {
        try {
            if (Objects.equals(userInput, "bye")) {
                taskList.updateTaskFile();
                Ui.printExitMessage();
                System.exit(0);

            } else if (Objects.equals(userInput, "list")) {
                String listTasks = taskList.listTasks();
                if (listTasks != "") {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(listTasks);
                    return "Here are the tasks in your list:\n" + listTasks;
                } else {
                    System.out.println("There are no tasks in your list at the moment. Add some!");
                    return "There are no tasks in your list at the moment. Add some!";
                }

            } else if (userInput.startsWith("find")) {
                String foundTasks = taskList.find(userInput);
                if (foundTasks != "") {
                    System.out.println("Here are the matching tasks in your list:");
                    System.out.println(foundTasks);
                    return "Here are the matching tasks in your list:\n" + foundTasks;
                } else {
                    System.out.println("There are no matching tasks in your list.");
                    return "There are no matching tasks in your list.";
                }

            } else if (userInput.startsWith("mark")) {
                response = taskList.markTask(userInput);
                taskList.updateTaskFile();

            } else if (userInput.startsWith("unmark")) {
                response = taskList.unmarkTask(userInput);
                taskList.updateTaskFile();

            } else if (userInput.startsWith("todo")) {
                if (userInput.equals("todo")) {
                    throw new EmptyTaskException("todo");
                }
                response = taskList.makeToDo(userInput);
                taskList.updateTaskFile();

            } else if (userInput.startsWith("deadline")) {
                if (userInput.equals("deadline")) {
                    throw new EmptyTaskException("deadline");
                } else if (userInput.endsWith("/by")) {
                    throw new EmptyDateException("deadline");
                }
                response = taskList.makeDeadline(userInput);
                taskList.updateTaskFile();

            } else if (userInput.startsWith("event")) {
                if (userInput.equals("event")) {
                    throw new EmptyTaskException("event");
                }
                response = taskList.makeEvent(userInput);
                taskList.updateTaskFile();

            } else if (userInput.startsWith("delete")) {
                response = taskList.deleteTask(userInput);
                taskList.updateTaskFile();
            } else if (userInput.equals("sort")){
                taskList.sortedTasksByDate();
                String listSortedTasks = taskList.listSortedTasks();
                if (listSortedTasks != "") {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(listSortedTasks);
                    return "Here are the tasks in your list:\n" + listSortedTasks;
                } else {
                    System.out.println("There are no tasks in your list at the moment. Add some!");
                    return "There are no tasks in your list at the moment. Add some!";
                }

            } else if (userInput.equals("i love u")) {
                response = "i love u too <3";
            } else {
                throw new InvalidInputException("Invalid Input");
            }
        } catch (InvalidInputException | EmptyTaskException | EmptyDateException | OutOfRangeException |
                 IOException | DateTimeParseException e) {
            System.out.println(e);
            response = e.toString();
        }
        return response;
    }
}
