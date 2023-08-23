import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Blob\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userCommand = scanner.nextLine();

            if ("bye".equals(userCommand)) {
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(userCommand);
            System.out.println("____________________________________________________________");
        }

        System.out.println(" Bye. Come talk to Blob again soon!");
        System.out.println("____________________________________________________________");
    }
}
