import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        printHoriLine();
        System.out.println("WEEWOOWEEWOO WELCOME! I'm Siren");
        System.out.println("What can I do for you?");
        printHoriLine();
        takeInput();
    }

    public static void printHoriLine() {
        System.out.println("____________________________________________________________");
    }

    public static void takeInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            printHoriLine();
            if (!input.equals("bye")) {
                System.out.println(input);
                printHoriLine();
            }
            else {
                System.out.println("WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!");
                printHoriLine();
                break;
            }
        }
    }
}
