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
        if (commandWord.contains("list")) {
            return Command.LIST;
        } else if (commandWord.contains("bye")) {
            return Command.BYE;
        } else if (commandWord.contains("mark")) {
            return Command.MARK;
        } else if (commandWord.contains("unmark")) {
            return Command.UNMARK;
        } else if (commandWord.contains("todo")) {
            return  Command.TODO;
        } else if (commandWord.contains("deadline")) {
            return Command.DEADLINE;
        } else if (commandWord.contains("event")) {
            return Command.EVENT;
        } else {
            return Command.ADD;
        }
    }
}
