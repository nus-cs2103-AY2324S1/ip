import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Botty!\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";
        System.out.println(greetings);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(farewell);
                sc.close();
                break;
            } else {
                System.out.println(command + "\n");
            }
        }
    }
}
