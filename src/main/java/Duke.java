import java.util.Scanner;

public class Duke {
    public static void printHorizontalLine() {
        System.out.println("\t------------------------------------------------------------------------------------");
    }
    public static void printBotMessage(String msg) {
        printHorizontalLine();
        System.out.println("\t" + msg);
        printHorizontalLine();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "\n" +
                "\t_________ .__            __    __                \n" +
                "\t\\_   ___ \\|  |__ _____ _/  |__/  |_  ___________ \n" +
                "\t/    \\  \\/|  |  \\\\__  \\\\   __\\   __\\/ __ \\_  __ \\\n" +
                "\t\\     \\___|   Y  \\/ __ \\|  |  |  | \\  ___/|  | \\/\n" +
                "\t \\______  /___|  (____  /__|  |__|  \\___  >__|   \n" +
                "\t        \\/     \\/     \\/                \\/       \n";
        printBotMessage("Hello! I'm" + logo + "\n\t \uD83E\uDD9C What can I do for you?");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printBotMessage("Bye. Hope to see you again soon! \uD83D\uDD19 \uD83D\uDD1B \uD83D\uDD1D");
                break;
            }
            printBotMessage(input);
        }

    }
}
