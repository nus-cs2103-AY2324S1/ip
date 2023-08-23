import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        TaskList store = new TaskList();
        String name = "CodeBuddy";
        String horizontal = "----------------------------------------";
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(horizontal);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(horizontal);

        while(true) {
            String input = inputScanner.nextLine();
            System.out.println(horizontal);
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon !");
                System.out.println(horizontal);
                break;
            } else if(input.equals("list")) {
                store.listTasks();
            } else if(input.startsWith("mark")) {
                String[] splitInput = input.split(" ");
                int index = Integer.parseInt(splitInput[1]);
                store.markTask(index);
            } else if(input.startsWith("unmark")) {
                String[] splitInput = input.split(" ");
                int index = Integer.parseInt(splitInput[1]);
                store.unmarkTask(index);
            } else {
                store.addTask(input);
            }
            System.out.println(horizontal);
        }
    }
}
