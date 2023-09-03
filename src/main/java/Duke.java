

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadTasks(), ui);
        this.parser = new Parser(ui, taskList);
    }

    public void run() {
        ui.welcomeMessage();
        String command = ui.getUserInput();
        while (parser.parseCommand(command)) {
            command = ui.getUserInput();
            storage.saveTasks(taskList);
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./data/gideon.txt").run();
    }
}
