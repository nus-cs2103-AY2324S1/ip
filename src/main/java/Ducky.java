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
        this.storage.load(this.taskList);
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = this.ui.readCommand();
                this.ui.showLine();
                Command c = Parser.parse(userInput);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DuckyException e) {
                this.ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Ducky("tasks.txt").run();
    }
}
