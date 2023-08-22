import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static int id = 1;
    private static ArrayList<Task> taskList = new ArrayList<>(); //universal task list in memory
    public static void main(String[] args) {
        String logo = " __          _        \n"
                + "| |     _   _| | _____ \n"
                + "| |    | | | | / / _ \\\n"
                + "| |___ | |_| |   <  __/\n"
                + "|____/ \\__,__|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println( "What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        String input;

        while(true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                int id = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : taskList) {
                    System.out.println(String.valueOf(id) + ". " + task);
                    id++;
                }
                continue;
            }

            Task newTask = new Task(input);
            taskList.add(newTask);
            System.out.println("added: " + input);
        }
        scanner.close();
    }
}
