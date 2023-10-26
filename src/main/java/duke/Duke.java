package duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Represents the ChatBot.
     * @param filePath  The local storage path.
     * @throws DukeException    The exception to be thrown.
     */
    public Duke(String filePath) throws DukeException {
        assert !filePath.isEmpty() : "filePath must be a defined String";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     * @throws DukeException    The exception to be thrown.
     */
    public void run() throws DukeException {
        ui.echo("list", tasks);
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }

    public String getResponse(String input) {
        try {
            return ui.echo(input, tasks);

        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
