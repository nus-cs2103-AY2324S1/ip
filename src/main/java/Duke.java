import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // Constants
        Scanner scanner = new Scanner(System.in);
        String line = "______________________________\n";
        String logo = " ____             _        \n"
                + "|  _ \\           | | \n"
                + "| |_| |_____,_ ,_| |,___  _  ___\n"
                + "|  _ /|  _  | ` _|  __\\ \\| |/ _  \\\n"
                + "| |_| | |_| | |  | |__/ /| |  ___/  \n"
                + "|____/ \\__,_|_|  |_|\\__/ |_|\\___/\n";
        String[] list = new String[100];

        // Intro
        System.out.println(line
                + "Hi Barbie! Hi Ken!\n"
                + "\nI'm\n"
                + logo
                + "\nWhat can I do for you?\n" +
                line);

        // Getting and reading input
        System.out.println("[you]: ");
        String input = scanner.nextLine();
        int itemNumber = 1;

        while (!Objects.equals(input, "bye")) {
            // Repeat input
            System.out.println("\t" + line
                    + "\t [barbie]: "
                    + input
                    + "\n\t"
                    + line);

            // Add to list
            list[itemNumber] = input;

            // Reset
            System.out.println("[you]: ");
            input = scanner.nextLine();
        }

        // Exit
        System.out.println(line + "Bye Barbie! Bye Ken!\n" + line);


    }
}
