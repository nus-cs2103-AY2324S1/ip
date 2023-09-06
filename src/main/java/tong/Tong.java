package tong;

import tong.command.Command;
import tong.command.CommandResult;
import tong.command.ExitCommand;

public class Tong {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Tong() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.load();
        parser = new Parser();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = parser.parse(fullCommand);
            c.setData(this.tasks);
            CommandResult result = c.execute();
            ui.showResultToUser(result);
            isExit = ExitCommand.isExit(c);
        }
        storage.save(this.tasks);
    }

    public static void main(String[] args) {
        new Tong().run();
    }
}
