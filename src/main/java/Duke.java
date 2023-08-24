import java.util.ArrayList;
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

        ArrayList list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {

            String botOutput = "";

            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    botOutput = botOutput + i + "." + " " + list.get(i-1) + "\n    ";
                }
            } else {
                list.add(userInput);
                botOutput = botOutput + "added: " + userInput;
            }

            System.out.println(botMessage(botOutput));
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
