public class Parser {

    public static Command parse(String input) throws 
        InvalidDescriptionException, InvalidCommandException, InvalidIndexException {
        //Split the input so that we can read integers.
        String[] inputStrings = input.split(" ", 2);
        CommandType command = CommandType.parseInput(inputStrings[0]);
        if (command == null) {
            throw new InvalidCommandException("What are you saying? Try again.");
        }

        switch(command) {
            case BYE:
                return new Bye("");

            case DISPLAY_LIST:
                return new DisplayList("");

            case MARK:
                return new MarkItem(inputStrings[1]);
                
            case UNMARK:
                return new UnmarkItem(inputStrings[1]);

            case ADD_TODO:
                return new AddToDo(inputStrings[1]);

            case ADD_DEADLINE:
                return new AddDeadline(inputStrings[1]);
            
            case ADD_EVENT:
                return new AddEvent(inputStrings[1]);

            case DELETE:
                return new DeleteItem(inputStrings[1]);

            default:
                throw new InvalidCommandException("Don't be stupid, speak english.");
        }
    }
}
