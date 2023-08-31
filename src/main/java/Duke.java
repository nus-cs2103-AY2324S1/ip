import taskClasses.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        }
        catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Parser.run(ui, storage, tasks);
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}