import java.util.Scanner;

public class Duke {
    private final String botName;
    private final Scanner scanner;

    public Duke(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Duke changooseBot = new Duke("Changoose");
        String startMessage = String.format("Hello! I'm %s%nWhat can I do for you?", changooseBot.getBotName());
        String endMessage = "Bye! Hope to see you again soon!";

        changooseBot.echo(startMessage);
        changooseBot.startParse();
        changooseBot.echo(endMessage);
    }

    private void startParse() {
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
    private void echo(String input) {
        int indentLength = 4;
        String divider = indentLeft(String.format("%80s", "").replace(" ", "-"), indentLength);
        System.out.println(divider);
        System.out.println(indentLeft(input, indentLength));
        System.out.println(divider);
    }

    private static String indentLeft(String input, int indentLength) {
        String indent = String.format("%" + indentLength + "s", "");
        String[] lines = input.split(System.lineSeparator()); // handle Unix and Windows new lines.
        for (int i = 0; i < lines.length; i++) {
            lines[i] = indent + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }
    private String getBotName() {
        return this.botName;
    }
}
