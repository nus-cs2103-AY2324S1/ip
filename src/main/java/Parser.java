public class Parser {

    enum CommandTypes {
        list, bye, todo, deadline, event, mark, unmark, delete
    }

    Command getCommandType(String userInput) throws InvalidCommandTypeException {
        // Returns the type of command input by the user
        // Possible values: "todo", "deadline", "event", "bye", "list", "mark", "unmark"
        // todo indentation of switch
        for (CommandTypes type : CommandTypes.values()) {
            String typeString = type.toString();
            if (userInput.startsWith(typeString)) {
                switch (typeString) {
                    case "todo":

                        break;
                    case "deadline":

                        break;
                    case "event":

                        break;
                    case "bye":

                        break;
                    case "list":

                        break;
                    case "mark":

                        break;
                    case "unmark":

                        break;
                }
            }
        }
        throw new InvalidCommandTypeException();
    }

}
