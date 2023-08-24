import java.util.Scanner;
public class Remy {
    public static void main(String[] args) {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Remy.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String exitMessage = " Bye. Hope to see you again soon!\n" +
        "____________________________________________________________\n";

        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(exitMessage);
                break;
            }
            System.out.println(input + "\n" + "--------");

        }
    }
}
