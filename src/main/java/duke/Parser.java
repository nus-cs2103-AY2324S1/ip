package duke;

import exceptions.*;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * The Parser class handles the parsing of user input and corresponding actions.
 */
public class Parser {
    private static String response;

    /**
     * Processes user input, invoking the parsing and execution of commands on the task list.
     *
     * @param userInput The input provided by the user.
     * @param taskList  The TaskList instance used to manage tasks.
     * @return A string containing the response or outcome of the user input processing.
     */
    public static String processUserInput(String userInput, TaskList taskList) {
        try {
            response = parseInput(userInput, taskList);
        } catch (InvalidInputException | EmptyTaskException | EmptyDateException | OutOfRangeException | IOException |
                 NonLinearDateTimeException | InvalidDateTimeFormatException e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        } catch (DateTimeParseException e) {
            response = "Invalid date or time! :(";
            System.out.println(response);
        } catch (NumberFormatException e) {
            response = "Invalid input. Index has to be a number.";
            System.out.println(response);
        }
        return response;
    }

    /**
     * Parses the user input and performs the corresponding actions on the task list.
     *
     * @param userInput The input provided by the user.
     * @param taskList  The TaskList instance used to manage tasks.
     * @return A string containing the response or outcome of the parsed input.
     * @throws InvalidInputException If the input is invalid or not recognized.
     * @throws EmptyTaskException    If the input for a task is empty.
     * @throws OutOfRangeException   If the input specifies an index that is out of range.
     * @throws EmptyDateException    If the date is missing for a deadline task.
     * @throws IOException           If there is an issue with reading or writing tasks to a file.
     */
    public static String parseInput(String userInput, TaskList taskList) throws InvalidInputException, EmptyTaskException, OutOfRangeException, EmptyDateException, IOException, NonLinearDateTimeException, InvalidDateTimeFormatException {
        userInput = userInput.trim();
        if (Objects.equals(userInput, "bye")) {
            response = Ui.sendExitMessage();
            System.exit(0);
        } else if (Objects.equals(userInput, "list")) {
            response = taskList.handleListCommand();
        } else if (userInput.startsWith("find")) {
            response = taskList.handleFindCommand(userInput);
        } else if (userInput.startsWith("mark")) {
            response = taskList.markTask(userInput);
        } else if (userInput.startsWith("unmark")) {
            response = taskList.unmarkTask(userInput);
        } else if (userInput.startsWith("todo")) {
            response = taskList.makeToDo(userInput);
        } else if (userInput.startsWith("deadline")) {
            response = taskList.makeDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            response = taskList.makeEvent(userInput);
        } else if (userInput.startsWith("delete")) {
            response = taskList.deleteTask(userInput);
        } else if (userInput.equals("sort")){
            response = taskList.handleSortCommand();
        } else if (userInput.equals("i love u")) {
            response = "i love u too <3";
        } else {
            throw new InvalidInputException("Invalid Input");
        }
        taskList.updateTaskFile();
        return response;
    }
}
