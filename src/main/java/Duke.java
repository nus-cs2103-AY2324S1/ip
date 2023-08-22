import java.util.Scanner;

public class Duke {

    static String chatbotName = "Gobble Gobble";
    static String lineSeparator = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(Duke.lineSeparator + "\n" + "Hello! I'm " + Duke.chatbotName + "\n" +
                "What can I do for you?" + "\n" + Duke.lineSeparator);

        Scanner scanner = new Scanner(System.in);

        // Data
        String[] answerList = new String[100];
        int counter = 0;

        while (true) {
            String userInput = scanner.nextLine().trim(); // trim() removes leading and trailing spaces

            // Different responses for different inputs
            if (userInput.equals("bye")) {
                // Display goodbye message
                System.out.println(Duke.lineSeparator + "\n" + "Bye. Hope to see you again soon!" + "\n" + Duke.lineSeparator);
                break;
            } else if (userInput.equals("list")) {
                // Display Ordered list
                System.out.println(Duke.lineSeparator);
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + answerList[i]);
                }
                System.out.println(Duke.lineSeparator);
            } else {
                // Default input

                // Save user input
                answerList[counter] = userInput;

                // Display user input has been added
                System.out.println(Duke.lineSeparator + "\n" + "added: " + userInput + "\n" + Duke.lineSeparator);
                counter++;
            }
        }

        scanner.close();
    }
}
