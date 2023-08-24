import java.util.Scanner;

public class Duke {
    private static final String SEPARATION_LINE  = "____________________________________________________________";
    private static final String INDENTATION = "    ";
    private static final String GREETING_TEXT = "Hello, I'm Tasket\n    What can I do for you?";
    private static final String GOODBYE_TEXT = "Bye. Hope to see you again soon!";

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

    public static void echoInputText(String string) {
        System.out.println(string);
        showSeparationLine();
    }

    public static void main(String[] args) {
        String prompt = "";
        Scanner sc = new Scanner(System.in);

        showGreetingText();

        while (!prompt.equals("bye")) {
            prompt = sc.next();
            showSeparationLine();

            echoInputText(prompt);
        }

        sc.close();
        showGoodbyeText();
    }
}
