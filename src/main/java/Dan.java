import dan.command.Command;
import dan.exceptions.DanException;
import dan.task.TaskList;


public class Dan {
    /** Fields */
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /** Constructor */
    public Dan(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = storage.load();
        } catch (DanException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            storage.init(tasks);
        }
    }


    /** Methods */
    public static void main(String[] args) {
        new Dan("data/dan.txt").run();
    }


    public void run() {
        ui.hello();
        chat();
        ui.goodbye();
    }

    private void chat() {
        Command command;
        while (true) {
            command = ui.getCommand();
            command.op(tasks);
            storage.checkChange(tasks);
            ui.afterCommand(command, tasks);
        }
    }


}
