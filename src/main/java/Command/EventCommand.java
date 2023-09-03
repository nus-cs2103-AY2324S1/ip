package Command;

import Tasks.EventTask;
import Tasks.TaskList;
import enums.CommandWord;
import enums.DukeDateFormats;

import java.time.LocalDate;

public class EventCommand extends Command {
    private static final DukeDateFormats dateTimeFormat = DukeDateFormats.DATE_ONLY;

    public EventCommand(String rawCommand) {
        super(rawCommand);
    }

    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand)) {
            return;
        }
        String[] args = getArgs(rawCommand);
        String description = args[1];
        LocalDate startDate = LocalDate.parse(args[3], dateTimeFormat.getFormatter());
        LocalDate endDate = LocalDate.parse(args[5], dateTimeFormat.getFormatter());
        taskList.addTask(new EventTask(description, startDate, endDate));
    }

    protected boolean validate(String rawCommand) {
        String[] args = getArgs(rawCommand);

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.EVENT)) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.FROM)) {
            return false;
        }

        if (!DukeDateFormats.validate(args[3], dateTimeFormat)) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[4]).equals(CommandWord.TO)) {
            return false;
        }

        if (!DukeDateFormats.validate(args[5], dateTimeFormat)) {
            return false;
        }

        return args.length == 6;
    }

}
