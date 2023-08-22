import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Derek";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        ArrayList<String> statements = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String line = in.nextLine();
            switch (line) {
                case "list":
                    for (int i = 0; i < statements.size(); i++) {
                        System.out.println((i + 1) + ". " + statements.get(i));
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    run = false;
                    break;
                default:
                    statements.add(line);
                    System.out.println("added: " + line);
            }
        }
    }
}
