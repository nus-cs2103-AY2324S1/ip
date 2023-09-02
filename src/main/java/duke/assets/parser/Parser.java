package duke.assets.parser;

import duke.assets.commands.Command;
import duke.assets.commands.ValidCommands;
import duke.assets.commands.Bye;

public class Parser {
    public static Command passCommand(String input) {

    }

    public static boolean isValidCommand(String input) {
        String[] delimitedBySpace = input.split(" ");
        String[] delimitedBySlash = input.split("/");

        try {
            String command = delimitedBySpace[0].toLowerCase();
            ValidCommands.valueOf(command);
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please input a valid command.");
            return false;
        } catch (IllegalArgumentException argExcept) {
            System.out.println("ChadGPT: Sorry I don't understand this command :-(");
            return false;
        }
        return true;
    }
}
