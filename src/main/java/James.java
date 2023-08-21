import java.util.Scanner;
import java.util.ArrayList;

public class James {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line + "\n" + "Hello! I'm James\n" + "What can I do for you?\n" + line);

        // User Input
        Scanner in = new Scanner(System.in);

        ArrayList<String> items = new ArrayList<String>();

        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(i + 1 + ". " + items.get(i));
                }
            } else {
                System.out.println(line + "\n" + "added: " + input + "\n" + line);
                items.add(input);
            }
            input = in.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);

    }
}
