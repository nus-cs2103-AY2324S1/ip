import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.intro();
        duke.run();
        duke.exit();
    }

    public void lines() {
        System.out.println("_".repeat(50));
    }
    public void intro() {
        lines();
        System.out.println("Hello! I'm sillyBot\nWhat can I do for you?");
        lines();
    }

    public void exit() {
        lines();
        System.out.println("Bye. Hope to see you again soon!");
        lines();
    }

    public void run() {
        String input = sc.nextLine();
        ArrayList<String> store = new ArrayList<>();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                lines();
                for (int i = 0; i < store.size(); i++) {
                    System.out.println(i + 1 + ". " + store.get(i));
                }
                lines();
                input = sc.nextLine();
                continue;
            }
            lines();
            store.add(input);
            System.out.println("added: " + input);
            lines();
            input = sc.nextLine();
        }
    }
}
