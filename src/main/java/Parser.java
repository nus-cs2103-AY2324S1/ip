/**
 * Deals with making sense of user commands
 */
public class Parser {

    public Input parse(String input) {
        String[] split = input.split(" ");
        String command = split[0].toLowerCase();
        int length = split.length;
        return new Input(command, input, length);
    }
}
