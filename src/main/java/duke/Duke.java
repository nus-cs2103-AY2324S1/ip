package duke;

import command.Command;

/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> A command line app </p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public class Duke {
    private DiskManager diskManager;
    private TaskManager taskManager;
    private Ui ui;

    public Duke(String directoryPath, String fileName) {
        this.ui = new Ui();
        this.diskManager = new DiskManager(directoryPath, fileName);
        try {
            this.taskManager = this.diskManager.loadFromDisk();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.taskManager = new TaskManager();
        }
    }

    private void run() {
        ui.showWlcmMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parseCommand(input);
                c.execute(taskManager, diskManager, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printOutput(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data", "tasks.json").run();
    }
}
