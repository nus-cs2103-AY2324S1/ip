package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates the chat bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this("data/tasks.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.printException(e);
            tasks = new TaskList(storage);
        } catch (IOException e) {
            ui.printException(new DukeException("Unable to find storage file."));
            tasks = new TaskList(storage);
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        Ui.welcomeMessage();
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String command = userInput.nextLine();
            Parser p = new Parser(command, this.storage, this.tasks);
            p.parse();
            if (p.isEnd()) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Parser parser = new Parser(input, this.storage, this.tasks);
        String response = parser.parse();
        return response;
    }

}
