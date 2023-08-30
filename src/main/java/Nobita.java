import java.util.Scanner;
import java.util.StringJoiner;

public class Nobita {

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    public Nobita() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.dir"));
        this.tasks = storage.readFile();
    }

    public static void main(String[] args) {
        new Nobita().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(fullCommand);
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NobitaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
