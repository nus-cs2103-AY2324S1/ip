package duke.parser;

import duke.exception.DukeException;
import duke.exception.IllegalTaskIndexException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Parser class to parse user's input.
 */
public class Parser {

    /**
     * Selects a command to run.
     * @param rawInput The user's input.
     * @param taskList The list of tasks.
     * @return The output of the command.
     */
    public static String parseCommand(String rawInput, TaskList taskList) {
        StringBuilder output = new StringBuilder();
        String input = rawInput.toLowerCase().trim();
        try {
            if (input.equals("list")) {
                output.append(taskList.listTasks());
            } else if (input.equals("sort")) {
                output.append(taskList.listSortedTasks());
            } else if (input.startsWith("sort by type")) {
                output.append(taskList.listSortedTasksByType());
            } else if (input.startsWith("mark")) {
                output.append(handleMark(input, taskList));
            } else if (input.startsWith("unmark")) {
                output.append(handleUnmark(input, taskList));
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                output.append(taskList.addTask(input));
            } else if (input.startsWith("delete")) {
                output.append(handleDelete(input, taskList));
            } else if (input.startsWith("find")) {
                output.append(handleFind(input, taskList));
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            output.append(handleDukeException(e));
        }
        return output.toString();
    }

    private static int extractIndexFromCommand(String input, String command) throws InvalidArgumentException {
        String rest = input.substring(command.length()).trim();
        if (rest.isEmpty()) {
            throw new InvalidArgumentException("The '" + command + "' command must be followed by a task index.");
        }
        return Integer.parseInt(rest);
    }

    private static String handleMark(String input, TaskList taskList)
            throws InvalidArgumentException, IllegalTaskIndexException {
        int index = extractIndexFromCommand(input, "mark");
        return taskList.markAsDone(index);
    }

    private static String handleUnmark(String input, TaskList taskList)
            throws InvalidArgumentException, IllegalTaskIndexException {
        int index = extractIndexFromCommand(input, "unmark");
        return taskList.markAsUndone(index);
    }

    private static String handleDelete(String input, TaskList taskList)
            throws InvalidArgumentException, IllegalTaskIndexException {
        int index = extractIndexFromCommand(input, "delete");
        return taskList.deleteTask(index);
    }

    private static String handleFind(String input, TaskList taskList) throws InvalidArgumentException {
        String keyword = extractArgFromCommand(input, "find");
        return taskList.findTasks(keyword);
    }

    private static String extractArgFromCommand(String input, String command) throws InvalidArgumentException {
        String rest = input.substring(command.length()).trim();
        if (rest.isEmpty()) {
            throw new InvalidArgumentException("The '" + command + "' command must be followed by a keyword.");
        }
        return rest;
    }

    private static String handleDukeException(DukeException e) {
        return Ui.getDottedLine() +
                e.getMessage() +
                Ui.getDottedLine();
    }
}
