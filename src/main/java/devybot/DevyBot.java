package devybot;
import java.util.Scanner;

import devybot.exceptions.DevyBotException;

public class DevyBot {
    private Storage storage;
    private TaskList tasks;

    public DevyBot(String filePath) {

        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DevyBotException e) {
            Ui.showMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui.showMessage("Hello! I'm DevyBot\nWhat can I do for you?");
        boolean run = true;

        while (run) {
            try {
                String userInput = scanner.nextLine();
                Parser.parse(userInput, tasks);
                storage.saveTasksToFile(tasks);
                run = Parser.isRun();
            } catch (DevyBotException e) {
                Ui.showMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new DevyBot("data/tasks.txt").run();
    }
}
