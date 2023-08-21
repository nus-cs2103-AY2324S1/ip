public enum Command {
    LIST,
    MARK,
    UNMARK,
    BYE,
    ADD,
    TODO,
    DEADLINE,
    EVENT;


    public static Command getCommand(String input) {
        String[] inputtedWords = input.split(" ");
        // Command word should always be first word without space
        String commandWord = inputtedWords[0];
        if (commandWord.startsWith("list")) {
            return Command.LIST;
        } else if (commandWord.startsWith("bye")) {
            return Command.BYE;
        } else if (commandWord.startsWith("mark")) {
            return Command.MARK;
        } else if (commandWord.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (commandWord.startsWith("todo")) {
            return  Command.TODO;
        } else if (commandWord.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (commandWord.startsWith("event")) {
            return Command.EVENT;
        } else {
            return Command.ADD;
        }
    }
}
