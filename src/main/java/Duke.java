public class Duke {

    private static final String BOT_NAME = "SoCrates";
    private static final String FILE_PATH = "data/tasks.txt";

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }

    public Duke(String filePath) {
        ui = new Ui(BOT_NAME);
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException exception) {
                ui.showErrorMessage(exception.getMessage());
            }
        }
    }

}
