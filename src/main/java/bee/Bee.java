package bee;

import java.util.Scanner;

public class Bee {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String DATA_FILE_PATH = "./data/bee.txt";

    public Bee(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (BeeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            String userInput = scanner.nextLine();
            isRunning = Parser.parseUserCommand(userInput, tasks, storage, ui);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Bee(DATA_FILE_PATH).run();
    }
}