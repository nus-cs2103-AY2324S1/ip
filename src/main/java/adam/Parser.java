package adam;
import adam.command.Command;
import adam.command.AddCommand;
import adam.command.EditCommand;
import adam.command.SingleCommand;
import adam.exception.AdamException;
public class Parser {
    public static Command parse(String li) {
        Command command ;
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        switch (tokens[0]) {
        case "bye":
        case "list":
           command = new SingleCommand(tokens, tokens[0]);
        break;
        case "todo":
        case "deadline":
        case "event":
            command = new AddCommand(tokens, item, tokens[0]);
        break;
        case "mark":
        case "unmark":
        case "delete":
            command = new EditCommand(tokens, tokens[0]);
        break;
        default:
                throw new AdamException();
        }
        return command;
    }
}
