package command;

import exception.IllegalExpressionBotException;

public abstract class Command {

    public static final String SPACER = "____________________________________________________________";

    public abstract void execute() throws IllegalExpressionBotException;
}
