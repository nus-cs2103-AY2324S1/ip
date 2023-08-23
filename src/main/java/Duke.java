import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[10];
        String horizontal_line =  "____________________________________________________________";
        int count  = 0;
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
            } else if ("list".equals(userCommand)) {
                System.out.println(horizontal_line);
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(horizontal_line);
            } else {
                System.out.println(horizontal_line);
                System.out.println("added: " + userCommand);
                System.out.println(horizontal_line);
                tasks[count] = userCommand;
                count++;
            }
        }
        System.out.println(horizontal_line);
        System.out.println(" Bye. Come talk to Blob again soon!");
        System.out.println(horizontal_line);
    }
}
