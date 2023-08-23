import java.util.Objects;
import java.util.Scanner;

/**
 * Evo is a Personal Assistant Chatbot that helps a person to keep track of various things.
 * Tasks are represented by the nested static class Task.
 */
public class Evo {

    /**
     * The main method of Evo program.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Display Evo logo and welcome message
        String logo = " _____\n"
                + "|  ___| \n"
                + "| |___ __    __  _____ \n"
                + "|  ___|\\ \\  / / |     | \n"
                + "| |___  \\ \\/ /  |     |  \n"
                + "|_____|  \\__/   |_____|  \n";
        System.out.println("Hello from\n" + logo);

        // Initialise welcome and goodbye messages
        String welcomeMsg = "Hello! I'm Evo.\n" + "What can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // Print out welcome message once the user using Evo
        System.out.println(welcomeMsg);
        // Initialise a scanner to receive text input from user
        Scanner scanner = new Scanner(System.in);

        // taskIndex use to keep track the index number of the first empty slot in taskList
        int taskIndex = 0;
        // An array to store the Task objects
        Task[] taskList = new Task[100];

        while (true) {
            // Assign the text to this string variable called instruction
            String instruction = scanner.nextLine();

            // If the text entered is bye, then print out the bye message and exit the loop
            if (Objects.equals(instruction, "bye")) {
                System.out.println(byeMsg);
                break;
            }

            /**
             * If the text entered is list, then print out the status and description of tasks added before by the user.
             * Then exit the current while loop and move to the next iteration.
             */
            if (Objects.equals(instruction, "list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskIndex; i++) {
                    if (i == taskIndex - 1) {
                        System.out.println(i + 1 + "." + "[" + taskList[i].getStatusIcon() + "] "
                                + taskList[i].description + "\n");
                    } else {
                        System.out.println(i + 1 + "." + "[" + taskList[i].getStatusIcon() + "] "
                                + taskList[i].description);
                    }
                }
                continue;
            }

            /**
             * Split the text entered by user and store it in a string array called actionType. Then, determine
             * which action to be taken, whether is to mark a task done, mark a task not done or add a new task.
             */
            String[] actionType = instruction.split(" ");
            if (Objects.equals(actionType[0], "mark")) {
                // Mark a task as done
                int taskNumberInList = Integer.parseInt(actionType[1]) - 1;
                taskList[taskNumberInList].markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + taskList[taskNumberInList].getStatusIcon() + "]" + " "
                        + taskList[taskNumberInList].description + "\n");
            } else if (Objects.equals(actionType[0], "unmark")) {
                // Mark a task as not done
                int taskNumberInList = Integer.parseInt(actionType[1]) - 1;
                taskList[taskNumberInList].markAsNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [" + taskList[taskNumberInList].getStatusIcon() + "]" + " "
                        + taskList[taskNumberInList].description + "\n");
            } else {
                // Add a new task
                taskList[taskIndex] = new Task(instruction);
                taskIndex++;

                System.out.println("added: " + instruction + "\n");
            }
        }
    }
}
