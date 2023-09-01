import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskManager taskManager;
    private UserInterface userInterface;

    public Duke(String filePath) {
        userInterface = new UserInterface();
        storage = new Storage(filePath);
        taskManager = new TaskManager(storage.load());
    }

    public void run() {
        userInterface.showWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while(!command.equals("bye")) {
            Parser.parseCommand(command, taskManager, userInterface);
            command = sc.nextLine();
        }

        userInterface.showGoodbyeMessage();
        userInterface.showCommandLine();
        storage.save(taskManager.displayList());
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}


