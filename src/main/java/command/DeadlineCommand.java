package command;

import duke.Duke;
import parser.Parser;
import tasks.DeadlineTask;
import tasks.TaskList;
import enums.CommandWord;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String rawCommand) {
        super(rawCommand);
    }

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

    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 4){
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DEADLINE)) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.BY)) {
            return false;
        }

        if (!Duke.validateDateTime(args[3])) {
            return false;
        }

        return true;
    }

}
