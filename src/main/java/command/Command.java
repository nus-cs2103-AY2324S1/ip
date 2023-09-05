package command;

import duke.Duke;
import parser.Parser;
import tasks.TaskList;
import enums.CommandWord;

import java.time.format.DateTimeFormatter;

public abstract class Command{

    private final String rawCommand;

    protected final DateTimeFormatter DATE_FORMATTER = Duke.DATETIME_FORMATTER;

    public abstract void execute(TaskList tasklist);

    public Command(String rawCommand) {
        this.rawCommand = rawCommand;
    }

    public String getRawCommand() {
        return this.rawCommand;
    }

    public boolean isByeCommand() {
        return CommandWord.commandWordToValueMap(Parser.getArgs(rawCommand)[0]).equals(CommandWord.BYE);
    }
}
