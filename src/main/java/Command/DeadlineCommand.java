package Command;

import Tasks.DeadlineTask;
import Tasks.TaskList;
import enums.CommandWord;
import enums.DukeDateFormats;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private static final DukeDateFormats dateTimeFormat = DukeDateFormats.DATE_ONLY;

    public DeadlineCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand)) {
            return;
        }
        String[] args = getArgs(rawCommand);
        String description = args[1];
        LocalDate endDate = LocalDate.parse(args[3], dateTimeFormat.getFormatter());
        taskList.addTask(new DeadlineTask(description, endDate));
    }

    protected boolean validate(String rawCommand) {
        String[] args = getArgs(rawCommand);

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DEADLINE)) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.BY)) {
            return false;
        }

        if (!DukeDateFormats.validate(args[3], dateTimeFormat)) {
            return false;
        }

        return args.length == 4;
    }

}
