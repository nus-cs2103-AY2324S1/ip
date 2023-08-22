import java.util.Scanner;

public class Duke {

    static String chatbotName = "Gobble Gobble";
    static String lineSeparator = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(Duke.lineSeparator + "\n" + "Hello! I'm " + Duke.chatbotName + "\n" +
                "What can I do for you?" + "\n" + Duke.lineSeparator);

        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String userInput = scanner.nextLine().trim(); // trim() removes leading and trailing spaces

            if (userInput.equals("bye")) {
                System.out.println(Duke.lineSeparator + "\n" + "Bye. Hope to see you again soon!" + "\n" + Duke.lineSeparator);
                break;
            } else {
                System.out.println(Duke.lineSeparator + "\n" + userInput + "\n" + Duke.lineSeparator);
            }
        }

        scanner.close();
    }
}
