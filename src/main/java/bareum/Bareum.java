package bareum;

import bareum.commands.ByeCommand;
import bareum.commands.Command;

public class Bareum {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Bareum(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public void run() {
        boolean isExit = false;

        storage.loadSavedTaskList(taskList);
        ui.showWelcomeMessage();

        while (!isExit) {
            try {
                ui.showLine();
                String input = ui.getUserInput();
                Command cmd = Parser.parse(input);
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
        new Bareum("./data/storedTasks.txt").run();
    }
}
