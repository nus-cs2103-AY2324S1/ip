import java.util.Scanner;
public class Duke {
    private String message;

    public Duke() {
        message = "";
    }
    public void greet() {
        this.indent();
        System.out.println("\t \t \t \t Greetings, I am Vision. How may I assist you today? \uD83E\uDD16\uD83D\uDD0D");
        this.indent();
    }

    public void indent() {
        System.out.println("\t \t \t \t_______________________________________________________________");
    }

    public void exit() {
        this.indent();
        System.out.println("\t \t \t \t I shall now take my leave. If you require further assistance, \n" +
                "\t \t \t \t do not hesitate to seek my guidance once more. Farewell. \uD83E\uDD16\uD83D\uDC4B");
        this.indent();
    }

    public void interact() {
        Scanner input = new Scanner(System.in);

        while (true) {
            this.updateMessage(input.nextLine());
            if (this.message.equals("bye")) {
                break;
            }
            this.indent();
            System.out.println("\t \t \t \t \t" + this.message);
            this.indent();
        }
    }

    public void updateMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Duke) {
            Duke d = (Duke) obj;
            return this.message == d.message;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Create a scanner object to read input
        Duke bot = new Duke();

        bot.greet();
        bot.interact();
        bot.exit();

        return;
    }
}
