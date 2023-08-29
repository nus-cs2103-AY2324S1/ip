package main.java;

import main.java.Command.Commands;
import main.java.Parser.Parser;
import main.java.Storage.Storage;
import main.java.Task.ListOfTask;
import main.java.Ui.Ui;

import java.time.format.DateTimeFormatter;

public class Duke {
    public static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static ListOfTask taskList = new ListOfTask();
    private static Ui ui = new Ui();
    public static void main(String[] args) {
        greet();

    }

    private static void greet() {
        if (!Storage.load(taskList)) {
            return;
        }
        ui.greet();
        nextCommand(ui.nextInput());
    }

    protected static void nextCommand(String command) {
        Parser cmd = new Parser(command);
        Commands action = cmd.parse();
        if (action.execute(taskList, ui) == 1) {
            nextCommand(ui.nextInput());
        }
    }
}
