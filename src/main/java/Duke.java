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
                String removedPrefix = input.substring("mark".length() + 1);
                int index = Integer.parseInt(removedPrefix);
                store.markTask(index);
            } else if(input.startsWith("unmark")) {
                String removedPrefix = input.substring("unmark".length() + 1);
                int index = Integer.parseInt(removedPrefix);
                store.unmarkTask(index);
            } else if(input.startsWith("todo")) {
                String removedPrefix = input.substring("todo".length() + 1);
              store.addTodo(removedPrefix);
            } else if(input.startsWith("deadline")) {
                String removedPrefix = input.substring("deadline".length() + 1);
                store.addDeadline(removedPrefix);
            } else if (input.startsWith("event")) {
                String removedPrefix = input.substring("event".length() + 1);
                store.addEvent(removedPrefix);
            } else {
                store.addTask(input);
            }
            System.out.println(horizontal);
        }
    }
}
