
package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.Date;
import java.util.Scanner;

import duke.Command.Command;
import duke.Exception.DukeException;



/**
 * Main class of the chatbot.
 */
public class Duke {
    private static Ui ui;
    private static Storage storage;
    private TaskList tasks;
    private Parser parser;

    public static void main(String[] args) {
        ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/duke.txt", tasks);
        boolean isRunning = true;
        Parser parser = new Parser();
        boolean isExit = false;

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
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } catch (IOException | DateTimeException | NumberFormatException e) {
                parser.handleException(e);
            }
        }
    }


    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}