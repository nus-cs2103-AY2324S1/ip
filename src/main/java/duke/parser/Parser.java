package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Parser class to parse user's input.
 */
public class Parser {

    /**
     * Select command to run.
     * @param input The user's input.
     * @param taskList The list of tasks.
     */
    public static String parseCommand(String input, TaskList taskList) {
        StringBuilder output = new StringBuilder();
        try {
            String trimmedInput = input.trim();
            if (input.equals("list")) {
                output.append(taskList.listTasks());
            } else if (input.startsWith("mark")) {
                String rest = trimmedInput.substring(4).trim();
                if (rest.isEmpty()) {
                    throw new InvalidArgumentException("The 'mark' command must be followed by a task index.");
                }
                int index = Integer.parseInt(input.substring(5));
                output.append(taskList.markAsDone(index));
            } else if (input.startsWith("unmark")) {
                String rest = trimmedInput.substring(6).trim();
                if (rest.isEmpty()) {
                    throw new InvalidArgumentException("The 'unmark' command must be followed by a task index.");
                }
                int index = Integer.parseInt(input.substring(7));
                output.append(taskList.markAsUndone(index));
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                output.append(taskList.addTask(input));
            } else if (input.startsWith("delete")) {
                String rest = trimmedInput.substring(6).trim();
                if (rest.isEmpty()) {
                    throw new InvalidArgumentException("The 'delete' command must be followed by a task index.");
                }
                int index = Integer.parseInt(input.substring(7));
                output.append(taskList.deleteTask(index));
            } else if (input.startsWith("find")) {
                String rest = trimmedInput.substring(4).trim();
                if (rest.isEmpty()) {
                    throw new InvalidArgumentException("The 'find' command must be followed by a keyword.");
                }
                String keyword = input.substring(5);
                output.append(taskList.findTasks(keyword));
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            output.append(Ui.getDottedLine());
            output.append(e.getMessage());
            output.append(Ui.getDottedLine());
        }
        return output.toString();
    }
}
