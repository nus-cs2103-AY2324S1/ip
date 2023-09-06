package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.ItemList;
import duke.ui.UI;

public class UIResponse {
    private Storage storage;

    public UIResponse(Storage storage) {
        this.storage = storage;
    }

    public String getResponseString(String input) {

        ItemList items = this.storage.getItems();
        try {
            Parser parser = new Parser(input);
            String command = parser.getCommand();
            Commands.CommandType given = Commands.CommandType.valueOf(command);

            switch (given) {
                case BYE:
                    return "SHUTDOWN";
                case LIST:
                    return items.showitems();
                case MARK:
                    return parser.parseMark(items);
                case UNMARK:
                    return parser.parseUnmark(items);
                case DELETE:
                    return parser.parseDelete(items);
                case DEADLINE:
                    return parser.parseDeadline(items);
                case TODO:
                    return parser.parseTodo(items);
                case EVENT:
                    return parser.parseEvent(items);
                case FIND:
                    return parser.parseFind(items);
                default:
                    throw new DukeException();

            }
        } catch (DukeException e) {
            return UI.printMessage(e.toString());
        } catch (IllegalArgumentException e) {
            return UI.printMessage("Invalid input");
        }


    }

}
