package veneto;

import veneto.command.Command;
import veneto.exceptions.VenetoException;
import veneto.storage.Storage;
import veneto.task.TaskList;
import veneto.ui.Ui;

public class Veneto {
    /* Fields */
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private String storagePath = "./src/main/data/veneto.txt";

    /* Constructor */

    public Veneto() {
        try {
            ui = new Ui();
            storage = new Storage(storagePath);
            tasks = storage.load();
        } catch (VenetoException e) {
            ui.showError(e);
            tasks = new TaskList();
            storage.init(tasks);
        }
    }

    /* Methods */
    /**
     * operate the user input and return responses
     * @return the responses
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = ui.getCommand(input);
            command.op(tasks);
            storage.checkChange(tasks);
            response = ui.afterCommand(command, tasks);
        } catch (VenetoException e) {
            response = ui.showError(e);
        }
        return response;
    }
}
