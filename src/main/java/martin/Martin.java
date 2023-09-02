package martin;

import martin.commands.Command;
import martin.parser.Parser;
import martin.task.TaskList;

public class Martin {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Martin(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile());
            parser = new Parser(tasks);
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command cmd = parser.parse(command);
                cmd.execute();

                if ("bye".equalsIgnoreCase(command.trim())) {
                    isExit = true;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                storage.saveToFile(tasks.getTasks());
            }
        }
    }

    public static void main(String[] args) {
        new Martin("data/martin.txt").run();
    }
}
