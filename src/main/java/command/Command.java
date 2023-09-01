package command;

import exception.FileErrorBotException;
import exception.IllegalExpressionBotException;

public abstract class Command {

    public static final String SPACER = "____________________________________________________________";

    public abstract void execute() throws IllegalExpressionBotException, FileErrorBotException;
}
