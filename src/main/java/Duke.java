import java.util.Scanner;
public class Duke {
    static String[] storage;
    static int storagePointer;
    public static void main(String[] args) {
        // Initialise the data storage
        Duke.storage = new String[100];
        Duke.storagePointer = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String chatbotName = "notDuke";
        String introMessage = "Hello! I'm " + chatbotName + "\n"
                        + "What can I do for you?\n";
        String exitMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(introMessage);
        Duke.echo();
        System.out.println(exitMessage);
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 1; i <= Duke.storagePointer; i ++) {
                    String item = Duke.storage[i - 1];
                    System.out.printf("%d. %s\n", i, item);
                }
                userInput = scanner.nextLine();
                continue;
            }
            Duke.storage[Duke.storagePointer] = userInput;
            Duke.storagePointer ++;
            System.out.println("added: " + userInput);
            userInput = scanner.nextLine();
        }
    }
}
