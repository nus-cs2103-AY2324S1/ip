package command;

import duke.Duke;
import enums.CommandWord;
import parser.Parser;
import tasks.DeadlineTask;
import tasks.TaskList;

import java.time.LocalDate;

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
     * @return `true` if the command is valid, `false` otherwise.
     */
    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 4) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DEADLINE)) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.BY)) {
            return false;
        }

        return Duke.validateDateTime(args[3]);
    }
  
    /**
     * Executes the "deadline" command. It parses the command, validates it, and adds a new
     * deadline task to the task list if the command is valid.
     *
     * @param taskList The task list to which the deadline task is added.
     */
    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand)) {
            return;
        }
        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        LocalDate endDate = LocalDate.parse(args[3], super.DATE_FORMATTER);
        taskList.addTask(new DeadlineTask(description, endDate));
    }
}
