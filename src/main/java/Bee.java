import java.util.Objects;
import java.util.Scanner;
public class Bee {
    public static void main(String[] args) {
        String logo = " \n" +
                "__________               \n" +
                "\\______   \\ ____   ____  \n" +
                " |    |  _// __ \\_/ __ \\ \n" +
                " |    |   \\  ___/\\  ___/ \n" +
                " |______  /\\___  >\\___  >\n" +
                "        \\/     \\/     \\/ \n";
        System.out.println("Hello! I'm " + logo + ".\n");

        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int listIndex = 0;
        System.out.println("~Bzzzz~ What may I assist you with today? ~Bzzzz~\n");

        boolean isRunning = true;
        while (isRunning) {
            String userInput = scanner.nextLine();

            // If user enters "bye", ends the program and says goodbye to the user.
            if (userInput.equals("bye")) {
                System.out.println("By-ee!. ~Bzzzz~");
                break;
            } else if (userInput.equals("list")) {
                for (int i=0; i < list.length; i++) {
                    if (list[i] != null) {
                        int order = i + 1;
                        System.out.println(order + ". " + list[i]);
                    } else {
                        break;
                    }
                }
                continue;
            }

            // Else, echo back the user input and add to list
            list[listIndex] = userInput;
            listIndex++;
            System.out.println("Added: " + userInput + " ~Bzzzz~\n");
        }
    }
}
