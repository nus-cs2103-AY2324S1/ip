import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<String> tasks = new LinkedList<>();

        System.out.println("____________________________________________________________\n" +
                "Hello! I'm CarrotCake\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        String input = scanner.nextLine();

        while (!input.toLowerCase().equals("bye")) {
            System.out.println("____________________________________________________________\n");
            if (input.toLowerCase().equals("list")) {
                //Print tasks
                int i = 1;
                for (String task : tasks) {
                    System.out.print(Integer.toString(i) + ". " + task + "\n");
                    i++;
                }
                System.out.println("____________________________________________________________\n");
                input = scanner.nextLine();
                continue;
            }

            tasks.add(input);
            System.out.println("added: " + input +
                    "\n____________________________________________________________\n");

            input = scanner.nextLine();
        }

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
