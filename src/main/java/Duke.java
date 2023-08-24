import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "Raman");*/

        String greeting = "Hello, I'm Capt. Price! \n    What can I do for you today, Sergeant?";
        String horizontalBar = "-------------------------------------------------";
        String exitGreeting = "Over and out. See you next mission!";

        System.out.println(botMessage(greeting));

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(botMessage(userInput));
            userInput = scanner.nextLine();
        }

        System.out.println(botMessage(exitGreeting));

    }

    public static String botMessage(String message) {
        String space = "    ";
        String horizontalBar = "-------------------------------------------------";
        return space + horizontalBar + "\n" + space + message + "\n" + space + horizontalBar;
    }
}
