import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        boolean hasEndedChat = false;

        System.out.println("Hello! I'm Max!");
        System.out.println("What can I do for you?");

        while (!hasEndedChat) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    hasEndedChat = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    break;
                default:
                    list.add(input);
                    System.out.println("added: " + input);
                    break;
            }
        }
    }
}
