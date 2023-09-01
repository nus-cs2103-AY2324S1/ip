package chatbuddy;

import chatbuddy.command.Command;
import chatbuddy.parser.Parser;
import chatbuddy.storage.Storage;
import chatbuddy.ui.Ui;

public class ChatBuddy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ChatBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBuddyException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ChatBuddyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new ChatBuddy("data/tasks.txt").run();
    }
}
