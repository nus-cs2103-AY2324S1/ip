public class Parser {
    public static Command parseCommand(String userInput) {
        String[] inputParts = userInput.split(" ", 2); // Split input into two parts: command and arguments
        String command = inputParts[0].toUpperCase(); // Convert command to uppercase for case-insensitivity


        switch (command) {
            case "TODO":
                return Command.TODO;
            case "DEADLINE":
                return Command.DEADLINE;
            case "EVENT":
                return Command.EVENT;
            case "LIST":
                return Command.LIST;
            case "DONE":
                return Command.DONE;
            case "DELETE":
                return Command.DELETE;
            case "FIND":
                return Command.FIND;
            case "BYE":
                return Command.EXIT;
            case "UNMARK":
                return Command.UNMARK;
            case "MARK":
                return Command.MARK;
            default:
                return Command.INVALID;
        }


    }

    public static String parseDescription(String userInput) {
        String[] parts = userInput.split(" ", 2); // Split input into command and arguments
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }

}
