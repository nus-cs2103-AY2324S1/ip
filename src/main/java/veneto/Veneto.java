package veneto;

import veneto.command.Command;
import veneto.command.ExitCommand;
import veneto.exceptions.VenetoException;
import veneto.storage.Storage;
import veneto.task.TaskList;
import veneto.ui.Ui;


public class Veneto {
    /* Fields */
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /* Constructor */
    /**
     * this constructor init Veneto
     * @param filePath the path of data stored
     */
    public Veneto(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = storage.load();
        } catch (VenetoException e) {
            ui.showError(e);
            tasks = new TaskList();
            storage.init(tasks);
        }
    }


    /* Methods */
    /**
     * creates Veneto and run it
     * @param args the path of data stored
     */
    public static void main(String[] args) {
        new Veneto("data/dan.txt").run();
    }

    /**
     * run Veneto
     */
    public void run() {
        ui.hello();
        chat();
    }

    /**
     * recursively chat with user
     */
    private void chat() {
        Command command = null;
        while (!(command instanceof ExitCommand)) {
            try {
                command = ui.getCommand();
                command.op(tasks);
                storage.checkChange(tasks);
                ui.afterCommand(command, tasks);
            } catch (VenetoException e) {
                ui.showError(e);
            }
        }
    }
}
