import java.util.ArrayList;
import java.util.Scanner;

public class Pogo {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Pogo\nWhat can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        String quitMessage = "Bye. Hope to see you again soon!";

        while (true) {
            String input = scanner.nextLine();
            System.out.println(horizontalLine);

            switch (input) {
                case "bye":
                    System.out.println(quitMessage);
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    break;
                default:
                    tasks.add(new Task(input));
                    System.out.println("added: " + input);
                    break;
            }
            System.out.println(horizontalLine);

        }
    }
}
