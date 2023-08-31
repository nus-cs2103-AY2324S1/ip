import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList list = null;

        Storage storage = new Storage();
        list = new TaskList(storage.getFile());

        Parser parser = new Parser();

        String input = ui.getFirstInput();

        while (true) {
            try {
                Parser.Command command = parser.parse(input);
                if (command == Parser.Command.BYE) {
                    list.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command == Parser.Command.LIST) {
                    list.printList();
                } else {
                    list.executeCommand(command, input);
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            input = ui.getInput();
        }
    }
}
