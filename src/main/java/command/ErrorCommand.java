package command;

import exception.IllegalExpressionBotException;

public class ErrorCommand extends Command {

    public void execute() throws IllegalExpressionBotException {
        throw new IllegalExpressionBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
