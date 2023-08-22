import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Phi {
    public static void main(String[] args) {
        // scanner object for user input
        Scanner sc = new Scanner(System.in);
        // arrayList to store input text given
        ArrayList<String> inputTextList = new ArrayList<>();

        greeting();
        
        String input = sc.nextLine();
        while (!Objects.equals(input, "bye")) {
            if (Objects.equals(input, "list")) {
                for (String s : inputTextList) {
                    System.out.println(s);
                }
            } else {
                inputTextList.add((inputTextList.size() + 1) + ": " + input);
                System.out.println("Added: " + input);
            }
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