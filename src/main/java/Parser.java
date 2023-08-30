public class Parser {


    public static Command parse(String fullCommand) {
        String[] strArr = fullCommand.split(" ", 2);
        String cmdWord;
        String arguments = "";
        if (strArr.length == 2) {
            arguments = strArr[1];
        }
        cmdWord = strArr[0];

        switch (cmdWord) {
        case "todo": case "event": case "deadline":
            return new AddCommand(cmdWord, arguments);
        case "list":
            return new ListCommand(cmdWord, arguments);
        case "delete":
            return new DeleteCommand(cmdWord, arguments);
        case "bye":
            return new ExitCommand(cmdWord, arguments);
        default:
            return new HelpCommand(cmdWord, arguments);
        }
    }
}
