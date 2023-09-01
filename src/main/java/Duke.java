import data.exception.DukeException;
import data.task.TaskList;
import parser.Parser;
import parser.Parser.Command;
import storage.Storage;
import ui.Ui;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList list = null;
        list = new TaskList(storage.getFile());
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
