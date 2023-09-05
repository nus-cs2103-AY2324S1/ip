package command;

import duke.Duke;
import parser.Parser;
import tasks.EventTask;
import tasks.TaskList;
import enums.CommandWord;

import java.time.LocalDate;

public class EventCommand extends Command {
    public EventCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand)) {
            return;
        }
        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        LocalDate startDate = LocalDate.parse(args[3], super.DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(args[5], super.DATE_FORMATTER);
        taskList.addTask(new EventTask(description, startDate, endDate));
    }

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

        if (!Duke.validateDateTime(args[5])) {
            return false;
        }

        return true;
    }

}
