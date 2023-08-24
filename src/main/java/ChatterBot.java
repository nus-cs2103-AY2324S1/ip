import java.util.ArrayList;
import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args) {

        String logo = "ChatterBot";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you?");

        ArrayList<String> list = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();

            if (userMessage.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userMessage.toLowerCase().equals("list")) {
                for (String str : list) {
                    System.out.println(list.indexOf(str) + 1 + ". " + str);
                }
            } else {
                list.add(userMessage);
                System.out.println("added " + userMessage);
            }
        }
    }
}
