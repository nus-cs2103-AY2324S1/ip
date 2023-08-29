import java.util.Scanner;

public class Ducky {

    private final Storage storage;
    private final TaskList taskList;
    private final UserInterface ui;

    public Ducky(String filePath) {
        Scanner sc = new Scanner(System.in);
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.ui = new UserInterface(sc);
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(userInput);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DuckyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Ducky("tasks.txt").run();
    }
}
