import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Peko {

    private static Task[] todoList = new Task[100];
    private static int pos = 0;
    private static final String lineBreak = "------------------------------------------"; //42
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";
    private static final String[] commands = new String[]
            {"echo:","otsupeko", "list", "write:", "mark", "unmark", "tell me a joke"};

    private static final int ECHO = 0;
    private static final int EXIT = 1;
    private static final int READ = 2;
    private static final int WRITE = 3;
    private static final int MARK = 4;
    private static final int UNMARK = 5;
    private static final int COPYPASTA = 6;
    private static String currInput;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String input;
        boolean loop = true;
        int responseValue;
        intro();

        while (loop) {
            input = interaction();
            responseValue = getResponseValue(input);
            //System.out.println(responseValue);
            switch (responseValue) {
                case ECHO:
                    echo(input);
                    System.out.println(lineBreak);
                    break;
                case READ:
                    readArray();
                    break;
                case WRITE:
                    input = input.startsWith(commands[3]) ? leftPad(input.substring(6, input.length())) : input;
                    addToArray(input);
                    break;
                case MARK:
                    input = leftPad(input.substring(4, input.length()));
                    setMarkArray(input);
                    break;
                case UNMARK:
                    input = leftPad(input.substring(6, input.length()));
                    setUnmarkArray(input);
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
                case EXIT:
                    loop = false;
                    break;

                default:
            }
        }
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
    public static int getResponseValue(String input) {
        int output = 3;
        input = input.toLowerCase();
        for (int i = 0; i < commands.length; i++) {
            if (input.startsWith(commands[i])) {
                output = i;
                break;
            }
        }

        return output;
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

    public static void readList() throws FileNotFoundException {
        File file = new File("src/main/List.txt");
        Scanner sc = new Scanner(file);
        System.out.println("--------------LIST-PEKO------------------");
        int curr = 1;
        while (sc.hasNextLine()) {
            System.out.println(curr + ". " + sc.nextLine());
            curr++;
        }
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

    public static void addToList(String s) throws IOException {
        Writer temp;
        temp = new BufferedWriter(new FileWriter("src/main/List.txt", true));
        temp.append("[ ] " + s + "\n");
        temp.close();
        System.out.println("Added: \"" + s + "\" Peko!");
        System.out.println(lineBreak);
    }
    public static void addToArray(String s) {
        todoList[pos] = new Task(s);
        System.out.println("Added: \"" + s + "\" Peko!");
        System.out.println(lineBreak);
        pos++;
    }

    public static void setMark(String s) {
        try {
            int i = Integer.parseInt(s);
            File file = new File("src/main/List.txt");
            Scanner sc = new Scanner(file);
            for (int j = 1; j < i; j++) {
                if (sc.hasNextLine()) {
                    sc.nextLine();
                } else {
                    System.out.println("Your list isn't long enough Bakatare");
                }
            }
            System.out.println(sc.nextLine());
            System.out.println("");
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");
        } catch (FileNotFoundException e) {
            System.out.println("Missing file peko! Pain Peko!");
        }

    }
    public static void setMarkArray(String s) {
        try {
            int markIndex = Integer.parseInt(s);
            todoList[markIndex-1].setMark();
            System.out.println("Marked as done peko!");
            System.out.println("    " + todoList[markIndex-1]);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");
        }
        System.out.println(lineBreak);
    }
    public static void setUnmarkArray(String s) {
        try {
            int markIndex = Integer.parseInt(s);
            todoList[markIndex-1].setUnmark();
            System.out.println("You haven't done this yet peko?!");
            System.out.println("    " + todoList[markIndex-1]);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number Bakatare!");
        }
        System.out.println(lineBreak);
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
