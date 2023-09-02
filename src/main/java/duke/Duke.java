package duke;

import command.Command;

/**
 * Represents the command line application Duke.
 */
public class Duke {
    private DiskManager diskManager;
    private TaskManager taskManager;
    private Ui ui;

    /**
     * Constructs a default constructor.
     */
    public Duke() {

    }


    /**
     * Constructs Duke with the directory path and file name that specifies the filepath
     * of data Duke should run on.
     *
     * @param directoryPath The directory path.
     * @param fileName The file name.
     */
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

//    public String execute(String input) {
//        try {
//            Command c = Parser.parseCommand(input);
//            c.execute(taskManager, diskManager, ui);
//
//        } catch (DukeException e) {
//
//        }
//
//    }

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
