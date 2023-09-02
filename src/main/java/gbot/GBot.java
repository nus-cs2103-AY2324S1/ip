package gbot;

import exceptions.GBotException;
import java.util.Scanner;

public class GBot {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public GBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    private void run() {
        ui.ask();
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            String message = inputScanner.nextLine();
            if (message.equals("bye")) {
                inputScanner.close();
                ui.end();
                return;
            }
            try {
                Parser.parse(message, tasks);
            } catch (GBotException e) {
                Ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new GBot("./data/tasks.txt").run();
    }
}

