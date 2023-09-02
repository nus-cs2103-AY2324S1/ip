package com.mimi.commands;
import com.mimi.main.Ui;

/**
 * A representation of the abstract idea of Commands.
 * @author Yuheng
 */
public abstract class Command {
    /**
     * Executes the given command.
     */
    public abstract void execute();

    /**
     * Calls the ui to give the appropriate response based on the type of command.
     * @param ui Ui class instance
     */
    public abstract void uiResponse(Ui ui);

    /**
     * Checks if the command given is the command to exit.
     * @return returns true if the command is the exit command and false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
