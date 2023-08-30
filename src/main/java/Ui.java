import java.util.Scanner;

public class Ui {
    public static final String INDENTATION = "    ";

    private Scanner sc = new Scanner(System.in);


    public String getInput() {
        return sc.nextLine();
    }

    private static String formatOutput(String output) {
        String horizontalLine = "____________________________________________________________";
        return INDENTATION + horizontalLine + "\n " +
                INDENTATION + output + '\n' + INDENTATION + horizontalLine + '\n';
    }

    public void printGreeting() {
        System.out.println(formatOutput("Hello! I'm Nano\n" + INDENTATION + " What can I do for you?"));
    }

    public void printBye() {
        System.out.println(formatOutput("Bye. Hope to see you again soon!"));
    }

    public void printInvalidCommandError() {
        System.out.println(formatOutput("I don't understand what you're saying."));
    }


    public void printOutput(String rawOutput) {
        System.out.println(formatOutput(rawOutput));
    }
}
