import java.io.FileNotFoundException;
import java.io.IOException;

public class CR7 {
    private Storage storage;

    private Ui ui;

    private TaskList tasks;

    public static class CR7Exception extends Throwable {}

    public static class WrongDateException extends CR7Exception {}

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showErrorMsg("LOL");
            } finally {
                ui.showLine();
            }
        }
    }

    public CR7(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFiles());
        } catch (FileNotFoundException e) {
            ui.showErrorMsg("LOL");
            tasks = new TaskList();
        }
    }


    public static void main(String[] args) {
        new CR7("src/main/data/CR7.txt").run();
    }

}
