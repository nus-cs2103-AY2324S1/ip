import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String botName = "Changoose";
        String startMessage = String.format("Hello! I'm %s%nWhat can I do for you?", botName);
        String endMessage = "Bye! Hope to see you again soon!";

        echo(startMessage);
        startParse();
        echo(endMessage);
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
        String divider = String.format("%80s", "").replace(" ", "-");
        System.out.println(divider);
        System.out.println(input);
        System.out.println(divider);
    }
}
