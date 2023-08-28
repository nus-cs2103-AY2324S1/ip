public class Parser {
    public static CommandType parseCommand(String line) throws DukeException {
        String command = line.split(" ")[0];
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("!!!: Sorry I do not understand what you mean");
        }
    }

    public static String parseOptions(String line) throws DukeException {
        String command = line.split(" ", 2)[1];
        try {
            return command;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Input is not complete");
        }
    }
}
