package duke;

import duke.Command.Command;
import duke.Exception.DukeException;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;

/**
 * Main class of the chatbot.
 */
public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/duke.txt", tasks);
        boolean isExit = false;
        Parser parser = new Parser();

        try {
            storage.saveTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        ui.printWelcomeMessage();
        while (!isExit) {
            String input = scanner.nextLine();
            try {
                Command command = parser.addToList(input, storage, tasks);
                command.execute(ui, storage, tasks);
                isExit = command.isExit();

            } catch (DukeException i) {
                ui.printError(i.getMessage());
            } catch (IOException | DateTimeException | NumberFormatException e) {
                parser.handleException(e);
            }
        }
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

