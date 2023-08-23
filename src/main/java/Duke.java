import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[10];
        String[] completed = new String[10];
        Arrays.fill(completed, " ");
        String horizontal_line = "____________________________________________________________";
        int count = 0;
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
                scanner.close();
                break;
            } else if ("list".equals(userCommand)) {
                System.out.println(horizontal_line);
                System.out.println("here are the tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + "[" + completed[i] + "] " + tasks[i]);
                }
                System.out.println(horizontal_line);
            } else if (userCommand.startsWith("mark ")) {
                String[] parts = userCommand.split(" ");
                if (parts.length == 2) {
                    int num = Integer.parseInt(parts[1]);
                    System.out.println("Nice! I've marked this task as done:");
                    completed[num] = "X";
                    System.out.println("[" + completed[num] + "] " + tasks[num]);
                }
            } else if (userCommand.startsWith("unmark ")) {
                String[] parts = userCommand.split(" ");
                if (parts.length == 2) {
                    int num = Integer.parseInt(parts[1]);
                    System.out.println("Okay. I see you have not done this task yet");
                    completed[num] = " ";
                    System.out.println("[" + completed[num] + "] " + tasks[num]);
                }
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
