package gbot;

import exceptions.GBotException;
import java.util.Scanner;

/**
 * The chatbot GBot that helps keep track of tasks for users.
 *
 * @author Gallen Ong
 */
public class GBot {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initialises the chatbot GBot.
     *
     * @param filePath The filepath to be written into or updated.
     */
    public GBot(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    private void run() {
        Ui.greetUser();
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            String message = inputScanner.nextLine();
            if (message.strip().equals("bye")) {
                inputScanner.close();
                Ui.displayExitMessage();
                return;
            }
            try {
                Ui.print(Parser.parse(message, tasks));
            } catch (GBotException e) {
                Ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Generates a response from the chatbot.
     *
     * @param input The user input.
     */
    String getResponse(String input) {
        if (input.equals("bye")) {
            return "Thank you for your time G. I'll be of your service at any time.";
        }
        try {
            return Parser.parse(input, tasks);
        } catch (GBotException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new GBot("./data/tasks.txt").run();
    }
}

