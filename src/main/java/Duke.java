import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> tasks = new ArrayList<>();

        String greeting = "Hello! I'm Toothless. \n" +
                "What can I do for you today? \n" +
                "---------------------------------";
        String farewell = "Goodbye. Hope to see you soon!\n" +
                "---------------------------------";
        String name = "Toothless";

        System.out.println(greeting);

        String bye = "bye";
        String taskList = "list";

        while (true) {

            String nextInput = scanner.nextLine().trim();

            if (nextInput.equals(bye)) {
                break;
            } else if (nextInput.equals(taskList)) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ": " + tasks.get(i));
                }
            } else {
                tasks.add(nextInput);
                System.out.println("Task " + nextInput + " has been added");
            }
            System.out.println("\n---------------------------------");
        }

        System.out.println(farewell);
    }
}
