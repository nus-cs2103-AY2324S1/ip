package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.ItemList;
import duke.ui.UI;

public class UiResponse {
    private final Storage storage;

    public UiResponse(Storage storage) {
        this.storage = storage;
    }
    /**
     * Gets a response from Chat Bot based on the input string
     *
     * @param input The user string passed in
     * @return The String representation of the Chat Bot response
     */
    public String getResponseString(String input) {

        ItemList items = this.storage.getItems();
        try {
            Parser parser = new Parser(input);
            String command = parser.getCommand();
            Commands.CommandType given = Commands.CommandType.valueOf(command);

            switch (given) {
            case BYE:
                return Greeting.bye();
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
            case RESCHEDULE:
                return parser.parseReschedule(items);
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
