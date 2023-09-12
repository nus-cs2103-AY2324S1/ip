package duke;

import java.util.ArrayList;

import duke.exception.*;
import duke.taskclasses.TaskList;

/**
 * Interprets and processes user commands.
 */
public class Parser {

    /**
     * Enum representing the possible command types that can be executed.
     */
    private enum Command {
        TODO, DEADLINE, EVENT, BYE, MARK, UNMARK, LIST, DELETE, CLEAR, FIND
    }

    /**
     * Processes the user input, determines the command, and executes it.
     *
     * @param input    The user's input string.
     * @param ui       The UI instance for displaying messages to the user.
     * @param storage  The Storage instance for reading/writing tasks.
     * @param taskList The current list of tasks.
     * @return A list of strings representing Duke's response.
     */
    public static ArrayList<String> run(String input, Ui ui, Storage storage, TaskList taskList) {
        ArrayList<String> output = new ArrayList<>();
        String[] parts = input.split(" ");
        String[] details = input.split("/");
        Command command;
        try {
            command = getCommand(parts[0]);
            executeCommand(output, command, ui, taskList, parts, details, input);
            if (command == Command.BYE) {
                storage.writeToDB(taskList);
            }
            return output;
        } catch (DukeException e) {
            ArrayList<String> error = new ArrayList<>();
            error.add(e.toString());
            return error;
        }
    }

    /**
     * Converts a string to its corresponding Command.
     *
     * @param input The command string from the user.
     * @return The Command equivalent of the input.
     * @throws InvalidInputException If the input does not correspond to a valid command.
     */
    private static Command getCommand(String input) throws InvalidInputException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Adds strings from an array to an ArrayList.
     *
     * @param res    The array of strings.
     * @param output The ArrayList to add to.
     */
    public static void addToOutput(String[] res, ArrayList<String> output) {
        for (String i : res) {
            output.add(i);
        }
    }

    /**
     * Executes the specified command.
     *
     * @param output    The ArrayList to store the output messages.
     * @param command   The command to execute.
     * @param ui        The UI for user interactions.
     * @param taskList  The current list of tasks.
     * @param parts     The segmented input.
     * @param details   The split details from the input.
     * @param fullInput The complete user input string.
     * @throws DukeException If there's an error executing the command.
     */
    private static void executeCommand(ArrayList<String> output, Command command, Ui ui, TaskList taskList,
                                       String[] parts, String[] details, String fullInput) throws DukeException {

        Integer number = (command == Command.MARK || command == Command.UNMARK || command == Command.DELETE)
                ? getTaskNumber(parts, taskList) : null;


        switch (command) {
            case BYE:
                addToOutput(ui.printBye(), output);
                break;

            case DELETE:
                String content = taskList.deleteTask(number);
                addToOutput(ui.deleteTask(content), output);
                addToOutput(ui.printTaskCount(taskList.getTasksCount()), output);
                break;

            case LIST:
                addToOutput(ui.returnList(taskList), output);
                break;

            case UNMARK:
                taskList.markTaskAsNotDone(number);
                addToOutput(ui.printTaskMarkAsNotDone(taskList.getStatusAndDescription(number)), output);
                break;

            case MARK:
                taskList.markTaskAsDone(number);
                addToOutput(ui.printTaskMarkAsDone(taskList.getStatusAndDescription(number)), output);
                break;

            case TODO:
                String toDoDescription = details[0].trim();
                assert details.length >= 1 : "Details should have at least one entry for TODO command";
                if (toDoDescription.length() <= 4) {
                    throw new InvalidToDoException();
                }
                addToOutput(
                        taskList.addToDoToList(false,
                                toDoDescription.substring(5).trim()).addedTaskDescription(),
                        output);
                break;

            case DEADLINE:
                if (details[0].trim().length() <= 8 || !fullInput.contains("by")) {
                    throw new InvalidDeadlineException();
                }
                String deadlineDescription = details[0].split("/")[0].substring(9).trim();
                assert details.length >= 1 : "Details should have at least one entry for DEADLINE command";
                String byTime = fullInput.split("by")[1].trim();
                addToOutput(taskList.addDeadlineToList(false, deadlineDescription, byTime).addedTaskDescription(),
                        output);
                break;

            case EVENT:
                if (details[0].trim().length() <= 5 || !fullInput.contains("from") || !fullInput.contains("to")) {
                    throw new InvalidEventException();
                }
                String eventDescription = details[0].split("/")[0].substring(6).trim();
                assert details.length >= 1 : "Details should have at least one entry for EVENT command";
                String fromTime = fullInput.split("from")[1].split("/to")[0].trim();
                String toTime = fullInput.split("to")[1].trim();
                addToOutput(taskList.addEventToList(false, eventDescription, fromTime, toTime).addedTaskDescription(),
                        output);
                break;

            case CLEAR:
                taskList.clear();
                break;

            case FIND:
                String findDescription = details[0].trim();
                if (findDescription.length() <= 4) {
                    throw new InvalidFindException();
                }
                addToOutput(ui.printTaskContainingKeyword(taskList, findDescription.substring(5).trim()), output);
                break;
            default:
                throw new InvalidInputException();
        }


    }

    /**
     * Parses the task number from the command and verifies its validity.
     *
     * @param parts    The segmented input.
     * @param taskList The current list of tasks.
     * @return The valid task number.
     * @throws InvalidListNumberException If the task number is out of bounds.
     */
    private static int getTaskNumber(String[] parts, TaskList taskList) throws InvalidListNumberException {
        try {
            int number = Integer.parseInt(parts[1]);
            if (number <= 0 || number > taskList.getTasksCount()) {
                throw new InvalidListNumberException();
            }
            return number;
        } catch (NumberFormatException e) {
            throw new InvalidListNumberException();
        }
    }
}
