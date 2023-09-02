package bot.command;

import bot.exception.FileErrorBotException;
import bot.exception.IllegalExpressionBotException;

import java.io.IOException;

public abstract class Command {

    public static final String SPACER = "____________________________________________________________";

    public abstract void execute() throws IllegalExpressionBotException, FileErrorBotException, IOException;
}
