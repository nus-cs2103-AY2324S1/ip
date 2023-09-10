package command;

import java.time.LocalDate;

import duke.Duke;
import enums.CommandWord;
import parser.Parser;
import tasks.EventTask;
import tasks.TaskList;

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
     * @return `true` if the command is valid, `false` otherwise.
     */
    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 6) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.EVENT)) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.FROM)) {
            return false;
        }

        if (!Duke.validateDateTime(args[3])) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[4]).equals(CommandWord.TO)) {
            return false;
        }

        return Duke.validateDateTime(args[5]);
    }

    /**
     * Executes the "event" command. It parses the command, validates it, and adds a new
     * event task to the task list if the command is valid.
     *
     * @param taskList The task list to which the event task is added.
     */
    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand)) {
            return;
        }
        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        LocalDate startDate = LocalDate.parse(args[3], super.getDateTimeformatter());
        LocalDate endDate = LocalDate.parse(args[5], super.getDateTimeformatter());
        taskList.addTask(new EventTask(description, startDate, endDate));
    }
}
