package bob;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.parser.Parser;
import bob.parser.Parser.Command;
import bob.storage.Storage;
import bob.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList list = new TaskList(storage.getFile());
        list.open();
        Parser parser = new Parser();

        String input = ui.getFirstInput();

        while (true) {
            try {
                Command command = parser.parse(input);
                if (command == Parser.Command.BYE) {
                    list.close();
                    ui.end();
                    break;
                } else if (command == Parser.Command.LIST) {
                    ui.print(list.toString());
                } else if (command == Parser.Command.FIND) {
                    ui.print(list.find(input));
                } else {
                    list.executeCommand(command, input);
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            input = ui.getInput();
        }
    }

    public String getResponse(String input) {
        return "Bob heard: " + input;
    }
}
