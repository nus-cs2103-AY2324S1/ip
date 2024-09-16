package com.alpha.commands;

/**
 * The type Invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Instantiates a new Invalid command.
     */
    public InvalidCommand() {
        super(null);
    }

    @Override
    public String execute() {
        return "Please enter a valid command.";
    }
}
