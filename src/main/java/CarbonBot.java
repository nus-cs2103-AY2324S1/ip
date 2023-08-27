import java.io.IOException;

public class CarbonBot {
    private final String saveFilePath;
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public CarbonBot(String filePath) {
        this.saveFilePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException | DukeException ex) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showGreetings();
        boolean isExit = false;
        while(!isExit) {
            String input = this.ui.getNextInput();

            // Ignore if the input was empty
            if (input.isBlank()) {
                continue;
            }

            try {
                ui.printDivider();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                continue;

            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        String saveFilePath = "./data/tasks.txt";
        new CarbonBot(saveFilePath).run();
    }

}