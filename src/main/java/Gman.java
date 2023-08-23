import java.util.Scanner;
public class Gman {
    public static String userInput;
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello! I'm Gman! \nWhat can I do for you?");
        userInput = myScanner.nextLine();
        String exitWord = "bye";

        while (!userInput.equals(exitWord)) {
            System.out.println("    " + userInput);
            userInput = myScanner.nextLine();
        }

        System.out.println("    Bye. Hope to see you again soon!");
    }
}
