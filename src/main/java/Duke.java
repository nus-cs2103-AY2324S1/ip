import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SEPARATION_LINE  = "____________________________________________________________";
    private static final String INDENTATION = "    ";
    private static final String GREETING_TEXT = "Hello, I'm Tasket\n    What can I do for you?";
    private static final String GOODBYE_TEXT = "Bye. Hope to see you again soon!";

    private static String prompt = "";
    private static ArrayList<String> list = new ArrayList<>();

    public static void showSeparationLine() {
        System.out.println(INDENTATION + SEPARATION_LINE);
    }

    public static void showGreetingText() {
        showSeparationLine();
        System.out.println(INDENTATION + GREETING_TEXT);
        showSeparationLine();
    }

    public static void showGoodbyeText() {
        System.out.println(INDENTATION + GOODBYE_TEXT);
        showSeparationLine();
    }

    public static void showAddedText(String text) {
        System.out.println(INDENTATION + "added: " + text);
        showSeparationLine();
    }

    public static void showLists() {
        for (int i = 1; i <= list.size(); i++) {
            System.out.printf("%s%d. %s\n", INDENTATION, i, list.get(i - 1));
        }
        showSeparationLine();
    }

    public static void echoInputText(String text) {
        System.out.println(text);
        showSeparationLine();
    }

    public static void parseText(String text) {
        switch (text) {
        case "list":
            showLists();
            break;

        case "bye":
            showGoodbyeText();
            break;

        default:
            list.add(text);
            showAddedText(text);
            break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        showGreetingText();

        while (!prompt.equals("bye")) {
            System.out.println();
            prompt = sc.nextLine();
            showSeparationLine();

            parseText(prompt);
        }

        sc.close();
    }
}
