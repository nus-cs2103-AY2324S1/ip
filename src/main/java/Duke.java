import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static void main(String[] args) {
        String endMessage = "Bye. Hope to see you again soon!";
        String endVal = "bye";
        String horizontalLine = "-".repeat(22);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Tom!" + "\n"  +  "What can I do for you?" + "\n");
        System.out.println(horizontalLine);

        while (true) {
            Scanner userInputObject = new Scanner(System.in);
            String userInput = userInputObject.nextLine();
            if (userInput.equals(endVal)) {
                System.out.println("\n" + endMessage);
                break;
            } else {
                System.out.println(horizontalLine);
                System.out.println(userInput);
                System.out.println(horizontalLine);

            }
        }
    }
}
