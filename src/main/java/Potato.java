import java.io.IOException;

public class Potato {
    private static String LINE = "-----------------------------------------\n";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Potato(String txt) {
        storage = new Storage(txt);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTask());
            System.out.println("here's the saved list");
        } catch (IOException e) {
            System.out.println("No saved list");
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        ui.showLine(); // show the divider line ("_______")
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Potato("./Potato.txt").run();
    }
}