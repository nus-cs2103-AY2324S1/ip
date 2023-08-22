import java.util.Objects;
import java.util.Scanner;

public class Phi {
    public static void main(String[] args) {

        greeting();

        // creating scanner object for user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }

        goodbye();
    }

    public static void greeting() {
        String logo = "___ _  _ ___ \n"
                + "| _ \\ || |_ _|\n"
                + "|  _/ __ || | \n"
                + "|_| |_||_|___| \n";
        String greetingMsg = "Hellos! I'm PHI (Programmed Human Interaction) \n What can I do for you?";

        System.out.println(logo + greetingMsg);
    }

    public static void goodbye() {
        String exitMsg = "okk THANKS FOR COMING BYE!";
        System.out.println(exitMsg);
    }
}
