import java.util.Scanner;

public class Juke {
    static void printLine() {
        System.out.println("_______________________________________________________");
    }
    public static void main(String[] args) {

        //Introduce itself to the user
        System.out.println("Hello! I'm Juke!");
        System.out.println("What can I do for you?");
        printLine();
        while(true) {
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                //Say goodbye
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                return;
            }
            else {
                System.out.println(input);
                printLine();
            }
        }
    }
}
