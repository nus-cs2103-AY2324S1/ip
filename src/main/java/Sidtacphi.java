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
        while (!scan.hasNext("bye")) {
            input = scan.nextLine();
            System.out.print("\nSidtacphi: " + input + "\n");
            System.out.print("\nYou: ");
        }
        scan.close();
    }
}
