package Eddie.Commands;

import Eddie.GUI.Ui;

/**
 * Command to exit the chat bot.
 */
public class ExitCommand {
    public static String execute() {
        System.exit(0);
        return Ui.exit();
    }
}
