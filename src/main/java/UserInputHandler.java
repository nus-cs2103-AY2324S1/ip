import java.util.Scanner;

public class UserInputHandler {
    enum CommandsInternal {
        ECHO,
        OTSUPEKO,
        LIST,
        WRITE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        COPYPASTA

    }
    private static final String lineBreak = "------------------------------------------"; //42
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Hololive san kisei no\n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";
    private static final String[] commands = new String[]
            {"echo","otsupeko", "list", "write", "mark", "unmark",
                    "todo", "deadline", "event", "delete","tell me a joke"};

    String input;
    CommandsInternal command;
    public UserInputHandler() {
        input = "";
    }
    public CommandsInternal getResponseValue(String s) {
        int output = 0;
        s = s.toLowerCase();
        for (int i = 0; i < commands.length; i++) {
            if (s.startsWith(commands[i])) {
                output = i;
                break;
            }
        }
        String temp = commands[output].toUpperCase().trim();
        System.out.println(temp);
        return CommandsInternal.valueOf(temp);
    }

    public void newInput() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        command = getResponseValue(input);
        System.out.println(lineBreak);
    }

    public boolean run() {

    }

}
