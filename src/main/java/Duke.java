import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list =  new ArrayList<>();

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        String input = scanner.nextLine();

        while(true) {
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                printList(list);
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }
}
