package duke;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke (String filePath) {
        tasks = new TaskList();
        storage = new Storage(filePath, tasks);
        storage.startStorage();
        ui = new Ui(tasks);
        ui.startUi();
        storage.writeToStorage();
    }
    public static void main(String[] args) throws EmptyDescriptionException, InvalidCommandException, NotANumberException {
        Duke duke = new Duke("./ip/src/main/data/duke.txt");
    }
}
