package rocket;

import java.time.format.DateTimeFormatter;

public class Rocket{
    public static final DateTimeFormatter uglyDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter prettyDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    public static final String FILE_PATH = "data/tasks.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Create new chatbot
     */
    public Rocket() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (RocketException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the chatbot
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RocketException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Run a new chatbot
     * @param args idk.
     */
    public static void main(String[] args) {
        new Rocket().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (RocketException e) {
            return e.getMessage();
        }
        return ui.getLastResponse();
    }

}
