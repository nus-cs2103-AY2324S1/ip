package Duke;

import Duke.exception.DukeException;
import Duke.message.Message;
import Duke.parser.Ui;
import Duke.storage.Storage;
import Duke.tasklist.TaskList;

public class Duke {
    @SuppressWarnings("FieldCanBeLocal")
    private final String name = "Iris";
    private Ui ui;
    private boolean awake = true;
    public Duke(String filePath) {
        try {
            Storage storage = new Storage(filePath);
            TaskList taskList = new TaskList(storage);
            ui = new Ui(taskList);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        ui.ShowMessage(Message.OnGreeting(name));
        while(awake){
            ui.HandleLine();
        }
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\ortt2\\Documents\\ip\\src\\data\\tasks.txt").run();
    }

    protected void Exit()
    {
        awake = false;
    }
}
