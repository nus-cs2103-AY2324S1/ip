package duke;

import command.Commands;
import dukeExceptions.DukeException;
import parser.Parser;
import storage.Storage;
import task.ListOfTask;
import ui.Ui;

import java.time.format.DateTimeFormatter;


public class Duke {
    public static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static ListOfTask taskList = new ListOfTask();
    private static Ui ui = new Ui();

    /**
     * This starts the Duke chatbot.
     * @param args Does nothing
     */
    public static void main(String[] args) {
        greet();
    }

    private static void greet() {
        if (!Storage.load(taskList, 1)) {
            return;
        }
        ui.greet();
        nextCommand(ui.nextInput());
    }

    private static void nextCommand(String command) {
        try {
            Parser cmd = new Parser(command);
            Commands action = cmd.parse();
            if (action.execute(taskList, 0, null) == 1) {
                nextCommand(ui.nextInput());
            } else {
                ui.exit();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            nextCommand(ui.nextInput());
        }
    }
}
