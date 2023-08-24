import java.io.IOException;

/**
 * Represents the Minion chatbot.
 */
public class Minion {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * A constructor for the Minion chatbot.
     * @param filePath The file path of the file storing the task list.
     */
    public Minion(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Minion("data/tasks.txt").run(args);
    }

    /**
     * Driver function for main.
     * @param args arguments passed in to stdin.
     */
    private void run(String[] args) {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MinionException | IOException e) {
                ui.print(e.getMessage());
            }
        }
    }
}
