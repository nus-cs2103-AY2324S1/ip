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
    public static String parseInput(String userInput, TaskList taskList) throws InvalidInputException, EmptyTaskException, OutOfRangeException, EmptyDateException, IOException {
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

    public static String processUserInput(String userInput, TaskList taskList) {
        try {
            parseInput(userInput, taskList);
        } catch (InvalidInputException | EmptyTaskException | EmptyDateException | OutOfRangeException | IOException e) {
            System.out.println(e);
            response = e.toString();
        } catch (DateTimeParseException e) {
            response = "Invalid date or time! :(";
        }
        return response;
    }
}
