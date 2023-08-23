import java.util.Scanner;

public class Monday {
    public static void main(String[] args) {
        String chatBotName = "Monday";
        Scanner scanner = new Scanner(System.in);

        printSeparator();
        greet(chatBotName);
        printSeparator();
        while (true) {
            String userInput = scanner.nextLine();
            printSeparator();
            if (userInput.equals("bye")) {
                exit();
                break;
            } else if (userInput.equals("list")) {
                Storage.displayList();
            } else if (userInput.contains("mark")) {
                String[] details = userInput.split(" ");
                int index = Integer.parseInt(details[1]);
                if (details[0].equals("mark")) {
                    Storage.mark(index);
                } else {
                    Storage.unMark(index);
                }
            } else {
                Storage.add(userInput);
                echo(userInput);
            }
            printSeparator();
        }
    }

    private static void echo(String word) {
        System.out.println("added: " + word);
    }

    private static void greet(String chatBotName) {
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
