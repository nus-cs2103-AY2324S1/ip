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
        MARK, UNMARK, LIST, EVENT, DEADLINE, TODO, BYE, DELETE, FIND, RESCHEDULE

    }


    /**
     * This method Run the Scanner to begin taking inputs from user, and check to see which commands to run.
     */
    public static void run(Storage storage) {
        Scanner sc = new Scanner(System.in);
        ItemList items = storage.getItems();

        boolean isRunning = true;
        while (isRunning) {
            if (!sc.hasNextLine()) {
                break;
            }

            String line = sc.nextLine();
            Parser parser = new Parser(line);

            try {
                CommandType commandType = CommandType.valueOf(parser.getCommand());
                if (commandType == CommandType.BYE) {
                    isRunning = false;
                } else {
                    executeCommand(commandType, parser, items);
                }
            } catch (IllegalArgumentException e) {
                UI.printMessage("Invalid input");
            } catch (DukeException e) {
                UI.printMessage(e.toString());
            }
        }
        Greeting.bye();
    }

    private static void executeCommand(CommandType commandType, Parser parser, ItemList items) throws DukeException {
        switch (commandType) {
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
        case RESCHEDULE:
            parser.parseReschedule(items);
            break;
        default:
            throw new DukeException();
        }
    }

}
