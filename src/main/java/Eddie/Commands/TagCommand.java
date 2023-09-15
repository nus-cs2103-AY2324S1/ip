package Eddie.Commands;

import Eddie.Storage;
import Eddie.Tasks.Task;
import Eddie.GUI.Ui;
public class TagCommand {
    public static String execute(Task t, String s) {
        t.tag(s);
        Storage.write();
        return Ui.tag(t, s);
    }
}
