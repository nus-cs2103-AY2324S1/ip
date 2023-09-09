package com.alpha.commands;

/**
 * The type Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Instantiates a new Bye command.
     */
    public ByeCommand() {
        super(null);
    }

    @Override
    public String execute() {
        return "bye";
    }
}
