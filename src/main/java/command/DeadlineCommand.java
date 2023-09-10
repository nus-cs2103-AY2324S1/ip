package command;

import java.time.LocalDate;

import enums.CommandWord;
import parser.Parser;
import tasks.DeadlineTask;
import tasks.TaskList;
import woof.Woof;

/**
 * The `DeadlineCommand` class represents a command to create a new deadline task.
 * When executed, it parses the command and adds a new deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a new `DeadlineCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public DeadlineCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "deadline" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @return an empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 4) {
            return "Invalid number of arguments for deadline command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DEADLINE)) {
            return "Invalid command word for deadline command.";
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.BY)) {
            return "Invalid subcommand '/by' for deadline command.";
        }

        if (!Woof.validateDateTime(args[3])) {
            return "Invalid date/time format for the '/by' field.";
        }

        return ""; // Return an empty string if the command is valid
    }


    /**
     * Executes the "deadline" command. It parses the command, validates it, and adds a new
     * deadline task to the task list if the command is valid.
     *
     * @param taskList The task list to which the deadline task is added.
     */
    public String execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        String validationError = validate(rawCommand);

        if (!validationError.isEmpty()) {
            return validationError; // Return the error message
        }

        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        LocalDate endDate = LocalDate.parse(args[3], super.getDateTimeFormatter());
        return taskList.addTask(new DeadlineTask(description, endDate));
    }

}
