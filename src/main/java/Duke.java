import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String botName = "Changoose";
        String startMessage = String.format("Hello! I'm %s%nWhat can I do for you?", botName);
        String endMessage = "Bye! Hope to see you again soon!";

        String divider = String.format("%80s", "").replace(" ", "-");

        System.out.println(divider);
        System.out.println(startMessage);
        System.out.println(divider);
        startParse();
        System.out.println(endMessage);
        System.out.println(divider);
    }

    private static void startParse() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandInput = scanner.nextLine();
            switch (commandInput) {
                case "bye":
                    return;
                default:
                    echo(commandInput);
            }

        }
    }
    private static void echo(String input) {
        System.out.println(input);
    }
}
