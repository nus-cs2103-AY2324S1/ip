import java.util.Scanner;

public class Duke {
    private FileHandler fileHandler;
    private TaskList task;
    private Ui ui;

    public Duke(String filePath) {
        this.fileHandler = new FileHandler(filePath);
        this.task = new TaskList(FileHandler.readTasksFromFile());
        this.ui = new Ui(task);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(task, ui, fileHandler);
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/TaskList.txt").run();
    }
}

