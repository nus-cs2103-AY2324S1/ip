package Eddie.Commands;

import Eddie.GUI.Ui;

public class ExitCommand {
    public static String execute() {
        System.exit(0);
        return Ui.exit();
    }
}
