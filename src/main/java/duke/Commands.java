package duke;


import java.util.Scanner;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.ItemList;
import duke.ui.UI;


/**
 * The Command Class encapsulates the functions to check the input string with the available commands.
 */
public class Commands {
    /**
     * The Enum Class encapsulates all the available commands.
     */
    public enum CommandType {
        MARK, UNMARK, LIST, EVENT, DEADLINE, TODO, BYE, DELETE, FIND

    }



    /**
     * This method Run the Scanner to begin taking inputs from user, and check to see which commands to run.
     */
    public static void run(Storage storage) {
        Scanner sc = new Scanner(System.in);
        ItemList items = storage.getItems();

        boolean isRunning = true;
        do {
            if (!sc.hasNextLine()) {
                break;
            }
            try {
                String line = sc.nextLine();
                Parser parser = new Parser(line);
                String command = parser.getCommand();
                CommandType given = CommandType.valueOf(command);

                switch (given) {
                case BYE:
                    isRunning = false;
                    break;
                case LIST:
                    items.showitems();
                    break;
                case MARK:
                    parser.parseMark(items);
                    break;
                case UNMARK:
                    parser.parseUnmark(items);
                    break;
                case DELETE:
                    parser.parseDelete(items);
                    break;
                case DEADLINE:
                    parser.parseDeadline(items);
                    break;
                case TODO:
                    parser.parseTodo(items);
                    break;
                case EVENT:
                    parser.parseEvent(items);
                    break;
                case FIND:
                    parser.parseFind(items);
                    break;
                default:
                    throw new DukeException();

                }
            } catch (DukeException e) {
                UI.printMessage(e.toString());
            } catch (IllegalArgumentException e) {
                UI.printMessage("Invalid input");
            }


        } while (isRunning);
        Greeting.bye();
    }


}
