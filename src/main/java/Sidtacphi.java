import java.util.Objects;
import java.util.Scanner;

/**
 * Sidtacphi is the main class for the Sidtacphi bot.
 * 
 * @author Jeffry Lum
 * @author Damith C. Rajapakse
 * @author Sean Leong
 * @author Liow Jia Cheng
 * @author Yu Lexuan
 */
public class Sidtacphi {
    private static String[] task_list = new String[100];
    private static boolean[] completed_task = new boolean[100];
    private static int list_ptr = 0;
    
    /**
     * The main method for the Sidtacphi class.
     * 
     * @param args
     */
    public static void main(String[] args) {
        startBot();
    }

    /**
     * This method starts the Sidtacphi bot.
     */
    private static void startBot() {
        String logo = " _______  ___   ______   _______  _______  _______  _______  __   __  ___  \n"
                + "|       ||   | |      | |       ||   _   ||       ||       ||  | |  ||   | \n"
                + "|  _____||   | |  _    ||_     _||  |_|  ||       ||    _  ||  |_|  ||   | \n"
                + "| |_____ |   | | | |   |  |   |  |       ||       ||   |_| ||       ||   | \n"
                + "|_____  ||   | | |_|   |  |   |  |       ||      _||    ___||       ||   | \n"
                + " _____| ||   | |       |  |   |  |   _   ||     |_ |   |    |   _   ||   | \n"
                + "|_______||___| |______|   |___|  |__| |__||_______||___|    |__| |__||___| \n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        readInputs();
    }

    /**
     * This method stops the Sidtacphi bot.
     */
    private static void stopBot() {
        System.out.println("\nSidtacphi: Goodbye non-Euclidean life form.");
        System.out.println("\n____________________________________________________________");
    }

    /**
     * The method for reading inputs for the bot.
     */
    private static void readInputs() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nYou: ");
        String input = "";
        while (true) { 
            if (!scan.hasNextLine()) {
                continue;
            }
            input = scan.nextLine();
            if (Objects.equals(input, "bye")) {
                stopBot();
                break;
            } else if (Objects.equals(input, "list")) {
                System.out.println("Sidtacphi:");
                showTaskList();
                System.out.print("\nYou: ");
                continue;
            }

            addTask(input);
            System.out.print("\nSidtacphi: Added \"" + input + "\".\n");
            System.out.print("\nYou: ");
        }
        scan.close();
    }

    /**
     * Adds the input to the task_list kept track of by the bot.
     * 
     * @param input Input to add to the task_list kept by the bot.
     */
    private static void addTask(String input) {
        task_list[list_ptr] = input;
        list_ptr++;

        // in case of > 100 messages, the first few will be purged
        if (list_ptr >= task_list.length) {
            list_ptr -= task_list.length;
        }
    }

    /**
     * Prints the task list.
     */
    private static void showTaskList() {
        for (int i = 0; i < list_ptr; i++) {
            System.out.println("" + (i + 1) + ". " + task_list[i]);
        }
    }
}
