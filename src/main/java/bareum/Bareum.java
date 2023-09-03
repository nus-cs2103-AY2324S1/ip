package bareum;

import bareum.commands.ByeCommand;
import bareum.commands.Command;

public class Bareum {
    static TaskList taskList = new TaskList();

    public void run() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage();
        boolean isExit = false;

        storage.loadSavedTaskList(taskList);
        ui.showWelcomeMessage();

        while (!isExit) {
            try {
                ui.showLine();
                String input = ui.getUserInput();
                Command cmd = parser.parse(input);
                cmd.execute(ui, storage, taskList);
                if (cmd instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (BareumException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bareum().run();
    }
}
