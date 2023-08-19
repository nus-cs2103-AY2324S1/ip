import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String divider = "_____________________________________\n";
        String opening = "Hello: I'm TY's slave\nWhat can I do for you?\n";
        String closing = "Bye. Hope to see you again soon!";
        System.out.println(divider + opening + divider);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(divider + closing + "\n" + divider);
                break;
            } else {
                System.out.println(divider + input + "\n" + divider);
            }
        }
    }
}
