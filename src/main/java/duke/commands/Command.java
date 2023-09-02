package duke.commands;

import duke.Duke;
import duke.service.UiService;

/**
 * Represents an abstract command that can be executed in the Duke application.
 * <p>
 * All commands that are intended to be executed in Duke should extend this class.
 * </p>
 */
public abstract class Command {
    protected Duke dukeBot;
    protected UiService uiService;

    /**
     * Constructs a Command with the given Duke instance and UI service.
     *
     * @param dukeBot   The main Duke instance.
     * @param uiService The UI service for interactions.
     */
    protected Command(Duke dukeBot, UiService uiService) {
        this.dukeBot = dukeBot;
        this.uiService = uiService;
    }

    /**
     * Executes the command.
     */
    public abstract void execute();

    /**
     * Indicates if the command should cause the program to exit.
     *
     * @return False by default; should be overridden by commands that cause program exit.
     */
    public boolean isExit() {
        return false;
    }
}