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
        String[] taskArray = new String[100];
        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (true) {
            String input = sc.nextLine();
            printHoriLine();

            if (input.equals("list")) {
                if (count == 0) {
                    System.out.println("HEYYYYYYYY! There's nothing to show here!");
                }
                else {
                    for (int i = 0; i < count; i++) {
                        System.out.println(i + 1 + "." + taskArray[i]);
                    }
                }
            }
            else if (!input.equals("bye")) {
                System.out.println("WAAAAAAOOOOO! Added: " + input);
                taskArray[count] = input;
                count++;
            }
            else {
                System.out.println("WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!");
                printHoriLine();
                break;
            }
            printHoriLine();
        }
    }
}
