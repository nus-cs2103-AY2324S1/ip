public class Parser {
    public static CommandType parseCommand(String input) throws UnknownCommandException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toUpperCase();

        try {
            return CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }

    public static String parseArgument(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }
}
