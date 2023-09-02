package ben;

import java.util.Objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a parser that takes in a user command and outputs a task.
 */
public class Parser {
    /**
     * The task list.
     */
    private TaskList tasks;

    /**
     * The constructor taking in a task list.
     *
     * @param tasks The task list.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Takes in a command and outputs a task. Throws an InvalidCommandException if command is invalid.
     *
     * @param command The command input by the user.
     * @return a Task.
     * @throws InvalidCommandException Error for invalid commands.
     */
    public Command parse(String command) throws InvalidCommandException {
        String[] words = command.split("\\s+");
        if (words[0].equalsIgnoreCase("delete") ||
                words[0].equalsIgnoreCase("unmark") ||
                words[0].equalsIgnoreCase("mark")) {
            return referenceListCommandParse(command);
        } else if (words[0].equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (words[0].equalsIgnoreCase("list")) {
            return new ListCommand();
        }
        return new AddCommand(command);
    }

    /**
     * Takes in either a String representation of a unmark, mark or delete command and outputs the task. Returns
     * null if it is not one of these commands. Throws an InvalidCommandException if the command is invalid.
     *
     * @param message The command.
     * @return The task.
     * @throws InvalidCommandException Thrown when the command is invalid.
     */
    public Command referenceListCommandParse(String message) throws InvalidCommandException{
        Pattern pattern = Pattern.compile("(unmark|mark|delete)\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(message.toLowerCase());

        if (matcher.find()) {
            // extract command
            String command = matcher.group(1);

            // extract task number
            String TaskNumber = matcher.group(2);
            int num = Integer.parseInt(TaskNumber) - 1;

            // check whether number is valid
            if (num < 0 || num >= tasks.size()) {
                throw new InvalidCommandException("Please input a valid task number");
            }

            // if valid, mark or unmark the task
            Task task = tasks.get(num);

            if (Objects.equals(command, "mark")) {
                return new MarkCommand(task);
            } else if ((Objects.equals(command, "delete"))) {
                return new DeleteCommand(task);
            } else {
                return new UnmarkCommand(task);
            }
        }
        return null;
    }
}
