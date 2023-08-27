class Parser {
    static Command parse(String userInput) throws DukeException {
        try {
            String[] tokens = userInput.split(" ", 2);
            String keyword = tokens[0].strip();
            String details = tokens.length > 1 ? tokens[1].strip() : null;

            switch (keyword) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(details);
            case "unmark":
                return new UnmarkCommand(details);
            case "delete":
                return new DeleteCommand(details);
            case "todo":
                return new TodoCommand(details);
            case "deadline":
                return new DeadlineCommand(details);
            case "event":
                return new EventCommand(details);
            default:
                throw new DukeException(" ☹ I'm not ChatGPT, cannot understand what you mean.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(" ☹ What are you exactly asking me to do?");
        }
    }
}
