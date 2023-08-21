import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int currIndex = 0;

        String greetings = "Hello! I'm Botty!\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        // Only exit when user types the command bye
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(farewell);
                sc.close();
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < currIndex; i++) {
                    System.out.println(i + 1 + ". " + tasks[i] + "\n");
                }
            } else {
                tasks[currIndex] = command;
                currIndex++;
                System.out.println("added: " + command + "\n");
            }
        }
    }
}
