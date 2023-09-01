package rocket;

import java.time.format.DateTimeFormatter;



public class Rocket {
    public static final DateTimeFormatter uglyDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter prettyDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Create new chatbot
     * @param filePath filePath where saved tasks should be stored.
     */
    public Rocket(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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

        new Rocket("data/tasks.txt").run();
    }
}
