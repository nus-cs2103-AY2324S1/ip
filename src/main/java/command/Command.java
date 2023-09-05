package command;

import duke.Duke;
import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;

import java.time.format.DateTimeFormatter;

public abstract class Command {

    protected final DateTimeFormatter DATE_FORMATTER = Duke.DATETIME_FORMATTER;
    private final String rawCommand;

    public Command(String rawCommand) {
        this.rawCommand = rawCommand;
    }

    public abstract void execute(TaskList tasklist);

    public String getRawCommand() {
        return this.rawCommand;
    }

    public boolean isByeCommand() {
        return CommandWord.commandWordToValueMap(Parser.getArgs(rawCommand)[0]).equals(CommandWord.BYE);
    }
}
