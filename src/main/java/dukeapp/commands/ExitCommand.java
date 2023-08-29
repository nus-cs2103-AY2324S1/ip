package dukeapp.commands;

import dukeapp.DukeConstants;

/**
 * Terminates the program.
 */
public class ExitCommand implements Command {
    /**
     * Prints an exit message and exit the application.
     *
     * @param input The user input.
     */
    @Override
    public void run(String input) {
        System.out.println(DukeConstants.EXIT_MESSAGE);
        System.exit(0);
    }
}
