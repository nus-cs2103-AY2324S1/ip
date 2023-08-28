public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            taskList = new TaskList(storage.loadList());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    private void runRion() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.nextCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    
    public static void main(String[] args) {
        Duke rion = new Duke("./data/tasks.txt");
        rion.runRion();
    }
}
