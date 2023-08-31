package duke.parse.command;

import duke.Duke;

public interface Command {
    /**
     * Execute the command.
     * @return whether the execution allows the program to continue,
     * true if it can, false means the program must exit
     */
    boolean execute(Duke bot);
}
