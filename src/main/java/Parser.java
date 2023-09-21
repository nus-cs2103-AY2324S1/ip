public class Parser {
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, SCHEDULE, UNKNOWN;
        public static Command translate(String input) {
            String lowerInput = input.toLowerCase();
            String command = lowerInput.split(" ")[0];
            switch (command) {
                case "bye":
                    return BYE;
                case "list":
                    return LIST;
                case "mark":
                    return MARK;
                case "unmark":
                    return UNMARK;
                case "todo":
                    return TODO;
                case "deadline":
                    return DEADLINE;
                case "event":
                    return EVENT;
                case "delete":
                    return DELETE;
                case "schedule":
                    return SCHEDULE;
                default:
                    return UNKNOWN;
            }
        }
    }

    public Command getCommand(String input) {
        return Command.translate(input);
    }

    public String[] splitInput(String input) {
        return input.split(" ", 2);
    }

    public String[] splitByKeyword(String input, String keyword) {
        return input.split(keyword, 2);
    }
}