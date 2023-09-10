package command;

import java.time.LocalDate;

import enums.CommandWord;
import parser.Parser;
import tasks.EventTask;
import tasks.TaskList;
import woof.Woof;

/**
 * The `EventCommand` class represents a command to create a new event task.
 * When executed, it parses the command, validates it, and adds a
 * new event task to the task list if the command is valid.
 */
public class EventCommand extends Command {

    /**
     * Constructs a new `EventCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public EventCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "event" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @return An empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 6) {
            return "Invalid number of arguments for event command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.EVENT)) {
            return "Invalid command word for event command.";
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.FROM)) {
            return "Invalid subcommand '/from' for event command.";
        }

        if (!Woof.validateDateTime(args[3])) {
            return "Invalid date/time format for the '/from' field.";
        }

        if (!CommandWord.commandWordToValueMap(args[4]).equals(CommandWord.TO)) {
            return "Invalid subcommand '/to' for event command.";
        }

        if (!Woof.validateDateTime(args[5])) {
            return "Invalid date/time format for the '/to' field.";
        }

        return ""; // Return an empty string if the command is valid
    }

    /**
     * Executes the "event" command. It parses the command, validates it, and adds a new
     * event task to the task list if the command is valid.
     *
     * @param taskList The task list to which the event task is added.
     */
    public String execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        String validationError = validate(rawCommand);
        if (isValidationError(validationError)) {
            return validationError;
        }
        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        LocalDate startDate = LocalDate.parse(args[3], super.getDateTimeFormatter());
        LocalDate endDate = LocalDate.parse(args[5], super.getDateTimeFormatter());
        return taskList.addTask(new EventTask(description, startDate, endDate));
    }

}
