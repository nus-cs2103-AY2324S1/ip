import dan.command.Command;
import dan.command.ExitCommand;
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
            ui.showError(e);
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
    }

    private void chat() {
        Command command = null;
        while (!(command instanceof ExitCommand)) {
            try {
                command = ui.getCommand();
                command.op(tasks);
                storage.checkChange(tasks);
                ui.afterCommand(command, tasks);
            } catch (DanException e) {
                ui.showError(e);
            }
        }
    }


}
