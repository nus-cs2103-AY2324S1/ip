import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath, String fileDir) {
        this.storage = new Storage(filePath, fileDir);
        this.tasks = new TaskList(this.storage.load());
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public void start() throws IOException {
        ui.displayIntro();

        // Begin chatbot's main event loop
        String input = "";
        boolean isExit = false;
        while (!isExit) {
            ui.displayInputStart();
            input = ui.readInput();
            try {
                Command c = parser.parse(input);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayError(e.toString());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Duke chatbot = new Duke(
            "./data/data.txt", 
            "./data"
        );
        chatbot.start();
    }
}
