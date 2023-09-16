package veneto;

import veneto.command.Command;
import veneto.command.ExitCommand;
import veneto.exceptions.DanException;
import veneto.storage.Storage;
import veneto.task.TaskList;
import veneto.ui.Ui;


public class Veneto {
    /** Fields */
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /** Constructor */
    public Veneto(String filePath) {
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
        new Veneto("data/dan.txt").run();
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
