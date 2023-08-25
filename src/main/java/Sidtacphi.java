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
    private static String[] list = new String[100];
    private static int list_ptr = 0;
    
    public static void main(String[] args) {
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

        System.out.println("\nSidtacphi: Bye. Hope to see you again soon!");
        System.out.println("\n____________________________________________________________");

    }

    static void readInputs() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nYou: ");
        String input = "";
        while (true) { 
            if (!scan.hasNextLine()) {
                continue;
            }
            input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Sidtacphi:");
                for (int i = 0; i < list_ptr; i++) {
                    System.out.println("" + (i + 1) + ". " + list[i]);
                }
                System.out.print("\nYou: ");
                continue;
            }
            list[list_ptr] = input;
            list_ptr++;
            System.out.print("\nSidtacphi: " + input + "\n");
            System.out.print("\nYou: ");
        }
        scan.close();
    }
}
