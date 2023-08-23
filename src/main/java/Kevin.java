import java.util.Scanner;

public class Kevin {
    public static final String HORIZONTAL_LINE = "_".repeat(70);
    public static final String BOT_NAME = "\t \n" +
            "\t" + " _   __ _____ _   _ _____ _   _ \n" +
            "\t" +"| | / /|  ___| | | |_   _| \\ | |\n" +
            "\t" +"| |/ / | |__ | | | | | | |  \\| |\n" +
            "\t" +"|    \\ |  __|| | | | | | | . ` |\n" +
            "\t" +"| |\\  \\| |___\\ \\_/ /_| |_| |\\  |\n" +
            "\t" +"\\_| \\_/\\____/ \\___/ \\___/\\_| \\_/\n";

    public static void wrapInHorizontalLines(String str) {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\t" + str);
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public static void echo(String command) {
        wrapInHorizontalLines(command);
    }

    public static void hello() {
        String welcomeMessage = "Hello! I'm" + BOT_NAME + "\n\t" + "What can I do for you?";
        wrapInHorizontalLines(welcomeMessage);
    }

    public static void bye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        wrapInHorizontalLines(goodbyeMessage);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hello();
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            }
            echo(command);
        }
        bye();
    }
}



