import pardiyem.parser.Parser;

import java.io.IOException;

import pardiyem.command.Command;
import pardiyem.storage.Storage;
import pardiyem.ui.Ui;
import pardiyem.task.TaskList;
 
public class Pardiyem {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Pardiyem() throws IOException {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.load();
    }

    public void run() throws IOException, NoSuchMethodException {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } finally {
                ui.showDivider();
            }
        }
    }

    public static void main(String[] args) throws IOException, NoSuchMethodException {
        new Pardiyem().run();
    }
}
