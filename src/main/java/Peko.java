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
        todoList = saveHandler.loadFrom();
        UserInputHandler UIhandler = new UserInputHandler();
        intro();
        while (true) {
            UIhandler.newInput();
            if (!UIhandler.run()) {
                System.out.println("break");
                break;
            }
            UIhandler = new UserInputHandler();
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

    public static void setDelete(int i) {
        while (i <= pos) {
            todoList[i] = todoList[i+1];
            i++;
        }
        pos--;
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
