import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public class Peko {
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

    private static Task[] todoList = new Task[100];
    private static int pos = 0;
    private static final String lineBreak = "------------------------------------------"; //42
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Hololive san kisei no\n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";
    private static final String[] commands = new String[]
            {"echo:","otsupeko", "list", "write", "mark", "unmark",
                    "todo", "deadline", "event", "delete","tell me a joke"};


    private static String currInput;
    private static final Scanner scanner = new Scanner(System.in);
    private static SaveHandler saveHandler = new SaveHandler(todoList, new File("src/main/List.txt"));
    public static void main(String[] args) throws InvalidTaskException {
        String input;
        boolean loop = true;
        CommandsInternal temp;
        todoList = saveHandler.loadFrom();
        UserInputHandler UIhandler = new UserInputHandler();
        intro();

        while (loop) {
            UIhandler.newInput();
            if (!UIhandler.run()) {
                break;
            }
            input = interaction();
            //responseValue = getResponseValue(input);
            temp = getResponseValue(input);
            //System.out.println(responseValue);
            switch (temp) {
                case ECHO:
                    echo(input);
                    System.out.println(lineBreak);
                    break;
                case LIST:
                    readArray();
                    break;
                case WRITE:
                    try {
                        addToArray(input);
                    } catch (InvalidTaskException e) {
                        System.out.println(e);
                        System.out.println(lineBreak);
                    }
                    break;
                case MARK:
                    setMarkArray(input);
                    break;
                case UNMARK:
                    setUnmarkArray(input);
                    break;
                case TODO:
                    try {
                        addToDo(input);
                    } catch (InvalidTaskException e) {
                        System.out.println(e);
                        System.out.println(lineBreak);
                    }
                    break;
                case DEADLINE:
                    try {
                        addDeadline(input);
                    } catch (InvalidTaskException e) {
                        System.out.println(e);
                        System.out.println(lineBreak);
                    }
                    break;
                case EVENT:
                    try {
                        addEvent(input);
                    } catch (InvalidTaskException e) {
                        System.out.println(e);
                        System.out.println(lineBreak);
                    }
                    break;
                case DELETE:
                        setDelete(input);
                        break;
                case COPYPASTA:
                    try  {
                        degen();
                    } catch (FileNotFoundException e) {
                        System.out.println("Hentai!");
                    } finally {
                        loop = false;
                    }
                    break;
                case OTSUPEKO:
                    loop = false;
                    break;

                default:
            }
        }
        saveHandler.saveTo();
        exit();
    }

    public static void intro() {
        String pekoLogo = " _____      _\n"
                + "|     |___ | | ______\n"
                + "|  ___/ _ \\| |/ /    \\\n"
                + "| |  <  __/|   <  <>  |\n"
                + "|_|   \\___||_|\\_\\____/";
        System.out.println(pekoLogo);

        System.out.println(lineBreak);
        System.out.println(introText);
        System.out.println(lineBreak);
    }

    public static String interaction() {
        currInput = scanner.nextLine();
        System.out.println(lineBreak);
        return currInput;
    }
    public static CommandsInternal getResponseValue(String input) {
        int output = 3;
        input = input.toLowerCase();
        for (int i = 0; i < commands.length; i++) {
            if (input.startsWith(commands[i])) {
                output = i;
                break;
            }
        }
        String temp = commands[output].toUpperCase().trim();
        System.out.println(temp);
        return CommandsInternal.valueOf(temp);
    }

    public static void echo(String s) {
        s = s.substring(5, s.length());
        s = leftPad(s);
        if (s.isBlank()) {
            System.out.println("You didn't say anything peko?");
        } else {
            System.out.println(s);
        }
    }

    public static void readArray() {
        int i = 0;
        System.out.println("--------------LIST-PEKO------------------");
        while (todoList[i] != null) {
            System.out.println(i+1 + ". " + todoList[i]);
            i++;
        }
        if (i == 0) {
            System.out.println("You are FREE PEKO!!!!!");
        }
        System.out.println(lineBreak);
    }

    public static void addToArray(Task t) {
        todoList[pos] = t;
        todoList[pos].reply(pos);
        pos++;
        System.out.println(lineBreak);
        saveHandler.saveTo();
    }
    public static void addToDo(String s) throws InvalidTaskException {
        todoList[pos] = new ToDos(s);
        todoList[pos].reply(pos);
        pos++;
        System.out.println(lineBreak);
        saveHandler.saveTo();
    }
    public static void addDeadline(String s) throws InvalidTaskException {
        todoList[pos] = new Deadline(s);
        todoList[pos].reply(pos);
        pos++;
        System.out.println(lineBreak);
        saveHandler.saveTo();
    }
    public static void addEvent(String s) throws InvalidTaskException {
        todoList[pos] = new Event(s);
        todoList[pos].reply(pos);
        pos++;
        System.out.println(lineBreak);
        saveHandler.saveTo();
    }

    public static void setMarkArray(int i) {
        todoList[i-1].setMark();
        System.out.println("Marked as done peko!");
        System.out.println("    " + todoList[i-1]);
        saveHandler.saveTo();
    }
    public static void setUnmarkArray(int i) {
        todoList[i-1].setUnmark();
        System.out.println("You haven't done this yet peko?!");
        System.out.println("    " + todoList[i-1]);
        saveHandler.saveTo();
    }

    public static void setDelete(String s) {
        try {
            s = s.split(" ", 2)[1];
            int markIndex = Integer.parseInt(s)-1;
            while (markIndex <= pos) {
                todoList[markIndex] = todoList[markIndex+1];
                markIndex++;
            }
            pos--;
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That's not a number in the list Peko!");
        }
        saveHandler.saveTo();
    }

    public static void degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        System.out.println(lineBreak);

    }

    private static String leftPad(String s) {
        while (s.startsWith(" ")) {
            s = s.substring(1, s.length());
        }
        return s;
    }
    private static void exit() {
        System.out.println(exitText);
    }

}
