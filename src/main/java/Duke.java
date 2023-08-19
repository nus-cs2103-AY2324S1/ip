import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<>();
        String divider = "_____________________________________\n";
        String opening = "Hello: I'm TY's slave\nWhat can I do for you?\n";
        String closing = "Bye. Hope to see you again soon!";
        System.out.println(divider + opening + divider);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(divider + closing + "\n" + divider);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + lst.get(i));
                }
            } else {
                lst.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
