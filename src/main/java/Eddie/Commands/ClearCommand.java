package Eddie.Commands;

import Eddie.GUI.Ui;
import Eddie.Storage;
import Eddie.TaskList;

public class ClearCommand {
    public static String execute() {
        TaskList.clear();


        Storage.write();
        return Ui.clear();
    }

}
