package duke;

public enum Command {
    LIST,
    MARK,
    UNMARK,
    BYE,
    ADD,
    TODO,
    DEADLINE,
    EVENT,
    DELETE;

    public static final String[] validCommands =
            {"list", "bye", "todo <task_name>", "deadline <task_name> /by <deadline>",
                    "event <task_name> /from <start-time> /to <end-time>", "mark <number>",
                    "unmark <number>", "delete <number>"};



    public static Command getCommand(String input) throws InvalidInputException {
        String[] inputtedWords = input.split(" ");
        // Command word should always be first word without space
        String commandWord = inputtedWords[0];
        if (commandWord.equals("list")) {
            return Command.LIST;
        } else if (commandWord.equals("bye")) {
            return Command.BYE;
        } else if (commandWord.startsWith("mark")) {
            return Command.MARK;
        } else if (commandWord.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (commandWord.startsWith("todo")) {
            return Command.TODO;
        } else if (commandWord.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (commandWord.startsWith("event")) {
            return Command.EVENT;
        } else if (commandWord.startsWith("delete")) {
            return Command.DELETE;
        } else {
            throw new InvalidInputException();
        }
    }


}
