import java.io.IOException;

public class Fishron {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Fishron(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.loadTasksFromFile();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand, this.taskList);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (FishronException e) {
                ui.showErrorMessage(e.getMessage());
                ui.showLine();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Fishron("data/tasks.txt").run();
    }
}