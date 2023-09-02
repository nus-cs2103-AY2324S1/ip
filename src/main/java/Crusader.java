public class Crusader {
    /** Logo generated from https://patorjk.com/software/taag */
    private static final String LOGO =
              "   _____                          _\n"
            + "  / ____|                        | |\n"
            + " | |     _ __ _   _ ___  __ _  __| | ___ _ __\n"
            + " | |    | '__| | | / __|/ _` |/ _` |/ _ \\ '__|\n"
            + " | |____| |  | |_| \\__ \\ (_| | (_| |  __/ |\n"
            + "  \\_____|_|   \\__,_|___/\\__,_|\\__,_|\\___|_|";

    /**
     * The filepath used to save data.
     */
    private static final String SAVE_FILE = "./data/crusader.txt";

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Crusader(String filePath, String logo) {
        TaskList taskList1;
        this.ui = new Ui(logo);
        this.storage = new Storage(filePath);
        try {
            taskList1 = new TaskList(storage.loadTasks());
        } catch (MissingSaveFileException e) {
            ui.say("Creating a new file, file does not exist!");
            taskList1 = new TaskList();
        } catch (CrusaderException e) {
            ui.say("Saved file is formatted wrongly!");
            taskList1 = new TaskList();
        }
        this.taskList = taskList1;
    }

    public void run() {
        this.ui.showLogo();
        this.ui.greet();
        boolean hasEnded = false;
        while (!hasEnded) {
            try {
                String command = this.ui.promptInput();
                Command c = Parser.parse(command);
                c.execute(ui, taskList);
                hasEnded = c.isExit();
            } catch (CrusaderException e) {
                this.ui.say(e.getMessage());
            }
        }
        this.ui.farewell();
        this.storage.saveTasks(taskList.getTasks());
    }

    public static void main(String[] args) {
        new Crusader(SAVE_FILE, LOGO).run();
    }
}
