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
                for (Task task : taskList) {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(task); //will print output in form 1. read book via tell dont ask
                }
                continue;
            }
            Task newTask = new Task(input, id);
            id++;
            taskList.add(newTask);
            System.out.println("added: " + input);
        }
    }
}
