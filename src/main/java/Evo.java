import java.util.Objects;
import java.util.Scanner;


public class Evo {
    public static void main(String[] args) {
        String logo = " _____\n"
                + "|  ___| \n"
                + "| |___ __    __  _____ \n"
                + "|  ___|\\ \\  / / |     | \n"
                + "| |___  \\ \\/ /  |     |  \n"
                + "|_____|  \\__/   |_____|  \n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Evo.\n" + "What can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // Print out welcome message once the user using Evo
        System.out.println(welcomeMsg);
        // Initialise a scanner to receive text input from user
        Scanner scanner = new Scanner(System.in);

        // taskIndex use to keep track the index number of the empty slot in taskList
        int taskIndex = 0;
        // Create a string array of size 100 to store the tasks added by the user
        String[] taskList = new String[100];
        while (true) {
            // Assign the text to this string variable called instruction
            String instruction = scanner.nextLine();

            // If the text entered is bye, then print out the bye message and exit the loop
            if (Objects.equals(instruction, "bye")) {
                System.out.println(byeMsg);
                break;
            }

            /**
             *  If the text entered is list, then print out the tasks before added by the user.
             *  Else, assign the text stored in the variable called instruction to the taskList with index
             *  taskIndex. Increase the taskIndex by one and print out the added text message.
             */
            if (Objects.equals(instruction, "list")) {
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println(i + 1 + ". " + taskList[i]);
                }
            } else {
                taskList[taskIndex] = instruction;
                taskIndex++;
                System.out.println("added: " + instruction);
            }
        }
    }
}
