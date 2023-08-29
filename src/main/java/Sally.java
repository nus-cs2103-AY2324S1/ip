import java.util.Scanner;

public class Sally {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Sally(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile().getTaskList());
        } catch (SallyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            try {
                String input = ui.readCommand();
                if (input.equals("bye")) {
                    ui.showGoodbyeMessage();
                    break;
                }

                Command command = Parser.parse(input);
                command.execute(tasks, storage, ui);
            } catch (SallyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Sally("data/sally.txt").run();
    }
}