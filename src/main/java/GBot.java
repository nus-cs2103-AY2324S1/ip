import java.util.Scanner;

public class GBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello I'm GBot\n");
        System.out.println("What can I do for you today?\n");
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            }
            System.out.println(message);
        }
    }
}

