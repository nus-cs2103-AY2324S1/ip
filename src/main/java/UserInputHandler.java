import java.io.FileNotFoundException;
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
        new StorageHandler();
    }
    public CommandsInternal getResponseValue(String s) {

        if (s.toLowerCase().equals("tell me a joke")) {
            return CommandsInternal.COPYPASTA;
        }
        int output = 0;
        s = s.toLowerCase();
        for (int i = 0; i < commands.length; i++) {
            if (s.startsWith(commands[i])) {
                output = i;
                break;
            }
        }
        String temp = commands[output].toUpperCase().trim();
        return CommandsInternal.valueOf(temp);
    }
    public void newInput() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        command = getResponseValue(input);
        input = input.split(" ",2).length < 2 ? " " : input.split(" ", 2)[1];
        System.out.println(lineBreak);
    }
    private void echo() {
        if (input.isBlank()) {
            System.out.println("You didn't say anything peko?");
        } else {
            System.out.println(input);
        }

        System.out.println(lineBreak);
    }
    private void readArray() {
        StorageHandler.readArray();
        System.out.println(lineBreak);
    }
    private void addToArray() throws InvalidTaskException {
        Task t = new Task(input);
        StorageHandler.addToArray(t);
        System.out.println(lineBreak);
    }
    private void mark(String s) throws NumberFormatException{
        int i = Integer.parseInt(s);
        StorageHandler.setMarkArray(i);
        System.out.println(lineBreak);
    }
    private void unmark(String s) {
        try {
            int i = Integer.parseInt(s);
            StorageHandler.setUnmarkArray(i);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");
        }
        System.out.println(lineBreak);
    }
    private void todo(String s) throws InvalidTaskException{
        Task t = new ToDos(input);
        StorageHandler.addToArray(t);
        System.out.println(lineBreak);
    }
    private void deadline(String s) throws InvalidTaskException {
        Task t = new Deadline(input);
        StorageHandler.addToArray(t);
        System.out.println(lineBreak);
    }
    private void Event(String s) throws InvalidTaskException {
        Task t = new Event(input);
        StorageHandler.addToArray(t);
        System.out.println(lineBreak);
    }
    public void delete(String s) {
        int i = Integer.parseInt(s);
        StorageHandler.setDelete(i);
    }
    public boolean run() {
        try {

            switch (command) {
                case ECHO:
                    echo();
                    return true;
                case LIST:
                    readArray();
                    return true;
                case WRITE:
                    addToArray();
                    return true;
                case MARK:
                    mark(input);
                    return true;
                case UNMARK:
                    unmark(input);
                    return true;
                case TODO:
                    todo(input);
                    return true;
                case DEADLINE:
                    deadline(input);
                    return true;
                case EVENT:
                    Event(input);
                    return true;
                case DELETE:
                    delete(input);
                    return true;
                case COPYPASTA:
                    try {
                        StorageHandler.degen();
                    } catch (FileNotFoundException e) {
                        System.out.println("Hentai!");
                    } finally {
                        return false;
                    }
                case OTSUPEKO:
                    SaveHandler.saveTo();
                    return false;
                default:
                    return true;

            }
        } catch (InvalidTaskException e) {
            System.out.println(e);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");

            return true;
        }
    }

}