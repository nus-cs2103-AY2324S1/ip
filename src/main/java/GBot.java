import java.util.ArrayList;
import java.util.Scanner;

public class GBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello I'm GBot\n");
        System.out.println("What can I do for you today?\n");

        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String message = scanner.nextLine();
            if (message.equals("list")) {
                int len = list.size();
                for (int i = 0; i < len; i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            } else {
                list.add(message);
                System.out.println("added: " + message);
            }
        }
    }
}

